/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programtester.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lib.userModule.test.Test;

/**
 *
 * @author Neel Patel
 */
public class Configuration {
     private Configuration(){}
     
     
public static final int TEST_PASS=2,TEST_PRESENT_ERROR=1,TEST_FAIL=-2,
             TEST_TIME_ERROR=-1,TEST_FILE_ERROR=-3;
     public static long minMem=1000000;
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
               Configuration.isParallel=isParallel;
               return;
          }finally{
               lIsParallel.writeLock().unlock();
          }
     }
     
     
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
     
/* 
     * defProDir is default path veriable which is used by methods if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
     static private Path defProDir=Paths.get(".").toAbsolutePath();
     
     //lock on defProDir
     static final private ReentrantReadWriteLock ldefProDir=new ReentrantReadWriteLock();
     
     /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      */
     static public Path getDefaultProDir(){
          try{
               ldefProDir.readLock().lock();
               return defProDir;
          }
          finally{
               ldefProDir.readLock().unlock();     
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
     static public boolean setDefaultProDir(Path p){
          if(!p.isAbsolute()||!Files.isDirectory(p))
               return false;
          try{
               if(getDefaultDir().equals(p))
                    return true;
               ldefProDir.writeLock().lock();
               defProDir=p;
               return true;
          }finally{
               ldefProDir.writeLock().unlock();
          }
     }
     
}
