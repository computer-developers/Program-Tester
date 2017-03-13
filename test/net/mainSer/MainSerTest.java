/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mainSer;

import net.logSer.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import net.UrlTools;
import static net.mainSer.MainSerClient.sc;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class MainSerTest {
     static Scanner sc=new Scanner(System.in);
     public static void main(String arg[]) throws RemoteException{
          Configurator.init();
          try{
               Registry r=LocateRegistry.createRegistry(8686);
          }catch(Exception e){}
          //System.out.println("sdckssnckjd");
          MainSer ms=new MainSer();
          //System.out.println("qqskqkq");
          String u=UrlTools.registerObj(ms,8686,"main");
          System.out.println("url = "+u);
          System.out.println("enter url of Logger :- ");
          String ipl="";
          for(ipl=sc.next();ipl.trim().isEmpty();ipl=sc.next());
          System.out.println("log register"+SerDetails.setLogSer(ipl));
          //System.out.println("sgfkspfkff");
          for(;sc.nextInt()!=0;)System.out.println();
          System.exit(0);
     }
}
