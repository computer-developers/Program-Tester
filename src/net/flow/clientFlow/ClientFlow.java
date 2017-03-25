/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/
package net.flow.clientFlow;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
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
     //local
     private Thread t=null;
     private Consumer<String> errRun=x->{},messageRun=x->{};
     private Scanner sc=new Scanner(System.in);
     private boolean flag=false;
     private String mainSer;
     private String uName="",passwd="";
     private Registry r;
     private int port;
     //remote reference
     private IntMainSer mainObj;
     private IntRemoteLog logOb;
     public ClientFlow(){}
     
     private void run(){
          flag=true;
          for(;flag;){
               try{
                    try {
                         Thread.sleep(1000);
                    } catch (InterruptedException ex) {}
                    if(!mainObj.aya()){
                         assert true:"main server says not alive";
                         return;
                    }
                    if(!logOb.aya()){
                         assert true:"log server says not alive";
                         return;
                    }
               }catch(RemoteException ex){
                    errRun.accept("Servers not Connected !!");
                    return;
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
          this.uName=uName;
          this.passwd=passwd;
          try{
               System.out.println("Main ser..."+mainSer);
               mainObj=(IntMainSer)Naming.lookup(mainSer);
               System.out.println("main object obtained");
               IntUserStatus u=mainObj.getStatus(uName, passwd);
               System.out.println("user status :- "+u);
               if(u==null){
                    errRun.accept("wrong username or Password");
                    return false;
               }
          }catch(Exception ex){
               System.err.println("Error in getting reference of Remote."+ex);
               ex.printStackTrace();
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
               System.out.println("data ser flow running");
               d.start();
               //d.join();
               System.out.println("before server error");
               //code for register User State as a backup logger.
               //IntRemoteLog rg=(IntRemoteLog)Naming.lookup(mainLogSer);
               //rg.setBackupLogger(UserFactory.init(mainLogSer));
          } catch (Exception ex) {
               System.err.println("Server error..."+ex);
               return false;
          }
          start();
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
          try{
               if(logOb==null)
                    return false;
               if(!logOb.aya())
                    assert true:"log server says not alive";
               return logOb.log(s);
          }catch(Exception ex){
               return false;
          }
     }

     @Override
     public int credit(long pid){
          try {
               if(mainObj==null)
                    return -1;
               if(!mainObj.aya())
                    assert true:"log server says not alive";
               IntUserStatus u=mainObj.getStatus(uName, passwd);
               return u.getAllProStatus().get(pid);
          } catch (Exception ex) {
               System.err.println("credit error");
               return -1;
          }
     }

     @Override
     public int userCredit() {
          try {
               if(mainObj==null){
                    System.err.println("mainObj null");
                    return -1;
               }
               if(!mainObj.aya())
                    assert true:"log server says not alive";
               IntUserStatus u=mainObj.getStatus(uName, passwd);
               System.err.println("u = "+u);
               return u.getUserCredit();
          } catch (Exception ex) {
               System.err.println("user credit error");
               return -1;
          }
     }

     @Override
     public Map<Long, Integer> getAllStatus() {
          try {
               if(mainObj==null)
                    return null;
               if(!mainObj.aya())
                    assert true:"log server says not alive";
               System.out.println("name pass "+uName+";"+passwd);
               IntUserStatus u=mainObj.getStatus(uName, passwd);
               System.out.println("user status :- "+u);
               return u.getAllProStatus();
          } catch (Exception ex) {
               System.err.println("status error :- "+ex);
               ex.printStackTrace();
               return null;
          }
     }
}
