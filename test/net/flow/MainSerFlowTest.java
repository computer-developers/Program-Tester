/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow;

import net.flow.mainSerFlows.MainSerFlow;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class MainSerFlowTest {
     public static void main(String... arg){
          Configurator.init();
          System.out.println("Main Ser");
          MainSerFlow r=new MainSerFlow();
          r.start();
          r.join();
          System.exit(0);
     }
}
