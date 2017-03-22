/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.logSer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
public interface IntLogProc extends Remote{
     List<String> getLogs(LocalDateTime start,LocalDateTime end) throws RemoteException;
}
