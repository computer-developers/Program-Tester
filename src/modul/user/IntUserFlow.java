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
package modul.user;

import java.io.Closeable;
import java.util.List;
import lib.run.test.util.IntLiveResultSet;
import ui.*;
import lib.run.test.util.IntProgramState;

/**
 *
 * @author Neel Patel
 */
public interface IntUserFlow extends Closeable{
     
     /**
      * implementation of this method should register the User Interface.
      * recorded user interface should be used to interact with user.
      * @param ui object of User Interface.
      */
     void register(IntUI ui);
     
     /**
      * implementation of this method should execute the command
        {@code cmd} and with the {@code pid} specified.
      * {@code cmd} should not be contain relative paths. each and every
        path should be absolute.
      * call of this method should not take long time to terminate.
      * this method must return immediately.
      * this method can start parallel threads which can modify the
        {@code resultSet} return by this method.
      * @param pid Program ID for which the command is going to be executed.
      * @param cmd Command for execution.
      * @return {@code IntLiveResultSet}, containing the result of command.
      */
     IntLiveResultSet execute(long pid,String cmd);
     
     /**
      * implementation of this method should return the total credit of the user.
      * credit returned by this method should be reflected everywhere i.e. remote
        server etc. 
      * @return credit of the user.
      */
     int getCredit();
     
     /**
      * implementation of this method should update the data.
      * call to this method should fetch the data from the remote servers and
        update it.
      */
     void refresh();
     
     /**
      * implementation of this method should return all the
        {@code Problem Defination} as a list of {@code ProgramState}.
      * @return list of IntProgramState.
      */
     List<? extends IntProgramState> getAllProgramDetail();
     
     /**
      * {@inheritDoc}
      */
     @Override
     void close();
}
