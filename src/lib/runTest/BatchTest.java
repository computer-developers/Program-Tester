package lib.runTest;
import java.util.*;
import lib.runDetails.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.*;

/**
 *
 * @author Neel Patel
 */
public class BatchTest {
     private RunTest rt[];
     private List<IntIODetail> io;
     private Stream<IntIODetail> st;
     private boolean f=false;
     private int index=-1;
     private Thread t;
     private Executor ex;
     public BatchTest(String cmd,IntInput... ar){
          rt=Arrays.stream(ar).map(s->new RunTest(s,cmd)).toArray(RunTest[]::new);
     }
     
     public synchronized Supplier<IntIODetail> execute(){
          
          return this::supp;
     }
     
     private IntIODetail supp(){
          synchronized(io){
               if(index+1>=rt.length)
                    return null;
               for(;index+1<io.size();){
                    try {
                         wait();
                    } catch(InterruptedException ex) {}
               }
               index++;
               return io.get(index);
          }
     }
     
     
}
