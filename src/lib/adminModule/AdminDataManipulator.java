/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.adminModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import lib.dT.inputGenerator.InputGen;
import lib.dT.manipulate.comparators.ListManipulator;
import lib.dT.manipulate.comparators.StringComparators;
import lib.runDetails.IOManager;
import lib.runDetails.IntIODetail;
import lib.runTest.RunTest;
import lib.userModule.test.Test;
import programtester.config.Configuration;

/**
 *
 * @author admin
 */
public class AdminDataManipulator {
     private static Scanner sc=new Scanner(System.in);
     public static synchronized void start(){
          while(true){
               System.out.println("\nMenu > Source Data manipulation..\n"
                         + "1.Create Test Case\n"
                         + "2.Decrypt Test Case\n"
                         + "3.Modify Test Case\n"
                         + "4.Generate Input Using R.E.\n"
                         + "5.Generate Output Using Original Program\n"
                         + "6.Compare Outputs\n"
                         + "7.Show Default Source Path\n"
                         + "0.Exit from Data Source manipulation\n");
               switch(sc.nextInt()){
                    case 1:   createTestCase();break;
                    case 2:   decryptTestCase();break;
                    case 3:   modifyTestCase();break;
                    case 4:   generateInput();break;
                    case 5:   generateOutput();break;
                    case 6:   compareOutputs();break;
                    case 7:   defaultPath();break;
                    case 0:return;
                    default: System.out.println("Invalid input!!!");
               }
          }
     }
     
     public static void createTestCase(){
          try {
               IntIODetail io;
               Path dir=Configuration.getDefaultDir();
               Path pi;
               System.out.println("Program id :-");
               long programID=sc.nextLong();
               System.out.println("index :-");
               int index=sc.nextInt();
               //System.out.println("encription ? (true | false) :-");
               //boolean b=sc.nextBoolean();
               String in;
               System.out.println("input file :- ");
               for(in=sc.nextLine();in.trim().isEmpty();in=sc.nextLine());
               String out;
               System.out.println("output file :- ");
               for(out=sc.nextLine();out.trim().isEmpty();out=sc.nextLine());
               pi=dir.resolve(in);
               //System.out.println("input :- "+pi);
               List<String> input=Files.readAllLines(pi);
               pi=dir.resolve(out);
               //System.out.println("out :- "+pi);
               List<String> output=Files.readAllLines(pi);
               io=IOManager.getIODetail(input, output,-1,programID,index);
               System.out.println(IOManager.writeIntIODetail(io, dir,true));
          } catch (IOException ex) {
               System.out.println("Error :- "+ex.getMessage());
          }
     }
     
     public static void decryptTestCase(){
          try{
               Path dir=Configuration.getDefaultDir();
               System.out.println("enter file name:-");
               String s;
               for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
               //System.out.println("encript ? :-");
               //boolean b=sc.nextBoolean();
               String in;
               System.out.println("input file :- ");
               for(in=sc.nextLine();in.trim().isEmpty();in=sc.nextLine());
               String out;
               System.out.println("output file :- ");
               for(out=sc.nextLine();out.trim().isEmpty();out=sc.nextLine());
               //System.out.println("input :- "+in);
               //System.out.println("out :- "+out);
               IntIODetail io;
               Path pi;
               pi=dir.resolve(s).toAbsolutePath();
               io=IOManager.getIODetail(pi,true);
               pi=dir.resolve(in);
               Files.deleteIfExists(pi);
               Files.write(pi,io.getAllInput(),StandardOpenOption.CREATE_NEW);
               pi=dir.resolve(out);
               Files.deleteIfExists(pi);
               Files.write(pi,io.getAllOutput(),StandardOpenOption.CREATE_NEW);
          }catch(IOException e){
               System.out.println("Error :- "+e.getMessage());
          }
     }
     
     public static void modifyTestCase(){
          try {
               Path dir=Configuration.getDefaultDir();
               System.out.println("enter file name:-");
               String s;
               for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
               //System.out.println("encript ? :-");
               //boolean b=sc.nextBoolean();
               Path pio;
               pio=dir.resolve(s).toAbsolutePath();
               IntIODetail io;
               io=IOManager.getIODetail(pio,true);
               System.out.println("Program id :-");
               long programID=sc.nextLong();
               System.out.println("index :-");
               int index=sc.nextInt();
               //System.out.println("encription ? (true | false) :-");
               //boolean b=sc.nextBoolean();
               IntIODetail nio;
               nio=IOManager.getIODetail(io.getAllInput(),io.getAllOutput()
                       ,-1,programID,index);
               System.out.println(IOManager.writeIntIODetail(nio, dir,true));
          } catch (IOException ex) {
               System.out.println("Error :- "+ex.getMessage());
          }
     }
     
     public static void generateInput(){
          try {
               Path dir=Configuration.getDefaultDir();
               System.out.println("enter file name:-");
               String s;
               for(s=sc.nextLine();s=="";s=sc.nextLine());
               Path p=dir.resolve(s);
               System.out.println("number of regx...");
               int n=sc.nextInt();
               String ar[]=new String[n];
               for(int i=0;i<n;i++){
                    System.out.println("enter regx:-");
                    s=null;
                    for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
                    ar[i]=s;
               }
               System.out.println("counts :- ");
               int c=sc.nextInt();
               List<String> lines= InputGen.getInput(c,ar);
               Files.write(p, lines,StandardOpenOption.CREATE_NEW);
          } catch (IOException ex) {
               System.out.println("Error :- "+ex.getMessage());
          }
     }
     
     public static void generateOutput(){
          try {
               Path dir=Configuration.getDefaultDir();
               String com;
               System.out.println("command :- ");
               for(com=sc.nextLine();com=="";com=sc.nextLine());
               String in;
               System.out.println("input file :- ");
               for(in=sc.nextLine();in=="";in=sc.nextLine());
               String out;
               System.out.println("output file :- ");
               for(out=sc.nextLine();out=="";out=sc.nextLine());
               Path pi;
               pi=dir.resolve(in);
               List<String> input=Files.readAllLines(pi);
               System.out.println("input read completed.");
               RunTest rt=new RunTest(IOManager.getInput(input),com);
               System.out.println("process start.");
               IntIODetail obj=rt.getIODetail();
               System.out.println("process completed.");
               pi=dir.resolve(out);
               Files.deleteIfExists(pi);
               Files.write(pi,obj.getAllOutput(),StandardOpenOption.CREATE_NEW);
               System.out.println("Run Time :- "+obj.getTime());
          } catch (IOException ex) {
               System.out.println("Error :- "+ex.getMessage());
          }
     }
     
     public static void compareOutputs(){
          try {
               Path pi;
               Path dir=Configuration.getDefaultDir();
               String out1;
               System.out.println("output 1 file :- ");
               for(out1=sc.nextLine();out1=="";out1=sc.nextLine());
               pi=dir.resolve(out1);
               List<String> output1=Files.readAllLines(pi);
               String out2;
               System.out.println("output 2 file :- ");
               for(out2=sc.nextLine();out2=="";out2=sc.nextLine());
               pi=dir.resolve(out2);
               List<String> output2=Files.readAllLines(pi);
               if(ListManipulator.compare(output1,output2,
                              StringComparators.getExactmatch())){
                    System.out.println("Exact match");
               }
               else if(ListManipulator.compare(output1,output2,
                              StringComparators.getIgnoreWhiteSpace())){
                    System.out.println("Presentation Error");
               }
               else {
                    System.out.println("not Match");
               }
          } catch (IOException ex) {
               System.out.println("Error :- "+ex.getMessage());
          }
     }
     
     public static void defaultPath(){
          System.out.println("Source data Directory :- "+Configuration.getDefaultDir());
     }
}
