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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.UrlTools;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class MainSerClient {
     static Scanner sc=new Scanner(System.in);
     public static void main(String arg[]) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException{
          Configurator.init();
          System.out.println("enter ip, port ,name");
          String ip="";
          for(ip=sc.next();ip.trim().isEmpty();ip=sc.next());
          int port=sc.nextInt();
          String name="";
          for(name=sc.next();name.trim().isEmpty();name=sc.next());
          String ur=UrlTools.makeRMI(ip,port,name);
          System.out.println("Main url :- "+ur);
          IntMainSer ms=(IntMainSer)Naming.lookup(ur);
          String s=sc.next();
          for(int i=0;!s.equals("0");s=sc.next(),i++){
               if(i%5==0){
                    System.out.println("aya = "+ms.aya());
                    System.out.println("log = "+ms.getLogSer());
               }
               if(ms.getLogSer()!=null){
                    
                    Thread t=new Thread(()->{
                         try {
                              MainLogTest.run(ms.getLogSer());
                         } catch (RemoteException ex) {
                         }
                    });
                    t.start();
                    t.join();
                    break;
               } 
          }
          //p.destroyForcibly();
          System.exit(0);
     }
}
