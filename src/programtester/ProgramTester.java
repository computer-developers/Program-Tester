package programtester;

import lib.dT.problemManipulate.ProgramDetails;
import lib.ui.IntUI;
import lib.ui.cli.CliUser;
import lib.userModule.IntUserFlow;
import lib.userModule.SingleUserFlow;
import lib.userModule.test.Test;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class ProgramTester {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
          mainCli();
     }
     
     public static void mainCli(String... ar){
          Configurator.init();
          //Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          //System.out.println("Working Directory = " +System.getProperty("user.dir"));
          //System.out.println("Data Directory = " +Test.getDefaultDir());
          //System.out.println("Program Directory = " +ProgramDetails.getDefaultDir());
          IntUserFlow uf=new SingleUserFlow();
          IntUI cli=new CliUser(uf);
          cli.start();
          //System.exit(0);
     }
}
