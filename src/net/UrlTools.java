/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neel Patel
 */
public class UrlTools {
     
     /**
      * this method return {@code uri} for {@code RMI} generated using
        parameters.
      * this method return string {@code rmi://ip:port/name}.
      * string returned by this method can be used as a Uri in many inbuilt
        methods.
      * @param ip IP address or domain name like 'localhost'
      * @param port port number.
      * @param name name of the object.
      * @return URI string for RMI generated using parameters.
      */
     public static String makeRMI(String ip,int port,String name){
          return "rmi://"+ip+":"+port+"/"+name;
     }
     
     /**
      * this method {@code rebind} the object.
      * this method bind the Remote object on specified port with specified name
        on local server.
      * this method will return URI to access the object remotely.
      * this method will return null if the exception occur while binding.<br>
      * Note:- make sure that the RMI registry is already created on specified
        port before method call, otherwise this method returns null without
        binding.
      * @param obj object to be binded
      * @param port port number on which it will be binded
      * @param name name
      * @return URI of the object if successfully binded, null otherwise.
      */
     public static String registerObj(Remote obj,int port,String name){
          try {
               String url=makeRMI(InetAddress.getLocalHost().getHostAddress(),port,name);
               new Thread(()->{
                    try {
                         //System.out.println("bind");
                         Naming.rebind(url, obj);
                    } catch (Exception ex) {
                         //System.out.println("bind exception");
                    }
               }).start();
               //System.out.println("bind suc :- "+obj);
               return url;
          } catch (Exception ex) {
               //System.out.println("reg exception");
               return null;
          }
     }
     
     /**
      * this method {@code rebind} the object.
      * this method bind the Remote object on specified port with specified name
        on local server.
      * this method will return URI to access the object remotely.
      * this method will return null if the exception occur while binding.<br>
      * Note:- make sure that the RMI registry is already created on specified
        port before method call, otherwise this method returns null without
        binding.
      * @param obj object to be binded
      * @param uri {@code uri} on which object going to be bind.
      * @return URI of the object if successfully binded, null otherwise.
      */
     public static boolean registerObj(Remote obj,String uri){
          try {
               new Thread(()->{
                    try {
                         String s;
                         /*if(uri.startsWith("rmi:")){
                              s=uri.replaceFirst("rmi:","");
                         }else*/
                              s=uri;
                         System.out.println("bind");
                         Naming.rebind(s, obj);
                    } catch (Exception ex) {
                         System.out.println("Register Thread Exception.."+ex);
                    }
               }).start();
               Thread.sleep(1000);
               System.out.println("bind suc :- "+obj);
               Remote r=Naming.lookup(uri);
               System.out.println("obj "+r);
               if(r!=null)
                    return true;
               else
                    return false;
          } catch (Exception ex) {
               System.out.println("reg exception");
               return false;
          }
     }
}
