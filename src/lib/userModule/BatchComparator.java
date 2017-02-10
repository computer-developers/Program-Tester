package lib.userModule;

import java.util.List;
import java.util.stream.Stream;
import lib.runDetails.IntIODetail;

/**
 *
 * @author Neel Patel
 */
public class BatchComparator {
     private List<IntIODetail> orig;
     private List<IntIODetail> user;
     
     public BatchComparator(List<IntIODetail> orig,List<IntIODetail> user){
          this.orig=orig;
          this.user=user;
     }
     
     public synchronized Stream<IntResult> compare(){
          return null;
     }
     
}
