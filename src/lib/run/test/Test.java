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
package lib.run.test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lib.problemDefination.util.*;
import lib.problemDefination.*;
import lib.run.execution.RunTest;
import lib.run.test.util.*;
import static programTester.config.Configuration.TEST_FAIL;
import static programTester.config.Configuration.TEST_FILE_ERROR;
import static programTester.config.Configuration.TEST_PASS;
import static programTester.config.Configuration.TEST_PRESENT_ERROR;
import static programTester.config.Configuration.TEST_TIME_ERROR;
import static programTester.config.Configuration.minMem;

/**
 *
 * @author Neel Patel
 */
public class Test {
     //private final Path dir;  //directory of test-cases
     private final long pid;  //programId
     private final String cmd;  //executable command
     private Thread t;  //Tester thread
     private List<TestState> ts;  //contain test-case results details
     private IntLiveResultSet lrs;  //refer to the object return by execute method
     private boolean flag=false;  //shows the state of thread t
     private IntObjectSource ob;
     boolean isParallel=true;
     /**
      * this method compare the output of {@code us} with
        corresponding output in list {@code orig}.
      * this method finds the equivalent original output from list {@code orig}
        and compare it's output to the output of {@code us}.
      * @param us object of TestState, output of which is going to be compared.
      * @throws IllegalArgumentException if {@code us} contain invalid
        information like negative {@code programID} or {@code index} etc..
      */
     private void comp(TestState us){
          try{
               if(!us.isExecuted())
                    return;
               if(us.getTime()<0){
                    us.setState("Time Limit Exceeded",TEST_TIME_ERROR);
                    return;
               }
               IntTestCase ori=ob.getTestCase(pid, us.index());
               if(ori==null)
                    us.setState("TestCase Not Found !!", TEST_FILE_ERROR);
               //System.out.println("enter check");
               if(ListManipulator.compare(us.getAllOutput(),ori.getAllOutput(),
                              StringComparators.getExactmatch())){
                    //System.out.println("perfect check");
                    us.setState("Pass",TEST_PASS);
               }
               else if(ListManipulator.compare(
                       ListManipulator.removeNull(us.getAllOutput()),
                       ListManipulator.removeNull(ori.getAllOutput()),
                              StringComparators.getIgnoreWhiteSpace())){
                    //System.out.println("persentation check");
                    us.setState("Presentation Error",TEST_PRESENT_ERROR);
               }
               else {
                    us.setState("Fail",TEST_FAIL);
               }
          }catch(Exception ex){
               throw new IllegalArgumentException();
          }
     }
     
     /**
      * read the test-cases from {@code dir}.
      * this method make a new list of test-cases found & make the reference
        {@code ts} to refer the list.
      * if the number of test-cases found is less then count then the IOException
        will be thrown.
      * @throws IOException if occurred while reading object or no object found 
      */
     private synchronized void reader()throws IOException{
          List<TestState> ts=ob.getTestCases(pid).stream()
                              .filter(i->i!=null)
                              .filter(i->i.programID()==pid)
                              .distinct()
                              //.peek(i->System.out.println("index :- "+i.index()))
                              .map(i->new TestState(i))
                              .collect(Collectors.toList());
          if(ts.isEmpty())
               throw new IOException();
          this.ts=ts;
     }
     
     /**
      * iterate over the list {@code ts} & execute & compare the test-cases.
      * this method execute the test-case & then compare it in sequence.
      * this method update boolean {@code flag} with true then continue.
      * if any instance the {@code flag} is false then this method terminate the
        the execution of test-cases & return. 
      */
     private void run(){
          flag=true;
          for(TestState i:ts){
               if(!flag) //check if the thread should be terminated.
                    break;
               for(;Runtime.getRuntime().freeMemory()<minMem&&
                       minMem*2<Runtime.getRuntime().maxMemory();){
                    try{
                         System.gc();
                         Thread.sleep(1000);
                    }catch(InterruptedException e){}
               }
               RunTest rt=new RunTest(i,cmd);
               i.setState("Executing", 0);
               try{
                   i.update(rt.getIODetail());
                   comp(i);
               //    i.setState("Error", TEST_FAIL);
               }catch(Exception e){
                   i.setState("Error", TEST_FAIL);
               }
               i.makeFinal();
               
          }
          //System.gc();
     }
     
     /**
      * iterate over the list {@code ts} & execute & compare the test-cases.
      * if the parameter {@code isParrallel} is {@code false} then this method
        works same as method {@code void run()}.
      * if the parameter {@code isParrallel} is {@code true} then this method
        execute the test-case & then compare it in parallel using concurrent
        framework.
      * this method update boolean {@code flag} with true then continue.
      * if any instance the {@code flag} is false then this method terminate the
        the execution of test-cases & return. 
      * @param isParallel boolean variable, refers to a concurrency of execution.
      */
     private void run(boolean isParallel){
          if(!isParallel){
               run();
               return;
          }
          flag=true;
          ts.parallelStream().peek(i->{
               if(!flag) //check if the thread should be terminated.
                    return;
               for(;Runtime.getRuntime().freeMemory()<minMem&&
                       minMem*2<Runtime.getRuntime().maxMemory();){
                    //System.out.println("jbahbajadk");
                    try{
                         System.gc();
                         Thread.sleep(1000);
                    }catch(InterruptedException e){}
               }
               RunTest rt=new RunTest(i,cmd);
               i.setState("Executing", 0);
               try{
                   i.update(rt.getIODetail());
                   comp(i);
               //    i.setState("Error", TEST_FAIL);
               }catch(Exception e){
                   i.setState("Error", TEST_FAIL);
               }
               i.makeFinal();
               System.gc();
          }).count();
     }
     
     /**
      * creates the object using {@code pid}, {@code dir} and {@code cmd}. 
      * @param pid programID.
      * @param ob path of directory.
      * @param cmd Executable command.
      */
     public Test(long pid,IntObjectBase ob,String cmd){
          this.ob=ob;
          this.pid=pid;
          this.cmd=cmd;
     }

     /**
      * start the execution in parallel and return object of
        {@code IntLiveResultSet}.
      * this method creates new thread {@code Tester Thread} if not created.
      * Tester Thread process the test-cases in parallel.
      * if the Tester Thread is already created, this method returns the 
        previous object of {@code IntLiveResultSet}.
      * @return object of {@code IntLiveResultSet}.
      * @throws IOException 
      */
     public synchronized IntLiveResultSet start() throws IOException{
          if(t!=null)
               return lrs;
          reader();
          try{
              isParallel=Boolean.parseBoolean(System.getProperty("parallel_execution", "true"));
          }catch(Exception e){}
          t=new Thread(()->run(isParallel),"Tester Thread");
          t.start();
          lrs=new LiveResultSetAdapter(ts);
          return lrs;
     }
     
     /**
      * this method wait for {@code Tester Thread} to terminate.
      */
     public void join(){
          for(;t!=null&&t.isAlive();)
          try {
               t.join();
          } catch (InterruptedException ex) {}
     }
     
     /**
      * this method return object of IntResultSet.
      * this method will wait until the result is ready.
      * @return object of IntResultSet, null if execution process not initialized
        properly.
      */
     public IntResultSet getIntResultSet(){
          if(lrs==null)
               return null;
          return new ResultSetAdapter(lrs.getAllResult());
     }
     
     /**
      * this method change the flag to false to terminate the
        Testing process.
      * although the currently running Testing process will be executed as it is.
      * next tasks will not be initiated.
      */
     public void stop(){
          flag=false;
     }
     
     /**
      * @return programID
      */
     public long getProgramID(){
          return pid;
     }
     
     @Override
     public void finalize() throws Throwable{
          try {
               stop();
          } finally {
               super.finalize();
          }
     }
}

     /*
      * read the test-cases from {@code dir}.
      * this method make a new list of test-cases found & make the reference
        {@code ts} to refer the list.
      * if the number of test-cases found is less then count then the IOException
        will be thrown.
      * @param count number of test-cases.
      * @throws IOException if occurred while reading object or number of
        objects found is less then {@code count}  
      */
     /*private synchronized void reader(int count)throws IOException{
          List<TestState> ts=IntStream.rangeClosed(1,30)
                              .mapToObj(i->ob.getTestCase(pid, i))
                              .filter(i->i!=null)
                              .filter(i->i.programID()==pid)
                              .distinct()
                              //.peek(i->System.out.println("index :- "+i.index()))
                              .map(i->new TestState(i))
                              .collect(Collectors.toList());
          if(ts.size()<count)
               throw new IOException();
          this.ts=ts;
     }*/
     
     /*
      * 
      * @param index index of test-case
      * @return Object of IntIODetail, null if not found
      */
     /*private IntIODetail read(long index){
          //System.out.println("read .... start");
          IntIODetail d;
          try{
               d=IOManager.getIODetail(dir, pid, index, true);
               return d;
          }catch(IOException e){}
          try{
               d=IOManager.getIODetail(dir, pid, index, false);
               return d;
          }catch(IOException e){}
          //System.out.println("read .... end");
          return null;
     }
     */
     