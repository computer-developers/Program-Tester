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
public class StartPage extends javax.swing.JPanel implements ActionListener {

     /**
      * Creates new form StartPage
      */
     JPanel StartPage;
     JButton[] jb=new JButton[10];
     JLabel[] jl=new JLabel[10];
     Long[] spid=new Long[10];
     UserGUI ugi = null;
     JButton close;
     private List<? extends IntProgramState> ps;
     public StartPage(List<? extends IntProgramState> ps,UserGUI ugi) {
        this.ps=ps;
        this.ugi = ugi;
        int i=0;
        for(IntProgramState ips:ps){
             jl[i]=new JLabel("Program no :"+i+1);
             jb[i]=new JButton("Attempt This");
             spid[i] = ips.getProgramID();
             jb[i].addActionListener(this);
             add(jb[i]);
             add(jl[i]);
             i++;
        }  
        JButton close=new JButton("Close Program");
        add(close);
        close.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             ugi.close();
           }
        });
     }
     
     @Override
     public void actionPerformed(ActionEvent e) {
          ugi.display(spid[e.getID()]);
     }
}