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
package lib.userModule.result;

import java.util.ArrayList;
import java.util.List;
import lib.dT.problemManipulate.IntProgramDetail;

/**
 *
 * @author Neel Patel
 */
public class ProgramStateAdapter implements IntProgramState{

     private int state=0;
     private IntProgramDetail pd;
     private List<Runnable> func=new ArrayList<>();
     
     private synchronized void run(){
          func.parallelStream().forEach(r->r.run());
     }
     
     protected synchronized void setState(int s){
          state=s;
          run();
     }
     
     public ProgramStateAdapter(IntProgramDetail pd){
          this.pd=pd;
     }
     
     @Override
     public synchronized void addRunnable(Runnable r) {
          func.add(r);
     }

     @Override
     public int getState() {
          return state;
     }

     @Override
     public String getTitle() {
          return pd.getTitle();
     }

     @Override
     public List<String> getDescription() {
          return pd.getDescription();
     }

     @Override
     public String getInput() {
          return pd.getInput();
     }

     @Override
     public String getOutput() {
          return pd.getOutput();
     }

     @Override
     public List<String> getSampleInput() {
          return pd.getSampleInput();
     }

     @Override
     public List<String> getSampleOutput() {
          return pd.getSampleOutput();
     }

     @Override
     public long getProgramID() {
          return pd.getProgramID();
     }

     @Override
     public int getCredit() {
          return pd.getCredit();
     }
     
}
