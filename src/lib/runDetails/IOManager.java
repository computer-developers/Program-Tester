package lib.runDetails;
import java.util.*;
import java.nio.file.*;
import java.io.*;
/**
 *
 * @author admin
 */
public class IOManager {
     /**
      * this method return immutable object of {@code IntIODetail} using specified parameters.
      * {@code getTime} & {@code programId} method of the object return by this method always return -1
      * @param input list of string which can be used as input of other program
      * @param output list of string which is output of any program
      * @return 
      */
     public static IntIODetail getIODetail(List<String> input,List<String> output){
          return new IODetail(input,output);
     }
     
     /**
      * this method return object of {@code IntIODetail} using specified parameters.
      * programId method of the object return by this method always return -1
      * @param input list of string which can be used as input of other program
      * @param output list of string which is output of any program
      * @param time execution time in milliseconds.
      * @return 
      */
     public static IntIODetail getIODetail(List<String> input,List<String> output,long time){
          return new IODetail(input,output,time);
     }
     
     /**
      * this method return object of {@code IntIODetail} from the file specified by path.
      * this method read the object of type {@code IntIODetail} 
        form file specified by {@param filepath}.
      * this method throw {@code IOException} if path is invalid or file not found or invalid format.
      * @param filepath path object specifying the valid path of the file which contain object.
      * @return object of type {@code IntIODetail} read from specified {@param filepath}.
      */
     public static IntIODetail getIODetail(Path filepath)throws IOException{
          return new IODetail(null,null);
     }
     
     /**
      * write the object in separate file in the directory specified by the path object {@param dir}.
      * return {@code true} if the object is written successfully otherwise return {@code false}.
      * @param obj object to be written in file.
      * @param dir directory in which the object is to be written.
      * @return 
      */
     public static boolean writeIntIODetail(IntIODetail obj,Path dir){
          return false;
     }
}
