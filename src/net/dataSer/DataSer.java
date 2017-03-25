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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Neel Patel
 */
public class DataSer extends UnicastRemoteObject implements IntDataSer,Serializable{
     private String url="";
     private final Map<String,byte[]> problems;
     private final Map<String,byte[]> testCases;
     private final LocalDateTime dt;
     
     public DataSer(Map<String,byte[]> problems, Map<String,byte[]> testCases)
             throws RemoteException{
          this.problems=problems;
          this.testCases=testCases;
          this.dt=LocalDateTime.now();
     }
     
     private DataSer(DataSer d) throws RemoteException{
          this.dt=d.dt;
          this.problems=d.problems;
          this.testCases=d.testCases;
          this.url=d.url;
     }
     
     /**
      * set the URI of the object which will return by toUrl method.
      * @param u URI String
      * @return true if the URI updated successfully.
      */
     @Override
     public boolean setUrl(String u)throws RemoteException{
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
     public String toUrl() throws RemoteException {
          return url;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public Map<String, byte[]> getAllProblems() {
          return problems;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public Map<String, byte[]> getAllTestCases() {
          return testCases;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public IntDataSer getObject() {
          try {
               return new DataSer(this);
          } catch (RemoteException ex) {
          }
          return null;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public LocalDateTime getTime() {
          return dt;
     }
     
}
