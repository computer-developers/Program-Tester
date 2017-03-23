package lib.ui.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import lib.userModule.result.IntProgramState;
import static programtester.config.Configuration.TEST_PASS;
import static programtester.config.Configuration.TEST_PRESENT_ERROR;


     /**
      * This page is called from the display method of UserGUI.
      * This page displays the details of the question selected by the user from the StartPage.
      * The selection of the language used is made here.
      * All the data is displayed in a predefined but editable format using JFrame.
      * The button to choose the selected file i.e.fchoose is provided here.
      * This button calls the run method of the UserGUI which in turns opens the JFileChooser.
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
     JLabel get_status;
     JLabel[] saminput;
     JLabel[] samoutput;
     JRadioButton jr1;
     JRadioButton jr2;
     JRadioButton jr3;
     JButton fchoose;
     ButtonGroup bg;
     int button_select=0,int_status;
     String str_status;
     IntProgramState det;
     UserGUI ug=null;

     public QuestionPage(IntProgramState dett, UserGUI ug) {
        super("Question Page");
        this.ug=ug;
        this.det=dett;
        det.addRunnable(this::refresh);
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
        jr1.setSelected(true);
        bg.add(jr1);
        jr1.setActionCommand("Cc");
        bg.add(jr2);
        jr1.setActionCommand("Ja");
        bg.add(jr3);
        jr1.setActionCommand("Py");
        //System.out.println(bg.getSelection());
        this.add(fchoose);
        get_status=new JLabel();
        refresh();
        this.add(get_status);
     }
     public void refresh(){
        int_status=det.getState();
        switch(int_status){
             case TEST_PRESENT_ERROR:
                  str_status="Presentation Error Found";
                  break;
             case TEST_PASS:
                  str_status="Program Successfully Executed";
                  break;
             default : 
                  str_status="Program Still Unsolved";
        }
        get_status.setText("Status of this question is - "+str_status);
    }
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
     
/*    public static void main(String[] args){
        QuestionPage qp=new QuestionPage();
    }
*/
}
