package lib.gui;
/**
 *This is a basic GUI page for selecting the saved .class file or .exe
 *file of the user program
 * 
 * @author Parth Doshi
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class FileChooser extends JFrame {

   public FileChooser() {
    super("Select your file from here");
    setSize(500,500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    
    JButton openButton = new JButton("Open");
    //final JLabel statusbar = new JLabel("Output of your selection will go here");

    // Create a file chooser that opens up as an Open dialog
    openButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("choosertitle");
        String fullPath = null, cmd;
        int option = chooser.showOpenDialog(FileChooser.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String sf = chooser.getSelectedFile().getName();
            File file = chooser.getCurrentDirectory();
            fullPath = file.getPath();
            System.out.println("The selected file's path is " + fullPath);
            System.out.println("The selected file is " + sf);
            cmd="java " + fullPath + "\\" + sf;
            System.out.println("The final command is "+cmd);
        }
      }
    });

    c.add(openButton);
    //c.add(statusbar);
  }

  public static void main(String args[]) {
    FileChooser fc = new FileChooser();
    fc.setVisible(true);
  }
}