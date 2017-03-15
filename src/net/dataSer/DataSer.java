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
     public String toUrl() throws RemoteException {
          return url;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public Map<String, byte[]> getAllProblems() {
          return problems;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public Map<String, byte[]> getAllTestCases() {
          return testCases;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public IntDataSer getObject() {
          return this;
     }

     /**
      * {@inheritDoc} 
      */
     @Override
     public LocalDateTime getTime() {
          return dt;
     }
     
}
