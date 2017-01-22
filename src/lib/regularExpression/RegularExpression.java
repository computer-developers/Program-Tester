package lib.regularExpression;
/**
 *
 * @author admin
 */

import java.util.*;
public class RegularExpression {
     static class Part{
          int min=1;
          int max=1;
          String s;
          Part(String s){
               this.s=s;
          }
     }
     
     List<Part> li=new ArrayList<>();
     public RegularExpression(String reg)throws IllegalArgumentException{
          String s;
          char[] arc=reg.toCharArray();
          try{
               for(int i=0;i<arc.length;i++){//replace charactor after \. 
                    if(arc[i]=='\\'){
                         arc[i+1]+=1000;
                    }
               }
               List<String> l=Arrays.asList(new String(arc).split("."));
               
          }catch(ArithmeticException ae){
               throw new IllegalArgumentException();
          }
     }
}