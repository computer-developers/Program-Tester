package lib.adminModule;

import java.util.Scanner;
import lib.dT.problemManipulate.ProgramDetails;
import lib.userModule.test.Test;

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
                         + "0.Exit\n");
               switch(sc.nextInt()){
                    case 1:disPath();
                              break;
                    case 2:AdminDataManipulator.start();
                              break;
                    case 3:AdminProgramManipulator.start();
                              break;
                    case 0:return;
                    default: System.out.println("Invalid input");
               }
          }
     }
     public static void disPath(){
          System.out.println("Working Directory = " +System.getProperty("user.dir"));
          System.out.println("Data Directory = " +Test.getDefaultDir());
          System.out.println("Program Directory = " +ProgramDetails.getDefaultDir());
     }
}