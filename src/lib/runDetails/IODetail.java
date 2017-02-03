package lib.runDetails;
import java.util.*;
import java.io.*;

/**
 * this class is used to store the data of single run of any program.
 * object of this class can easily be written to the file or passed in stream using
   {@code ObjectOutputStream} & read using {@code ObjectInputStream}.
 * version of class refers the serialVersionUID.
 * if the serialVersionUID differs at ObjectInputStram from ObjectOutputStream 
   then the Object can not be read by ObjectInputStream.
 * @version 1
 * @author Neel Patel
 */
class IODetail implements IntIODetail, Serializable{
     private static final long serialVersionUID = 1L;
     static long getVersion(){
          return serialVersionUID;
     }
     long time=-1;
     long programId=-1;
     long index=-1;
     List<String> input=new ArrayList<>(),output=new ArrayList<>();
     
     IODetail(List<String> input){
          this.input.addAll(input);
     }
     
     IODetail(List<String> input,List<String> output){
          this(input);
          if(output!=null)
               this.output.addAll(output);
          else
               this.output=null;
     }
     
     IODetail(List<String> input,List<String> output, long time){
          this(input,output);
          this.time=time;
     }
    
     IODetail(List<String> input,List<String> output, long time, long programId, long index){
          this(input,output,time);
          this.programId=programId;
          this.index=index;
     }
     
     /**
      * returns the programId, for which the {@code input} & {@code output} related.
      * the programId is a unique identification of program type for which the 
        the list of string return by the method {@link getAllInput} can be used 
        as input.
      * the programId refers to the problem definition related to the input & 
        corresponding output.
      * @return positive long programId, negative value if not applicable
      */
     @Override
     public long programID(){
          return programId;
     }
     
     /**
      * return index of the {@code input} & corresponding {@code output}
        if applicable.
      * index is unique serial number to identify the test {@code input}
        & {@code output} for the program corresponding to the {@code programId}
      * @return positive long index, negative value if not applicable
      */
     @Override
     public long index(){
          return index;
     }
     
     /**
      * returns all input as copy of the list of string.
      * it returns null if not applicable.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string if applicable, null otherwise
      */
     @Override
     synchronized public List<String> getAllOutput() {
          if(output==null)
               return null;
          List<String> l=new ArrayList<String>();
          l.addAll(output);
          return l;
     }

     /**
      * returns all input as copy of the list of string.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string.
      */
     @Override
     synchronized public List<String> getAllInput() {
          List<String> l=new ArrayList<String>();
          l.addAll(input);
          return l;
     }
     
     /**
      * it returns time taken by the process to generate {@code output} from
        {@code input}.
      * @return positive long time, negative value if not applicable.
      */
     @Override
     public long getTime() {
          return time;
     }
}
