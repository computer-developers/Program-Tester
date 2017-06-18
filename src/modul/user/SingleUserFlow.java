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
package modul.user;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import lib.db.ObjectDataBase;
import lib.problemDefination.*;
import ui.IntUI;
import lib.run.test.*;
import lib.run.test.util.*;

/**
 *
 * @author Neel Patel
 */
public class SingleUserFlow implements IntUserFlow{
     private IntUI ui; //Refrence to the UI
     private List<ProblemState> ps; //Reference to the live Problem definations.
     private IntObjectBase ob=new ObjectDataBase(Paths.get("data.db"));
     //private Thread t;
     //this is executer service used to execute the parallel task.
     private ExecutorService es=Executors.newCachedThreadPool();

     @Override
     public int getCredit() {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public void refresh() {
     }

     /**
      * this is simple extension of class {@code ProgramStateAdapter},
        which implements the {@code IntProblemState}.
      * this class Override the default implementation of {@code setState}
        method.
      */
     private class ProblemState extends ProgramStateAdapter{
          /**
           * constructor
           * @param pd object of type {@code IntProgramDetail} which will be
             used to initialize this object.
           */
          ProblemState(IntProgramDetail pd){
               super(pd);
          }
          
          /**
           * this is synchronized implementation of setState method.
           * this method will simple call the implementation of super class.
           * @param s 
           */
          @Override
          public synchronized void setState(int s){
               super.setState(s);
          }
     }
     
     /**
      * this method is used to update the {@code ProblemState}.
      * this method get the final results from {@code t} and determine if
        the state of the corresponding problem should be updated or not.
      * if the state of the corresponding problem is 1, then it remain as it is.
      * if the state of the corresponding problem is 0 and all the test cases passed
        by the program corresponding to the {@code t} then the state of the
        problem will be updated with 1.
      * this method can be executed in parallel to support live platforms.
      * @param t object of Test for with update operation perform.
      */
     private void update(Test t){
          t.join();
          IntResultSet rs=t.getIntResultSet();
          ProblemState x=ps.stream().filter(i->i.getProgramID()==t.getProgramID())
                  .findAny().get();
          int code=rs.getAllResult().stream().mapToInt(r->r.getMessageCode())
                  .min().orElse(0);
          if(code>x.getState()){
               x.setState(code);
          }
          /*if(logger!=null)
               logger.log("DateTime = "+LocalDateTime.now()
               ,"PID = "+x.getProgramID()
               ,"State = "+x.getState());
          */
          System.gc();
     }
     
     /**
      * this method read and return all the program from default program directory.
      * this method internally call {@code ProgramDetails.readProgramDetail()}
        to get problem definition.
      * this process create the new objects of type ProblemState using the
        objects of type {@code IntProgramState}.
      * @return unmodifiable list of IntProgramStates.
      */
     private List<? extends IntProgramState> getPrograms(){
          //System.out.println("suf getPro...");
          List<ProblemState> psl=ob.getAllProgramDetails().stream()
                  //.peek(i->System.out.println("Pid :- "+i.getProgramID()))
                  .map(i->new ProblemState(i)).collect(Collectors.toList());
          psl=Collections.unmodifiableList(psl);
          return psl;
     }
     
     /**
      * constructor.
      * initialize the object with all problem definition read from the default
        program directory.
      */
     public SingleUserFlow(){
          ps=(List<ProblemState>)getPrograms();
     }
     
     /**
      * this method register the user interface.
      * calling this method will replace the previous registered user interface
        with {@code ui}.
      * @param ui object of IntUI.
      */
     @Override
     public synchronized void register(IntUI ui) {
          this.ui=ui;
          ui.start();
     }

     /**
      * this method executes the command {@code cmd} with proper inputs
        corresponding to the Program ID specified by {@code pid}.
      * this method internally execute the command with all available inputs
        corresponds to the Program Id available in the default source directory.
      * this method returns live result set immediately and start the processing
        in parallel.
      * this will method update the resultSet in parallel, after returning.
      * @param pid program ID corresponds to the executable command
      * @param cmd executable command
      * @return object of type IntLiveResultSet.
      */
     @Override
     public IntLiveResultSet execute(long pid,String cmd) {
          try {
               Test t=new Test(pid,ob,cmd);
               IntLiveResultSet rt=t.start();
               es.submit(()->update(t));
               return rt;
          } catch (IOException ex) {
               ui.showMessage("file not found !!!");
          }
          return null;
     }
     
     /*
      * set logger.
      * @param logger object of logger. 
      
     @Override
     public synchronized void setLogger(MyLogger logger){
          this.logger=logger;
     }*/

     /**
      * this method return list of {@code IntProgramState} corresponds to the
        all Program Details.
      * the list return by this method is unmodifiable list.
      * @return unmodifiable list of Program states
      */
     @Override
     public synchronized List<? extends IntProgramState> getAllProgramDetail() {
          return ps;
     }
     
     /**
      * {@inheritDoc }
      */
     @Override
     public void finalize(){
          es.shutdownNow();
     }
     
     /**
      * {@inheritDoc }
      */
     @Override
     public void close(){
          es.shutdownNow();
        //  logger.close();
          ui.close();
     }
}
