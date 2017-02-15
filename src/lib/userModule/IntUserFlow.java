package lib.userModule;

import java.util.List;
import lib.userModule.result.IntLiveResultSet;
import lib.ui.*;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Parth Doshi
 */
public interface IntUserFlow {
    void register(IntUI ui);
    IntLiveResultSet execute(long pid,String cmd);
    List<? extends IntProgramState> getAllProgramDetail();
}
