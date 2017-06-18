/*
 * The MIT License
 *
 * Copyright 2017 Neel Patel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lib.problemDefination;

import java.util.List;

/**
 *
 * @author Rushabh
 */
public class ProgramDetails {
    public static IntProgramDetail getProgramDetail(String title,
            List<String> description,List<String> input,List<String> output,
            List<String> sampleInput, List<String> sampleOutput,int credit){
        return new ProgramDetail(title,description,input,output
             ,sampleInput,sampleOutput,credit);
    }
    
    /**
      * returns object of IntInput.
      * {@code getAllInput} method of the object returned by this process always
        return the list of string referenced by {@code input}.
      * {@code input} should not be change by other thread during method call.
      * @param input list of String.
      * @return Object of IntInput.
      */
    public static IntInput getInput(List<String> input){
        return new TestCase(input);
    }
               
    /**
    * returns object of IntInput.
    * {@code getAllInput} method of the object returned by this process always
    return the list of string referenced by {@code input}.
    * same as {@code index} and {@code programID} returns {@code index} and 
    {@code programID} respectively.
    * {@code input} should not be change by other thread during method call.
    * @param input list of String.
    * @param programID unique programID.
    * @param index index of the input.
    * @return Object of IntInput.
    */
    public static IntInput getInput(List<String> input,long programID,long index){
        return getTestCase(input,null,-1,programID,index);
    }
               
    /**
    * this method return immutable object of {@code IntTestCase}
    using specified parameters.
    * {@code getTime} and {@code programId} method of the object return by this 
    method always return -1
    * @param input list of string which can be used as input of other program
    * @param output list of string which is output of any program
    * @return object of IntTestCase.
    */
    public static IntTestCase getTestCase(List<String> input,List<String> output){
        return new TestCase(input,output);
    }
     
    /**
    * this method return immutable object of {@code IntTestCase}
    using specified parameters.
    * @param input object of IntInput.
    * @param output list of string which is output of any program.
    * @param time time taken for generate output for {@code input}.
    * @return object of IntTestCase.
    */
    public static IntTestCase getTestCase(IntInput input, List<String> output, long time){
        return getTestCase(input.getAllInput(),output,time,input.programID(),input.index());
    }
     
    /**
    * this method return immutable object of {@code IntTestCase}
    using specified parameters.
    * programId method of the object return by this method always return -1
    * @param input list of string which can be used as input of other program
    * @param output list of string which is output of any program
    * @param time execution time in milliseconds.
    * @return object of IntTestCase.
    * @deprecated use {@code getIODetail(IntInput input,List<String> output,long time)}
    instead. 
    */
    public static IntTestCase getTestCase(List<String> input,List<String> output,long time){
        return new TestCase(input,output,time);
    }
     
    /**
    * this method return immutable object of {@code IntTestCase}
    using specified parameters.
    * @param input list of string which can be used as input of other program.
    * @param output list of string which is output of any program.
    * @param time execution time in milliseconds.
    * @param programID long programID.
    * @param index long index value.
    * @return Object of IntTestCase.
    */
    public static IntTestCase getTestCase(List<String> input,List<String> output
           ,long time,long programID,long index){
        return new TestCase(input,output,time,programID,index);
    }
     
}
/*
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
          Path dir=getDefaultProDir();
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
     
     /**
      * 
      * @return 
      
     public static List<IntProgramDetail> readProgramDetail(){
          Path dir=getDefaultProDir();
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
          Path dir=getDefaultProDir();
          return writeIntProgramDetail(obj,dir);     
     }
     
     public static boolean writeToTxt(IntProgramDetail obj)throws IOException{
          long pid=obj.getProgramID();
          Path dir=getDefaultProDir();
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
*/