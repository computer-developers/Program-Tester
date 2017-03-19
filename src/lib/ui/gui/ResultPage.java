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
        java.util.List<IntLiveResult> ilr = this.irs.getAllLiveResult();
        int i = 0;
        for(IntLiveResult ir: ilr) //for-each loop
        {
        
             
             //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        tcno[i]=new JLabel("Test Case - "+ir.getMessageCode());
        ans[i]=new JLabel(ir.getMessage());
        //tcno.setText("Test Case 1 : ");
        
        this.add(tcno[i]);
        this.add(ans[i]);
        i++;
        }
    }
    /*public static void main(String[] args){
        ResultPage rp=new ResultPage();
    }*/
}