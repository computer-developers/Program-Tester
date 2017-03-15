/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
