/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.logSer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Neel Patel
 */
public class RemoteLog extends UnicastRemoteObject implements IntRemoteLog{
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
          return LogHandler.setBackupLog(url);
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public String toUrl() throws RemoteException {
          return url;
     }
     
}
