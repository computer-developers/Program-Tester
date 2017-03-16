/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow.clientFlow;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import net.UrlTools;
import net.logSer.IntRemoteLog;
import net.mainSer.MainSer;
import net.mainSer.SerDetails;
import net.mainSer.userStatus.UserFactory;
import static programtester.config.Configuration.getDefaultMainDataSer;
import static programtester.config.Configuration.getDefaultMainLogSer;
import static programtester.config.Configuration.getDefaultMainSer;
import static programtester.config.Configuration.getDefaultRMIPort;

/**
 *
 * @author Neel Patel
 */
public class ClientFlow {
     private Thread t=null;
     private Scanner sc=new Scanner(System.in);
     private boolean flag=false;
     private String mainSer,mainDataSer,mainLogSer,dataSer;
     private MainSer remoteObj;
     private Registry r;
     private int port;
     public ClientFlow(){}
     
     private void run(){
          if(!init())
               return;
          flag=true;
          System.out.println("enter 0 to exit");
          for(;flag;){
               try{
                    if(sc.nextInt()==0)
                    break;
               }catch(Exception ex){ 
               }
          }
     }
     private boolean init(){
          this.mainSer=getDefaultMainSer();
          try{
               r=LocateRegistry.createRegistry(port);
          }catch(Exception ex){
               System.err.println("Registry fail");
          }
          try {
               remoteObj=new MainSer();
               if(SerDetails.registerMainDataSer(mainDataSer))
                    throw new RemoteException();
               if(SerDetails.setLogSer(mainLogSer))
                    throw new RemoteException();
               //code for register User State as a backup logger.
               IntRemoteLog rg=(IntRemoteLog)Naming.lookup(mainLogSer);
               rg.setBackupLogger(UserFactory.init(mainLogSer));
          } catch (Exception ex) {
               System.err.println("Object creation error");
               return false;
          }
          try{
               if(!UrlTools.registerObj(remoteObj,mainSer))
                    throw new RemoteException();
          }catch(Exception ex){
               System.out.println("Object Binding fail");
               return false;
          }
          return true;
     }
     
     public void start(){
          if(t!=null&&t.isAlive())
               return;
          t=new Thread(this::run);
          t.start();
     }
     
     public void stop(){
          flag=false;
     }
}
