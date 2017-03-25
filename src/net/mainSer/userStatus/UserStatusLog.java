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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import net.logSer.IntRemoteLog;

/**
 *
 * @author Neel Patel
 */
public class UserStatusLog extends UnicastRemoteObject implements IntRemoteLog{
     private String url="";
     
     public UserStatusLog() throws RemoteException{}
     
     public boolean setUrl(String u){
          url=u;
          return true;
     }
     
     @Override
     public boolean aya() throws RemoteException {
          return true;
     }

     @Override
     public boolean log(String s) throws RemoteException {
          System.out.println("log arrived");
          //return UserFactory.logHandle(s);
          return UserFactory.processLog(s);
     }

     @Override
     public boolean setBackupLogger(String bl) throws RemoteException {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public String toUrl() throws RemoteException {
          return url;
     }
     
}
