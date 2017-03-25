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
package net.logSer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Neel Patel
 */
public interface IntRemoteLog extends Remote{
     /**
      * are you alive.
      * @return this method should always return true;
      * @throws RemoteException if occur in RMI framework.
      */
     boolean aya()throws RemoteException;
     
     /**
      * this method should log the the string s at proper place
      * @param s log String
      * @return true if logged successfully
      * @throws RemoteException if occur in RMI framework.
      */
     boolean log(String s)throws RemoteException;
     
     /**
      * this method should set the backup logger.
      * log method of the backup logger is always called by this logger's log
        method.
      * @param bl URI of the backup logger.
      * @return true if backup logger is set, false otherwise.
      * @throws RemoteException if occur in RMI framework.
      */
     boolean setBackupLogger(String bl)throws RemoteException;
     
     /**
      * 
      * @return URI of the object to access remotely, null otherwise.
      * @throws RemoteException if occur in RMI framework.
      */
     String toUrl()throws RemoteException;
}
