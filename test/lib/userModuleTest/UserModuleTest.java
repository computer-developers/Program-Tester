/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModuleTest;

import java.nio.file.Paths;
import lib.ui.IntUI;
import lib.ui.cli.CliUser;
import lib.userModule.IntUserFlow;
import lib.userModule.SingleUserFlow;
import lib.userModule.test.Test;

/**
 *
 * @author admin
 */
public class UserModuleTest {
     public static void main(String... arg){
          Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          System.out.println("Working Directory = " +System.getProperty("user.dir"));
          System.out.println("Data Directory = " +Test.getDefaultDir());
          IntUserFlow uf=new SingleUserFlow();
          IntUI cli=new CliUser(uf);
          cli.start();
     }
}
