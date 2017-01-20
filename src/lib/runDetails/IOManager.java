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
      * this method return object of IntIODetail using specified parameters.
      * getTime & programId method of the object return by this method always return -1
      * getName method of the object return by this method always return 'Default'.
      * @param input list of string which can be used as input of other program
      * @param output list of string which is output of any program
      * @return 
      */
     public static IntIODetail getIODetail(List<String> input,List<String> output){
          return new DummyIODetail();
     }
     /**
      * this method return object of IntIODetail using specified parameters.
      * programId method of the object return by this method always return -1
      * getName method of the object return by this method always return 'Default'. 
      * @param input list of string which can be used as input of other program
      * @param output list of string which is output of any program
      * @param time execution time in milliseconds.
      * @return 
      */
     public static IntIODetail getIODetail(List<String> input,List<String> output,long time){
          return new DummyIODetail();
     }
     
     /**
      * this method return object of IntIODetail from the file specified by path.
      * this method read the object of type IntIODetail form file specified by path.
      * this method throw IOException if path is invalid or file not found or invalid format.
      * @param filepath path object specifing the valid path of the file which contain object.
      * @return object of type IntIODetail readed from specified filepath.
      */
     public static IntIODetail getIODetail(Path filepath)throws IOException{
          return new DummyIODetail();
     }
     
     /**
      * write the object in separate file in the directory specified by the path object dir.
      * return true if the object is written successfully otherwise return false.
      * @param obj object to be written in file.
      * @param dir directory in which the object is to be written.
      * @return 
      */
     public static boolean writeIntIODetail(IntIODetail obj,Path dir){
          return false;
     }
}
