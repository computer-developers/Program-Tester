/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.adminModule.start;

import java.util.Scanner;
import net.flow.dataSerFlow.MainDataSerFlow;

/**
 *
 * @author Neel Patel
 */
public class AdminStartDataServer {
     private static Scanner sc=new Scanner(System.in);
     public static synchronized void start(){
          System.out.println("\nMenu > Main Server > Start Server ..\n");
          System.out.println("Main Ser");
          MainDataSerFlow r=new MainDataSerFlow();
          r.start();
          r.join();
     }
}
