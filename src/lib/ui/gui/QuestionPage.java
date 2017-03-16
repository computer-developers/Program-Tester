package lib.ui.gui;
import javax.swing.*;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Parth Doshi
 */
public class QuestionPage extends JFrame{
     JLabel pid;
     JLabel title;
     JLabel description;
     JLabel input;
     JLabel output;
     JPanel saminput;
     JPanel samoutput;
     IntProgramState det;
     public QuestionPage(IntProgramState dett) {
        super("Question Page");
        this.det=dett;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        pid=new JLabel("Program ID : "+det.getProgramID());    //Converted to String from Long
        title=new JLabel("Program Title : "+det.getTitle());
        description=new JLabel("Description : "+det.getDescription());
        input=new JLabel("Input : "+det.getInput());
        output=new JLabel("Output : "+det.getOutput());
        saminput=new JPanel();
        samoutput=new JPanel();
        setVisible(true);
        this.add(pid);
        this.add(title);
        this.add(description);
        this.add(input);
        this.add(output);
        
     }
     
     
     public static void main(String[] args){
        QuestionPage qp=new QuestionPage();
    }
}