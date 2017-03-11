package testProgramAdder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import lib.dT.problemManipulate.IntProgramDetail;
import lib.dT.problemManipulate.ProgramDetails;
import lib.userModule.test.Test;
import static programtester.config.Configuration.getDefaultProDir;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class TestProgramDetailsGetPid {
     public static void main(String args[]) throws IOException{
          Scanner sc=new Scanner(System.in);
          Configurator.init();
          Path dir=getDefaultProDir();
          System.out.println("Program Directory = " + dir);
          System.out.println("enter program ID = ");
          long pid=sc.nextLong();
          IntProgramDetail x=ProgramDetails.readProgramDetail(pid);
          System.out.println("Program Id :"+x.getProgramID());
          System.out.println("Title :"+x.getTitle());
          System.out.println("Description :"+x.getDescription());
          System.out.println("Input :"+x.getInput());
          System.out.println("Output :"+x.getOutput());
          System.out.println("Sample Input :"+x.getSampleInput());
          System.out.println("Sample Output :"+x.getSampleOutput());
          System.out.println("Credit :"+x.getCredit());
     }
}