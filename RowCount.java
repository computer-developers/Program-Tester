import java.nio.file.*;
import java.io.*;
public class RowCount{
	static int count=0;
	public static void main(String... arg)throws IOException{
		Files.walk(Paths.get("."))
            //.sorted(Comparator.reverseOrder())
            .filter(i->!Files.isDirectory(i)&&i.toString().endsWith(".java"))
            .forEach(x->{
            	try{
            		count+=Files.readAllLines(x).size();
            	}catch(Exception e){}
            });
        System.out.println(count);
    }
}