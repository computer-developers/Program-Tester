/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testRunDetails;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import lib.runDetails.*;
import lib.userModule.test.Test;
import programtester.config.Configurator;

/**
 *
 * @author Rushabh Modi
 */
public class IOManagerWriteTest {
     static Scanner sc=new Scanner(System.in);
    
    public static void main(String... args) throws IOException{
        Configurator.init();
        IntIODetail io;
        Path dir=Test.getDefaultDir();
        System.out.println("Data Directory = " + dir);
        Path pi;
        System.out.println("encript ? :-");
          boolean b=sc.nextBoolean();
          String in;
          System.out.println("input file :- ");
          for(in=sc.nextLine();in=="";in=sc.nextLine());
          String out;
          System.out.println("output file :- ");
          for(out=sc.nextLine();out=="";out=sc.nextLine());
          System.out.println("input :- "+in);
          System.out.println("out :- "+out);
          pi=dir.resolve(in);
          List<String> input=Files.readAllLines(pi);
          pi=dir.resolve(out);
          List<String> output=Files.readAllLines(pi);
        io=IOManager.getIODetail(input, output);
        pi=dir;
        System.out.println(IOManager.writeIntIODetail(io, pi,b));
    }
}
