package lib.logger;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 *
 * @author Neel Patel
 */
public class MyLogger implements Closeable{
     private String sep=",";
     private final PrintStream out;
     public MyLogger(OutputStream out){
          this.out=new PrintStream(out);
     }
     
     public void log(String s){
          out.println(s);
     }
     
     public synchronized void setSep(String sep){
          this.sep=sep;
     }
     
     public synchronized void log(String... ar){
          String l=Arrays.stream(ar)
                  .reduce((s1,s2)->s1+sep+s2).orElse("");
          this.log(l);
     }
     
     public synchronized String getlogString(String... ar){
          String l=Arrays.stream(ar)
                  .reduce((s1,s2)->s1+sep+s2).orElse("");
          return l;
     }
     
     @Override
     public void finalize(){
          close();
     }

     @Override
     public void close(){
          out.close();
     }
     
}
