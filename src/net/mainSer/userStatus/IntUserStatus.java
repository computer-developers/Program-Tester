package net.mainSer.userStatus;

import java.util.Map;

/**
 * this Interface is used to remotely transfer data from main server.
 * @author Neel Patel
 */
public interface IntUserStatus {
     
     /**
      * @return Map object which map ProgramID to the Credit earned by user.
      */
     Map<Long,Integer> getAllProStatus();
     
     /**
      * this method returns the credit of the user at instance.
      * @return Credit earned by the User at the movement.
      */
     int getUserCredit();
     
     /**
      * @return the user name.
      */
     String getUName();
     
     /**
      * @return the password.
      */
     String getPasswd();
}
