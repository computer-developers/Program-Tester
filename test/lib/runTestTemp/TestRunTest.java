package lib.runTestTemp;
import lib.runTest.DummyIntIODetail;
import java.io.IOException;
import lib.runDetails.*;
import lib.runTest.*;
import java.util.*;
import java.nio.file.*;
/**
 *
 * @author admin
 */
public class TestRunTest {
     static Scanner sc=new Scanner(System.in);
     public static void main(String... arg) throws IOException{
          String com=sc.nextLine();
          Path pi;
          if(arg.length>0)
               pi=Paths.get(arg[0]);
          else
               pi=Paths.get("resources","input.txt");
          List<String> input=Files.readAllLines(pi);
          RunTest rt=new RunTest(DummyIntIODetail.getIntIODetail(input,null, 0),com);
          rt.run();
          IntIODetail obj=rt.getIODetail();
          if(arg.length>1)
               pi=Paths.get(arg[1]);
          else
               pi=Paths.get("resources","output.txt");
          Files.write(pi,obj.getAllOutput());
          System.out.println("Time :- "+obj.getTime());
     }
}
