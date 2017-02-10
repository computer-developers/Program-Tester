package lib.userModule;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;
import lib.runDetails.IOManager;
import lib.runDetails.IntIODetail;
import lib.runDetails.IntInput;
import lib.runTest.BatchTest;

/**
 *
 * @author Neel Patel
 */
public class Execute {
//static Part
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

//local Part     
     private final Path dir;
     private final long pid;
     private String cmd;
     private Thread t;
     private BatchTest bt;

     private boolean fpred(Path n,BasicFileAttributes a){
          if(Files.isDirectory(n))
               return false;
          return n.getFileName().getFileName().toString()
                    .matches("^c?v"+IOManager.getVersion()+"p"+pid+".*$");
     }
     
     private synchronized void reader()throws IOException{
          IntIODetail io[]=(IntIODetail[])Files.find(dir, 0,this::fpred)
                    .map(p->{
                         try{
                              return (IntIODetail)IOManager.getIODetail(p);
                         }catch(IOException e){}
                              return null;
                    }).toArray();
          bt=new BatchTest(cmd,io);
     }
     
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
     
     
     public Path getDir(){
          return dir;
     }
     
     public void stop(){
          bt.stop();
     }
     
     public IntInput[] getInputs(){
          return bt.getInputs();
     }
     
     @Override
     public void finalize() throws Throwable{
          try {
               stop();
          } finally {
               super.finalize();
          }
     }
}
