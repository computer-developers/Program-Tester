package lib.runTestTemp;
import java.io.IOException;
import lib.runDetails.*;
import lib.runTest.*;
import java.util.*;
import java.nio.file.StandardOpenOption;
import java.nio.file.*;
import programtester.config.Configuration;
import programtester.config.Configurator;
/**
 *
 * @author Neel Patel
 */
public class TestRunTest {
     static Scanner sc=new Scanner(System.in);
     public static void main(String... arg) throws IOException{
          Configurator.init();
          System.out.println("Working Directory = " +System.getProperty("user.dir"));
          Path dir=Configuration.getDefaultDir();
          System.out.println("Data Directory = " + dir);
          String com;
          System.out.println("command :- ");
          for(com=sc.nextLine();com=="";com=sc.nextLine());
          String in;
          System.out.println("input file :- ");
          for(in=sc.nextLine();in=="";in=sc.nextLine());
          String out;
          System.out.println("output file :- ");
          for(out=sc.nextLine();out=="";out=sc.nextLine());
          System.out.println("cmd :- "+com);
          System.out.println("input :- "+in);
          System.out.println("out :- "+out);
          Path pi;
          pi=dir.resolve(in);
          System.out.println("pi :- "+pi);
          List<String> input=Files.readAllLines(pi);
          System.out.println("input read completed.");
          RunTest rt=new RunTest(IOManager.getIODetail(input,null, 0),com);
          System.out.println("process start.");
          //rt.run();
          IntIODetail obj=rt.getIODetail();
          System.out.println("process completed.");
          if(arg.length>1)
               pi=dir.resolve(arg[1]);
          else
               pi=dir.resolve(out);
          System.out.println("pi :- "+pi);
          Files.write(pi,obj.getAllOutput(),StandardOpenOption.CREATE_NEW);
          System.out.println("Time :- "+obj.getTime());
     }
}
