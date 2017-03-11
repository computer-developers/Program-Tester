package lib.inputGenratorTest;
import lib.dT.inputGenerator.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import programtester.config.Configuration;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class InputGenTest {
     static Scanner sc=new Scanner(System.in);
     public static void main(String... arg) throws IOException{
          Configurator.init();
          Path dir=Configuration.getDefaultDir();
          System.out.println("Data Directory = " + dir);
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
               for(s=sc.nextLine();s.trim().length()==0;s=sc.nextLine());
               ar[i]=s;
          }
          System.out.println("counts :- ");
          int c=sc.nextInt();
          List<String> lines= InputGen.getInput(c,ar);
          Files.write(p, lines,StandardOpenOption.CREATE_NEW);
     }
}
