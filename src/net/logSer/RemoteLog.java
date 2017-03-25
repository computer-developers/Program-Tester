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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
public class RemoteLog extends UnicastRemoteObject implements IntRemoteLog,
        IntLogProc{
     private String url="";
     
     public RemoteLog()throws RemoteException{
          LogHandler.start();
     }
     
     /**
      * set the URI of the object which will return by toUrl method.
      * @param u URI String
      * @return true if the URI updated successfully.
      */
     public boolean setUrl(String u){
          url=u;
          return true;
     }
     
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
     public boolean log(String s) throws RemoteException {
          return LogHandler.log(s);
     }

     /**
      * {@inheritDoc}
      * this method will set the backupLogger if it was not set already.
      */
     @Override
     public boolean setBackupLogger(String bl) throws RemoteException {
          return LogHandler.setBackupLog(bl);
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public String toUrl() throws RemoteException {
          return url;
     }

     @Override
     public List<String> getLogs(LocalDateTime start, LocalDateTime end) {
          return LogHandler.getLogs();
     }
     
}
