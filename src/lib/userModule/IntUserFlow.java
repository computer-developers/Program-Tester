package lib.userModule;

import java.io.Closeable;
import java.util.List;
import lib.logger.MyLogger;
import lib.userModule.result.IntLiveResultSet;
import lib.ui.*;
import lib.userModule.result.IntProgramState;

/**
 *
 * @author Neel Patel
 */
public interface IntUserFlow extends Closeable{
     
     /**
      * implementation of this method should register the User Interface.
      * recorded user interface should be used to interact with user.
      * @param ui object of User Interface. 
      */
     void register(IntUI ui);
     
     /**
      * implementation of this method should register the User Interface.
      * recorded user interface should be used to interact with user.
      * @param logger Object of MyLogger
      */
     void setLogger(MyLogger logger);
     
     /**
      * implementation of this method should execute the command
        {@code cmd} and with the {@code pid} specified.
      * {@code cmd} should not be contain relative paths. each and every
        path should be absolute.
      * call of this method should not take long time to terminate.
      * this method must return immediately.
      * this method can start parallel threads which can modify the
        {@code resultSet} return by this method.
      * @param pid Program ID for which the command is going to be executed.
      * @param cmd Command for execution.
      * @return {@code IntLiveResultSet}, containing the result of command.
      */
     IntLiveResultSet execute(long pid,String cmd);
     
     /**
      * implementation of this method should return all the
        {@code Problem Defination} as a list of {@code ProgramState}.
      * @return list of IntProgramState.
      */
     List<? extends IntProgramState> getAllProgramDetail();
     
     /**
      * {@inheritDoc}
      */
     @Override
     void close();
}
