package lib.ui.gui;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lib.userModule.result.IntLiveResult;
import lib.userModule.result.IntLiveResultSet;

/**
 *
 * @author Parth Doshi
 */

public class ResultPage extends JFrame{
     /**
      * This Page is called from the run method of the UserGUI
      * This method displays the test cases and their states
      * This is the final page that is displayed in the GUI
      */
    JLabel[] tcno= new JLabel[10];
    JLabel[] ans= new JLabel[10];
    java.util.List<IntLiveResult> ilr = null;
    IntLiveResultSet irs = null;
    public ResultPage(IntLiveResultSet irs) {    
        super("Result Page");
        this.irs=irs;
        irs.getAllLiveResult().forEach(i->i.addRunnable(this::refresh));
        setLayout(new GridLayout(10,2));
        setSize(700, 700);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        ilr = this.irs.getAllLiveResult();
        //System.out.println(ilr);
        
        int i = 0;
        for(IntLiveResult ir: ilr) //for-each loop
        {
            tcno[i]=new JLabel("Test Case - "+ir.index());
            ans[i]=new JLabel(ir.getMessage());
            this.add(tcno[i]);
            this.add(ans[i]);
            i++;
        }
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void refresh(){
         ilr = irs.getAllLiveResult();
         int i = 0;
         for(IntLiveResult ir: ilr) //for-each loop
        {
            tcno[i].setText("Test Case - "+ir.index());
            ans[i].setText(ir.getMessage());
            i++;
        }
    }
    /*public static void main(String[] args){
        ResultPage rp=new ResultPage(null);
    }*/
}