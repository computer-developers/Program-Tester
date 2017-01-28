package lib.dataTools.problemAdder;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author admin
 */
public final class ProgramIDGenerator{
     private ProgramIDGenerator(){}
     private static long last=0;
     
     /**
      * generate unique programID.
      * it use current system date & time to generate programID.
      * @return long programID.
      */
     public static long newProgramID(){
          LocalDateTime dt=LocalDateTime.now();
          long pid=Long.parseLong(dt.format(DateTimeFormatter.ofPattern("yyMMddhhmmss")));
          if(pid!=last){
               last=pid;
               return pid;
          }
          else{
               try{
                    Thread.sleep(1000);
               }catch(InterruptedException e){}
               return newProgramID();
          }
     }     
}
