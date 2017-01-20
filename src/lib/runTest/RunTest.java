package lib.runTest;
import lib.runDetails.*;
import java.util.*;
import java.io.*;
/**
 * this class provide facility to execute specified command with given input.
 * @author Neel Patel
 */
public class RunTest {
     public static final long runTime = 30000;
     ProgramExecuter pb;
     Thread t;
     List<String> out;
     long time=-1;
     
     /**
      * create object with specified input & command
      * @param input used to create IntIODetail object by executing command.
      * @param cmd command to create subprocess.
      */
     public RunTest(IntInput input,String cmd){
          this(input.getAllInput(),cmd);
     }
     
     RunTest(List<String> input,String cmd){
          pb=new ProgramExecuter(input,cmd);
     }
     
     /**
      * start new subprocess.
      * start new subprocess using command specified in constructor if subprocess is already not running.
      * create new thread with execute the subprocess if currently not executing.
      * start subprocess if the previous execution is completed.
      */
     synchronized public void run(){
          if(t!=null&&t.isAlive())
               return;
          t=new Thread();
          long st=System.nanoTime();
          t.start();
     }
     
     /**
      * @return true if subprocess is alive, false otherwise. 
      */
     public boolean isAlive(){
          if(t!=null)
               return t.isAlive();
          return false;
     }
     
     /**
      * start new subprocess.
      * wait until the process is terminated.
      */
     void start(){
          try{
               out=pb.execute(runTime);
          }catch(IOException e){}
     }
     
     /**
      * return the result of previous execution of subprocess.
      * if the subprocess is not executed once then execute the subprocess & wait until the process terminate.
      * if the subprocess is executing currently then it will wait to finish it.
      * @return object of IntIODetail of last execution of process.
      */
     synchronized public IntIODetail getIODetail(){
          if(t==null)
               run();
          try{
               t.join();
          }catch(InterruptedException e){}
          return IOManager.getIODetail(null, null);
     }
     
}
