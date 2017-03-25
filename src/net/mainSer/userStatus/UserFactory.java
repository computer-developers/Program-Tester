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
package net.mainSer.userStatus;

import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lib.dT.problemManipulate.IntProgramDetail;
import lib.dT.problemManipulate.ProgramDetails;
import lib.logger.LogTools;
import net.UrlTools;
import net.logSer.IntLogProc;
import net.logSer.IntRemoteLog;
import static programtester.config.Configuration.TEST_FAIL;
import static programtester.config.Configuration.TEST_FILE_ERROR;
import static programtester.config.Configuration.TEST_PASS;
import static programtester.config.Configuration.TEST_PRESENT_ERROR;
import static programtester.config.Configuration.TEST_TIME_ERROR;
import static programtester.config.Configuration.getDefaultRMIPort;
import static programtester.config.Configuration.getDefaultUserDetailPath;

/**
 *
 * @author Neel Patel
 */
public class UserFactory {
     private UserFactory(){}
     private static final List<IntProgramDetail> problems=
             Collections.synchronizedList(new ArrayList<>());
     private static final Set<UserStatus> user=
             Collections.synchronizedSet(new HashSet<UserStatus>());

     /**
      * this method initialize the user status logger.
      * @param lp URI of remote object of IntLogProc.
      * @return URI of user status logger, null otherwise.
      */
     public static synchronized String init(String lp){
          try {
               IntRemoteLog ir=new UserStatusLog();
               int port=getDefaultRMIPort();
               String uri=UrlTools.registerObj(ir, port,"userState");
               ((UserStatusLog)ir).setUrl(uri);
               
               readProgramDetails();
               readUserDetail(getDefaultUserDetailPath());
               System.out.println("user factory uri :- "+uri);
               new Thread(()->proBack(lp)).start();
               return uri;
          } catch (Exception ex) {
               return null;
          }
     }
     
     /**
      * add new user if already not exist system.
      * this method return true if new user added successfully, false otherwise.
      * if the user having same user name is already exist then this method
        will not add new user and return false;
      * @param uName user name
      * @param passwd password
      * @return true if user added successfully, false otherwise.
      */
     public static synchronized boolean addUser(String uName,String passwd){
          UserStatus u=new UserStatus(uName,passwd,problems,new HashMap<>());
          return user.add(u);
     }
     
     /**
      * add problem if already not exist in system.
      * this method add new problem with specified credit in the system.
      * it will return true if the problem already not in the system and
        added successfully.
      * it will return false if the problem is not exist in the system.<br>
      * Note:-if the problem is already in the system then it will not make any
        change and simply return false;
      * @param pd object of type IntProgramDetail
      * @return true if successfully added, false otherwise
      */
     public static synchronized boolean addProblem(IntProgramDetail pd){
          if(!problems.contains(pd)){
               problems.add(pd);
               user.parallelStream().forEach(i->i.addProgramId(pd));
               return true;
          }
          return false;
     }
     
     /**
      * this method return object of {@code IntUserStatus}.
      * this method return return object of {@code IntUserStatus} from the
        system containing same username and password.
      * this method returns {@code null} if such a object is not exist in the
        system.
      * @param uName username
      * @param passwd password
      * @return object of {@code IntUserStatus} if exist, null otherwise.
      */
     public static synchronized IntUserStatus getUser(String uName,String passwd){
          UserStatus u=user.parallelStream()
                         .filter(i->i.getUName().equals(uName))
                         .findAny().orElse(null);
          if(u==null)
               System.out.println("null user state");
          System.out.println("user quary :- "+u.getUName()+";"+u.getPasswd());
          if(u==null)
               return null;
          System.out.println("check1");
          if(u.getPasswd().equals(passwd)){
               System.out.println("check2");
               return u;
          }
          else{
               System.out.println("err check");
               return null;
          }
     }
     
     /**
      * this method return object of {@code IntUserStatus}.
      * this method return return object of {@code IntUserStatus} from the
        system containing same username.
      * this method returns {@code null} if such a object is not exist in the
        system.
      * @param uName username
      * @return object of {@code IntUserStatus} if exist, null otherwise.
      */
     public static synchronized IntUserStatus getUser(String uName){
          UserStatus u=user.parallelStream()
                         .filter(i->i.getUName().equals(uName))
                         .findAny().orElse(null);
          if(u==null)
               return null;
          return u;
     }
     
     /**
      * this method process the log and update UserStatus related to log.
      * this method return true if log is successfully processed.
      * if the log is not processed properly by any reason (i.e malformed log),
        this method will not update anything and simply return false;
      * @param log String of log
      * @return true if processed successfully, false otherwise.
      */
     public static synchronized boolean processLog(String log){
          try{
               String uName=LogTools.getLogProperty(log, "username");
               //String passwd=LogTools.getLogProperty(log, "password");
               long pid=Long.parseLong(LogTools.getLogProperty(log, "pid"));
               int status=Integer.parseInt(LogTools.getLogProperty(log, "state"));
               UserStatus u=(UserStatus)getUser(uName);
               if(status==TEST_PASS)
                    return u.update(pid, TEST_PASS);
               else if(status==TEST_PRESENT_ERROR)
                    return u.update(pid, TEST_PRESENT_ERROR);
               else if(status==TEST_TIME_ERROR)
                    return u.update(pid, 0);
               else if(status==TEST_FAIL)
                    return u.update(pid, 0);
               else if(status==TEST_FILE_ERROR)
                    return u.update(pid, 0);
               else return false;
          }catch(Exception ex){
               System.out.println("Log Processing fail :- "+log);
               return false;
          }
     }
     
     /**
      * return the credit of particular problem from the system.
      * if the problem does not exist in the system then this method return -1.
      * @param pid programId.
      * @return credit of the program.
      */
     public static int getCredit(long pid){
          return problems.stream().filter(i->i.getProgramID()==pid)
                  .mapToInt(i->i.getCredit()).findAny().orElse(-1);
     }
     
     public static synchronized boolean logHandle(String s){
          return processLog(s);
     }
     
     public static void proBack(String uri){
          try {
               IntLogProc lp=(IntLogProc)Naming.lookup(uri);
               List<String> l=lp.getLogs(null,null);
               long c=l.stream().peek(i->processLog(i)).count();
               System.out.println("Log recovered "+c);
          } catch (Exception ex) {
               System.err.println("Error in log recovery!!");
          }
     }

     public static List<String> getAllUser(){
          return user.stream().map(i->i.getUName())
                    .sorted().collect(Collectors.toList());
     }
     
     public static List<IntProgramDetail> getAllProblems(){
          return Collections.unmodifiableList(problems);
     }
     
     static boolean readUserDetail(Path file){
          System.out.println("start readUserDetail. "+file);
          try {
               Files.lines(file).peek(System.out::println).peek(l->{
                    String s[]=Arrays.stream(l.split("=",2)).map(i->i.trim())
                                   .toArray(String[]::new);
                    addUser(s[0],s[1]);
               }).count();
               return true;
          } catch (Exception ex) {
               System.err.println("readUserdetail :- "+ex);
               //ex.printStackTrace();
               return false;
          }
     }
     
     static void readProgramDetails(){
          ProgramDetails.readProgramDetail().forEach(i->addProblem(i));
     }
}
