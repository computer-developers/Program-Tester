package lib.ui.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Parth Doshi
 */
public class StartPage extends javax.swing.JFrame implements ActionListener {

     /**
      * Creates new form StartPage
      */
     //JPanel StartPage;
     JButton[] jb=new JButton[10];
     JLabel[] jl=new JLabel[10];
     //JLabel[] jl2=new JLabel[10];
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
             //jl2[i]=new JLabel("<html><br/></html>");
             jb[i]=new JButton("Attempt This");
             spid[i] = ips.getProgramID();
             jb[i].addActionListener(this);
             add(jl[i]);
             jb[i].setActionCommand(""+i);
             add(jb[i]);
             //add(jl2[i]);
             
             i++;
        }  
        JButton close=new JButton("Close Program");
        add(close);
        close.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
             ugi.close();
             StartPage.this.dispose();
           }
        });
     }
     
     @Override
     public void actionPerformed(ActionEvent e) {
          ugi.display(spid[Integer.parseInt(e.getActionCommand())]);
          
     }
     /*public static void main(String args[]){
          StartPage spp=new StartPage(null,null);
     }*/
}