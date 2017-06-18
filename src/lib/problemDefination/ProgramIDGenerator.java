/*
 * The MIT License
 *
 * Copyright 2017 Neel Patel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lib.problemDefination;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

/**
 * this class contain method to generate unique programId.
 * this is strongly recommended to use the programId provided the method of this
   class only. 
 * @author Neel Patel
 */
public final class ProgramIDGenerator{
     private ProgramIDGenerator(Callable<IntProgramDetail> func){}
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
