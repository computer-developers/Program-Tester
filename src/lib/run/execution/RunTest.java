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
package lib.run.execution;
import lib.problemDefination.IntInput;
import java.util.*;
import lib.problemDefination.IntTestCase;
import lib.problemDefination.ProgramDetails;
/**
 * this class provide facility to execute specified command with given input.
 * @author Neel Patel
 */
public class RunTest {

     /**
      * default runTime.
      * every process must completed before the runTime.
      * if the process is not terminated in less then runTime then it will
        forcefully terminated.
      */
     public static final long runTime = 5000; //default runtime.
     ProgramExecuter pe; //to execute subprocess.
     Thread t; //to execute subprocess in parallel
     List<String> out; //to store output
     long time=-1; //time taken by suprocess
     IntInput in;
     
     /**
      * create object with specified input and command
      * @param input used to create IntTestCase object by executing command.
      * @param cmd command to create subprocess.
      */
     public RunTest(IntInput input,String cmd){
          this(input.getAllInput(),cmd);
          in=input;
     }
     
     RunTest(List<String> input,String cmd){
          pe=new ProgramExecuter(input,cmd);
          in=ProgramDetails.getInput(input);
     }
     
     /**
      * start new subprocess.
      * start new subprocess using command specified in constructor if subprocess is already not running.
      * create new thread with execute the subprocess if currently not executing.
      * start subprocess if the previous execution is completed.
      */
     synchronized public void run(){
          if(t!=null&&t.isAlive())
               return;
          t=new Thread(this::start); //create thread to execute the subprocess.
          t.start();
     }
     
     /**
      * @return true if subprocess is alive, false otherwise. 
      */
     public boolean isAlive(){
          if(t!=null)
               return t.isAlive();
          return false;
     }
     
     /**
      * start new subprocess.
      * wait until the process is terminated.
      */
     private void start(){
          try{
               out=pe.execute(runTime);
          }catch(Exception e){
               System.out.println("Exception :- RunTest.start() "+e);
               //e.printStackTrace();
          }
     }
     
     /**
      * return the result of previous execution of subprocess.
      * if the subprocess is not executed before then execute the subprocess and wait until the process terminate.
      * if the subprocess is executing currently then it will wait to finish it.
      * note:- if subprocess is already executed ones this method create new subprocess.
      * @return object of IntTestCase of last execution of process.
      */
     synchronized public IntTestCase getIODetail(){
          try{
          //call methode run to execute the subprocess if subprocess is not executing.
          if(t==null) 
               run(); 
          try{ //wait for subprocess to terminate.
               t.join();
          }catch(InterruptedException e){}
          //create and return object of IntTestCase.
          return ProgramDetails.getTestCase(in,out,pe.getRunTime());
          }catch(Exception e){
              return null;
          }
     }
     
}
