
package lib.userModule;

import lib.ui.*;

/**
 *
 * @author Parth Doshi
 */
public interface IntUserFlow {
    void register(IntUI ui);
    IntResultSet execute(String cmd, int pid);
    
}
