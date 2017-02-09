package lib.userModule;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;
import lib.runDetails.IOManager;
import lib.runDetails.IntIODetail;
import lib.runTest.BatchTest;

/**
 *
 * @author Neel Patel
 */
public class Execute {
     static private Path defdir=Paths.get(".").toAbsolutePath();
     static final private ReentrantReadWriteLock ldefdir=new ReentrantReadWriteLock();
     
     static public Path getDefaultDir(){
          try{
               ldefdir.readLock().lock();
               return defdir;
          }
          finally{
               ldefdir.readLock().unlock();     
          }
     }
     
     static public boolean setDefaultDir(Path p){
          if(!p.isAbsolute())
               return false;
          try{
               ldefdir.readLock().lock();
               try {
                    if(defdir.equals(p))
                         return true;
               } finally {
                    ldefdir.readLock().unlock();
               }
               ldefdir.writeLock().lock();
               defdir=p;
               return true;
          }finally{
               ldefdir.readLock().unlock();
               ldefdir.writeLock().unlock();
          }
     }
     
     private final Path dir;
     private final long pid;
     private String cmd;
     private Thread t;
     private BatchTest bt;

     public Execute(long pid,String cmd){
          this(pid,getDefaultDir(),cmd);
     }
     
     public Execute(long pid,Path dir,String cmd){
          this.dir=dir;
          this.pid=pid;
          this.cmd=cmd;
     }
     
     public synchronized Stream<IntIODetail> start()throws IOException{
          reader();
          return bt.execute();
     }
     
     private synchronized void reader()throws IOException{
          IntIODetail io[]=Files.find(dir, 0,this::fpred)
                    .map(p->{
                         try{
                              return (IntIODetail)IOManager.getIODetail(p);
                         }catch(IOException e){}
                              return null;
                    }).toArray(() -> new IntIODetail[0]);
          bt=new BatchTest(cmd,io);
     }
     
     public Path getDir(){
          return dir;
     }
     
     private boolean fpred(Path n,BasicFileAttributes a){
          if(Files.isDirectory(n))
               return false;
          return n.getFileName().getFileName().toString()
                    .matches("^c?v"+IOManager.getVersion()+"p"+pid+".*$");
     }
}
