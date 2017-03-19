package lib.ui.gui;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import lib.userModule.result.IntLiveResult;
import lib.userModule.result.IntLiveResultSet;

/**
 *
 * @author Parth Doshi
 */
public class ResultPage extends JFrame{
    JLabel[] tcno,ans = new JLabel[10];
    
    IntLiveResultSet irs = null;
    public ResultPage(IntLiveResultSet irs) {    
        
    super("Result Page");
        this.irs=irs;
             setSize(500,500);
             setVisible(true);
             Container c = getContentPane();
             c.setLayout(new FlowLayout());
    
        java.util.List<IntLiveResult> ilr = this.irs.getAllLiveResult();
        int i = 0;
        for(IntLiveResult ir: ilr) //for-each loop
        {
            tcno[i]=new JLabel("Test Case - "+ir.index());
            ans[i]=new JLabel(ir.getMessage());
            this.add(tcno[i]);
            this.add(ans[i]);
            i++;
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
    }
    /*public static void main(String[] args){
        ResultPage rp=new ResultPage();
    }*/
}