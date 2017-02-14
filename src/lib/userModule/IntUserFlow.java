package lib.userModule;

import lib.userModule.result.IntLiveResultSet;
import lib.ui.*;

/**
 *
 * @author Parth Doshi
 */
public interface IntUserFlow {
    void register(IntUI ui);
    IntLiveResultSet execute(long pid,String cmd);
}
