/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.adminModule;

import java.nio.file.Paths;
import java.util.Scanner;
import lib.adminModule.start.AdminStartDataServer;
import static programtester.config.Configuration.getDefaultDir;
import static programtester.config.Configuration.getDefaultMainDataSer;
import static programtester.config.Configuration.getDefaultProDir;
import static programtester.config.Configuration.setDefaultDir;
import static programtester.config.Configuration.setDefaultMainDataSer;
import static programtester.config.Configuration.setDefaultProDir;

/**
 *
 * @author Neel Patel
 */
public class AdminMainDataServer {
     private static Scanner sc=new Scanner(System.in);
     public static synchronized void start(){
          while(true){
               System.out.println("\nMenu > Main Data Server..\n"
                         + "1.show default Paths & URI\n"
                         + "2.set defaults\n"
                         + "3.start Server\n"
                         + "0.exit from Exit from Main Data Server\n");
               switch(sc.nextInt()){
                    case 1:defPathsURI();
                              break;
                    case 2:setPathsURI();
                              break;
                    case 3:AdminStartDataServer.start();
                              break;
                    case 0:return;
                    default: System.out.println("Invalid input");
               }
          }
     }
     
     private static void defPathsURI(){
          System.out.println("Working Directory = " +System.getProperty("user.dir"));
          System.out.println("Data Directory = " +getDefaultDir());
          System.out.println("Program Directory = " +getDefaultProDir());
          System.out.println("Main Data Server Url = " +getDefaultMainDataSer());
     }
     
     private static void setPathsURI(){
          System.out.println("\nMenu > Main Data Server..\n"
                         + "1.set Data Directory\n"
                         + "2.set Program Directory\n"
                         + "3.set Main Data Server Url\n"
                         + "0.exit from Exit from Data Server\n");
          String s="";
          switch(sc.nextInt()){
               case 1:for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                      setDefaultDir(Paths.get(s).toAbsolutePath());
                         break;
               case 2:s="";
                      for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                      setDefaultProDir(Paths.get(s).toAbsolutePath());
                         break;
               case 3:s="";
                      for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                      setDefaultMainDataSer(s);
                         break;
               case 0:return;
               default: System.out.println("Invalid input");
          }
     }
}
