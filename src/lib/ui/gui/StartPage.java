package lib.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Parth Doshi
 */

public class StartPage extends JFrame implements ActionListener {
      /**
      * This page is called from the start method of the UserGUI by creating an object of StartPage
      * This class is displays the first page of the GUI
      * User can select the Program he wants to attempt from the list of programs using JButtons
      */

     JButton[] jb=new JButton[10];
     JLabel[] jl=new JLabel[10];
     Long[] spid=new Long[10];
     UserGUI ugi = null;
     JButton close;
     private List<? extends IntProgramState> ps;
     public StartPage(List<? extends IntProgramState> ps,UserGUI ugi) {
        this.ps=ps;
        this.ugi = ugi;
        //setLayout(new BorderLayout());
        //JLabel background=new JLabel(new ImageIcon("C:\\Users\\Parth Doshi\\Documents\\NetBeansProjects\\Program-Tester\\X-pro coder.png"));
        //background.setLayout(new FlowLayout());
        //background.setSize(500, 500);
        //add(background);
        setLayout(new GridLayout(12,2));
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int i=0;
        for(IntProgramState ips:ps){
             jl[i]=new JLabel("Program no :"+(i+1),SwingConstants.CENTER);
             jb[i]=new JButton("Attempt This");
             spid[i] = ips.getProgramID();
             jb[i].addActionListener(this);
             add(jl[i]);
             jb[i].setActionCommand(""+i);
             add(jb[i]);
             i++;
        }  

        JButton close=new JButton("Close Program");
        add(close);
        close.addActionListener(new ActionListener() {           //anonymous class
             @Override
             public void actionPerformed(ActionEvent e) {
             ugi.close();
             StartPage.this.dispose();
           }
        });
     }
     
     @Override
     public void actionPerformed(ActionEvent e) {
          /**
           * This method will work on every JButton of jb[]
           * This will send the pid of the program user chooses to attempt to the UserGUI
           */
          ugi.display(spid[Integer.parseInt(e.getActionCommand())]);
     }

     /*public static void main(String args[]){
          StartPage spp=new StartPage(null,null);
     }*/
}