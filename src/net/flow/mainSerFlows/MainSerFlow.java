package net.flow.mainSerFlows;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.stream.Collectors;
import net.UrlTools;
import net.logSer.IntRemoteLog;
import net.mainSer.MainSer;
import net.mainSer.SerDetails;
import net.mainSer.userStatus.UserFactory;
import static programtester.config.Configuration.*;

/**
 *
 * @author Neel Patel
 */
public class MainSerFlow {
     private Thread t=null;
     private Scanner sc=new Scanner(System.in);
     private boolean flag=false;
     private String mainSer,mainDataSer,mainLogSer;
     private MainSer remoteObj;
     private Registry r;
     private int port;
     public MainSerFlow(){}
     
     private void run(){
          if(!init())
               return;
          flag=true;
          System.out.println(UserFactory.getAllProblems().keySet()
                  .stream().collect(Collectors.toList()));
          System.out.println(UserFactory.getAllUser());
          System.out.println("enter 0 to stop server");
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
          this.mainDataSer=getDefaultMainDataSer();
          this.mainLogSer=getDefaultMainLogSer();
          this.port=getDefaultRMIPort();
          try{
               r=LocateRegistry.createRegistry(port);
          }catch(Exception ex){
               System.err.println("Registry fail");
          }
          try {
               remoteObj=new MainSer();
               System.out.println("Main Data Ser :- "+mainDataSer);
               if(!SerDetails.registerMainDataSer(mainDataSer)){
                    System.out.println("DataSer fail");
                    throw new RemoteException();
               }
               if(!SerDetails.setLogSer(mainLogSer)){
                    System.out.println("LogSer fail");
                    throw new RemoteException();
               }
               //code for register User State as a backup logger.
               IntRemoteLog rg=(IntRemoteLog)Naming.lookup(mainLogSer);
               if(!rg.setBackupLogger(UserFactory.init(mainLogSer)))
                    System.out.println("UserStatus Log registration fail.");
          } catch (Exception ex) {
               System.err.println("Object creation error.."+ex);
               //return false;
          }
          try{
               System.out.println("URI :- "+mainSer);
               if(!UrlTools.registerObj(remoteObj,mainSer))
                    throw new RemoteException();
          }catch(Exception ex){
               System.out.println("Object Binding fail.."+ex);
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
