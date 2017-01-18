package lib.runTest;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
class ProgramExecuter {
     ProcessBuilder pb;
     Process pr;
     Thread tin,tout;
     List<String> input;
     List<String> output;
     Scanner sc;
     long time=-1;
     //InputStreamReader in;
     OutputStreamWriter out;
     ProgramExecuter(List<String> input,String cmd){
          this.input=input;
          pb=new ProcessBuilder(cmd);
          pb.redirectErrorStream(true);
     }
     List<String> execute(long time) throws IOException{
          output=new ArrayList();
          long st=System.currentTimeMillis();
          pr=pb.start();
          out=new OutputStreamWriter(pr.getOutputStream());
          sc=new Scanner(pr.getInputStream());
          tin=new Thread(this::giveIn);
          tout=new Thread(this::getOut);
          tin.start();
          tout.start();
          try {
               tout.join(time);
               tin.join();
          } catch(InterruptedException ex) {}
          
          return output;
     }
     void stop(){
          if(pr!=null){
               pr.destroyForcibly();
               pr=null;
          }
          if(tin!=null&&tin.isAlive()){
               tin.destroy();
               tin=null;
          }
          if(tout!=null&&tout.isAlive()){
               tout.destroy();
               tout=null;
          }
          output=null;
     }
     boolean isAlive(){
          if(pr!=null&&pr.isAlive())
               return pr.isAlive();
          return false;
     }
     private void giveIn(){
          input.stream().forEach(str -> {
               try {
                    out.write(str);
               } catch(IOException ex) {}});
     }
     private void getOut(){
          try{
               for(;;)
               output.add(sc.nextLine());
          }catch(NoSuchElementException ex){}               
     }
}
