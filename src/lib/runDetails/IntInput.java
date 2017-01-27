package lib.runDetails;
import java.util.*;
/**
 * this interface used to get input for specific type of program.
 * @author admin
 */
public interface IntInput {
     /**
      * @return list of string which can be used as input of related program
      */
     List<String> getAllInput();
     /**
      * @return return unique id for which input is generated, -1 if not specified.
      */
     default long programId(){
          return -1;
     }
     /**
      * @return return index of input, -1 if not specified.
      */
     default long index(){
          return -1;
     }
}
