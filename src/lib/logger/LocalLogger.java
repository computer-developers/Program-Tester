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
package lib.logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import static programtester.config.Configuration.getDefaultLogDir;

/**
 *
 * @author Neel Patel
 */
public class LocalLogger extends MyLogger{
//local part
     
          
     public LocalLogger(String name) throws FileNotFoundException{
          this(getDefaultLogDir().resolve(name));
     }
     
     public LocalLogger(Path filePath) throws FileNotFoundException{
          super(new FileOutputStream(filePath.toFile(),true));
     }
     
     
     
}
     /*
     String formate="%l";
     private Runnable[] r;
     public synchronized void setFormater(String f,Runnable... r){
          this.r=r.clone();
          this.formate=f;
     }
     private String formator(String... lr){
          String sb=formate;
          String[] ar=sb.split("%l");
          for(int i=0;i<ar.length-1;i++)
               ar[i]=ar[i]+lr[i];
          Arrays.spliterator(lr,ar.length-1,lr.length)
                  .forEachRemaining(j->ar[ar.length-2]+=sep+j);
          String front=Arrays.stream(ar).reduce("",(x,y)->x+y);
          
          return sb;
     }

     */