/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule.result;

import lib.userModule.TimeNotAvailableException;

/**
 *
 * @author Neel Patel
 */
public interface IntResult {
     long getRunTime()throws TimeNotAvailableException;
     default long index(){
          return -1;
     }
     String getMessage();
     int getMessageCode();
}
