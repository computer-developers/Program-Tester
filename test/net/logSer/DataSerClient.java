/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.logSer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import net.UrlTools;
import net.dataSer.IntDataSer;
import static programtester.config.Configuration.getDefaultMainDataSer;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class DataSerClient {
     static Scanner sc=new Scanner(System.in);
     public static void main(String arg[]) throws RemoteException, NotBoundException, MalformedURLException{
          Configurator.init();
          System.out.println("enter ip, port ,name");
          String ip="";
          for(ip=sc.next();ip.trim().isEmpty();ip=sc.next());
          int port=sc.nextInt();
          String name="";
          for(name=sc.next();name.trim().isEmpty();name=sc.next());
          String ur=UrlTools.makeRMI(ip,port,name);
          System.out.println("url :- "+ur);
          ur=getDefaultMainDataSer();
          IntDataSer rl=(IntDataSer)Naming.lookup(ur);
          String s=sc.next();
          System.out.println("aya = "+rl.aya());
          System.out.println("url = "+rl.toUrl());
          System.out.println("All Problem = "+rl.getAllProblems());
          System.out.println("All Test Cases = "+rl.getAllTestCases());
          System.out.println("Object = "+rl.getObject());
          System.out.println("Time = "+rl.getTime());
          
          //p.destroyForcibly();
          System.exit(0);
     }
}
