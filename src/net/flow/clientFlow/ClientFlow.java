/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow.clientFlow;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.function.Consumer;
import net.UrlTools;
import net.flow.dataSerFlow.DataSerFlow;
import net.logSer.IntRemoteLog;
import net.mainSer.IntMainSer;
import net.mainSer.userStatus.IntUserStatus;
import static programtester.config.Configuration.getDefaultMainSer;

/**
 *
 * @author Neel Patel
 */
public class ClientFlow implements IntNetClient{
     private Thread t=null;
     private Consumer<String> errRun=x->{},messageRun=x->{};
     private Scanner sc=new Scanner(System.in);
     private boolean flag=false;
     private String mainSer;
     private IntMainSer mainObj;
     private IntRemoteLog logOb;
     private Registry r;
     private int port;
     private String uName="",passwd="";
     public ClientFlow(){}
     
     private void run(){
          flag=true;
          for(;flag;){
               try{
                    Thread.sleep(1000);
               }catch(Exception ex){ 
               }
          }
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

     @Override
     public boolean init(String uName,String passwd){
          this.mainSer=getDefaultMainSer();
          try{
               mainObj=(IntMainSer)Naming.lookup(mainSer);
               IntUserStatus u=mainObj.getStatus(uName, passwd);
               if(u!=null){
                    errRun.accept("wrong username or Password");
                    return false;
               }
          }catch(Exception ex){
               System.err.println("Error in getting reference of Remote.");
               return false;
          }
          try {
               logOb=(IntRemoteLog)Naming.lookup(mainObj.getLogSer());
               if(logOb==null)
                    return false;
               if(!logOb.aya()){
                    assert true:"logger says not alive";
                    return false;
               }
               DataSerFlow d=new DataSerFlow(mainSer);
               d.start();
               d.join();
               //code for register User State as a backup logger.
               //IntRemoteLog rg=(IntRemoteLog)Naming.lookup(mainLogSer);
               //rg.setBackupLogger(UserFactory.init(mainLogSer));
          } catch (Exception ex) {
               System.err.println("Server error error");
               return false;
          }
          t=new Thread(()->start());
          return true;
     }
     
     @Override
     public boolean regErrRunner(Consumer r) {
          errRun=r::accept;
          return true;
     }

     @Override
     public boolean regMessageRunner(Consumer r) {
          messageRun=r::accept;
          return true;
     }

     @Override
     public boolean log(String s) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public int credit(long pid) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     @Override
     public int userCredit() {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
}
