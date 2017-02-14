/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule.result;

import java.util.ArrayList;
import java.util.List;
import lib.dT.problemManipulate.IntProgramDetail;

/**
 *
 * @author Neel Patel
 */
public class ProgramStateAdapter implements IntProgramState{

     private int state=0;
     private IntProgramDetail pd;
     private List<Runnable> func=new ArrayList<>();
     
     private synchronized void run(){
          func.parallelStream().forEach(r->r.run());
     }
     
     protected synchronized void setState(int s){
          run();
          state=s;
     }
     
     public ProgramStateAdapter(IntProgramDetail pd){
          this.pd=pd;
     }
     
     @Override
     public synchronized void addRunnable(Runnable r) {
          func.add(r);
     }

     @Override
     public int getState() {
          return state;
     }

     @Override
     public String getTitle() {
          return pd.getTitle();
     }

     @Override
     public List<String> getDescription() {
          return pd.getDescription();
     }

     @Override
     public String getInput() {
          return pd.getInput();
     }

     @Override
     public String getOutput() {
          return pd.getOutput();
     }

     @Override
     public List<String> getSampleInput() {
          return pd.getSampleInput();
     }

     @Override
     public List<String> getSampleOutput() {
          return pd.getSampleOutput();
     }

     @Override
     public long getProgramID() {
          return pd.getProgramID();
     }

     @Override
     public int getCredit() {
          return pd.getCredit();
     }
     
}
