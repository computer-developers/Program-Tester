package lib.dT.problemManipulate;
import java.util.*;

/**
 * Implementation of this interface is used to store details of program's aim
 * @author Rushabh
 */
public interface IntProgramDetail {
     String getTitle();
     List<String> getDescription();
     String getInput();
     String getOutput();
     List<String> getSampleInput();
     List<String> getSampleOutput();
     long getProgramID();
}
