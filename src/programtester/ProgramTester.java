package programtester;

import java.io.FileNotFoundException;
import lib.adminModule.AdminFlow;
import lib.logger.LocalLogger;
import lib.logger.MyLogger;
import lib.ui.IntUI;
import lib.ui.cli.CliUser;
import lib.userModule.IntUserFlow;
import lib.userModule.SingleUserFlow;
import programtester.config.Configurator;

/**
 *
 * @author Neel Patel
 */
public class
ProgramTester {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
          //mainUserCli();
          mainAdminCli();
     }
     
     public static void mainUserCli(){
          Configurator.init();
          
          //Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          //System.out.println("Working Directory = " +System.getProperty("user.dir"));
          //System.out.println("Data Directory = " +Test.getDefaultDir());
          //System.out.println("Program Directory = " +ProgramDetails.getDefaultDir());
          IntUserFlow uf=new SingleUserFlow();
          IntUI cli=new CliUser(uf);
          try {
               MyLogger l=new LocalLogger("log.txt");
               l.setSep(",");
               uf.setLogger(l);
          } catch (FileNotFoundException ex) {
               System.out.println("error in log file.");
          }
          cli.start();
          //System.exit(0);
     }
     
     public static void mainAdminCli(){
          Configurator.init();
          new AdminFlow().start();
     }
}
