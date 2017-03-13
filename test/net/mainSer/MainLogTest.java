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
import net.logSer.IntRemoteLog;

/**
 *
 * @author Neel Patel
 */
public class MainLogTest {
     public static Scanner sc=new Scanner(System.in);
     public static void run(String ur){
          try {
               IntRemoteLog rl=(IntRemoteLog)Naming.lookup(ur);
               String s=sc.next();
               for(int i=0;!s.equals("0");s=sc.next(),i++){
                    if(i%5==0){
                         System.out.println("aya = "+rl.aya());
                         System.out.println("url = "+rl.toUrl());
                    }
                    System.out.println(rl.log(s));
                    
               }
               //p.destroyForcibly();
               System.exit(0);
          } catch (Exception ex) {
               System.out.println(ex.getMessage());
               ex.printStackTrace();
          }
     }
}
