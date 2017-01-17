package lib.runDetails;
import java.util.*;
import java.nio.file.*;
/**
 *
 * @author admin
 */
public class IOManager {
     public static IntIODetail getIODetail(List<String> input,List<String> output){
          return new DummyIODetail();
     }
     public static IntIODetail getIODetail(Path filepath){
          return new DummyIODetail();
     }
}
