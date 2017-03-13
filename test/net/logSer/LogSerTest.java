/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.logSer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import net.UrlTools;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class LogSerTest {
     static Scanner sc=new Scanner(System.in);
     public static void main(String arg[]) throws RemoteException{
          Configurator.init();
          Registry r=LocateRegistry.createRegistry(8686);
          //System.out.println("sdckssnckjd");
          RemoteLog rl=new RemoteLog();
          //System.out.println("qqskqkq");
          String u=UrlTools.registerObj(rl,8686,"log");
          rl.setUrl(u);
          //System.out.println("sgfkspfkff");
          System.out.println("url = "+u);
          for(;sc.nextInt()!=0;)System.out.println();
          System.exit(0);
     }
}
