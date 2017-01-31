package lib.dT.manipulate.comparators;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * this class contain methods to perform various operation on Lists of Strings.
 * @author Neel Patel
 */
public class ListManipulator {
     private ListManipulator(){}
     
     /**
      *
      * @param o1
      * @param o2
      * @param bp
      * @return
      */
     public static boolean compare(List<String>o1,List<String>o2,
               BiPredicate<String,String> bp){
          Iterator<String> i1=o1.iterator();
          try{
               if(o2.stream().allMatch((s2) ->bp.test(s2,i1.next()))) {
                    if(!i1.hasNext())
                         return true;
               }
          }catch(NoSuchElementException e){
               return false;
          }
          return false; 
     }
     
     /**
      *
      * @param li
      * @return
      */
     public static List<String> removeNull(List<String> li){
          return li.stream().filter(s->s!=null&&s!="")
                    .collect(Collectors.toList());
     }
     
     /**
      *
      * @param li
      * @return
      */
     public static List<String> trim(List<String> li){
          return li.stream().map(s->s.trim()).collect(Collectors.toList());
     }
}
