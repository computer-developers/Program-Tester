/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/
package lib.run.test.util;

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
