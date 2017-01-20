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
      * @return string
      */
     String getName();
     /**
      * @return execution time in milli seconds taken by program, -1 if not applicable 
      */
     long getTime();
}
