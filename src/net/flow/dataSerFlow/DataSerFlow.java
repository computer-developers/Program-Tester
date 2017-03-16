/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow.dataSerFlow;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import net.UrlTools;
import net.dataSer.DataSer;
import net.dataSer.IntDataSer;
import net.mainSer.IntMainSer;
import static programtester.config.Configuration.getDefaultDataSer;
import static programtester.config.Configuration.getDefaultMainSer;
import static programtester.config.Configuration.getDefaultRMIPort;

/**
 *
 * @author Neel Patel
 */
public class DataSerFlow {
     private Thread t=null;
     private Scanner sc=new Scanner(System.in);
     private boolean flag=false;
     private String mainSer,dataSer;
     private IntDataSer dataObj;
     private IntMainSer mainObj;
     private Registry r;
     private int port;
     public DataSerFlow(String url){
          mainSer=url;
     }
     
     public DataSerFlow(){
          mainSer=getDefaultMainSer();
     }
     
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
          try {
               mainObj=(IntMainSer)Naming.lookup(mainSer);
               dataSer=mainObj.getDataSer();
               dataObj=(IntDataSer)Naming.lookup(dataSer);
          } catch (Exception ex) {
               System.err.println("Remote Object access error");
               return false;
          }
          //code to write data on local.
          this.dataSer=getDefaultDataSer();
          this.port=getDefaultRMIPort();
          try{
               r=LocateRegistry.createRegistry(port);
          }catch(Exception ex){
               System.err.println("Registry fail");
          }
          try{
               if(!UrlTools.registerObj(dataObj,dataSer))
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
