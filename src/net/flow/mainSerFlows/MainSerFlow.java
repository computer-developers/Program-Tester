package net.flow.mainSerFlows;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import net.UrlTools;
import net.logSer.IntRemoteLog;
import net.mainSer.MainSer;
import net.mainSer.SerDetails;
import net.mainSer.userStatus.IntUserStatus;
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
          for(;flag;){
               System.out.println("\nMenu..\n"
                         + "1.get Main Data Server\n"
                         + "2.get all Data Servers\n"
                         + "3.get log Server\n"
                         + "4.get all Programs\n"
                         + "5.get all Users\n"
                         + "6.get Users Details\n"
                         + "0.Stop Server\n");
               switch(sc.nextInt()){
                    case 1:System.out.println("Main Server URI :- "
                            +SerDetails.getMainDataSer());
                              break;
                    case 2:System.out.println("Data Servers...");
                              SerDetails.getAllDataSer()
                                   .forEach(i->System.out.println(i));
                              break;
                    case 3:System.out.println("Log Server URI :- "
                            +SerDetails.getLogSer());
                              break;
                    case 4:System.out.println("Program list...");
                              UserFactory.getAllProblems()
                                   .forEach(i->{
                                           System.out.println(i.getProgramID()
                                                   +"\t"+i.getCredit());
                                   });
                              break;
                    case 5:System.out.println("Users list...");
                              UserFactory.getAllUser()
                                   .forEach((i)->System.out.println(i));
                              break;
                    case 6:System.out.println("Enter User name...");
                              String s;
                              for(s=sc.next();s.trim().isEmpty();s=sc.next());
                              IntUserStatus u=UserFactory.getUser(s);
                              System.out.println("Password :- "+u.getPasswd());
                              System.out.println("Total Credit :- "+u.getUserCredit());
                              System.out.println("problem details...");
                              u.getAllProStatus()
                                      .forEach((x,y)->System.out.println(x+"\t"+y));
                              break;
                    case 0:stop();return;
                    default: System.out.println("Invalid input");
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
               System.out.println("Main Data Ser URI :- "+mainDataSer);
               if(!SerDetails.registerMainDataSer(mainDataSer)){
                    System.out.println("DataSer Registration fail");
                    throw new RemoteException();
               }
               if(!SerDetails.setLogSer(mainLogSer)){
                    System.out.println("LogSer Registration fail");
                    throw new RemoteException();
               }
               //code for register User State as a backup logger.
               IntRemoteLog rg=(IntRemoteLog)Naming.lookup(mainLogSer);
               if(!rg.setBackupLogger(UserFactory.init(mainLogSer))){
                    System.out.println("UserStatus Log registration fail.");
                    return false;
               }
          } catch (Exception ex) {
               System.err.println("Object creation error.."+ex);
               return false;
          }
          try{
               System.out.println("URI :- "+mainSer);
               System.out.println("Enter 1 to bind the Server...");
               if(sc.nextInt()!=1)
                    return false;
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
