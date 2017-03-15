/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.logger;

import java.util.Arrays;

/**
 *
 * @author Neel Patel
 */
public class LogTools {
     /**
      * this method return log property from the log string.
      * log string must be in the form of {@code P1=V1[,P2=V2]*}.
      * this method return the Value(V) of Property(p) specified by argument p.
      * if log String contain multiple pairs of property p then the value(v) from
        leftmost pair of property(p) will be returned.<br>
      * for example consider log string {@code p=v1,p=v2},method with parameter p
        will return v1 as the 'p=v1' is the first occurrence.<br>
      * Note:- this method ignore the whitespace between different elements of log
        String. this method will ignore the case will comparing property p.
      * @param log Log String
      * @param p Parameter
      * @return value of property p, null if not exist or Log string malformed.
      */
     public static String getLogProperty(String log,String p){
          try{
               String[] ar=Arrays.stream(log.split(";")).map(i->i.trim())
                              .toArray(String[]::new);
               for(String s:ar){
                    String[] a=Arrays.stream(log.split("=",2)).map(i->i.trim())
                                   .toArray(String[]::new);
                    if(a[0].equalsIgnoreCase(p))
                         return a[1];
               }
          }catch(Exception e){
               return null;
          }
          return null;
     }
}
