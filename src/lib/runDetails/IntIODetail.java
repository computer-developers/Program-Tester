package lib.runDetails;
import java.util.*;

/**
 * this interface can be used to store the information about single run.
 * @author Neel Patel
 */
public interface IntIODetail extends IntInput{
     /**
      * @return output of related program for specified input as list of string,
        {@code null} otherwise.
      */
     List<String> getAllOutput();
     
     /**
      * @return execution time in milli seconds taken by program, -1 if not applicable 
      */
     long getTime();
     
     /**
      * @return index of input and corresponding output, -1 if not specified.
      */
     default long index(){
          return -1;
     }
}
