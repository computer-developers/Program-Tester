package lib.ui.gui;

import java.util.List;
import lib.ui.IntUI;
import lib.userModule.IntUserFlow;
import lib.userModule.result.IntLiveResult;
import lib.userModule.result.IntLiveResultSet;
import lib.userModule.result.IntProgramState;
import static programtester.config.Configuration.TEST_PASS;

/**
 *
 * @author Parth Doshi
 */
public class UserGUI implements IntUI{

     private IntUserFlow uf;
     private List<? extends IntProgramState> ps;
     
          
          
          
     public UserGUI(IntUserFlow uf){
          this.uf=uf;
          this.uf.register(this);
          ps=uf.getAllProgramDetail();
     }
  public void display(long pid)
  {
       IntProgramState ips = null;
       for (IntProgramState k : ps)
            if(k.getProgramID() == pid)
            {
                 ips = k;
                 break;
            }
    QuestionPage qqp=new QuestionPage(ips,this);
          
    
    }
     public void run(long pid){
        FileChooser ffc=new FileChooser();
        String retCmd = ffc.returnCmd();
          IntLiveResultSet ilr = uf.execute(pid, retCmd);
          ResultPage rrp=new ResultPage(ilr);
          
     }
     
     
     @Override
     public void showMessage(String message) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public void start() {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public void close() {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public String Prompt(String message) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
     
}
/*
          int i=obj.getState();
          switch (i){
               case TEST_PASS://code
                    break;
          }
*/