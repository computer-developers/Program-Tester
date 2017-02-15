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
public class IOManagerTest {
     static Scanner sc=new Scanner(System.in);
    
    public static void main(String... args) throws IOException{
        Configurator.init();
        IntIODetail io;
        Path dir=Test.getDefaultDir();
        Path pi;
        System.out.println("encript ? :-");
          boolean b=sc.nextBoolean();
        if(args.length>0)
               pi=dir.resolve(args[0]);
          else
               pi=dir.resolve("input.txt");
          System.out.println("pi :- "+pi);
          List<String> input=Files.readAllLines(pi);
        if(args.length>1)
               pi=dir.resolve(args[1]);
          else
               pi=dir.resolve("output.txt");
          System.out.println("pi :- "+pi);
          List<String> output=Files.readAllLines(pi);
        io=IOManager.getIODetail(input, output);
        pi=Paths.get("resources\\temp").toAbsolutePath();
        System.out.println(IOManager.writeIntIODetail(io, pi,b));
    }
}
