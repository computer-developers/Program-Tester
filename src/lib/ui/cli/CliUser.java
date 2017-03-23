package lib.ui.cli;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import lib.ui.IntUI;
import lib.userModule.result.IntLiveResultSet;
import lib.userModule.IntUserFlow;
import lib.userModule.TimeNotAvailableException;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Neel Patel
 */
public class CliUser implements IntUI{
     private final IntUserFlow uf;
     private List<?extends IntProgramState> ps;
     private Scanner sc=new Scanner(System.in);
     private boolean isAlive=true;
     public CliUser(IntUserFlow uf){
          this.uf=uf;
          this.uf.register(this);
          ps=uf.getAllProgramDetail();
          ps.forEach(i->i.addRunnable(
                         ()->System.out.println("#PID :- "
                         +i.getProgramID()+ "\n#Title :- "+i.getTitle()
                         +"\n#State :- "+i.getState()+"\n")
                         )
                    );
     }
     
     @Override
     public void showMessage(String message) {
          System.out.println("message :- "+message);
     }
     
     @Override
     public synchronized void start(){
          while(isAlive){
               System.out.println("\nMenu..\n"
                         + "1.Test\n"
                         + "2.Display Problems\n"
                         + "3.Display User's Total Credit\n"
                         + "4.Refresh\n"
                         + "0.Exit\n");
               switch(sc.nextInt()){
                    case 1:System.out.println("Enter Program ID (Enter the unique 12 length number):-");
                              long p=sc.nextLong();
                              System.out.println("Enter command (Enter the path of the .exe file of the program) :-");
                              String s;
                              for(s=sc.nextLine();s.isEmpty();s=sc.nextLine());
                              test(p,s);
                              break;
                    case 2:dispDetail();
                              break;
                    case 3:System.out.println("Users Credit:-"+uf.getCredit());
                              break;
                    case 4:uf.refresh();
                              break;
                    case 0:uf.close();
                         return;
                    default: System.out.println("Invalid input");
               }
          }
     }
     
     public void test(long pid,String cmd){
          System.out.println("test case\tcode\tmessage\ttime");
          IntLiveResultSet lrs=uf.execute(pid,cmd);
          if(lrs==null)
               return;
          lrs.getAllLiveResult()
                    .forEach(i->i.addRunnable(()->{
                         String s="test "
                                 +i.index()+" :-\t"
                                 +i.getMessageCode()+",\t"+i.getMessage();
                         try{
                              long t=i.getRunTime();
                              s+=",\t"+t;
                         }catch(TimeNotAvailableException e){}
                         System.out.println(s);
                    }
               ));
          lrs.getAllResult();
          //System.out.println("test end..");
     }
     
     public void dispDetail(){
          System.out.println("index\tProblemId\tState\tTitle\n");
          ps.stream().forEach(i->{
                         String s=""
                                 +ps.indexOf(i)+"\t"
                                 +i.getProgramID()+"\t"
                                 +i.getState()+"\t"+i.getTitle();
                         System.out.println(s);
                    }
               );
          try{
               for(;;){
                    System.out.println("\nenter index :- ");
                    int i=sc.nextInt();
                    IntProgramState x=ps.get(i);
                    System.out.println("\nProgram Id :\t"+x.getProgramID());
                    System.out.println("\nTitle :\t\t"+x.getTitle());
                    System.out.println("\nDescription :-");
                    x.getDescription().forEach(j->System.out.println(j));
                    System.out.println("\nInput :\t\t"+x.getInput());
                    System.out.println("\nOutput :\t"+x.getOutput());
                    System.out.println("\nSample Input :-");
                    x.getSampleInput().forEach(j->System.out.println(j));
                    System.out.println("\nSample Output :-");
                    x.getSampleOutput().forEach(j->System.out.println(j));
                    System.out.println("\nCredit :\t"+x.getCredit());
               }
          }catch(IndexOutOfBoundsException e){}
     }

     @Override
     public void close() {
          isAlive=false;
     }

     @Override
     public String Prompt(String message) {
          System.out.println("prompt :- "+message);
          String s="";
          for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
          return s;
     }
     
}
