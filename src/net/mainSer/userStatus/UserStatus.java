package net.mainSer.userStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.Collections;


/**
 *
 * @author Neel Patel
 */
public class UserStatus implements IntUserStatus, Serializable{
     private final Map<Long,Integer> ps;
     private int credit;
     private final String uName,passwd;
     UserStatus(String uName,String passwd,Map<Long,Integer> ps){
          this.ps=ps;
          this.uName=uName;
          this.passwd=passwd;
     }

     @Override
     public synchronized Map<Long, Integer> getAllProStatus() {
          return Collections.unmodifiableMap(ps);
     }

     @Override
     public synchronized int getUserCredit() {
          return credit;
     }
     
     @Override
     public String getUName() {
          return uName;
     }

     @Override
     public String getPasswd() {
          return passwd;
     }
     
     public synchronized boolean update(Long problemId,int credit){
          if(!ps.containsKey(problemId))
               return false;
          if(ps.get(problemId)>credit)
               return true;
          ps.replace(problemId,credit);
          creditCal();
          return true;
     }
     
     synchronized void addProgramId(Long... ar){
          Arrays.stream(ar)
                  .filter(i->(!ps.containsKey(i)))
                  .forEach(i->{
                       ps.put(i,0);
                  });
     }
     
     private synchronized void creditCal(){
          credit=0;
          ps.forEach((x,y)->{
               if(y>0)
                    credit+=y;
          });
     }
     
     @Override
     public boolean equals(Object o){
          if(o instanceof UserStatus){
               UserStatus s=(UserStatus)o;
               if(getUName().equals(s.getUName()))
                    return true;
          }
          return false;
     }

     @Override
     public int hashCode() {
          return 1566161616;
     }
}
