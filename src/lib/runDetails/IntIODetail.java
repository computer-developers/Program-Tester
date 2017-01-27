package lib.runDetails;
import java.util.*;
/**
 *
 * @author admin
 */
public interface IntIODetail extends IntInput{
     /**
      * @return output of related program for specified input as list of string
      */
     List<String> getAllOutput();
     /**
      * @return execution time in milli seconds taken by program, -1 if not applicable 
      */
     long getTime();
     /**
      * @return return index of input & corresponding output, -1 if not specified.
      */
     default long index(){
          return -1;
     }
}
