/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.adminModule;

import java.util.Scanner;
import static lib.adminModule.AdminProgramManipulator.addProgramDefination;
import static lib.adminModule.AdminProgramManipulator.showDefaultPath;
import static lib.adminModule.AdminProgramManipulator.showProgramDetail;
import net.flow.mainSerFlows.MainSerFlow;

/**
 *
 * @author Neel Patel
 */
public class AdminStartServer {
     private static Scanner sc=new Scanner(System.in);
     public static synchronized void start(){
          System.out.println("\nMenu > Main Server > Start Server ..\n");
          System.out.println("Main Ser");
          MainSerFlow r=new MainSerFlow();
          r.start();
          r.join();
     }
}
