package lib.userModule;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import lib.dT.manipulate.comparators.ListManipulator;
import lib.dT.manipulate.comparators.StringComparators;
import lib.runDetails.IntIODetail;
import lib.runTest.RunTest;

/**
 * this class provide a facility to compare the batch of outputs
   mapped to the original output on different parameters.
 * @author Neel Patel
 */
public class BatchComparator {
     private List<IntIODetail> orig;
     private List<IntIODetail> user;
     private List<IntResult> lr;
     //private boolean isExecuted=false;
     private boolean flag=false;
     private int index=-1;
     private Thread t;
     public BatchComparator(List<IntIODetail> orig,List<IntIODetail> user){
          this.orig=orig;
          this.user=user;
          lr=new ArrayList<>();
     }
     
     public synchronized Stream<IntResult> compare(){
          if(t!=null)
               return null;
          t=new Thread(this::run);
          t.start();
          return Stream.generate(this::supp);
     }
     
     private void run(){
          flag=true;
          for(IntIODetail i:user){
               if(!flag) //check if the thread should to be terminated.
                    break;
               IntResult d=comp(i);
               synchronized(lr){
                    lr.add(d);
               }
               lr.notifyAll();
          }
          lr.notifyAll();
          flag=false;
     }
     
     public void stop(){
          flag=false;
     }
     
     public synchronized IntResultSet getOutputs(){
          if(t==null) //check if the executer thread is not already initiated before.
               compare();
          for(;t.isAlive();) //wait for executer thread to be terminated.
               try {
                    t.join();
               } catch(InterruptedException ex) {
               }
          return new ResultSetAdapter((IntResult[])lr.toArray());
     }
     
     private IntResult supp(){
          synchronized(lr){
//               if(index+1>=rt.length ) // check if any further result exist.
//                    return null;
               for(;index+1<lr.size();){ //wait for next result to be available.
                    if(!flag)
                         return null;
                    try {
                         lr.wait(1000);
                    } catch(InterruptedException ex) {}
               }
               index++;
               return lr.get(index);
          }
     }
     
     private IntResult comp(IntIODetail us){
          try{
               long time=us.getTime();
               if(time<0)
                    return new ResultAdapter(time,-1,"Timelimit Exceeds");
               IntIODetail ori=orig.stream()
                              .filter(p->p.programID()>=0&&p.index()>=0)
                              .filter(p2->p2.programID()==us.programID()&&
                                        p2.index()==us.index())
                              .findFirst().get();
               if(ListManipulator.compare(us.getAllOutput(),ori.getAllOutput(),
                              StringComparators.getExactmatch()))
                    return new ResultAdapter(time,1,"Perfact");
               if(ListManipulator.compare(us.getAllOutput(),ori.getAllOutput(),
                              StringComparators.getIgnoreWhiteSpace()))
                    return new ResultAdapter(time,2,"Presentation Error");
               return new ResultAdapter(time,-2,"Fail");
          }catch(NoSuchElementException ex){
               throw new IllegalArgumentException();
          }
     }
     
     @Override
     public void finalize() throws Throwable{
          try {
               stop();
          } finally {
               super.finalize();
          }
     }
}
