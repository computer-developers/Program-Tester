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
                    case 1:addProgramDefination();
                              break;
                    case 2:showProgramDetail();
                              break;
                    case 3:showDefaultPath();
                              break;
                    case 0:return;
                    default: System.out.println("Invalid input");
               }
          }
     }
}
