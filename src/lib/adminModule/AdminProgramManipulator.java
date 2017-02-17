/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.adminModule;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.dT.problemManipulate.IntProgramDetail;
import lib.dT.problemManipulate.ProgramDetails;

/**
 *
 * @author admin
 */
public class AdminProgramManipulator {
     private static Scanner sc=new Scanner(System.in);
     public static synchronized void start(){
          while(true){
               System.out.println("\nMenu > Program Defination manipulation..\n"
                         + "1.add Program Defination\n"
                         + "2.show Program Detail\n"
                         + "3.show default Path\n"
                         + "0.exit from Program Defination manipulation\n");
               switch(sc.nextInt()){
                    case 1:System.out.println("Enter Program ID..");
                              long p=sc.nextLong();
                              System.out.println("Enter command");
                              String s;
                              for(s=sc.nextLine();s.isEmpty();s=sc.nextLine());
                              break;
                    case 2:   break;
                    case 0:return;
                    default: System.out.println("Invalid input");
               }
          }
     }
     
     public static void addProgramDefination(){
          try {
               Path dir=ProgramDetails.getDefaultDir();
               System.out.println("enter file name = ");
               String s;
               for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
               IntProgramDetail x=ProgramDetails.parseProgramDetail(dir.resolve(s));
               System.out.println("\nProgram Id :\t"+x.getProgramID());
               System.out.println("\nTitle :\t\t"+x.getTitle());
               System.out.println("\nDescription :-");
               x.getDescription().forEach(j->System.out.println(j));
               System.out.println("\nInput :\t\t"+x.getInput());
               System.out.println("\nOutput :\t"+x.getOutput());
               System.out.println("\nSample Input :-");
               x.getSampleInput().forEach(j->System.out.println(j));
               System.out.println("\nSample Output :-");
               x.getSampleOutput().forEach(j->System.out.println(j));
               System.out.println("\nCredit : "+x.getCredit());
               System.out.println("Do you want to save ?\n1.Yes\n0.No");
               int i;
               for(i=sc.nextInt();i>=0&&i<=1;i=sc.nextInt())
                    System.out.println("invalid!!");
               if(sc.nextInt()==1)
                    ProgramDetails.writeIntProgramDetail(x);
          } catch (IOException ex) {
               System.out.println("Error :- "+ex.getMessage());
          }
     }
     
     public static void showProgramDetail(){
          try {
               Path dir=ProgramDetails.getDefaultDir();
               System.out.println("Program Directory = " + dir);
               System.out.println("enter file name = ");
               String s="";
               for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
               IntProgramDetail x=ProgramDetails.readProgramDetail(dir.resolve(s));
               System.out.println("\nProgram Id :\t"+x.getProgramID());
               System.out.println("\nTitle :\t\t"+x.getTitle());
               System.out.println("\nDescription :-");
               x.getDescription().forEach(j->System.out.println(j));
               System.out.println("\nInput :\t\t"+x.getInput());
               System.out.println("\nOutput :\t"+x.getOutput());
               System.out.println("\nSample Input :-");
               x.getSampleInput().forEach(j->System.out.println(j));
               System.out.println("\nSample Output :-");
               x.getSampleOutput().forEach(j->System.out.println(j));
               System.out.println("\nCredit : "+x.getCredit());
               System.out.println("Do you want to save ?\n1.Yes\n0.No");
               int i;
               for(i=sc.nextInt();i>=0&&i<=1;i=sc.nextInt())
                    System.out.println("invalid!!");
               if(sc.nextInt()==1)
                    System.out.println(ProgramDetails.writeToTxt(x));
          } catch (IOException ex) {
               System.out.println("Error :- "+ex.getMessage());
          }
     }
     
     public static void showDefaultPath(){
          
     }
}
