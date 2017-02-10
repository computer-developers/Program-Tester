package lib.ui.cli;

import lib.ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public class CliUser implements IntUI{

     @Override
     public void showMessage(String message) {
          System.out.println("message :- "+message);
     }
     
}
