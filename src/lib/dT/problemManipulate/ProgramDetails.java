package lib.dT.problemManipulate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rushabh
 */
public class ProgramDetails {
     
     public static IntProgramDetail parseProgramDetail(Path filepath)
             throws IOException{
          if(!filepath.isAbsolute())
               return null;
          if((!Files.exists(filepath))||Files.isDirectory(filepath))
               return null;
          List<String> temp=Files.readAllLines(filepath);
          int i=0;
          String title="",input="",output="";
          List<String> description=new ArrayList<>(), sampleInput=new ArrayList<>(),
                       sampleOutput=new ArrayList<>();
          try{
               for(;;){
                    switch(temp.get(i)){
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
                         default:
                              i++;
                    }
               }
          }
          catch(IndexOutOfBoundsException e){}
          return new ProgramDetail(title,description,input,output,sampleInput,sampleOutput);
     }
     
}