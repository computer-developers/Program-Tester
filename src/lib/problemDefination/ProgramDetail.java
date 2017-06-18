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

import java.io.Serializable;
import java.util.*;
import static lib.problemDefination.ProgramIDGenerator.newProgramID;

/**
 *
 * @author Rushabh
 */
public class ProgramDetail implements IntProgramDetail, Serializable {
     private static final long serialVersionUID = 2L;
     static long getVersion(){
          return serialVersionUID;
     }
     int credit;
     long programId=-1;
     String title;
     List<String> description, sampleInput, sampleOutput, input, output;
     
     ProgramDetail(String title,List<String> description, List<String> input,List<String> output
             ,List<String> sampleInput, List<String> sampleOutput,int credit){
          this.programId=newProgramID();
          this.title=title;
          this.description=Collections.unmodifiableList(description);
          this.input=Collections.unmodifiableList(input);
          this.output=Collections.unmodifiableList(output);
          this.sampleInput=Collections.unmodifiableList(sampleInput);
          this.sampleOutput=Collections.unmodifiableList(sampleOutput);
          this.credit=credit;
     }
     
     @Override
     public long getProgramID(){
          return programId;
     }
     
     /**
      * 
      * @return 
      */
     @Override
     public String getTitle(){
          return title;
     }
     
     /**
      * returns all input as copy of the list of string.
      * it returns null if not applicable.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string if applicable, null otherwise
      */
     @Override
     synchronized public List<String> getDescription() {
          return description;
     }

     /**
      * returns all input as copy of the list of string.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string.
      */
     @Override
     public List<String> getInput() {
          return input;
     }
     
     /**
      * it returns time taken by the process to generate {@code output} from
        {@code input}.
      * @return positive long time, negative value if not applicable.
      */
     @Override
     public List<String> getOutput() {
          return output;
     }
     
     @Override
     synchronized public List<String> getSampleInput() {
          return sampleInput;
     }
     
     @Override
     synchronized public List<String> getSampleOutput() {
          return sampleOutput;
     }

     @Override
     public int getCredit() {
          return credit;
     }
     
     @Override
     public boolean equals(Object o){
          if(o instanceof ProgramDetail){
               ProgramDetail p=(ProgramDetail)o;
               if(p.getProgramID()==this.getProgramID())
                    return true;
          }
          return false;
     }

     @Override
     public int hashCode() {
          int hash = 7;
          hash = 71 * hash + this.credit;
          hash = 71 * hash + (int) (this.programId ^ (this.programId >>> 32));
          return hash;
     }
}
