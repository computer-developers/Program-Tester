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
package programTester.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 *
 * @author Neel Patel
 */
public class Configurator {
    private Configurator(){}
    public static void init(){
        System.setProperty("sun.rmi.transport.proxy.connectTimeout","1800000");
        try {
            Path p=Paths.get("config.txt").toAbsolutePath();
            Files.lines(p).map(i->i.trim())
                    .filter(i->!i.startsWith("#"))
                    //.peek(i->System.out.println("peek :- "+i))
                    .forEach(i->proc(i));
            post();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
    private static void proc(String s){
        String ar[]=Arrays.stream(s.split(":-",2)).map(i->i.trim())
                .toArray(String[]::new);
        if(ar.length>=2){
            System.setProperty(ar[0], ar[1]);
        }
    }

    private static void post(){
        try{
            int i=Integer.parseInt(System.getProperty("parallel_thread",
                    System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism")));
            System.setProperty("java.util.concurrent.ForkJoinPool"
                                 + ".common.parallelism",""+i);
                         
        }catch(NumberFormatException e){}
                    
    }
}
/*
working_dir :- .\resources\
problem_dir :- .\resources\data
source_data_dir :- .\resources\data
local_logger_dir :- .\resources\
network_logger_ip :- 127.0.0.1
network_logger_port :- 8686



*/