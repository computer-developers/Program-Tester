package lib.runTest;
import java.util.List;
import lib.runDetails.*;
import java.util.*;
/**
 *
 * @author admin
 */
public class DummyIntIODetail implements IntIODetail{

     long time;
     List<String> input,output;
     private DummyIntIODetail(){}
     static public IntIODetail getIntIODetail(List<String> input,List<String> output,long time){
          DummyIntIODetail d=new DummyIntIODetail();
          d.input=input;
          d.output=output;
          d.time=time;
          return d;
     }
     
     public List<String> getAllOutput() {
          return output;
     }

     public String getName() {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public long getTime() {
          return time;
     }

     @Override
     public List<String> getAllInput() {
          return input;
     }
     
}
