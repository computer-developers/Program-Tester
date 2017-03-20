/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow;

import java.nio.file.Paths;
import net.flow.dataSerFlow.MainDataSerFlow;
import static programtester.config.Configuration.getDefaultDir;
import static programtester.config.Configuration.getDefaultProDir;
import static programtester.config.Configuration.setDefaultDir;
import static programtester.config.Configuration.setDefaultProDir;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class MainDataFlowTest {
     public static void main(String... arg){
          Configurator.init();
          //setDefaultLogDir(Paths.get("datalog").toAbsolutePath());
          setDefaultDir(Paths.get("resources/datatest").toAbsolutePath());
          setDefaultProDir(Paths.get("resources/datapro").toAbsolutePath());
          System.out.println("Pro :- "+getDefaultProDir());
          System.out.println("Data :- "+getDefaultDir());
          System.out.println("Main Data Ser");
          MainDataSerFlow r=new MainDataSerFlow();
          r.start();
          r.join();
          System.exit(0);
     }
}
