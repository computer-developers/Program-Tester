package lib.dT.inputGenerator;
import java.util.*;

/**
 *
 * @author Neel Patel
 */
public class InputGen {
     private InputGen(){}
     
     /**
      *
      * @param count
      * @param reg
      * @return
      */
     public static List<String> getInput(int count,String... reg){
          List<String> li=new ArrayList();
          StringGen sg=new StringGen(reg);
          for(int i=0;i<count;i++)
               li.add(sg.nextString());
          return li;
     }
}
