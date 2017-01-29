import java.io.IOException;
import java.nio.*;
import java.nio.file.*;
import java.util.*;

/**
 * this class must be run from the perent directory.
 * @author neel patel
 */
public class gitignoreGen {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) throws IOException {
          List<String> l=new ArrayList();
          Path p=Paths.get(System.getProperty("user.dir"),"build");
          Files.walk(p).filter(s->Files.isDirectory(s)).forEach(t->{
          	try{
          		l.add(t.toString().split("ProgramTester")[1]+"\\*.class");
          	}catch(ArrayIndexOutOfBoundsException e){}});
          Files.write(Paths.get(".gitignore"), l,StandardOpenOption.CREATE);
     }
     
}
