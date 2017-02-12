package lib.logger;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author Neel Patel
 */
public class MyLogger {
     private final PrintStream out;
     public MyLogger(OutputStream out){
          this.out=new PrintStream(out);
     }
     
     public void log(String s){
          out.println(s);
     }
     
     @Override
     public void finalize(){
          out.close();
     }
}
