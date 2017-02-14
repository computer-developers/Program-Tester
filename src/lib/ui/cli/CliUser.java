package lib.ui.cli;

import java.util.Scanner;
import lib.ui.IntUI;
import lib.userModule.IntUserFlow;
import lib.userModule.TimeNotAvailableException;

/**
 *
 * @author Neel Patel
 */
public class CliUser implements IntUI{
     private IntUserFlow uf;
     public CliUser(IntUserFlow uf){
          this.uf=uf;
          this.uf.register(this);
     }
     
     @Override
     public void showMessage(String message) {
          System.out.println("message :- "+message);
     }
     
     @Override
     public synchronized void start(){
          Scanner sc=new Scanner(System.in);
          while(true){
               System.out.println("Menu..\n"
                         + "1.Test"
                         + "2.Display"
                         + "0.Exit");
               switch(sc.nextInt()){
                    case 1:System.out.println("Enter Program ID..");
                              long p=sc.nextLong();
                              System.out.println("Enter command");
                              String s;
                              for(s=sc.nextLine();s.isEmpty();s=sc.nextLine());
                              test(p,s);
                              break;
                    case 2:break;
                    case 0:return;
                    default: System.out.println("Invalid input");
               }
          }
     }
     
     public void test(long pid,String cmd){
          //System.out.println("test start...");
          uf.execute(pid,cmd).getAllLiveResult()
                    .forEach(i->i.addRunnable(()->{
                         String s="test "
                                 +i.index()+" :- "
                                 +i.getMessageCode()+","+i.getMessage();
                         try{
                              long t=i.getRunTime();
                              s+=","+t;
                         }catch(TimeNotAvailableException e){}
                         System.out.println(s);
                    }
                  ));
                  
          //System.out.println("test end..");
     }
}
