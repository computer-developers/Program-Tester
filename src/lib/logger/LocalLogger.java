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
               ldefLogDir.readLock().lock();
               try {
                    if(defLogDir.equals(p))
                         return true;
               } finally {
                    ldefLogDir.readLock().unlock();
               }
               ldefLogDir.writeLock().lock();
               defLogDir=p;
               return true;
          }finally{
               ldefLogDir.readLock().unlock();
               ldefLogDir.writeLock().unlock();
          }
     }
     
//local part
     private String sep="";
     public LocalLogger() throws FileNotFoundException{
          this(getDefaultLogDir());
     }
     
     public LocalLogger(Path filePath) throws FileNotFoundException{
          super(new FileOutputStream(filePath.toFile()));
     }
     
     public synchronized void setSep(String sep){
          this.sep=sep;
     }
     
     public synchronized void log(String... ar){
          String l=Arrays.stream(ar)
                  .reduce("",(s1,s2)->s1+sep+s2);
          super.log(l);
     }
     
}
