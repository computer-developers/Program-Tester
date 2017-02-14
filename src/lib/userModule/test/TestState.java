/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import lib.runDetails.IntIODetail;
import lib.runDetails.IntInput;
import lib.userModule.result.IntLiveResult;
import lib.userModule.result.IntResult;
import lib.userModule.TimeNotAvailableException;

/**
 *
 * @author Neel Patel
 */
final class TestState implements IntIODetail, IntLiveResult{

     private long programId=-1, index=-1, time=-1;
     private List<String> input=new ArrayList<>(), output=new ArrayList<>();
     private String message="";
     private int code=0;
     private boolean isExecuted;
     private final AtomicBoolean isFinal=new AtomicBoolean(false);
     private List<Runnable> func=new ArrayList<>();
     
     private void run(){
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
     
     synchronized void update(IntIODetail io){
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
