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

import java.nio.file.Paths;
import java.util.Scanner;
import lib.adminModule.start.AdminStartLogServer;
import static programtester.config.Configuration.getDefaultLogDir;
import static programtester.config.Configuration.getDefaultMainLogSer;
import static programtester.config.Configuration.setDefaultLogDir;
import static programtester.config.Configuration.setDefaultMainLogSer;

/**
 *
 * @author Neel Patel
 */
public class AdminMainLogServer {
     private static Scanner sc=new Scanner(System.in);
     public static synchronized void start(){
          while(true){
               System.out.println("\nMenu > Main Log Server..\n"
                         + "1.show default Paths & URI\n"
                         + "2.set defaults\n"
                         + "3.start Server\n"
                         + "0.exit from Exit from Main Server\n");
               switch(sc.nextInt()){
                    case 1:defPathsURI();
                              break;
                    case 2:setPathsURI();
                              break;
                    case 3:AdminStartLogServer.start();
                              break;
                    case 0:return;
                    default: System.out.println("Invalid input");
               }
          }
     }
     
     private static void defPathsURI(){
          System.out.println("Working Directory = " +System.getProperty("user.dir"));
          System.out.println("Log Directory = " +getDefaultLogDir());
          System.out.println("Log Server Url = " +getDefaultMainLogSer());
     }
     
     private static void setPathsURI(){
          System.out.println("\nMenu > Main Server..\n"
                         + "1.set Log Directory\n"
                         + "2.set Main Log Server Url\n"
                         + "0.exit from Exit from Log Server\n");
          String s="";
          switch(sc.nextInt()){
               case 1:for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                      setDefaultLogDir(Paths.get(s).toAbsolutePath());
                         break;
               case 2:s="";
                      for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                      setDefaultMainLogSer(s);
                         break;
               case 0:return;
               default: System.out.println("Invalid input");
          }
     }
}
