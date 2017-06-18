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
import java.util.*;
import java.io.*;

/**
 * this class is used to store the data of single run of any program.
 * object of this class can easily be written to the file or passed in stream using
   {@code ObjectOutputStream} and read using {@code ObjectInputStream}.
 * version of class refers the serialVersionUID.
 * if the serialVersionUID differs at ObjectInputStram from ObjectOutputStream 
   then the Object can not be read by ObjectInputStream.
 * @version 1
 * @author Neel Patel
 */
class TestCase implements IntTestCase, Serializable{
     private static final long serialVersionUID = 1L;
     static long getVersion(){
          return serialVersionUID;
     }
     long time=-1;
     long programId=-1;
     long index=-1;
     List<String> input=new ArrayList<>(),output=new ArrayList<>();
     
     TestCase(List<String> input){
          this.input.addAll(input);
     }
     
     TestCase(List<String> input,List<String> output){
          this(input);
          if(output!=null)
               this.output.addAll(output);
          else
               this.output=null;
     }
     
     TestCase(List<String> input,List<String> output, long time){
          this(input,output);
          this.time=time;
     }
    
     TestCase(List<String> input,List<String> output, long time, long programId, long index){
          this(input,output,time);
          this.programId=programId;
          this.index=index;
     }
     
     /**
      * returns the programId, for which the {@code input} and {@code output} related.
      * the programId is a unique identification of program type for which the 
        the list of string return by the method {@link getAllInput} can be used 
        as input.
      * the programId refers to the problem definition related to the input and 
        corresponding output.
      * @return positive long programId, negative value if not applicable
      */
     @Override
     public long programID(){
          return programId;
     }
     
     /**
      * return index of the {@code input} and corresponding {@code output}
        if applicable.
      * index is unique serial number to identify the test {@code input}
        and {@code output} for the program corresponding to the {@code programId}
      * @return positive long index, negative value if not applicable
      */
     @Override
     public long index(){
          return index;
     }
     
     /**
      * returns all input as copy of the list of string.
      * it returns null if not applicable.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string if applicable, null otherwise
      */
     @Override
     synchronized public List<String> getAllOutput() {
          if(output==null)
               return null;
          List<String> l=new ArrayList<String>();
          l.addAll(output);
          return l;
     }

     /**
      * returns all input as copy of the list of string.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string.
      */
     @Override
     synchronized public List<String> getAllInput() {
          List<String> l=new ArrayList<String>();
          l.addAll(input);
          return l;
     }
     
     /**
      * it returns time taken by the process to generate {@code output} from
        {@code input}.
      * @return positive long time, negative value if not applicable.
      */
     @Override
     public long getTime() {
          return time;
     }
     
     @Override
     public boolean equals(Object o){
          if(!(o instanceof TestCase))
               return false;
          TestCase i=(TestCase)o;
          if(this.programID()!=i.programID())
               return false;
          if(this.index()!=i.index())
               return false;
          return true;
     }
     
     @Override
     public int hashCode(){
          return (int)this.programId;
     }
}
