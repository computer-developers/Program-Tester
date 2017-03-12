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
     static PrintStream ps;
     private static final String dtf="yyMMddhhmmss";
     private static boolean flag=true;
     private static Thread t;
     private static final int timeInter=10000;
     
     public static synchronized boolean log(String log){
          return false;
     }
     
     public static synchronized void makeFile() throws FileNotFoundException{
          Path p=getDefaultLogDir();
          p=p.resolve(LocalDateTime.now().format(DateTimeFormatter.ofPattern(dtf))+".txt");
          FileOutputStream out=new FileOutputStream(p.toFile());
          ps=new PrintStream(out);
     }
     
     static{
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
               } catch (InterruptedException | FileNotFoundException ex) {
               }
          }
     }
}
