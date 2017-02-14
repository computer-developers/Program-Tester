package lib.dT.problemManipulate;

import java.io.Serializable;
import java.util.*;
import static lib.dT.problemManipulate.ProgramIDGenerator.newProgramID;

/**
 *
 * @author Rushabh
 */
public class ProgramDetail implements IntProgramDetail, Serializable {
     private static final long serialVersionUID = 1L;
     static long getVersion(){
          return serialVersionUID;
     }
     long programId=-1;
     String title,input,output;
     List<String> description=new ArrayList<>(), sampleInput=new ArrayList<>(),
                  sampleOutput=new ArrayList<>();
     
     ProgramDetail(String title,List<String> description, String input, 
                    String output,List<String> sampleInput, List<String> sampleOutput){
          this.programId=newProgramID();
          this.title=title;
          this.description.addAll(description);
          this.input=input;
          this.output=output;
          this.sampleInput.addAll(sampleInput);
          this.sampleOutput.addAll(sampleOutput);
     }
     
     @Override
     public long getProgramID(){
          return programId;
     }
     
     /**
      * 
      * @return 
      */
     @Override
     public String getTitle(){
          return title;
     }
     
     /**
      * returns all input as copy of the list of string.
      * it returns null if not applicable.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string if applicable, null otherwise
      */
     @Override
     synchronized public List<String> getDescription() {
          List<String> l=new ArrayList<String>();
          l.addAll(description);
          return l;
     }

     /**
      * returns all input as copy of the list of string.
      * the original list remain unchanged if any changes performed in the list
        returned by this method.
      * @return output as list of string.
      */
     @Override
     public String getInput() {
          return input;
     }
     
     /**
      * it returns time taken by the process to generate {@code output} from
        {@code input}.
      * @return positive long time, negative value if not applicable.
      */
     @Override
     public String getOutput() {
          return output;
     }
     
     @Override
     synchronized public List<String> getSampleInput() {
          List<String> l=new ArrayList<String>();
          l.addAll(sampleInput);
          return l;
     }
     
     @Override
     synchronized public List<String> getSampleOutput() {
          List<String> l=new ArrayList<String>();
          l.addAll(sampleOutput);
          return l;
     }
}
