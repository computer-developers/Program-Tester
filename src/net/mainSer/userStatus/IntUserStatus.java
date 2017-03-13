package net.mainSer.userStatus;

import java.util.Map;

/**
 *
 * @author Neel Patel
 */
public interface IntUserStatus {
     Map<Long,Integer> getAllProStatus();
     int getUserCredit();
     String getUName();
     String getPasswd();
}
