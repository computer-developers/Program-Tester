package lib.userModule;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;
import lib.runDetails.IOManager;
import lib.runDetails.IntIODetail;
import lib.runDetails.IntInput;
import lib.runTest.BatchTest;

/**
 * this class provide facility to run the program for corresponding
   inputs.
 * @author Neel Patel
 */
public class Execute {
//static Part
     
     /* 
     * defDir is default path veriable which is used by construtor if no path
       provided explicitly.
     * it should only accessed by getter & setter methods only.
     */
     static private Path defDir=Paths.get(".").toAbsolutePath();
     
     //lock on defDir
     static final private ReentrantReadWriteLock ldefDir=new ReentrantReadWriteLock();
     
     /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      */
     static public Path getDefaultDir(){
          try{
               ldefDir.readLock().lock();
               return defDir;
          }
          finally{
               ldefDir.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default path.
      * this method is thread safe.<br>
      * Note:- changes made in default path is not reflected in existing objects
        of the class as they use their local variable to store path variable
        to locate the data files.
      * @param p new default path.
      * @return true if the default path is updated with {@param p}, false if p
        is not absolute or not a directory.
      */
     static public boolean setDefaultDir(Path p){
          if(!p.isAbsolute()||!Files.isDirectory(p))
               return false;
          try{
               ldefDir.readLock().lock();
               try {
                    if(defDir.equals(p))
                         return true;
               } finally {
                    ldefDir.readLock().unlock();
               }
               ldefDir.writeLock().lock();
               defDir=p;
               return true;
          }finally{
               ldefDir.readLock().unlock();
               ldefDir.writeLock().unlock();
          }
     }

//local Part     
     private final Path dir;
     private final long pid;
     private final String cmd;
     private Thread t;
     private BatchTest bt;

     /**
      * this method can be used as lambda to filter the files.
      * @param n {@code Path} of the file or directory.
      * @param a {@code BasicFileAttributes} of the file.
      * @return true if the file name is valid according to standard conventions.
      */
     private boolean fpred(Path n,BasicFileAttributes a){
          if(Files.isDirectory(n))
               return false;
          return n.getFileName().getFileName().toString()
                    .matches("^c?v"+IOManager.getVersion()+"p"+pid+".*$");
     }
     
     /**
      * it reads the object from the files.
      * if the file name starts with 'c' it call the {@code IOManager.getIODetail}
        with isComp parameter true otherwise call with false.
      * this method creates the object of {@code BatchTest} with read data & assign
        it to the reference {@code bt}.
      * @throws IOException if underlying implementation throws.
      */
     private synchronized void reader()throws IOException{
          IntIODetail io[]=(IntIODetail[])Files.find(dir, 0,this::fpred)
                    .map(p->{
                         try{
                              if(p.getFileName().toString().startsWith("c"))
                                   return (IntIODetail)IOManager.getIODetail(p,true);
                              else
                                   return (IntIODetail)IOManager.getIODetail(p,false);
                         }catch(IOException e){}
                              return null;
                    }).toArray();
          bt=new BatchTest(cmd,io);
     }
     
     /**
      * creates the object using {@param pid}, {@param cmd} & default path. 
      * @param pid programID.
      * @param cmd Executable command.
      */
     public Execute(long pid,String cmd){
          this(pid,getDefaultDir(),cmd);
     }
     
     /**
      * creates the object using {@param pid}, {@param dir} & {@param cmd}. 
      * @param pid programID.
      * @param dir path of directory.
      * @param cmd Executable command.
      */
     public Execute(long pid,Path dir,String cmd){
          this.dir=dir;
          this.pid=pid;
          this.cmd=cmd;
     }
     
     /**
      * read the corresponding IntIODetail object from the directory & call the
      * {@code execute} method on {@code bt}.
      * @return Stream of IntIODetail.
      * @throws IOException if occurs while reading objects from file.
      */
     public synchronized Stream<IntIODetail> start()throws IOException{
          reader();
          if(bt==null)
               throw new IOException();
          return bt.execute();
     }
     
     /**
      * getter method of {@code dir}
      * @return absolute path of working directory of the object.
      */
     public Path getDir(){
          return dir;
     }
     
     /**
      * stop the process of execution.
      */
     public void stop(){
          if(bt!=null)
               bt.stop();
     }
     
     /**
      * return array of IntInput read from the directory.
      * if data is not read yet then it returns null;
      * @return array of IntInput if available, null otherwise.
      */
     public IntInput[] getInputs(){
          if (bt==null)
               return null;
          return bt.getInputs();
     }
     
     /**
      * this method returns outputs of the execution.
      * this method can make current thread wait if outputs are not available at
        the moment. 
      * @return list of {@code IntIODetail}.
      */
     public synchronized List<IntIODetail> getOutputs(){
          return bt.getOutputs();
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
