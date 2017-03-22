package programtester;

import java.io.FileNotFoundException;
import lib.adminModule.AdminFlow;
import lib.logger.LocalLogger;
import lib.logger.MyLogger;
import lib.ui.IntUI;
import lib.ui.cli.CliUser;
import lib.ui.gui.UserGUI;
import lib.userModule.IntUserFlow;
import lib.userModule.local.SingleUserFlow;
import lib.userModule.net.NetUserFlow;
import net.flow.dataSerFlow.MainDataSerFlow;
import net.flow.logSerFlow.RemoteLogFlow;
import net.flow.mainSerFlows.MainSerFlow;
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
          //mainUserGui();
          //mainUserCli();
          if(args.length>0&&args[0].equalsIgnoreCase("admin"))
               mainAdminCli();
          else if(args.length>0&&args[0].equalsIgnoreCase("localuser"))
               mainUserGui();
          else if(args.length>0&&args[0].equalsIgnoreCase("localusercli"))
               mainUserCli();
          else if(args.length>0&&args[0].equalsIgnoreCase("localusercli"))
               mainUserCli();
          else mainNetUserGui();
     }
     
     public static void mainUserCli(){
          Configurator.init();
          
          //Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          //System.out.println("Working Directory = " +System.getProperty("user.dir"));
          //System.out.println("Data Directory = " +Test.getDefaultDir());
          //System.out.println("Program Directory = " +ProgramDetails.getDefaultDir());
          IntUserFlow uf=new SingleUserFlow();
          try {
               MyLogger l=new LocalLogger("log.txt");
               l.setSep(",");
               uf.setLogger(l);
          } catch (FileNotFoundException ex) {
               System.out.println("error in log file.");
          }
          IntUI cli=new CliUser(uf);
          cli.start();
          //System.exit(0);
     }
     public static void mainUserGui(){
          Configurator.init();
          
          //Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          //System.out.println("Working Directory = " +System.getProperty("user.dir"));
          //System.out.println("Data Directory = " +Test.getDefaultDir());
          //System.out.println("Program Directory = " +ProgramDetails.getDefaultDir());
          IntUserFlow uf=new SingleUserFlow();
          try {
               MyLogger l=new LocalLogger("log.txt");
               l.setSep(",");
               uf.setLogger(l);
          } catch (FileNotFoundException ex) {
               System.out.println("error in log file.");
          }
          IntUI cli=new UserGUI(uf);
          cli.start();
          //System.exit(0);
     }
     public static void mainNetUserGui(){
          Configurator.init();
          
          //Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          //System.out.println("Working Directory = " +System.getProperty("user.dir"));
          //System.out.println("Data Directory = " +Test.getDefaultDir());
          //System.out.println("Program Directory = " +ProgramDetails.getDefaultDir());
          IntUserFlow uf=new NetUserFlow();
          try {
               MyLogger l=new LocalLogger("log.txt");
               l.setSep(",");
               uf.setLogger(l);
          } catch (FileNotFoundException ex) {
               System.out.println("error in log file.");
          }
          IntUI cli=new UserGUI(uf);
          cli.start();
          //System.exit(0);
     }
     public static void mainNetUserCli(){
          Configurator.init();
          
          //Test.setDefaultDir(Paths.get("Data").toAbsolutePath());
          //System.out.println("Working Directory = " +System.getProperty("user.dir"));
          //System.out.println("Data Directory = " +Test.getDefaultDir());
          //System.out.println("Program Directory = " +ProgramDetails.getDefaultDir());
          IntUserFlow uf=new NetUserFlow();
          try {
               MyLogger l=new LocalLogger("log.txt");
               l.setSep(",");
               uf.setLogger(l);
          } catch (FileNotFoundException ex) {
               System.out.println("error in log file.");
          }
          IntUI cli=new CliUser(uf);
          cli.start();
          //System.exit(0);
     }
     
     public static void mainAdminCli(){
          Configurator.init();
          new AdminFlow().start();
     }
     
     public static void mainSerCli(){
          Configurator.init();
          new MainSerFlow().start();
     }
     
     public static void mainDataSerCli(){
          Configurator.init();
          new MainDataSerFlow().start();
     }
     
     public static void mainLogSerCli(){
          Configurator.init();
          new RemoteLogFlow().start();
     }
}
