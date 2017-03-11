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
import programtester.config.Configuration;
import programtester.config.Configurator;

/**
 *
 * @author Rushabh Modi
 */
public class IOManagerGetPathTest {
    static Scanner sc=new Scanner(System.in);
    public static void main(String... args) throws IOException{
          Configurator.init();
          Path dir=Configuration.getDefaultDir();
          System.out.println("Data Directory = " + dir);
          System.out.println("enter file name:-");
          String s;
          for(s=sc.nextLine();s=="";s=sc.nextLine());
          System.out.println("encript ? :-");
          String in;
          System.out.println("input file :- ");
          for(in=sc.nextLine();in=="";in=sc.nextLine());
          String out;
          System.out.println("output file :- ");
          for(out=sc.nextLine();out=="";out=sc.nextLine());
          System.out.println("input :- "+in);
          System.out.println("out :- "+out);
          boolean b=sc.nextBoolean();
        IntIODetail io;
        Path pi;
        pi=dir.resolve(s).toAbsolutePath();
        io=IOManager.getIODetail(pi,b);
        //io=IOManager.getIODetail(pi);
        pi=dir.resolve(in);
        Files.write(pi,io.getAllInput(),StandardOpenOption.CREATE_NEW);
        pi=dir.resolve(out);
        Files.write(pi,io.getAllOutput(),StandardOpenOption.CREATE_NEW);
    }
}
