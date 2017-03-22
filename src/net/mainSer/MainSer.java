/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mainSer;

import net.mainSer.userStatus.IntUserStatus;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
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
