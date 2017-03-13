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
          return LogHandler.log(s);
     }

     @Override
     public boolean setBackupLogger(IntRemoteLog bl) throws RemoteException {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public String toUrl() throws RemoteException {
          return url;
     }
     
}
