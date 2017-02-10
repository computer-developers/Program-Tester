package lib.userModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
class ResultSetAdapter implements IntResultSet{
     private List<IntResult> li;
     
     ResultSetAdapter(IntResult... ar){
          this(Arrays.asList(ar.clone()));
     }
     
     ResultSetAdapter(List<IntResult> li){
          this.li=new ArrayList();
          this.li.addAll(li);
     }
     
     @Override
     public int getCount() {
          return li.size();
     }

     @Override
     public List<IntResult> getAllResult() {
          List<IntResult> nli=new ArrayList<>();
          nli.addAll(li);
          return nli;
     }

     @Override
     public IntResult getResult(int index) {
          return li.get(index);
     }
     
}
