package lib.runTest;
import lib.runDetails.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author admin
 */
public class RunTest {
     public static long runTime = 30000;
     ProgramExecuter pb;
     Thread t;
     List<String> out;
     long time=-1;
     public RunTest(IntInput input,String cmd){
          this(input.getAllInput(),cmd);
     }
     
     public RunTest(List<String> input,String cmd){
          pb=new ProgramExecuter(input,cmd);
     }
     
     public void run(){
          synchronized(t){
               if(t!=null&&t.isAlive())
                    return;
               t=new Thread();
               long st=System.nanoTime();
               st=System.currentTimeMillis();
               t.start();
               try{
                    t.join(runTime);
               }catch(InterruptedException e){}
               time=System.currentTimeMillis()-st;
          }
     }
     
     public boolean isAlive(){
          if(t!=null)
               return t.isAlive();
          return false;
     }
     
     public boolean isStarted(){
          return false;
     }
     
     void start(){
          try{
               out=pb.execute(runTime);
          }catch(IOException e){}
     }
     
     public IntIODetail getIODetail(){
          return IOManager.getIODetail(null, null);
     }
     
}
