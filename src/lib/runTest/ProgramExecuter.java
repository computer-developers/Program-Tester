package lib.runTest;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *this is class is used to execute the program once with given in input.
 * standard output and error streams are bind together by default.
 * @author Neel Patel
 */
class ProgramExecuter {
     boolean executed=false;
     String cmd;
     ProcessBuilder pb; //used to create subprocess
     Process pr; //refer to the subprocess
     Thread tin,tout; //refer to the thread which give input and output
     List<String> input; //input of subprocess
     List<String> output; //output of subprocess
     Scanner sc; //Scanner bind to standard outputStream and error stream
     long time=-1; //time taken by subprocess in milli secounds.
     BufferedReader in;
     PrintWriter out; //bind to standard input stream of subprocess.
     
     /**
      * create object with list of input string command.
      * the command is not executed until the execute method called on the object.
      * @param input list of string going to be provided as input to the program when executed.
      * @param cmd command to execute program.
      */
     ProgramExecuter(List<String> input,String cmd){
          this.input=input;
          this.cmd=cmd;
          pb=new ProcessBuilder(cmd);
          pb.redirectErrorStream(true); //error stream bind with the output steam of subprocess
          output=new ArrayList<>();
     }
     
     /**
      * this method create the subprocess by command provided in constructor.
      * method waits for subprocess to terminate.
      * if the subprocess is not terminate in specified period of time then the process is terminated forcefully.
      * return list printed by subprocess on output stream or error stream.
      * return previous result immediately if the subprocess already executed ones. 
      * @param time time in milli seconds, the method wait for subprocess to terminate. value must be greater then 0.
      * @return output of subprocess as list of string, null if process is terminated forcefully. 
      * @throws IOException if error occurred during creating subprocess.
      */
     synchronized List<String> execute(long time) throws IOException{
          if(executed)
               return output;
          executed=true;
          output=new ArrayList();
          long st=System.currentTimeMillis();
          boolean b=false;
          //pb.inheritIO();
          pr=Runtime.getRuntime().exec(cmd);
          //pr=pb.start(); //start new subprocess.
          //System.out.println("ProgramExecuter.execute :- started");
          //in=new BufferedReader(new InputStreamReader(pr.getInputStream()));
          //in=new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
          out=new PrintWriter(new BufferedOutputStream(pr.getOutputStream()));
          sc=new Scanner(pr.getInputStream());
          tin=new Thread(this::giveIn); //create thread to give input to subprocess 
          tout=new Thread(this::getOut); //create thread to get output of subprocess
          tout.start();
          tin.start();
          try{
               b=pr.waitFor(time, TimeUnit.MILLISECONDS); //wait for subprocess to terminate in time
          }catch(InterruptedException e){
               return null;
          }
          if(!b){ //if the process is not terminated in specified time
               stop();
               output.clear();
               return output;
          }
          this.time=System.currentTimeMillis()-st; //calculate the ruuning time of subprocess
          try { //join the input and output Threads.
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
               sc.close();
               //tin.destroy();
          }
          if(tout!=null&&tout.isAlive()){
               //tout.destroy();
               out.close();
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
          int i=0;
          //System.out.println("ProgramExecuter.getin :- started");
          input.stream().forEach(str -> {
               //try {
                    out.println(str);
                    //System.out.println("ProgramExecuter.getin :- give in "+str);
               //} catch(IOException ex) {}
          });
          out.close();
     }
     
     /**
      * get the output from standard output stream of subprocess and store in list output.
      * method can be called from new thread.
      */
     private void getOut(){
          //System.out.println("ProgramExecuter.getOut :- started");
          //for(;pr.isAlive();){
          //in.lines().forEach(s->output.add(s));
          sc.forEachRemaining(s->{
               try{
                    //String s=in.readLine();
                    output.add(s);
                    //System.out.println("ProgramExecuter.getout :- get out "+s);
               }catch(NoSuchElementException ex){
                    //System.out.println(ex);
               }
          });
          //System.out.println("ProgramExecuter.getOut : - ended");
     }
     
     /**return runtime of subprocess in milli seconds.
      * 
      * @return runtime in milliseconds, -1 if process is not executed or terminated forcefully. 
      */ 
     synchronized long getRunTime(){
          return time;
     }
     
     @Override
     public void finalize()throws Throwable{
          try {
               stop();
          } finally {
               super.finalize();
          }
     }
}
