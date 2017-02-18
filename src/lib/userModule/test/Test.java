/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lib.dT.manipulate.comparators.ListManipulator;
import lib.dT.manipulate.comparators.StringComparators;
import lib.runDetails.IOManager;
import lib.runDetails.IntIODetail;
import lib.runTest.RunTest;
import lib.userModule.result.IntLiveResultSet;
import lib.userModule.result.IntResultSet;
import lib.userModule.result.LiveResultSetAdapter;
import lib.userModule.result.ResultSetAdapter;

/**
 *
 * @author Neel Patel
 */
public class Test {
//static Part
     public static final int TEST_PASS=2,TEST_PRESENT_ERROR=1,TEST_FAIL=-2,
             TEST_TIME_ERROR=-1,TEST_FILE_ERROR=-3;
     public static long minMem=20000000;
     /* 
     * defDir is default path veriable which is used by construtor if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
     static private Path defDir=Paths.get(".").toAbsolutePath();
     
     //lock on defDir
     static final private ReentrantReadWriteLock ldefDir=new ReentrantReadWriteLock();
     
     /* 
     * defDir is default path veriable which is used by construtor if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
     static private boolean isParallel=true;
     
     //lock on defDir
     static final private ReentrantReadWriteLock lIsParallel=new ReentrantReadWriteLock();
     
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
      * @return true if the default path is updated with {@code p}, false if p
        is not absolute or not a directory.
      */
     static public boolean setDefaultDir(Path p){
          if(!p.isAbsolute()||!Files.isDirectory(p))
               return false;
          try{
               if(getDefaultDir().equals(p))
                    return true;
               ldefDir.writeLock().lock();
               defDir=p;
               return true;
          }finally{
               ldefDir.writeLock().unlock();
          }
     }
     
     
     /**
      * getter method of default execution method.
      * it is used by default.
      * this method is thread safe.
      * @return default execution method.
      */
     static public boolean getIsParallel(){
          try{
               lIsParallel.readLock().lock();
               return isParallel;
          }
          finally{
               lIsParallel.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default execution method.
      * this method is thread safe.<br>
      * @param isParallel if the execution method is parallel or not.
      */
     static public void setIsParallel(boolean isParallel){
          try{
               if(getIsParallel()==isParallel)
                    return;
               lIsParallel.writeLock().lock();
               Test.isParallel=isParallel;
               return;
          }finally{
               lIsParallel.writeLock().unlock();
          }
     }

//local Part     
     private final Path dir;
     private final long pid;
     private final String cmd;
     private Thread t;
     private List<TestState> ts;
     private IntLiveResultSet lrs;
     private boolean flag=false;
     
     /**
      * this method compare the output of {@code us} with
        corresponding output in list {@code orig}.
      * this method finds the equivalent original output from list {@code orig}
        and compare it's output to the output of {@code us}.
      * @param us object of TestState, output of which is going to be compared.
      * @throws IllegalArgumentException if no equivalent object exist in list
        {@code orig} or {@code us} contain invalid information like negative
        {@code programID} or {@code index} etc..
      */
     private void comp(TestState us){
          try{
               if(!us.isExecuted())
                    return;
               if(us.getTime()<0){
                    us.setState("Time Limit Exceeded",TEST_TIME_ERROR);
                    return;
               }
               IntIODetail ori=read(us.index());
               if(ori==null)
                    us.setState("File Not Found !!", TEST_FILE_ERROR);
               //System.out.println("enter check");
               if(ListManipulator.compare(us.getAllOutput(),ori.getAllOutput(),
                              StringComparators.getExactmatch())){
                    //System.out.println("perfect check");
                    us.setState("Pass",TEST_PASS);
               }
               else if(ListManipulator.compare(
                       ListManipulator.removeNull(us.getAllOutput()),
                       ListManipulator.removeNull(ori.getAllOutput()),
                              StringComparators.getIgnoreWhiteSpace())){
                    //System.out.println("persentation check");
                    us.setState("Presentation Error",TEST_PRESENT_ERROR);
               }
               else {
                    us.setState("Fail",TEST_FAIL);
               }
          }catch(NullPointerException ex){
               throw new IllegalArgumentException();
          }
     }
     
     private synchronized void reader()throws IOException{
          List<TestState> ts=IntStream.rangeClosed(1,30)
                              .mapToObj(i->read(i))
                              .filter(i->i!=null)
                              //.peek(i->System.out.println("index :- "+i.index()))
                              .map(i->new TestState(i))
                              .collect(Collectors.toList());
          if(ts.isEmpty())
               throw new IOException();
          this.ts=ts;
     }
     
     private IntIODetail read(long index){
          //System.out.println("read .... start");
          IntIODetail d;
          try{
               d=IOManager.getIODetail(dir, pid, index, true);
               return d;
          }catch(IOException e){}
          try{
               d=IOManager.getIODetail(dir, pid, index, false);
               return d;
          }catch(IOException e){}
          //System.out.println("read .... end");
          return null;
     }
     
     private void run(){
          flag=true;
          for(TestState i:ts){
               if(!flag) //check if the thread should be terminated.
                    break;
               for(;Runtime.getRuntime().freeMemory()<minMem&&
                       minMem*2<Runtime.getRuntime().maxMemory();){
                    try{
                         System.gc();
                         Thread.sleep(1000);
                    }catch(InterruptedException e){}
               }
               RunTest rt=new RunTest(i,cmd);
               i.setState("Executing", 0);
               i.update(rt.getIODetail());
               comp(i);
               i.makeFinal();
          }
          //System.gc();
     }
     
     private void run(boolean isParallel){
          if(!isParallel){
               run();
               return;
          }
          flag=true;
          ts.parallelStream().peek(i->{
               if(!flag) //check if the thread should be terminated.
                    return;
               for(;Runtime.getRuntime().freeMemory()<minMem&&
                       minMem*2<Runtime.getRuntime().maxMemory();){
                    //System.out.println("jbahbajadk");
                    try{
                         System.gc();
                         Thread.sleep(1000);
                    }catch(InterruptedException e){}
               }
               RunTest rt=new RunTest(i,cmd);
               i.setState("Executing", 0);
               i.update(rt.getIODetail());
               comp(i);
               i.makeFinal();
               System.gc();
          }).count();
     }
     
     /**
      * creates the object using {@code pid}, {@code cmd} and default path. 
      * @param pid programID.
      * @param cmd Executable command.
      */
     public Test(long pid,String cmd){
          this(pid,getDefaultDir(),cmd);
     }
     
     /**
      * creates the object using {@code pid}, {@code dir} and {@code cmd}. 
      * @param pid programID.
      * @param dir path of directory.
      * @param cmd Executable command.
      */
     public Test(long pid,Path dir,String cmd){
          this.dir=dir;
          this.pid=pid;
          this.cmd=cmd;
     }

     public synchronized IntLiveResultSet start() throws IOException{
          if(t!=null)
               return null;
          reader();
          t=new Thread(()->run(getIsParallel()),"Tester Thread");
          t.start();
          lrs=new LiveResultSetAdapter(ts);
          return lrs;
     }
     
     public void join(){
          for(;t!=null&&t.isAlive();)
          try {
               t.join();
          } catch (InterruptedException ex) {}
     }
     
     public IntResultSet getIntResultSet(){
          return new ResultSetAdapter(lrs.getAllResult());
     }
     
     /**
      * this method change the flag to false to terminate the
        Testing process.
      * although the currently running Testing process will be executed as it is.
      * next tasks will not be initiated.
      */
     public void stop(){
          flag=false;
     }
     
     public long getProgramID(){
          return pid;
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
