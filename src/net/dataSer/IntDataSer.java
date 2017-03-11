/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dataSer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Neel Patel
 */
public interface IntDataSer extends Remote{
     boolean aya()throws RemoteException;
}
