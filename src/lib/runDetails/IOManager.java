package lib.runDetails;
import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.*;
import java.util.function.*;

/**
 * this is factory class provide various method to create object of 
   IntIODetail.
 * this class provide facility to create object of IntIODetail.
 * it also provide facility of writing & reading IntIODetail objects to & from file.
 * @author Neel Patel
 */
public class IOManager {
     private IOManager(){}
               
     /**
      * this method return immutable object of {@code IntIODetail}
        using specified parameters.
      * {@code getTime} & {@code programId} method of the object return by this 
        method always return -1
      * @param input list of string which can be used as input of other program
      * @param output list of string which is output of any program
      * @return object of IntIODetail.
      */
     public static IntIODetail getIODetail(List<String> input,List<String> output){
          return new IODetail(input,output);
     }
     
     /**
      * this method return immutable object of {@code IntIODetail}
        using specified parameters.
      * @param input object of IntInput.
      * @param output list of string which is output of any program.
      * @param time time taken for generate output for {@param input}.
      * @return object of IntIODetail.
      */
     public static IntIODetail getIODetail(IntInput input, List<String> output, long time){
          return getIODetail(input.getAllInput(),output,time,input.programID(),input.index());
     }
     
     /**
      * this method return immutable object of {@code IntIODetail}
        using specified parameters.
      * programId method of the object return by this method always return -1
      * @param input list of string which can be used as input of other program
      * @param output list of string which is output of any program
      * @param time execution time in milliseconds.
      * @return object of IntIODetail.
      */
     public static IntIODetail getIODetail(List<String> input,List<String> output,long time){
          return new IODetail(input,output,time);
     }
     
     /**
      * this method return immutable object of {@code IntIODetail}
        using specified parameters.
      * @param input list of string which can be used as input of other program.
      * @param output list of string which is output of any program.
      * @param time execution time in milliseconds.
      * @param programID long programID.
      * @param index long index value.
      * @return Object of IntIODetail.
      */
     public static IntIODetail getIODetail(List<String> input,List<String> output
               ,long time,long programID,long index){
          return new IODetail(input,output,time,programID,index);
     }
     
     /**
      * this method return object of {@code IntIODetail} from the file 
        specified by path.
      * this method read the object of type {@code IntIODetail} 
        form file specified by {@param filepath}.
      * the path specified by {@param filepath} must be absolute.
      * this method throw {@code IOException} if path is invalid or file not 
        found or invalid format or object can not be {@code deserialize} because
        of version difference of corresponding class.
      * @param filepath path object specifying the valid path of the file which 
        contain object.
      * @return object of type {@code IntIODetail} read from specified {@param filepath}.
      * @throws IOException if the error occur while reading object from file.
      */
     public static IntIODetail getIODetail(Path filepath)throws IOException{
          if(!filepath.isAbsolute())
               return null;
          if((!Files.exists(filepath))||Files.isDirectory(filepath))
               return null;
          FileInputStream f=new FileInputStream(filepath.toFile());
          IntIODetail obj=ObjectHandler.readObj(f);
          if(obj!=null)
               return obj;
          throw new IOException();
     }
     
     /**
      * write the object in separate file in the directory specified by
        the path object {@param dir}.
      * return {@code true} if the object is written successfully otherwise 
        return {@code false}.
      * the path specified by {@param dir} must be absolute.
      * the new file is created in the directory specified by {@param dir} with
        the formated name as follows..<br>
      * {@code vVpPiI.data} where {@code 'V'} specify the {@code version} of the
        class if available, otherwise {@code 'NA'}, {@code 'P'} specify the 
        {@code programID} if available, otherwise {@code 'NA'},
        {@code 'I'} specify the index if available, otherwise {@code 'NA'}.
      * @param obj object to be written in file.
      * @param dir directory in which the object is to be written.
      * @return true if the object written in file successfully, false otherwise. 
      */
     public static boolean writeIntIODetail(IntIODetail obj,Path dir){
          if(!dir.isAbsolute())
               return false;
          if(!Files.isDirectory(dir))
               return false;
          String name="";
          {//generate the formated file name. 
               name+="v";
               if(obj instanceof IODetail)
                    name+=IODetail.getVersion();
               else
                    name+="NA";
               name=name+"p";
               if(obj.programID()>=0)
                    name+=obj.programID();
               else
                    name+="NA";
               name+="i";
               if(obj.index()>=0)
                    name+=obj.index();
               else
                    name+="NA";
               name+=".data";
          }
          Path f=dir.resolve(name);
          try {
               FileOutputStream fout=new FileOutputStream(f.toFile());
               return ObjectHandler.writeObj(fout, obj);
          } catch(IOException ex) {
               return false;
          }
     }
     
     /**
      * this method compare the IntInput Objects.
      * if {@code programID} & {@code index} is available than this method compare
        those values & return false if anyone value for both objects are not same.
      * if {@code programID} & {@code index} are available but same then this
        method compare the list of sting return by {@code getAllInput} method & 
        return true if both are same, otherwise returns false.
      * @param a object going to be compare with another one.
      * @param b object going to be compare with another one.
      * @return result of comparison performed on certain parameters.
      */
     public static boolean isEqual(IntInput a,IntInput b){
          if(a.programID()>0 && b.programID()>0 && a.programID()!=b.programID())
               if(a.index()>0 && b.index()>0 && a.index()!=b.index())
                    return false;
          return a.getAllInput().equals(b.getAllInput());
     }
}
