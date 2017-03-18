/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow.dataSerFlow;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import net.UrlTools;
import net.dataSer.DataSer;
import net.dataSer.DataSerHandler;
import static programtester.config.Configuration.getDefaultMainDataSer;
import static programtester.config.Configuration.getDefaultRMIPort;

/**
 *
 * @author Neel Patel
 */
public class MainDataSerFlow {
     private Thread t=null;
     private Scanner sc=new Scanner(System.in);
     private boolean flag=false;
     private String dataSer;
     private DataSer remoteObj;
     private Registry r;
     private int port;
     public MainDataSerFlow(){}
     
     private void run(){
          if(!init())
               return;
          flag=true;
          for(;flag;){
               try{
                    System.out.println("enter 0 to stop server");
                    if(sc.nextInt()==0)
                         stop();
               }catch(Exception ex){ 
               }
          }
     }
     
     private boolean init(){
          this.dataSer=getDefaultMainDataSer();
          this.port=getDefaultRMIPort();
          try {
               System.out.println("start object creation");
               remoteObj=(DataSer)DataSerHandler.makeObject();
               System.out.println("end object creation");
          } catch (Exception ex) {
               System.err.println("Object creation error");
               return false;
          }
          try{
               r=LocateRegistry.createRegistry(port);
          }catch(Exception ex){
               System.err.println("Registry fail");
          }
          try{
               System.out.println("URI :- "+dataSer);
               if(!UrlTools.registerObj(remoteObj,dataSer))
                    throw new RemoteException();
               if(!remoteObj.setUrl(dataSer))
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
     
     public void join(){
          try{
               t.join();
          }catch(Exception ex){
          }
     }
     
     public void stop(){
          flag=false;
     }
}
