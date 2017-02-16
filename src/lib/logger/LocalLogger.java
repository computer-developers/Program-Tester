package lib.logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Neel Patel
 */
public class LocalLogger extends MyLogger{
//static part     
     /* 
     * defLogDir is default path veriable which is used by construtor if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
     static private Path defLogDir=Paths.get(".").toAbsolutePath();
     
     //lock on defLogDir
     static final private ReentrantReadWriteLock ldefLogDir=new ReentrantReadWriteLock();
     
     /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      */
     static public Path getDefaultLogDir(){
          try{
               ldefLogDir.readLock().lock();
               return defLogDir;
          }
          finally{
               ldefLogDir.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default path.
      * this method is thread safe.<br>
      * Note:- changes made in default path is not reflected in existing objects
        of the class as they use their local variable to store path variable
        to locate the logger file.
      * @param p new default path.
      * @return true if the default path is updated with {@code p}, false if p
        is not absolute or not a directory.
      */
     static public boolean setDefaultLogDir(Path p){
          if(!p.isAbsolute()||!Files.isDirectory(p))
               return false;
          try{
               if(getDefaultLogDir().equals(p))
                    return true;
               ldefLogDir.writeLock().lock();
               defLogDir=p;
               return true;
          }finally{
               ldefLogDir.writeLock().unlock();
          }
     }
     
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