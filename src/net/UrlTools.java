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
     public static String makeRMI(String ip,int port,String name){
          return "rmi://"+ip+":"+port+"/"+name;
     }
     
     public static String registerObj(Remote obj,int port,String name){
          try {
               String url=makeRMI(InetAddress.getLocalHost().getHostAddress(),port,name);
               new Thread(()->{
                    try {
                         System.out.println("bind");
                         Naming.rebind(url, obj);
                    } catch (Exception ex) {
                         System.out.println("bind exception");
                    }
               }).start();
               System.out.println("bind suc :- "+obj);
               return url;
          } catch (Exception ex) {
               System.out.println("reg exception");
               return null;
          }
     }
}
