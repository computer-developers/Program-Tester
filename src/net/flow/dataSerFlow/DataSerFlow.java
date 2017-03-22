/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow.dataSerFlow;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Comparator;
import java.util.Scanner;
import net.UrlTools;
import net.dataSer.DataSer;
import net.dataSer.IntDataSer;
import net.mainSer.IntMainSer;
import static programtester.config.Configuration.getDefaultDataSer;
import static programtester.config.Configuration.getDefaultDir;
import static programtester.config.Configuration.getDefaultMainSer;
import static programtester.config.Configuration.getDefaultProDir;
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
     
     public static boolean writeData(IntDataSer ds){
          try {
               Path pd=getDefaultProDir();
               Path td=getDefaultDir();
               cleanDir(pd);
               ds.getAllProblems().forEach((n,d)->{
                    Path p=pd.resolve(n);
                    try {
                         Files.write(p, d, StandardOpenOption.TRUNCATE_EXISTING,
                                 StandardOpenOption.CREATE);
                    } catch (Exception ex) {
                    }
               });
               if(ds.getAllProblems().size()!=Files.list(pd)
                       .filter(i->!Files.isDirectory(i)).count())
                    return false;
               cleanDir(td);
               ds.getAllTestCases().forEach((n,d)->{
                    Path p=td.resolve(n);
                    try {
                         Files.write(p, d, StandardOpenOption.TRUNCATE_EXISTING,
                                 StandardOpenOption.CREATE);
                         System.out.println("write fail");
                    } catch (Exception ex) {
                    }
               });
               if(ds.getAllTestCases().size()!=Files.list(td)
                       .peek(System.out::println)
                       .filter(i->!Files.isDirectory(i)).count()){
                    return false;
               }
               System.out.println("write done");
               return true;
          } catch (Exception ex) {
               return false;
          }
     }
     
     public static boolean cleanDir(Path dir){
          try {
               Files.createDirectories(dir);
               Files.walk(dir,1)
                       //.sorted(Comparator.reverseOrder())
                       .filter(i->!Files.isDirectory(i))
                       .map(Path::toFile)
                       //.peek(System.out::println)
                       .forEach(File::delete);
               Files.createDirectories(dir);
          } catch (Exception ex) {
               return false;
          }
          return true;
     }
     
     public DataSerFlow(String mainUrl){
          mainSer=mainUrl;
          System.out.println("Data ser const");
     }
     
     public DataSerFlow(){
          mainSer=getDefaultMainSer();
          System.out.println("Data ser const 2");
     }
     
     private void run(){
          flag=true;
          for(;flag;){
               try{
                    if(!mainObj.aya())
                         break;
                    Thread.sleep(10000);
               }catch(Exception ex){ 
                    break;
               }
          }
     }
     
     private boolean init(){
          try {
               System.out.println("start data ser");
               mainObj=(IntMainSer)Naming.lookup(mainSer);
               dataSer=mainObj.getDataSer();
               dataObj=(IntDataSer)Naming.lookup(dataSer);
               dataObj=dataObj.getObject();
               System.out.println("object reference done");
          } catch (Exception ex) {
               System.err.println("Remote Object access error.."+ex);
               ex.printStackTrace();
               return false;
          }
          if(!writeData(dataObj)){
               System.err.println("error in getting data");
               return false;
          }
          System.out.println("writing successfully");
          this.dataSer=getDefaultDataSer();
          this.port=getDefaultRMIPort();
          try{
               r=LocateRegistry.createRegistry(port);
          }catch(Exception ex){
               System.err.println("Registry fail");
          }
          try{
               System.out.println("data server bind :- "+dataSer);
               if(!UrlTools.registerObj(dataObj,dataSer))
                    throw new RemoteException();
               dataObj.setUrl(dataSer);
          }catch(Exception ex){
               System.out.println("Object Binding fail.."+ex);
               ex.printStackTrace();
               return false;
          }
          try {
               if(mainObj.registerDataSer(dataSer))
                    System.out.println("registration done");
          } catch (Exception ex) {
               System.out.println("Object registration fail");
               return false;
          }
          return true;
     }
     
     public void join(){
          try{
               t.join();
          }catch(Exception ex){
          }
     }
     
     public void start(){
          System.out.println("data ser run");
          if(t!=null&&t.isAlive())
               return;
          if(!init())
               return;
          t=new Thread(this::run);
          t.start();
     }
     
     public void stop(){
          flag=false;
     }
     
}
