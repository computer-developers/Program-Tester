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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import lib.problemDefination.*;
import lib.run.test.util.*;

/**
 *
 * @author Neel Patel
 */
final class TestState implements IntTestCase, IntLiveResult{

     private long programId=-1, index=-1, time=-1;
     private List<String> input=new ArrayList<>(), output=new ArrayList<>();
     private String message="";
     private int code=0;
     private boolean isExecuted;
     private final AtomicBoolean isFinal=new AtomicBoolean(false);
     private List<Runnable> func=new ArrayList<>();
     
     private synchronized void run(){
          func.parallelStream().forEach(r->r.run());
     }
     
     TestState(List<String> input, long programId, long index){
          this.input.clear();
          this.input.addAll(input);
          this.programId=programId;
          this.index=index;
          setState("waiting",0);
     }
     
     TestState(IntInput in){
          this.input.clear();
          this.input.addAll(in.getAllInput());
          this.programId=in.programID();
          this.index=in.index();
          setState("waiting",0);
     }
     
     synchronized void update(IntTestCase io){
          if(isFinal())
               return;
          this.input.clear();
          this.input.addAll(io.getAllInput());
          this.output.clear();
          this.output.addAll(io.getAllOutput());
          this.programId=io.programID();
          this.index=io.index();
          this.time=io.getTime();
          isExecuted=true;
     }
     
     synchronized void setState(String message,int code){
          if(isFinal())
               return;
          this.message=message;
          this.code=code;
          run();
     }
     
     synchronized boolean isExecuted(){
          return isExecuted;
     }
     
     @Override
     public synchronized List<String> getAllOutput() {
          if(!isExecuted)
               throw new UnsupportedOperationException("Not available yet.");
          List<String> no=new ArrayList<>();
          no.addAll(output);
          return no;
     }

     @Override
     public long getTime() {
          return time;
     }

     @Override
     public long index() {
          return index;
     }

     @Override
     public List<String> getAllInput() {
          List<String> nl=new ArrayList<>();
          nl.addAll(input);
          return nl;
     }

     @Override
     public long programID() {
          return programId;
     }

     @Override
     public long getRunTime() throws TimeNotAvailableException {
          if(!isExecuted)
               throw new TimeNotAvailableException();
          return getTime();
     }

     @Override
     public String getMessage() {
          return message;
     }

     @Override
     public int getMessageCode() {
          return code;
     }

     @Override
     public synchronized void addRunnable(Runnable r) {
          func.add(r);
     }
     
     @Override
     public IntResult toIntResult(){
          synchronized(isFinal){
               for(;!isFinal();){    
                    try {
                         isFinal.wait();
                    } catch (InterruptedException ex){}
               }
               return this;
          }
     } 

     @Override
     public void makeFinal() {
          synchronized(isFinal){
               isFinal.set(true);
               isFinal.notify();
          }
     }
     
     public boolean isFinal(){
          synchronized(isFinal){
               return isFinal.get();
          }
     }
}
