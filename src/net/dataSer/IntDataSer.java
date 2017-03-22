/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
