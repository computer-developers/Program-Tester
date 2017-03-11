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
     boolean aya()throws RemoteException;
     boolean log(String s)throws RemoteException;
     boolean setBackupLogger(IntRemoteLog bl)throws RemoteException;
}
