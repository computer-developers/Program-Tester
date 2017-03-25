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
package net.mainSer.userStatus;

import java.io.Serializable;
import java.util.Map;

/**
 * this Interface is used to remotely transfer data from main server.
 * @author Neel Patel
 */
public interface IntUserStatus extends Serializable{
     
     /**
      * @return Map object which map ProgramID to the Credit earned by user.
      */
     Map<Long,Integer> getAllProStatus();
     
     /**
      * this method returns the credit of the user at instance.
      * @return Credit earned by the User at the movement.
      */
     int getUserCredit();
     
     /**
      * @return the user name.
      */
     String getUName();
     
     /**
      * @return the password.
      */
     String getPasswd();
}
