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
package lib.dT.manipulate.comparators;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.*;

/**
 * this class contain different types of {@code BiPredicates} to compare Strings
 * @author Neel Patel
 */
public class StringComparators {
     private StringComparators(){}
     
     /**
      * @author Neel Patel
      */
     private static class ExactMatch implements BiPredicate<String,String>{
          private static BiPredicate<String,String> obj;
          public boolean test(String o1, String o2) {
               return o1.equals(o2);
          }
          
     }
     
     /**
      * @author Neel Patel
      */
     private static class IgnoreCase implements BiPredicate<String,String>{
          private static BiPredicate<String,String> obj;
          public boolean test(String o1, String o2) {
               return o1.equalsIgnoreCase(o2);
          }
     }
     
     /**
      * @author Neel Patel
      */
     private static class IgnoreWhiteSpace
               implements BiPredicate<String,String>{
          private static BiPredicate<String,String> obj;
          public boolean test(String o1, String o2) {
               List<String> l1=Arrays.asList(o1.split("\\s"));
               List<String> l2=Arrays.asList(o2.split("\\s"));
               return l1.equals(l2);
          }
     }
     
     /**
      * @author Neel Patel
      */
     private static class IgnoreWhiteSpaceAndCase
               implements BiPredicate<String,String>{
          private static BiPredicate<String,String> obj;
          public boolean test(String o1, String o2) {
               List<String> l1=Arrays.asList(o1.split("\\s"))
                         .parallelStream().map(s->s.toLowerCase())
                         .collect(Collectors.toList());
               List<String> l2=Arrays.asList(o2.split("\\s"))
                         .parallelStream().map(s->s.toLowerCase())
                         .collect(Collectors.toList());
               return l1.equals(l2);
          }
     }
     
     /**
      * return instance of BiPredicate to compare String.
      * BiPredicate instance returned by this method can be used as argument
        of different methods to compare string.
      * test method of the BiPredicate will return true if both the Strings are 
        equal, return false otherwise.
      * @return BiPredicate object
      */
     public static BiPredicate<String,String> getExactmatch(){
          if(ExactMatch.obj==null){
               synchronized(ExactMatch.class){
                    if(ExactMatch.obj==null)
                         ExactMatch.obj=new ExactMatch();
               }
          }
          return ExactMatch.obj;
     }
     
     /**
      * return instance of BiPredicate to compare String ignoring case.
      * BiPredicate instance returned by this method can be used as argument
        of different methods to compare string.
      * test method of the BiPredicate will return true if both the Strings are 
        equal ignoring case, returns false otherwise.
      * @return BiPredicate object
      */
     public static BiPredicate<String,String> getIgnoreCase(){
          if(IgnoreCase.obj==null){
               synchronized(IgnoreCase.class){
                    if(IgnoreCase.obj==null)
                         IgnoreCase.obj=new IgnoreCase();
               }
          }
          return IgnoreCase.obj;
     }
     
     /**
      * return instance of BiPredicate to compare String ignoring whitespace.
      * BiPredicate instance returned by this method can be used as argument
        of different methods to compare string.
      * test method of the BiPredicate will return true if both the Strings are 
        equal ignoring multiple whitespace, returns false otherwise.
        if there is multiple whitespace in between string then this method 
        encounter only one whitespace and compare those strings.<br>
      * {@code let consider string s1 and s2..
        s1="abc   d\ne\tf" &
        s2="abc e f".
        test method of the object return by this method encounters s1 and s2 is equal
        and return true.}<br>
      * @return BiPredicate object
      */
     public static BiPredicate<String,String> getIgnoreWhiteSpace(){
          if(IgnoreWhiteSpace.obj==null){
               synchronized(IgnoreWhiteSpace.class){
                    if(IgnoreWhiteSpace.obj==null)
                         IgnoreWhiteSpace.obj=new IgnoreWhiteSpace();
               }
          }
          return IgnoreWhiteSpace.obj;
     }
     
     /**
      * return instance of BiPredicate to compare String ignoring whitespace
        and case.
      * BiPredicate instance returned by this method can be used as argument
        of different methods to compare string.
      * test method of the BiPredicate will return true if both the Strings are 
        equal ignoring multiple whitespace and case, returns false otherwise.
        if there is multiple whitespace in between string then this method 
        encounter only one whitespace and compare those strings.<br>
      * {@code let consider string s1 and s2..
        s1="abc   d\ne\tf" &
        s2="aBc e f".
        test method of the object return by this method encounters s1 and s2 is equal
        and return true.}<br>
      * @return BiPredicate object
      */
     public static BiPredicate<String,String> IgnoreWhiteSpaceAndCase(){
          if(IgnoreWhiteSpaceAndCase.obj==null){
               synchronized(IgnoreWhiteSpaceAndCase.class){
                    if(IgnoreWhiteSpaceAndCase.obj==null)
                         IgnoreWhiteSpaceAndCase.obj=new IgnoreWhiteSpaceAndCase();
               }
          }
          return IgnoreWhiteSpaceAndCase.obj;
     }
     
}
