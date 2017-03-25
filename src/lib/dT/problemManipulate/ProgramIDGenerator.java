/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/
package lib.dT.problemManipulate;
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
      * it use current system date and time to generate programID.
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
