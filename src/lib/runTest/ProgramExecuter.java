package lib.runTest;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *this is class is used to execute the program once with given in input.
 * @author Neel Patel
 */
class ProgramExecuter {
     boolean executed=false;
     ProcessBuilder pb;
     Process pr;
     Thread tin,tout;
     List<String> input;
     List<String> output;
     Scanner sc;
     long time=-1;
     //InputStreamReader in;
     OutputStreamWriter out;
     /**
      * create object with list of input string command.
      * the command is not executed until the execute method called on the object.
      * @param input list of string going to be provided as input to the program when executed.
      * @param cmd command to execute program.
      */
     ProgramExecuter(List<String> input,String cmd){
          this.input=input;
          pb=new ProcessBuilder(cmd);
          pb.redirectErrorStream(true);
          output=new ArrayList<>();
     }
     
     /**
      * this method create the subprocess by command provided in constructor.
      * method waits for subprocess to terminate.
      * if the subprocess is not terminate in specified period of time then the process is terminated forcefully.
      * return list printed by subprocess on output stream or error stream.
      * return immediately if the subprocess executed ones. 
      * @param time time in milli seconds, the method wait for subprocess to terminate. value must be greater then 0.
      * @return output of subprocess as list of string, null if process is terminated forcefully. 
      * @throws IOException
      */
     synchronized List<String> execute(long time) throws IOException{
          if(executed)
               return output;
          executed=true;
          output=new ArrayList();
          long st=System.currentTimeMillis();
          boolean b=false;
          pr=pb.start();
          out=new OutputStreamWriter(pr.getOutputStream());
          sc=new Scanner(pr.getInputStream());
          tin=new Thread(this::giveIn);
          tout=new Thread(this::getOut);
          tin.start();
          tout.start();
          try{
               b=pr.waitFor(time, TimeUnit.MILLISECONDS);
          }catch(InterruptedException e){return null;}
          if(!b){
               stop();
               output.clear();
               return output;
          }
          this.time=System.currentTimeMillis()-st;
          try {
               tout.join();
               tin.join();
          } catch(InterruptedException ex) {return null;}
          return output;
     }
     
     /**
      * this method stop the execution of subprocess forcefully if process is alive.
      */
     void stop(){
          if(pr!=null){
               pr.destroyForcibly();
          }
          if(tin!=null&&tin.isAlive()){
               tin.destroy();
          }
          if(tout!=null&&tout.isAlive()){
               tout.destroy();
          }
     }
     /**
      * @return true if subprocess is alive, false otherwise
      */
     boolean isAlive(){
          if(pr!=null&&pr.isAlive())
               return pr.isAlive();
          return false;
     }
     /**
      * give the input to the subprocess created by execute method.
      * this method can be invoke from new thread.
      * print strings on standard input stream of subprocess provided in constructor. 
      */
     private void giveIn(){
          input.stream().forEach(str -> {
               try {
                    out.write(str);
               } catch(IOException ex) {}});
     }
     /**
      * get the output from standard output stream of subprocess & store in list output.
      * method can be called from new thread.
      */
     private void getOut(){
          try{
               for(;;)
               output.add(sc.nextLine());
          }catch(NoSuchElementException ex){}               
     }
     
     /**return runtime of subprocess in milli seconds.
      * 
      * @return runtime in milliseconds, -1 if process is not executed or terminated forcefully. 
      */ 
     synchronized long getRunTime(){
          return time;
     }
}
