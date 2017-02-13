/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.logger.MyLogger;
import lib.ui.IntUI;
import lib.userModule.test.Test;

/**
 *
 * @author Neel Patel
 */
public class SingleUserFlow implements IntUserFlow{
     private IntUI ui;
     private MyLogger logger;

     public SingleUserFlow(){
          
     }
     
     @Override
     public synchronized void  register(IntUI ui) {
          this.ui=ui;
     }

     @Override
     public IntLiveResultSet execute(long pid,String cmd) {
          try {
               return new Test(pid,cmd).start();
          } catch (IOException ex) {
               System.out.println("Error");
          }
          return null;
     }
     
     public synchronized void setLogger(MyLogger logger){
          this.logger=logger;
     }
}
