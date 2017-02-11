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
     private List<IntIODetail> orig; //original input maped outputs, to be compared by
     private List<IntIODetail> user; //user's input maped outputs, to be compared with
     private List<IntResult> lr; //result of comparision.
     //private boolean isExecuted=false;
     private boolean flag=false; //flag to indicate if the process of comparision is running
     private int index=-1; //index of last returned IntResult in 'lr'.
     private Thread t; //known as comparator thread.
     
     /**
      * creates object with original input mapped output & user's
        input mapped output.
      * @param orig original input mapped outputs.
      * @param user user's input mapped outputs.
      */
     public BatchComparator(List<IntIODetail> orig,List<IntIODetail> user){
          this.orig=orig;
          this.user=user;
          lr=new ArrayList<>();
     }
     
     /**
      * start comparison process using comparator thread.
      * it creates a new thread known as {@code Comparator Thread} which will
        compare the outputs of user with corresponding original outputs.
      * it returns immediately, without waiting for tasks to done.
      * if the Comparator Thread is already initiated it return with null immediately.
      * it returns stream of IntResult which gives result & corresponding comparison.
      * the stream returned by this method is infinite stream, it will encounter null
        when there is no elements exist to process.
      * @return stream of IntResult, each element contain result of comparison.
      */
     public synchronized Stream<IntResult> compare(){
          if(t!=null)
               return null;
          t=new Thread(this::run,"Comparator Thread");
          t.start();
          return Stream.generate(this::supp);
     }
     
     /**
      * this method can be used to create new thread which will compare
        the user's outputs with corresponding original outputs.
      * this method call {@code comp} method on all the elements of list 
        {@link user}.
      * this method with puts current thread in waiting until the all comparison
        completed or process of comparison terminated by stop method.
      */
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
     
     /**
      * this method change the flag to false to terminate the
        Comparison process.
      * although the currently running comparison process will be executed as it is.
      * next tasks will not be initiated.
      */
     public void stop(){
          flag=false;
     }
     
     /**
      * this method returns the result as IntResultSet.
      * this method will call compare method if it is not called before.
      * if the comparator thread is not terminated yet then it will wait for it
        to join.
      * @return result as object of IntResultSet.
      */
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
     
     /**
      * this method can be used as supplier by giving reference.
      * this method gives the next result of comparison of user's output.
      * this method will wait if next result is not available at the moment.
      * method will return null if there is no further results available.
      * @return next Object of IntResult, null if no further objects available.
      */
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
     
     /**
      * this method compare the output of {@param us} with
        corresponding output in list {@code orig}.
      * this method finds the equivalent original output from list {@code orig}
        & compare it's output to the output of {@param us}.
      * @param us object of IntIODetail, output of which is going to be compared.
      * @return object of IntResult containing the result of the comparison.
      * @throws IllegalArgumentException if no equivalent object exist in list
        {@code orig} or {@param us} contain invalid information like negative
        {@code programID} or {@code index} etc..
      */
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
