/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
      * if the Username & Password is not valid then null will be return.
      * @param user username
      * @param passwd password
      * @return the Object of IntUserStatus if the username & password is valid.
      * @throws RemoteException if occur in RMI framework.
      */
     IntUserStatus getStatus(String user,String passwd)throws RemoteException;
}
