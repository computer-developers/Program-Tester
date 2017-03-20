/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow;

import java.nio.file.Paths;
import net.flow.logSerFlow.RemoteLogFlow;
import static programtester.config.Configuration.getDefaultLogDir;
import static programtester.config.Configuration.setDefaultLogDir;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class MainLogFlowTest {
     public static void main(String... arg){
          Configurator.init();
          setDefaultLogDir(Paths.get("resources/logser").toAbsolutePath());
          System.out.println("path :- "+getDefaultLogDir());
          RemoteLogFlow r=new RemoteLogFlow();
          r.start();
          r.join();
          System.exit(0);
     }
}
