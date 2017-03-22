package lib.ui.gui;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import lib.ui.IntUI;
import lib.userModule.IntUserFlow;
import lib.userModule.result.IntLiveResultSet;
import lib.userModule.result.IntProgramState;
import javax.swing.JOptionPane; 
import javax.swing.SwingConstants;

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
        //System.out.println("Inside display of UserGUI");
        IntProgramState ips = null;
        for (IntProgramState k : ps)
        if(k.getProgramID() == pid)
        {
            ips = k;
            break;
        }
        QuestionPage qqp=new QuestionPage(ips,this);
     }
     public void run(long pid,int select){
        String cmd=null,dcmd=null;
        StringTokenizer st;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("choosertitle");
        String fullPath = null;
        JFrame j=new JFrame();
        int option = chooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            String sf = chooser.getSelectedFile().getName();
            File file = chooser.getCurrentDirectory();
            fullPath = file.getPath();
            //System.out.println("Path "+fullPath);
            //System.out.println(select);
            switch(select){
                 case 1:cmd="\""+fullPath+"\\"+sf+"\"";
                        break;
                 case 2:dcmd = "java " + fullPath + "\\" + sf;
                        st = new StringTokenizer(dcmd,".");
                        cmd = st.nextToken();
                        break;
                 case 3:dcmd = "python " + fullPath + "\\" + sf;
                        st = new StringTokenizer(dcmd,".");
                        cmd = st.nextToken()+".py";
                        break;
             }
            //System.out.println("The selected file's path is " + fullPath);
            //System.out.println("The selected file is " + sf);
        }
        //System.out.println(cmd);
          IntLiveResultSet ilr = uf.execute(pid, cmd);
          ResultPage rrp=new ResultPage(ilr);
          //System.out.println(ilr);
     }
     
     
     @Override
     public void showMessage(String message) {
          JOptionPane.showMessageDialog(null, message, "Input Your Message", JOptionPane.INFORMATION_MESSAGE);
     }

     @Override
     public void start() {
          //System.out.println("ABC");
          StartPage sp=new StartPage(ps,this);
     }

     @Override
     public void close() {
          uf.close();
     }

     @Override
     public String Prompt(String message) {
          System.out.println("ABCD");
          String mes =(JOptionPane.showInputDialog(this, message));
        
        return mes;
     }
     
}
/*
          int i=obj.getState();
          switch (i){
               case TEST_PASS://code
                    break;
          }
*/