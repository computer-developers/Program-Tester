/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mainSer.userStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lib.logger.LogTools;

/**
 *
 * @author Neel Patel
 */
public class UserFactory {
     private UserFactory(){}
     private static final Map<Long,Integer> problems=new HashMap<>();
     private static final Set<UserStatus> user=new HashSet<UserStatus>();
     
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
          UserStatus u=new UserStatus(uName,passwd,new HashMap<>());
          u.addProgramId(problems.keySet().toArray(new Long[0]));
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
      * @param pid ProgramId
      * @param credit credit of the program.
      * @return true if successfully added, false otherwise
      */
     public static synchronized boolean addProblem(Long pid,Integer credit){
          if(problems.putIfAbsent(pid, credit)==null){
               user.parallelStream().forEach(i->i.addProgramId(pid));
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
               return null;
          if(u.getPasswd().equals(passwd))
               return u;
          else
               return null;
     }
     
     /**
      * this method process the log and update UserStatus related to log.
      * this method return true if log is successfully processed.
      * if the log is not processed properly by any reason (i.e malformed log),
        this method will not update anything and simply return false;
      * @param log String of log
      * @return true if processed successfully, false otherwise.
      */
     public static boolean processLog(String log){
          try{
               String uName=LogTools.getLogProperty(log, "username");
               String passwd=LogTools.getLogProperty(log, "password");
               long pid=Long.parseLong(LogTools.getLogProperty(log, "pid"));
               int status=Integer.parseInt(LogTools.getLogProperty(log, "status"));
               UserStatus u=(UserStatus)getUser(uName,passwd);
               return u.update(pid, status);
          }catch(Exception ex){
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
          if(problems.containsKey(pid))
               return problems.get(pid);
          return -1;
     }
}
