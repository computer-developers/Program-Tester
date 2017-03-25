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

import java.util.Scanner;
import programtester.config.Configuration;
import static programtester.config.Configuration.getDefaultProDir;

/**
 *
 * @author Neel Patel
 */
public class AdminFlow {
     private Scanner sc=new Scanner(System.in);
     public synchronized void start(){
          while(true){
               System.out.println("\nMenu..\n"
                         + "1.Display Paths\n"
                         + "2.Program Defination manipulation\n"
                         + "3.Source Data manipulation\n"
                         + "4.Log Analysis\n"
                         + "5.Main Server\n"
                         + "6.Main Data Server\n"
                         + "7.Main Log Server\n"
                         + "0.Exit\n");
               switch(sc.nextInt()){
                    case 1:disPath();
                              break;
                    case 2:AdminProgramManipulator.start();
                              break;
                    case 3:AdminDataManipulator.start();
                              break;
                    case 4:AdminLogAnalyser.start();
                              break;
                    case 5:AdminMainServer.start();
                              break;
                    case 6:AdminMainDataServer.start();
                              break;
                    case 7:AdminMainLogServer.start();
                              break;
                    case 0:return;
                    default: System.out.println("Invalid input");
               }
          }
     }
     
     public static void disPath(){
          System.out.println("Working Directory = " +System.getProperty("user.dir"));
          System.out.println("Data Directory = " +Configuration.getDefaultDir());
          System.out.println("Program Directory = " +getDefaultProDir());
     }
}