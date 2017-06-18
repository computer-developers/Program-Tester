/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/
package programTester.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Neel Patel
 */
public class Configuration {
     private Configuration(){}
     
//local
     public static final int TEST_PASS=2,TEST_PRESENT_ERROR=1,TEST_FAIL=-2,
             TEST_TIME_ERROR=-1,TEST_FILE_ERROR=-3;
     public static long minMem=1000000;
}
     /*static private Path defDir=Paths.get(".").toAbsolutePath();
     static final private ReentrantReadWriteLock ldefDir=new ReentrantReadWriteLock();
     static private boolean isParallel=true;
     static final private ReentrantReadWriteLock lIsParallel=new ReentrantReadWriteLock();
     static private Path defLogDir=Paths.get(".").toAbsolutePath();
     static final private ReentrantReadWriteLock ldefLogDir=new ReentrantReadWriteLock();
     static private Path defProDir=Paths.get(".").toAbsolutePath();
     static final private ReentrantReadWriteLock ldefProDir=new ReentrantReadWriteLock();
     static private Path defUserDetPath=Paths.get(".").toAbsolutePath();
     static final private ReentrantReadWriteLock ldefUserDetPath=new ReentrantReadWriteLock();
//network
     static private String defMainSer="";
     static final private ReentrantReadWriteLock ldefMainSer=new ReentrantReadWriteLock();
     static private String defMainDataSer="";
     static final private ReentrantReadWriteLock ldefMainDataSer=new ReentrantReadWriteLock();
     static private String defMainLogSer="";
     static final private ReentrantReadWriteLock ldefMainLogSer=new ReentrantReadWriteLock();
     static private String defDataSer="";
     static final private ReentrantReadWriteLock ldefDataSer=new ReentrantReadWriteLock();
     static private int defRMIPort=1099;
     static final private ReentrantReadWriteLock ldefRMIPort=new ReentrantReadWriteLock();
     
     /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      
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
     
     
     
     /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      
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
     
     
     /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      
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
      
     static public boolean setDefaultProDir(Path p){
          if(!p.isAbsolute()||!Files.isDirectory(p))
               return false;
          try{
               if(getDefaultProDir().equals(p))
                    return true;
               ldefProDir.writeLock().lock();
               defProDir=p;
               return true;
          }finally{
               ldefProDir.writeLock().unlock();
          }
     }
     
     /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      
     static public Path getDefaultUserDetailPath(){
          try{
               ldefUserDetPath.readLock().lock();
               return defUserDetPath;
          }
          finally{
               ldefUserDetPath.readLock().unlock();     
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
      
     static public boolean setDefaultUserDetailPath(Path p){
          if(!p.isAbsolute()||Files.isDirectory(p))
               return false;
          try{
               if(getDefaultUserDetailPath().equals(p))
                    return true;
               ldefUserDetPath.writeLock().lock();
               defUserDetPath=p;
               return true;
          }finally{
               ldefUserDetPath.writeLock().unlock();
          }
     }
     
//network
     
     /**
      * getter default Main Server URI.
      * @return default Main Server URI.
      
     static public String getDefaultMainSer(){
          try{
               ldefMainSer.readLock().lock();
               return defMainSer;
          }
          finally{
               ldefMainSer.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default Main Server URI.
      * this method is thread safe.<br>
      * Note:- changes made in default Main Server URI is not reflected in
        existing objects of the class.
      * @param uri new default Main Server's URI.
      * @return true if the default Main Server URI is updated with {@code uri},
        false otherwise.
      
     static public boolean setDefaultMainSer(String uri){
          try{
               if(getDefaultMainSer().equals(uri))
                    return true;
               ldefMainSer.writeLock().lock();
               defMainSer=uri;
               return true;
          }finally{
               ldefMainSer.writeLock().unlock();
          }
     }
     
     /**
      * getter default Main Data Server URI.
      * @return default Main Data Server URI.
      
     static public String getDefaultMainDataSer(){
          try{
               ldefMainDataSer.readLock().lock();
               return defMainDataSer;
          }
          finally{
               ldefMainDataSer.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default Main Data Server URI.
      * this method is thread safe.<br>
      * Note:- changes made in default Main Data Server URI is not reflected in
        existing objects of the class.
      * @param uri new default Main Data Server's URI.
      * @return true if the default Main Data Server URI is updated with {@code uri},
        false otherwise.
      
     static public boolean setDefaultMainDataSer(String uri){
          try{
               if(getDefaultMainDataSer().equals(uri))
                    return true;
               ldefMainDataSer.writeLock().lock();
               defMainDataSer=uri;
               return true;
          }finally{
               ldefMainDataSer.writeLock().unlock();
          }
     }
     
     /**
      * getter default Main Log Server URI.
      * @return default Main Log Server URI.
      
     static public String getDefaultMainLogSer(){
          try{
               ldefMainLogSer.readLock().lock();
               return defMainLogSer;
          }
          finally{
               ldefMainLogSer.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default Main Log Server URI.
      * this method is thread safe.<br>
      * Note:- changes made in default Main Log Server URI is not reflected in
        existing objects of the class.
      * @param uri new default Main Log Server's URI.
      * @return true if the default Main Log Server URI is updated with {@code uri},
        false otherwise.
      
     static public boolean setDefaultMainLogSer(String uri){
          try{
               if(getDefaultMainLogSer().equals(uri))
                    return true;
               ldefMainLogSer.writeLock().lock();
               defMainLogSer=uri;
               return true;
          }finally{
               ldefMainLogSer.writeLock().unlock();
          }
     }
     
     /**
      * getter default Data Server URI.
      * @return default Data Server URI.
      
     static public String getDefaultDataSer(){
          try{
               ldefDataSer.readLock().lock();
               return defDataSer;
          }
          finally{
               ldefDataSer.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default Data Server URI.
      * this method is thread safe.<br>
      * Note:- changes made in default Data Server URI is not reflected in
        existing objects of the class.
      * @param uri new default Data Server's URI.
      * @return true if the default Data Server URI is updated with {@code uri},
        false otherwise.
      
     static public boolean setDefaultDataSer(String uri){
          try{
               if(getDefaultDataSer().equals(uri))
                    return true;
               ldefDataSer.writeLock().lock();
               defDataSer=uri;
               return true;
          }finally{
               ldefDataSer.writeLock().unlock();
          }
     }
     
     /**
      * getter default RMI Port.
      * @return default RMI Port.
      
     static public int getDefaultRMIPort(){
          try{
               ldefRMIPort.readLock().lock();
               return defRMIPort;
          }
          finally{
               ldefRMIPort.readLock().unlock();     
          }
     }
     
     /**
      * setter method of default Data Server URI.
      * this method is thread safe.<br>
      * Note:- changes made in default Data Server URI is not reflected in
        existing objects of the class.
      * @param port new default port.
      * @return true if the default Data Server URI is updated with {@code uri},
        false otherwise.
      
     static public boolean setDefaultRMIPort(int port){
          try{
               if(getDefaultRMIPort()==port)
                    return true;
               ldefRMIPort.writeLock().lock();
               defRMIPort=port;
               return true;
          }finally{
               ldefRMIPort.writeLock().unlock();
          }
     }
     

*/
     /* 
     * defProDir is default path veriable which is used by methods if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
     
     //lock on defProDir
     
     /* 
     * defDir is default path veriable which is used by construtor if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
     
     //lock on defDir
     
     /* 
     * defDir is default path veriable which is used by construtor if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
     
     //lock on defDir
     
     /* 
     * defLogDir is default path veriable which is used by construtor if no path
       provided explicitly.
     * it should only accessed by getter and setter methods only.
     */
          
     
     //lock on defLogDir