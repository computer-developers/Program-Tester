package lib.logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import static programtester.config.Configuration.getDefaultLogDir;

/**
 *
 * @author Neel Patel
 */
public class LocalLogger extends MyLogger{
//local part
     
          
     public LocalLogger(String name) throws FileNotFoundException{
          this(getDefaultLogDir().resolve(name));
     }
     
     public LocalLogger(Path filePath) throws FileNotFoundException{
          super(new FileOutputStream(filePath.toFile(),true));
     }
     
     
     
}
     /*
     String formate="%l";
     private Runnable[] r;
     public synchronized void setFormater(String f,Runnable... r){
          this.r=r.clone();
          this.formate=f;
     }
     private String formator(String... lr){
          String sb=formate;
          String[] ar=sb.split("%l");
          for(int i=0;i<ar.length-1;i++)
               ar[i]=ar[i]+lr[i];
          Arrays.spliterator(lr,ar.length-1,lr.length)
                  .forEachRemaining(j->ar[ar.length-2]+=sep+j);
          String front=Arrays.stream(ar).reduce("",(x,y)->x+y);
          
          return sb;
     }

     */