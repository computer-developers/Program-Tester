package lib.dT.problemManipulate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 *
 * @author Rushabh
 */
public class ProgramDetails {
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
     static public Path getDefaultDir(){
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
     static public boolean setDefaultDir(Path p){
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
     
     public static IntProgramDetail parseProgramDetail(Path filepath)
             throws IOException{
          if(!filepath.isAbsolute())
               return null;
          if((!Files.exists(filepath))||Files.isDirectory(filepath))
               return null;
          List<String> temp=Files.readAllLines(filepath);
          int i=0;
          int credit=-1;
          String title="",input="",output="";
          List<String> description=new ArrayList<>(), sampleInput=new ArrayList<>(),
                       sampleOutput=new ArrayList<>();
          try{
               for(;;){
                    switch(temp.get(i).trim()){
                         case "@title":
                              i++;
                              title=temp.get(i);
                              break;
                         case "@description":
                              i++;
                              while(temp.get(i).charAt(0)!='@'){
                                   description.add(temp.get(i));
                                   i++;
                              }
                              break;
                         case "@input":
                              i++;
                              input=temp.get(i);
                              break;
                         case "@output":
                              i++;
                              output=temp.get(i);
                              break;
                         case "@sample_input":
                              i++;
                              while(temp.get(i).charAt(0)!='@'){
                                   sampleInput.add(temp.get(i));
                                   i++;
                              }
                              break;
                         case "@sample_output":
                              i++;
                              while(temp.get(i).charAt(0)!='@'){
                                   sampleOutput.add(temp.get(i));
                                   i++;
                              }
                              break;
                         case "@credit":
                              i++;
                              credit=Integer.parseInt(temp.get(i).split(" ")[0]);
                              break;
                         default:
                              i++;
                    }
               }
          }
          catch(IndexOutOfBoundsException e){}
          return new ProgramDetail(title,description,input,output,sampleInput
                              ,sampleOutput,credit);
     }
     
     /**
      *
      * @param filepath
      * @return
      * @throws java.io.IOException
      */
     public static IntProgramDetail readProgramDetail(Path filepath)
               throws IOException{
          if(!filepath.isAbsolute())
               return null;
          if((!Files.exists(filepath))||Files.isDirectory(filepath))
               return null;
          try(FileInputStream f=new FileInputStream(filepath.toFile());
                    BufferedInputStream bin=new BufferedInputStream(f)){    
               IntProgramDetail obj=ObjectHandler.readCompObj(bin);
               if(obj!=null)
                    return obj;
               throw new IOException();
          }
     }
     
     public static IntProgramDetail readProgramDetail(Long programID)
               throws IOException{
          Path dir=getDefaultDir();
          String name="";
          {//generate the formated file name. 
               name+="v";
               name+=ProgramDetail.getVersion();
               name+="p";
               name+=programID;
               name+=".data";
          }
          Path f=dir.resolve(name);
          return readProgramDetail(f);
     }
     
     public static List<IntProgramDetail> readProgramDetail(){
          Path dir=getDefaultDir();
          try {
               List<IntProgramDetail> lp=Files.list(dir).filter(i->pred(i))
                              //.peek(i->System.out.println("file :- "+i.getFileName()))
                              .map(i->{
                                   try{
                                        return readProgramDetail(i);
                                   }catch(IOException e){
                                        return null;
                                   }
                              }).filter(i->i!=null)
                              .collect(Collectors.toList());
                       
               return lp;
          } catch (IOException ex) {
               return null;
          }
     }
     
     private static boolean pred(Path p){
          //System.out.println("file  :- "+p.getFileName());
          return p.getFileName().toString().matches("^v"+ProgramDetail.getVersion()
                  +"p[0-9]{1,12}"
                  +"\\.data$"   
               );
     }
     public static boolean writeIntProgramDetail(IntProgramDetail obj
             ,Path dir){
          if(!dir.isAbsolute())
               return false;
          if(!Files.isDirectory(dir))
               return false;
          String name="";
          {//generate the formated file name. 
               name+="v";
               if(obj instanceof ProgramDetail)
                    name+=ProgramDetail.getVersion();
               else
                    name+="NA";
               name=name+"p";
               if(obj.getProgramID()>=0)
                    name+=obj.getProgramID();
               else
                    name+="NA";
               name+=".data";
          }
          Path f=dir.resolve(name);
          try(FileOutputStream fout=new FileOutputStream(f.toFile());
                    BufferedOutputStream bout=new BufferedOutputStream(fout)){
               return ObjectHandler.writeCompObj(bout, obj);
          } catch(IOException ex) {
               return false;
          }     
     }
     
     public static boolean writeIntProgramDetail(IntProgramDetail obj){
          Path dir=getDefaultDir();
          return writeIntProgramDetail(obj,dir);     
     }
     
     public static boolean writeToTxt(IntProgramDetail obj)throws IOException{
          long pid=obj.getProgramID();
          Path dir=getDefaultDir();
          String name="";
          {//generate the formated file name. 
               name+=pid;
               name+="_"+obj.getTitle();
               name+=".txt";
          }
          Path f=dir.resolve(name);
          File fi=f.toFile();
          try(FileOutputStream out=new FileOutputStream(fi);
              PrintStream p=new PrintStream(out)){
               p.println("@programID");
               p.println(obj.getProgramID());
               p.println("@title");
               p.println(obj.getTitle());
               p.println("@description");
               obj.getDescription().forEach(p::println);
               p.println(obj.getTitle());
               p.println("@input");
               p.println(obj.getInput());
               p.println("@output");
               p.println(obj.getOutput());
               p.println("@sample input");
               obj.getSampleInput().forEach(p::println);
               p.println("@sample output");
               obj.getSampleOutput().forEach(p::println);
               p.println("@credit");
               p.println(obj.getCredit());
          }
          fi.setReadOnly();
          return true;
     }
     public static boolean isEqual(IntProgramDetail a,IntProgramDetail b){
          if(a.getProgramID()>0 && b.getProgramID()>0
                  && a.getProgramID()!=b.getProgramID())
                    return true;
          return false;
     }
}