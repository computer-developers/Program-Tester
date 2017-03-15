/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mainSer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dataSer.IntDataSer;
import net.logSer.IntRemoteLog;

/**
 *
 * @author Neel Patel
 */
public class SerDetails {
     private SerDetails(){}
     private static IntRemoteLog log;
     private static IntDataSer mainDataSer;
     private final static Map<IntDataSer,Long> dataSer=new ConcurrentHashMap<>();
     
     /**
      * 
      * @return return the data Server, null if no data server connected.
      */
     public static String getDataSer(){
          for(;!(dataSer.isEmpty()&&mainDataSer==null);){
               String s=findDataSer();
               if(s!=null)
                    return s;
          }
          return null;
     }
     
     /**
      * this method return URI of data server.
      * return the String of URI of data server from list {@code dataSer},
        which have minimum timestamp.
      * this method also update the timestamp of the server.
      * if the selected remote server is not reachable then the URI will
        be removed and returns null
      * if there is no server in the list {@code dataSer} then the
        {@code main data server} will be selected.
      * @return data server, null if exception occur.
      */
     private synchronized static String findDataSer(){
          IntDataSer d=dataSer.keySet().parallelStream()
                              .reduce((x,y)->{
                                   if(dataSer.get(x)>dataSer.get(y))
                                        return y;
                                   return x;
                              }).orElse(mainDataSer);
          try {
               if(d.aya()){
                    dataSer.replace(d,System.currentTimeMillis());
                    return d.toUrl();
               }else{
                    assert true:"remote data Server say 'not alive'";
                    return null;
               }
          } catch (Exception ex) {
               dataSer.remove(d);
               return null;
          }
     }
     
     /**
      * return log server.
      * @return URI of log server, null if log sever is not reachable
        exception occur
      */
     public static synchronized String getLogSer(){
          try {
               if(log.aya())
                    return log.toUrl();
               else{
                    assert true:"remote Log Server say 'not alive'";
                    return null;
               }
          } catch (Exception ex) {
               return null;
          }
     }
     
     /**
      * register the log data server.
      * this method first get a reference of remote object using String 
        {@code url} then it calls {@code aya} method.
      * if the the object reference is successfully obtain then it will update
        {@code log} and return true.
      * if any exception occur while the getting reference of remote object
        then this method returns false without updating reference.<br>
      * Note :- if the new remote server is added then the old one is
        removed if any.(reference of log server is maintained internally)
      * @param url URI of remote data Server.
      * @return true if successfully updated, false if not updated due to some
        reason.
      */
     public static boolean setLogSer(String url){
          try {
               IntRemoteLog ls=(IntRemoteLog)Naming.lookup(url);
               if(ls.aya()){
                    log=ls;
                    return true;
               }
               else{
                    assert true:"remote Log Server say 'not alive'";
                    return false;
               }
          } catch (Exception ex) {
               return false;
          } 
     }
     
     /**
      * register the data server.
      * this method first get a reference of remote object using String 
        {@code url} then it calls {@code aya} method.
      * if the the object reference is successfully obtain then it will add 
        in the {@code dataSer} and return true.
      * if any exception occur while the getting reference of remote object
        then this method returns false without updating reference.<br>
      * Note :- if the new remote server is added then the old one is not
        removed if any.(list of data server is maintained internally)
      * @param url URI of remote data Server.
      * @return true if successfully added, false if not added due to some
        reason.
      */
     public static boolean registerDataSer(String url){
          try {
               IntDataSer ds=(IntDataSer)Naming.lookup(url);
               if(ds.aya()){
                    dataSer.put(ds,System.currentTimeMillis());
                    return true;
               }else{
                    assert true:"remote Log Server say 'not alive'";
                    return false;
               }
          } catch (Exception ex) {
               return false;
          }
     }
     
     /**
      * register the main data server.
      * this method first get a reference of remote object using String 
        {@code url} then it calls {@code aya} method.
      * if the the object reference is successfully obtain then it will update
        {@code mainDataSer} and return true.
      * if any exception occur while the getting reference of remote object
        then this method returns false without updating reference.<br>
      * Note :- if the new remote server is added then the old one is
        removed if any.(reference of main data server is maintained internally)
      * @param url URI of remote data Server.
      * @return true if successfully updated, false if not updated due to some
        reason.
      */
     public synchronized static boolean registerMainDataSer(String url){
          try {
               IntDataSer ds=(IntDataSer)Naming.lookup(url);
               if(ds.aya()){
                    mainDataSer=ds;
                    return true;
               }else{
                    assert true:"remote Log Server say 'not alive'";
                    return false;
               }
          } catch (Exception ex) {
               return false;
          }
     }
     
}
