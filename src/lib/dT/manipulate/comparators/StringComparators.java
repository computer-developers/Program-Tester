package lib.dT.manipulate.comparators;
import java.util.*;
import java.util.stream.Collectors;
/**
 * this class contain different types of comparators to compare Strings
 * @author Neel Patel
 */
public class StringComparators {
     private StringComparators(){}
     
     static class ExactMatch implements Comparator<String>{
          @Override
          public int compare(String o1, String o2) {
               return o1.compareTo(o2);
          }
     }
     
     static class IgnoreCase implements Comparator<String>{
          @Override
          public int compare(String o1, String o2) {
               return o1.compareToIgnoreCase(o2);
          }
     }
     
     static class IgnoreWhiteSpace implements Comparator<String>{
          @Override
          public int compare(String o1, String o2) {
               List<String> l1=Arrays.asList(o1.split("\\s"));
               List<String> l2=Arrays.asList(o2.split("\\s"));
               if(l1.equals(l2))
                    return 0;
               else
                    return -1;
          }
     }
     
     static class IgnoreWhiteSpaceAndCase implements Comparator<String>{
          @Override
          public int compare(String o1, String o2) {
               List<String> l1=Arrays.asList(o1.split("\\s"))
                         .parallelStream().map(s->s.toLowerCase())
                         .collect(Collectors.toList());
               List<String> l2=Arrays.asList(o2.split("\\s"))
                         .parallelStream().map(s->s.toLowerCase())
                         .collect(Collectors.toList());
               if(l1.equals(l2))
                    return 0;
               else
                    return -1;
          }
     }
     
}
