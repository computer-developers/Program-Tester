package net.logSer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static programtester.config.Configuration.getDefaultLogDir;

/**
 *
 * @author Neel Patel
 */
public class LogHandler {
     private LogHandler(){}
     private static PrintStream ps;
     private static IntRemoteLog back=null;
     private static final String dtf="yyMMddhhmmss";
     private static boolean flag=false;
     private static Thread t;
     private static Executor es=new ThreadPoolExecutor(0,5, 10, TimeUnit.MINUTES
             ,new ArrayBlockingQueue<Runnable>(100));
     private static final int timeInter=600000;
     
     /**
      * this method log the String log to the specific file.
      * this method also call the log method of backup logger if it is set.
      * this method will remove the backup logger if the {@code RemoteException} occur
        will calling the log method of the backup logger.
      * @param log log string
      * @return true if logged successfully, false otherwise.
      */
     public static synchronized boolean log(String log){
          try{
               if(ps==null)
                    return false;
               ps.println(log);
               System.out.println("log :-"+log);
               ps.flush();
               if(back!=null){
                    es.execute(()->{
                         try{
                              back.log(log);
                         }catch(RemoteException ex){
                              System.out.println("backup logger fail .."+ex);
                              back=null;
                         }
                    });
               }
               System.out.println("log return");
               return true;
          }catch(Exception ex){
               System.err.println("logging fail");
               return false;
          }
     }
     
     /**
      * this method create a new log file with the name based on time.
      * this method also update PrintStream {@code ps} with the newly created
        file.
      * this method will close PrintStream object previously pointed by {@code ps}.
      * @throws FileNotFoundException if occur while creating file.
      */
     private static synchronized void makeFile() throws FileNotFoundException{
          Path p=getDefaultLogDir();
          p=p.resolve(LocalDateTime.now().format(DateTimeFormatter.ofPattern(dtf))+".txt");
          FileOutputStream out=new FileOutputStream(p.toFile());
          ps=new PrintStream(out);
     }
     
     /**
      * start the new thread "Logger Thread" which call the
        makeFile method after {@code timeInter}.
      * if the Logger Thread is created already then this method will not create
        another thread.
      */
     public static void start(){
          flag=true;
          if(t!=null && t.isAlive())
               return;
          t=new Thread(LogHandler::run,"Logger Thread");
          t.start();
     }
     
     /**
      * this method used by start to create new thread.
      */
     private static void run(){
          for(;flag;){
               try {
                    PrintStream p=ps;
                    makeFile();
                    p.flush();
                    p.close();
                    sleep(timeInter);
               } catch (Exception ex) {
               }
          }
     }
     
     /**
      * stop the logger thread and close the log file.
      * call to the method log after calling this method will return false
        unless the call to the method start performed.
      */
     public static void stop(){
          if(t==null)
               return;
          flag=false;
          ps.close();
          ps=null;
          t=null;
     }
     
     /**
      * set the backupLoger if not set previously.
      * @param url URI of Remote Backup Logger
      * @return true if the logger set successfully, false otherwise
      */
     public static boolean setBackupLog(String url){
          if(back!=null)
               return false;
          try {
               IntRemoteLog ls=(IntRemoteLog)Naming.lookup(url);
               if(ls.aya()){
                    back=ls;
                    System.out.println("backup logger :- "+back.toUrl());
                    return true;
               }
               else{
                    assert true:"remote Log Server say 'not alive'";
                    return false;
               }
          } catch (Exception ex) {
               return false;
          }
     }
     
     public static List<String> getLogs(){
          try {
               Path dir=getDefaultLogDir();
               List<String> li=new ArrayList();
               Files.list(dir).filter(i->!Files.isDirectory(i))
                       .forEach(p->{
                            try {
                                 li.addAll(Files.readAllLines(p));
                            } catch (Exception ex) {
                            }
                       });
               return li;
          } catch (Exception ex) {
               return null;
          }
     }
}
