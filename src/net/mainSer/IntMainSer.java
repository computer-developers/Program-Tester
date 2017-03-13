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
     boolean aya()throws RemoteException;
     String getDataSer()throws RemoteException;
     String getLogSer()throws RemoteException;
     boolean registerDataSer(String url)throws RemoteException;
     IntUserStatus getStatus(String user,String passwd)throws RemoteException;
}
