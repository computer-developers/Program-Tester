/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule;

import lib.userModule.result.IntLiveResultSet;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lib.dT.problemManipulate.IntProgramDetail;
import lib.dT.problemManipulate.ProgramDetails;
import lib.logger.MyLogger;
import lib.ui.IntUI;
import lib.userModule.result.IntProgramState;
import lib.userModule.result.ProgramStateAdapter;
import lib.userModule.test.Test;

/**
 *
 * @author Neel Patel
 */
public class SingleUserFlow implements IntUserFlow{
     private IntUI ui;
     private MyLogger logger;
     private List<IntProgramState> ps;
     private class ProblemState extends ProgramStateAdapter{
          ProblemState(IntProgramDetail pd){
               super(pd);
          }
          
          @Override
          protected synchronized void setState(int s){
               super.setState(s);
          }
     }
     
     private List<IntProgramState> getPrograms(){
          List<IntProgramState> ps=ProgramDetails.readProgramDetail().stream()
                  .map(i->new ProblemState(i)).collect(Collectors.toList());
          return Collections.unmodifiableList(ps);
     }
     
     public SingleUserFlow(){
          ps=getPrograms();
     }
     
     @Override
     public synchronized void  register(IntUI ui) {
          this.ui=ui;
     }

     @Override
     public IntLiveResultSet execute(long pid,String cmd) {
          try {
               return new Test(pid,cmd).start();
          } catch (IOException ex) {
               System.out.println("Error");
          }
          return null;
     }
     
     public synchronized void setLogger(MyLogger logger){
          this.logger=logger;
     }

     @Override
     public synchronized List<IntProgramState> getAllProgramDetail() {
          return ps;
     }
}
