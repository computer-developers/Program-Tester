package lib.dataTools.inputGenerator;
import java.util.*;

/**
 * this class provide advanced functionality to generate the random string using
   using nesting of regular expressions.
 * @author admin
 */
public class InputGenExt {
     int count;
     final String reg;
     List<String> part=new ArrayList();
     public InputGenExt(String reg){
          this.reg=reg;
     }
     
     public List<String> getInput(int count){
          List<String> li=new ArrayList();
          return li;
     }
     
     private void regProcessor(){
          if(reg.contains(reg)){
               
          }
     }
}
