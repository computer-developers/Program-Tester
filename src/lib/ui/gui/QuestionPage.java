package lib.ui.gui;
import java.awt.Font;
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
     JLabel samin;
     JLabel samout;
     JLabel[] saminput;
     JLabel[] samoutput;
     JRadioButton jr1;
     JRadioButton jr2;
     JRadioButton jr3;
     JButton fchoose;
     ButtonGroup bg;
     int button_select=0;
     IntProgramState det;
     UserGUI ug=null;
     public QuestionPage(IntProgramState dett, UserGUI ug) {
        super("Question Page");
        this.ug=ug;
        this.det=dett;
        setLayout(new GridLayout(30,1));
        setSize(700, 700);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        }                                                        //setFont(field.getFont().deriveFont(Font.BOLD));
        input=new JLabel("Input : "+det.getInput());
        output=new JLabel("Output : "+det.getOutput());
        samin=new JLabel("The sample input for this program is : ");
        this.add(samin);
        saminput = new JLabel[(det.getSampleInput().size())];
        for(String s:det.getSampleInput()){
             saminput[i]=new JLabel(s);
             add(saminput[i]);
        }
        samout=new JLabel("The sample output for this program is : ");
        this.add(samout);
        samoutput = new JLabel[(det.getSampleOutput().size())];
        for(String s:det.getSampleOutput()){
             samoutput[i]=new JLabel(s);
             add(samoutput[i]);
        }
        fchoose=new JButton("Choose Your File");
        fchoose.addActionListener(this);
        fchoose.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jr1=new JRadioButton("C or C++");
        jr2=new JRadioButton("Java");
        jr3=new JRadioButton("Python");
        bg=new ButtonGroup();
        this.add(input);
        this.add(output);
        this.add(jr1);
        this.add(jr2);
        this.add(jr3);
        bg.add(jr1);
        jr1.setActionCommand("Cc");
        bg.add(jr2);
        jr1.setActionCommand("Ja");
        bg.add(jr3);
        jr1.setActionCommand("Py");
        //System.out.println(bg.getSelection());
        this.add(fchoose);     
     }
     
     
/*     public static void main(String[] args){
        QuestionPage qp=new QuestionPage();
    }
*/
     @Override
     public void actionPerformed(ActionEvent e) {
          if(jr1.isSelected()){                   //bg.getSelection().getActionCommand().equals("Cc")
                button_select=1;
                //System.out.println("JR1");
             }
             else if(jr2.isSelected()){
                button_select=2;
                //System.out.println("JR2");
             }
             else if(jr3.isSelected()){
                button_select=3;
                //System.out.println("JR3");
             }
          //System.out.println(button_select);
          ug.run(det.getProgramID(),button_select);
          
     }
}