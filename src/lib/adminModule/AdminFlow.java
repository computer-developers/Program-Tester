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