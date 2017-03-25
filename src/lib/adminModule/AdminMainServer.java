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
package lib.adminModule;

import lib.adminModule.start.AdminStartMainServer;
import java.nio.file.Paths;
import java.util.Scanner;
import static programtester.config.Configuration.getDefaultDir;
import static programtester.config.Configuration.getDefaultMainDataSer;
import static programtester.config.Configuration.getDefaultMainLogSer;
import static programtester.config.Configuration.getDefaultMainSer;
import static programtester.config.Configuration.getDefaultProDir;
import static programtester.config.Configuration.setDefaultDir;
import static programtester.config.Configuration.setDefaultMainDataSer;
import static programtester.config.Configuration.setDefaultMainLogSer;
import static programtester.config.Configuration.setDefaultMainSer;
import static programtester.config.Configuration.setDefaultProDir;

/**
 *
 * @author Neel Patel
 */
public class AdminMainServer {
     private static Scanner sc=new Scanner(System.in);
     public static synchronized void start(){
          while(true){
               System.out.println("\nMenu > Main Server..\n"
                         + "1.show default Paths & URI\n"
                         + "2.set defaults\n"
                         + "3.start Server\n"
                         + "0.exit from Exit from Main Server\n");
               switch(sc.nextInt()){
                    case 1:defPathsURI();
                              break;
                    case 2:setPathsURI();
                              break;
                    case 3:AdminStartMainServer.start();
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
          System.out.println("Main Server Url = " +getDefaultMainSer());
          System.out.println("Main Data Server Url = " +getDefaultMainDataSer());
          System.out.println("Main Log Server Url = " +getDefaultMainLogSer());
     }
     
     private static void setPathsURI(){
          System.out.println("\nMenu > Main Server..\n"
                         + "1.set Data Directory\n"
                         + "2.set Program Directory\n"
                         + "3.set Main Server Url\n"
                         + "4.set Main Data Server Url\n"
                         + "5.set Main Log Server Url\n"
                         + "0.exit from Exit from Main Server\n");
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
                      setDefaultMainSer(s);
                         break;
               case 4:s="";
                      for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                      setDefaultMainDataSer(s);
                         break;
               case 5:s="";
                      for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                      setDefaultMainLogSer(s);
                         break;
               case 0:return;
               default: System.out.println("Invalid input");
          }
     }
}
