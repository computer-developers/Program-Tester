package lib.dT.manipulate.comparators;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.*;

/**
 * this class contain different types of {@code BiPredicates} to compare Strings
 * @author Neel Patel
 */
public class StringComparators {
     private StringComparators(){}
     
     public static class ExactMatch implements BiPredicate<String,String>{
          public boolean test(String o1, String o2) {
               return o1.equals(o2);
          }
          
     }
     
     public static class IgnoreCase implements BiPredicate<String,String>{
          public boolean test(String o1, String o2) {
               return o1.equalsIgnoreCase(o2);
          }
     }
     
     public static class IgnoreWhiteSpace
               implements BiPredicate<String,String>{
          public boolean test(String o1, String o2) {
               List<String> l1=Arrays.asList(o1.split("\\s"));
               List<String> l2=Arrays.asList(o2.split("\\s"));
               return l1.equals(l2);
          }
     }
     
     public static class IgnoreWhiteSpaceAndCase
               implements BiPredicate<String,String>{
          public boolean test(String o1, String o2) {
               List<String> l1=Arrays.asList(o1.split("\\s"))
                         .parallelStream().map(s->s.toLowerCase())
                         .collect(Collectors.toList());
               List<String> l2=Arrays.asList(o2.split("\\s"))
                         .parallelStream().map(s->s.toLowerCase())
                         .collect(Collectors.toList());
               return l1.equals(l2);
          }
     }
     
}
