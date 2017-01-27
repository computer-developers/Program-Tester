package lib.runDetails;
import java.util.*;

/**
 *
 * @author admin
 */
class IODetail implements IntIODetail{
     long time=-1;
     List<String> input=new ArrayList<>(),output=new ArrayList<>();
     IODetail(List<String> input,List<String> output){
          this.input.addAll(input);
          this.output.addAll(output);
     }
     
     IODetail(List<String> input,List<String> output, Long time){
          this(input,output);
          this.time=time;
     }
     
     public List<String> getAllOutput() {
          List<String> l=new ArrayList<String>();
          l.addAll(output);
          return l;
     }
     
     @Override
     public long getTime() {
          return time;
     }

     @Override
     public List<String> getAllInput() {
          List<String> l=new ArrayList<String>();
          l.addAll(input);
          return l;
     }
     
}
