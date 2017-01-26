package lib.inputGenerator;
import java.util.*;

/**
 *
 * @author admin
 */
public class InputGen {
     private InputGen(){}
     
     public static List<String> getInput(int count,String reg){
          List<String> li=new ArrayList();
          StringGen sg=new StringGen(reg);
          for(int i=0;i<count;i++)
               li.add(sg.nextString());
          return li;
     }
}
