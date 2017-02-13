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

/**
 *
 * @author Rushabh Modi
 */
public class IOManagerTest3 {
    static Scanner sc=new Scanner(System.in);
    public static void main(String... args) throws IOException{
          System.out.println("enter pid :- ");
          long pid=sc.nextLong();
          System.out.println("enter index :- ");
          long index=sc.nextLong();
          System.out.println("encrypt ? :- ");
          boolean b=sc.nextBoolean();
        IntIODetail io;
        Path pi;
        pi=Paths.get("temp").toAbsolutePath();
        io=IOManager.getIODetail(pi,pid,index,b);
        pi=Paths.get("input2.txt");
        Files.write(pi,io.getAllInput(),StandardOpenOption.CREATE_NEW);
        pi=Paths.get("output2.txt");
        Files.write(pi,io.getAllOutput(),StandardOpenOption.CREATE_NEW);
    }
}
