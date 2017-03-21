package lib.ui.gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Parth Doshi
 */
public class QuestionPage extends JFrame implements ActionListener{
     JLabel pid;
     JLabel title;
     JLabel credit;
     JLabel des;
     JLabel[] description;
     JLabel input;
     JLabel output;
     JLabel saminput;
     JLabel samoutput;
     String samin="",samout="";
     JButton fchoose;
     IntProgramState det;
     UserGUI ug=null;
     public QuestionPage(IntProgramState dett, UserGUI ug) {
        super("Question Page");
        this.ug=ug;
        this.det=dett;
        setLayout(new GridLayout(20,1));
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pid=new JLabel("Program ID : "+det.getProgramID());    //Converted to String from Long
        title=new JLabel("Program Title : "+det.getTitle());
        des=new JLabel("Description : ");
        description=new JLabel[(det.getDescription()).size()];
        this.add(pid);
        this.add(title);
        this.add(des);
        int i=0;
        for(String s:det.getDescription()){
             description[i]=new JLabel(s);
             add(description[i]);
        }
        input=new JLabel("Input : "+det.getInput());
        output=new JLabel("Output : "+det.getOutput());
        for(String s:det.getSampleInput()){
             samin+=s;
             samin+="<html><br></html>";
        }
        saminput=new JLabel("Sample Input: "+samin);
        add(saminput);
        samoutput=new JLabel();
        for(String s:det.getSampleOutput()){
             samout+=s+"<html><br></html>";
        }
        samoutput=new JLabel("Sample output: "+samout);
        add(samoutput);
        fchoose=new JButton("Choose Your File");
        fchoose.addActionListener(this);
        setVisible(true);
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