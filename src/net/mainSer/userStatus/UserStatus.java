package net.mainSer.userStatus;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import lib.dT.problemManipulate.IntProgramDetail;
import static programtester.config.Configuration.TEST_PASS;
import static programtester.config.Configuration.TEST_PRESENT_ERROR;


/**
 *
 * @author Neel Patel
 */
public class UserStatus implements IntUserStatus{
     private final Map<Long,Integer> ps;
     private final List<IntProgramDetail> pc;
     private int credit;
     private final String uName,passwd;
     UserStatus(String uName,String passwd,List<IntProgramDetail> lpc,Map<Long,Integer> mps){
          this.pc=lpc;
          ps=new HashMap<>();
          ps.putAll(mps);
          pc.forEach(i->{
               ps.putIfAbsent(i.getProgramID(),0);
          });
          this.uName=uName;
          this.passwd=passwd;
          creditCal();
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public synchronized Map<Long, Integer> getAllProStatus() {
          return Collections.unmodifiableMap(ps);
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public synchronized int getUserCredit() {
          return credit;
     }
     
     /**
      * {@inheritDoc}
      */
     @Override
     public String getUName() {
          return uName;
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public String getPasswd() {
          return passwd;
     }
     
     /**
      * update the status of the problem.
      * return true if the credit is updated or already up to date.
      * return false if problemId is not exist in the system.
      * this method update credit of the problem if new credit is greater then
        the old credit and return true;
      * if old credit greater then the new credit then this method simply
        return true without changing anything.
      * @param problemId programId
      * @param status new credit
      * @return true if updated successfully, false otherwise.
      */
     public synchronized boolean update(long problemId,int status){
          if(!ps.containsKey(problemId))
               return false;
          if(ps.get(problemId)>status)
               return true;
          ps.replace(problemId,status);
          creditCal();
          return true;
     }
     
     /**
      * add programIds with the initial credit 0.
      * this method will not perform any change if the program id already exist.
      * this method will add all the programIds which is not already in system
        with credit 0
      * @param ar array of programIds
      */
     synchronized void addProgramId(IntProgramDetail pd){
          if(pc.contains(pd))
               return;
          pc.add(pd);
          ps.put(pd.getProgramID(),0);
     }
     
     /**
      * update user credit.
      * this method should be called when any changes performed in the credits
        of individual problems.
      */
     private synchronized void creditCal(){
          credit=0;
          pc.forEach(p->{
               int s=ps.getOrDefault(p.getProgramID(),0);
               if(s==TEST_PRESENT_ERROR)
                    credit+=(p.getCredit()-1);
               else if(s==TEST_PASS)
                    credit+=p.getCredit();
          });
     }
     
     /**
      * this method returns true if and only if the userID of
        the object o is same as this object
      * @param o object to be compared
      * @return ture if object is type of UserStatus and contain same userID.
      */
     @Override
     public boolean equals(Object o){
          if(o instanceof UserStatus){
               UserStatus s=(UserStatus)o;
               if(getUName().equals(s.getUName()))
                    return true;
          }
          return false;
     }

     /**
      * @return 1566161616
      */
     @Override
     public int hashCode() {
          return 1566161616;
     }
}
