/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/
package modul.com.userstatus;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import lib.problemDefination.IntProgramDetail;
import static programTester.config.Configuration.TEST_PASS;
import static programTester.config.Configuration.TEST_PRESENT_ERROR;

/**
 *
 * @author Neel Patel
 */
public class UserStatusAdapter implements IntUserStatus{
     private static final long serialVersionUID = 1L;
     static long getVersion(){
          return serialVersionUID;
     }
     private final Map<Long,Integer> ps;
     private final Map<Long,Integer> pc;
     private int credit;
     private final String uName,passwd;
     public UserStatusAdapter(String uName,String passwd,List<IntProgramDetail> lpc,Map<Long,Integer> mps){
          this.pc=new HashMap<>();
          for(IntProgramDetail x:lpc){
              pc.put(x.getProgramID(), x.getCredit());
          }
          ps=new HashMap<>();
          ps.putAll(mps);
          pc.forEach((p,c)->{
               ps.putIfAbsent(p,0);
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
     public synchronized void addProgramId(IntProgramDetail pd){
          if(pc.containsKey(pd.getProgramID()))
               return;
          pc.put(pd.getProgramID(),pd.getCredit());
          ps.put(pd.getProgramID(),0);
     }
     
     /**
      * update user credit.
      * this method should be called when any changes performed in the credits
        of individual problems.
      */
     private synchronized void creditCal(){
          credit=0;
          pc.forEach((p,c)->{
               int s=ps.getOrDefault(p,0);
               if(s==TEST_PRESENT_ERROR)
                    credit+=(c-1);
               else if(s==TEST_PASS)
                    credit+=c;
          });
     }
     
     @Override
     public int getEarnedProblemCredit(long pid) {
         int t=0;
         int s=ps.getOrDefault(pid,0);
         if(s==TEST_PRESENT_ERROR)
            t=pc.getOrDefault(s,1)-1;
         else if(s==TEST_PASS)
            t=pc.getOrDefault(s,0);
         return t;
     }
     
     /**
      * this method returns true if and only if the userID of
        the object o is same as this object
      * @param o object to be compared
      * @return ture if object is type of UserStatusAdapter and contain same userID.
      */
     @Override
     public boolean equals(Object o){
          if(o instanceof UserStatusAdapter){
               UserStatusAdapter s=(UserStatusAdapter)o;
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
