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
package net.dataSer;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Neel Patel
 */
public interface IntDataSer extends Remote,Serializable{
     /**
      * are you alive.
      * @return this method should always return true;
      * @throws RemoteException if occur in RMI framework.
      */
     boolean aya()throws RemoteException;
     
     /**
      * 
      * @return URI of the object to access remotely, null otherwise.
      * @throws RemoteException if occur in RMI framework.
      */
     String toUrl()throws RemoteException;
     
     /**
      * this method should return all the problem files as map
        object of which maps the name of the file to byte array of file data.
      * @return map object containing data of Problems.
      */
     Map<String,byte[]> getAllProblems()throws RemoteException;
     
     /**
      * this method should return all the Test files as map object of which
        maps the name of the file to byte array of file data.
      * @return map object containing data of Test files.
      */
     Map<String,byte[]> getAllTestCases()throws RemoteException;
     
     /**
      * when this method called remotely, this object should be
        transfer from the server to client through the network.
      * @return this object.
      */
     IntDataSer getObject()throws RemoteException;
     
     /**
      * should return creation time of the object
      * @return object of LocalDateTime
      */
     LocalDateTime getTime()throws RemoteException;
     
     /**
      * 
      */
     boolean setUrl(String s)throws RemoteException;
}
