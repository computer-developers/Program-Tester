package lib.ui.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Parth Doshi
 */
public class QuestionPage extends JFrame implements ActionListener{
     JLabel pid;
     JLabel title;
     JLabel credit;
     JTextArea  description;
     JLabel input;
     JLabel output;
     JPanel saminput;
     JPanel samoutput;
     JButton fchoose;
     IntProgramState det;
     UserGUI ug=null;
     public QuestionPage(IntProgramState dett, UserGUI ug) {
        super("Question Page");
        this.ug=ug;
        this.det=dett;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        pid=new JLabel("Program ID : "+det.getProgramID());    //Converted to String from Long
        title=new JLabel("Program Title : "+det.getTitle());
//        description=new JTextArea("Description : "+det.getDescription());
        input=new JLabel("Input : "+det.getInput());
        output=new JLabel("Output : "+det.getOutput());
        saminput=new JPanel();
        samoutput=new JPanel();
        fchoose.addActionListener(this);
        setVisible(true);
        this.add(pid);
        this.add(title);
        this.add(description);
        this.add(input);
        this.add(output);
        this.add(fchoose);
     }
     
     
     public static void main(String[] args){
//        QuestionPage qp=new QuestionPage();
    }

     @Override
     public void actionPerformed(ActionEvent e) {
          ug.run(det.getProgramID());
     }
}