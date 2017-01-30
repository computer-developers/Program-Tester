package lib.dT.problemAdder;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * this class contain method to generate unique programId.
 * this is strongly recommended to use the programId provided the method of this
   class only. 
 * @author Neel Patel
 */
public final class ProgramIDGenerator{
     private ProgramIDGenerator(){}
     private static long last=0;
     
     /**
      * generate unique programID.
      * it use current system date & time to generate programID.
      * make sure that your system date is correct.
      * wrong system date can lead to generation of duplicate programID.
      * @return long programID.
      */
     public static synchronized long newProgramID(){
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
