package lib.userModule.result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Neel Patel
 */
public class LiveResultSetAdapter implements IntLiveResultSet{
     private List<IntLiveResult> li;
     
     public LiveResultSetAdapter(IntLiveResult... ar){
          this(Arrays.asList(ar));
     }
     
     public LiveResultSetAdapter(List<? extends IntLiveResult> li){
          this.li=Collections.unmodifiableList(li);
     }
     
     @Override
     public int getCount() {
          return li.size();
     }

     @Override
     public List<? extends IntResult> getAllResult() {
          List<IntResult> nl = li.stream().map(i->i.toIntResult())
                  .collect(Collectors.toList());
          return nl;
     }

     @Override
     public IntResult getResult(int index) {
          return li.get(index).toIntResult();
     }

     @Override
     public IntLiveResult getLiveResult(int index) {
          return li.get(index);
     }

     @Override
     public List<IntLiveResult> getAllLiveResult() {
          return li;
     }
     
     
}
