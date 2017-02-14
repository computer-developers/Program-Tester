package lib.userModule.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
public class ResultSetAdapter implements IntResultSet{
     private List<IntResult> li;
     
     public ResultSetAdapter(IntResult... ar){
          this(Arrays.asList(ar));
     }
     
     public ResultSetAdapter(List<? extends IntResult> li){
          this.li=Collections.unmodifiableList(li);
     }
     
     @Override
     public int getCount() {
          return li.size();
     }

     @Override
     public List<IntResult> getAllResult() {
          return li;
     }

     @Override
     public IntResult getResult(int index) {
          return li.get(index);
     }
     
}
