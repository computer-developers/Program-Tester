/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mainSer.userStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Neel Patel
 */
public class UserFactory {
     private UserFactory(){}
     private static final Set<Long> problems=new HashSet<>();
     private static final Set<UserStatus> user=new HashSet<UserStatus>();
     
     public static synchronized boolean addUser(String uName,String passwd){
          UserStatus u=new UserStatus(uName,passwd,new HashMap<>());
          u.addProgramId(problems.toArray(new Long[0]));
          return user.add(u);
     }
     
     public static synchronized boolean addProblem(Long... ar){
          if(problems.addAll(Arrays.asList(ar))){
               user.parallelStream().forEach(i->i.addProgramId(ar));
               return true;
          }
          return false;
     }
     
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
}
