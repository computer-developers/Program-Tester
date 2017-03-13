/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dataSer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Neel Patel
 */
public class DataSer extends UnicastRemoteObject implements IntDataSer{
     private String url="";
     private final Map<String,byte[]> problems;
     private final Map<String,byte[]> testCases;
     private final LocalDateTime dt;
     
     public DataSer(Map<String,byte[]> problems, Map<String,byte[]> testCases)
             throws RemoteException{
          this.problems=problems;
          this.testCases=testCases;
          this.dt=LocalDateTime.now();
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
     public String toUrl() throws RemoteException {
          return url;
     }

     @Override
     public Map<String, byte[]> getAllProblems() {
          return problems;
     }

     @Override
     public Map<String, byte[]> getAllTestCases() {
          return testCases;
     }

     @Override
     public IntDataSer getObject() {
          return this;
     }

     @Override
     public LocalDateTime getTime() {
          return dt;
     }
     
}
