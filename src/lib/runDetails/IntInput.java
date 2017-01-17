package lib.runDetails;
import java.util.*;
/**
 *
 * @author admin
 */
public interface IntInput {
     List<String> getAllInput();
     default long programId(){
          return 10;
     }
}
