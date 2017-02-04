/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ui.gui;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Parth Doshi
 */
public class resultPage extends JFrame{
    JLabel tcno;
    JTextField ans;
    public resultPage() {    
        
    super("Result Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        tcno=new JLabel("Test Case");
        ans=new JTextField(10);
        tcno.setText("Test Case 1 : ");
        setVisible(true);
        this.add(tcno);
        this.add(ans);
    }
    public static void main(String[] args){
        resultPage rp=new resultPage();
    }
}