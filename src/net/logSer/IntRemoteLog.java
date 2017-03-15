/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
