/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow;

import java.nio.file.Paths;
import net.flow.mainSerFlows.MainSerFlow;
import static programtester.config.Configuration.setDefaultProDir;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class MainSerFlowTest {
     public static void main(String... arg){
          Configurator.init();
          setDefaultProDir(Paths.get("resources/datapro").toAbsolutePath());
          System.out.println("Main Ser");
          MainSerFlow r=new MainSerFlow();
          r.start();
          r.join();
          System.exit(0);
     }
}
