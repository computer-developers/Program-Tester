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
package net.dataSer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import static programtester.config.Configuration.getDefaultDir;
import static programtester.config.Configuration.getDefaultProDir;

/**
 *
 * @author Neel Patel
 */
public class DataSerHandler {
     private DataSerHandler(){}
     
     /**
      * this method return object of type IntDataSer.
      * this method create the object using {@code Default Problem Directory}
        and {@code Default Data Directory} to create the object.
      * this method read all the files from the specified directory to create
        the object.
      * this method return null if any exception occur while creating a object.
      * @return object if created successfully, null otherwise.
      */
     public static IntDataSer makeObject(){
          try {
               Path td=getDefaultDir();
               Path pd=getDefaultProDir();
               if(!(Files.isDirectory(pd)&&Files.isDirectory(td)))
                    return null;
               Map<String,byte[]> pm=getAllFiles(pd);
               Map<String,byte[]> tm=getAllFiles(td);
               IntDataSer a=new DataSer(pm,tm);
               return a;
          } catch (Exception ex) {
               System.out.println("Object handler :- "+ex.getMessage());
               ex.printStackTrace();
               return null;
          }
     }
     
     /**
      * @param dir path of the directory
      * @return return map object of all the files in the directory at level 0,
        null otherwise.
      */
     private static Map<String,byte[]> getAllFiles(Path dir){
          try {
               if(!Files.isDirectory(dir))
                    return null;
               Map<String,byte[]> m=new HashMap<>();
               Files.list(dir).filter(i->!Files.isDirectory(i))
                         .forEach(p->{
                              try {
                                   m.put(p.getFileName().toString(),
                                           Files.readAllBytes(p));
                              } catch (IOException ex) {
                              }
                         });
               return m;
          } catch (Exception ex) {
               return null;
          }
     }
}
