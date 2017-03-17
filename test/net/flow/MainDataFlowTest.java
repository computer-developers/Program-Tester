/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow;

import net.flow.dataSerFlow.MainDataSerFlow;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class MainDataFlowTest {
     public static void main(String... arg){
          Configurator.init();
          MainDataSerFlow r=new MainDataSerFlow();
          r.start();
          r.join();
          System.exit(0);
     }
}
