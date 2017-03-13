package net.logSer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static programtester.config.Configuration.getDefaultLogDir;

/**
 *
 * @author Neel Patel
 */
public class LogHandler {
     private LogHandler(){}
     private static PrintStream ps;
     private static final String dtf="yyMMddhhmmss";
     private static boolean flag=false;
     private static Thread t;
     private static final int timeInter=600000;
     
     public static synchronized boolean log(String log){
          try{
               if(ps==null)
                    return false;
               ps.println(log);
               ps.flush();
               return true;
          }catch(Exception ex){
               return false;
          }
     }
     
     public static synchronized void makeFile() throws FileNotFoundException{
          Path p=getDefaultLogDir();
          p=p.resolve(LocalDateTime.now().format(DateTimeFormatter.ofPattern(dtf))+".txt");
          FileOutputStream out=new FileOutputStream(p.toFile());
          ps=new PrintStream(out);
     }
     
     public static void start(){
          flag=true;
          if(t!=null)
               return;
          t=new Thread(LogHandler::run,"Logger Thread");
          t.start();
     }
     
     public static void run(){
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
     
     public static void stop(){
          if(t==null)
               return;
          flag=false;
          ps.close();
          ps=null;
          t=null;
     }
}
