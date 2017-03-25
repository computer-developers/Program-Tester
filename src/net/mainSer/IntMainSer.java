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
package net.mainSer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import net.mainSer.userStatus.IntUserStatus;

/**
 *
 * @author Neel Patel
 */
public interface IntMainSer extends Remote{
     /**
      * are you alive.
      * @return this method should always return true.
      * @throws RemoteException if occur in RMI framework.
      */
     boolean aya()throws RemoteException;
     
     /**
      * this method return URI of data server.
      * @return Data Server if available, null if not available.
      * @throws RemoteException if occur in RMI framework.
      */
     String getDataSer()throws RemoteException;
     
     /**
      * return log server if available.
      * @return return URI of log server, null if not available.
      * @throws RemoteException if occur in RMI framework.
      */
     String getLogSer()throws RemoteException;
     
     /**
      * this method register the remote data server.
      * this method return true if the log server successfully registered,
        false otherwise.
      * @param url URI String of remoteDataServer.
      * @return return true if the Data server registered successfully,
        false otherwise.
      * @throws RemoteException if occur in RMI framework.
      */
     boolean registerDataSer(String url)throws RemoteException;
     
     /**
      * return the object of IntUserStatus if parameters are valid.
      * if the Username and Password is not valid then null will be return.
      * @param user username
      * @param passwd password
      * @return the Object of IntUserStatus if the username and password is valid.
      * @throws RemoteException if occur in RMI framework.
      */
     IntUserStatus getStatus(String user,String passwd)throws RemoteException;
}
