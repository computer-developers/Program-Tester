/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule;

import lib.ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public class SingleUserFlow implements IntUserFlow{
     private IntUI ui;

     @Override
     public synchronized void  register(IntUI ui) {
          this.ui=ui;
     }

     @Override
     public IntResultSet execute(String cmd, int pid) {
          
          return null;
     }
     
}
