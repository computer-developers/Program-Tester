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

import lib.dT.problemManipulate.IntProgramDetail;

/**
 *
 * @author Neel Patel
 */
public interface IntProgramState extends IntProgramDetail{
     /**
      * register runnable listener.
      * run method of the registered Runnable object will be called every time
        when the state change.
      * @param r Object of type Runnable.
      */
     void addRunnable(Runnable r);
     
     /**
      * return the state of the problem.
      * @return state of the problem.
      */
     int getState();
}
