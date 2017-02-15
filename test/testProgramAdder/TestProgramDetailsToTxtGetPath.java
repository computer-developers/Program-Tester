package testProgramAdder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import lib.dT.problemManipulate.IntProgramDetail;
import lib.dT.problemManipulate.ProgramDetails;
import lib.userModule.test.Test;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class TestProgramDetailsToTxtGetPath {
     public static void main(String args[]) throws IOException{
          Scanner sc=new Scanner(System.in);
          Configurator.init();
          Path dir=ProgramDetails.getDefaultDir();
          System.out.println("Program Directory = " + dir);
          System.out.println("enter file name = ");
          String s="";
          for(s=sc.nextLine();s=="";s=sc.nextLine());
          IntProgramDetail x=ProgramDetails.readProgramDetail(dir.resolve(s));
          System.out.println(ProgramDetails.writeToTxt(x));
     }
}