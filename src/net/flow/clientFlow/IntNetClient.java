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
package net.flow.clientFlow;

import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author Neel Patel
 */
public interface IntNetClient {
     boolean regErrRunner(Consumer<String> r);
     boolean regMessageRunner(Consumer<String> r);
     boolean init(String uName,String passwd);
     boolean log(String s);
     int credit(long pid);
     int userCredit();
     /**
      * implementation of this method should return the total credit of the user.
      * credit returned by this method should be reflected everywhere i.e. remote
        server etc. 
      * @return return Map of ProblemId to Status.
      */
     Map<Long,Integer> getAllStatus();
     
}
