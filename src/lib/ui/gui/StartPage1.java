package lib.ui.gui;

import java.util.List;
import javax.swing.*;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Parth Doshi
 */
public class StartPage extends javax.swing.JPanel {

     /**
      * Creates new form StartPage
      */
     JPanel StartPage;
     JButton[] jb=new JButton[10];
     JLabel[] jl=new JLabel[50];
     
     private List<? extends IntProgramState> ps;
     public StartPage(List<? extends IntProgramState> ps) {
          initComponents();
          this.ps=ps;
     }
     public int start(){
          JButton[] jb=new JButton[10];
          JLabel[] jl=new JLabel[50];
          
          return pid;
     }
}