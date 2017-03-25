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

import net.mainSer.userStatus.IntUserStatus;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import net.mainSer.userStatus.UserFactory;

/**
 *
 * @author Neel Patel
 */
public class MainSer extends UnicastRemoteObject implements IntMainSer{

     public MainSer()throws RemoteException{}
     
     /**
      * {@inheritDoc} 
      */
     @Override
     public boolean aya() throws RemoteException {
          return true;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public String getDataSer() throws RemoteException {
          return SerDetails.getDataSer();
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public String getLogSer() throws RemoteException {
          return SerDetails.getLogSer();
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public boolean registerDataSer(String url) throws RemoteException {
          return SerDetails.registerDataSer(url);
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public IntUserStatus getStatus(String user, String passwd) {
          IntUserStatus u=UserFactory.getUser(user, passwd);
          System.out.println("us name - "+u.getUName());
          System.out.println("us pass - "+u.getPasswd());
          System.out.println("us credit - "+u.getUserCredit());
          System.out.println("us status - "+u.getAllProStatus());
          return u;
     }
     
}
