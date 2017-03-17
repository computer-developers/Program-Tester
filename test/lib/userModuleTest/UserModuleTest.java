/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModuleTest;

import lib.dT.problemManipulate.ProgramDetails;
import lib.ui.IntUI;
import lib.ui.cli.CliUser;
import lib.userModule.IntUserFlow;
import lib.userModule.local.SingleUserFlow;
import programtester.config.Configuration;
import static programtester.config.Configuration.getDefaultProDir;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class UserModuleTest {
     public static void main(String... arg){
          Configurator.init();
          //Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          System.out.println("Working Directory = " +System.getProperty("user.dir"));
          System.out.println("Data Directory = " +Configuration.getDefaultDir());
          System.out.println("Program Directory = " +getDefaultProDir());
          IntUserFlow uf=new SingleUserFlow();
          IntUI cli=new CliUser(uf);
          cli.start();
          //System.exit(0);
     }
}
