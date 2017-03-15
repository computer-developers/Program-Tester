/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule.result;

import lib.dT.problemManipulate.IntProgramDetail;

/**
 *
 * @author Neel Patel
 */
public interface IntProgramState extends IntProgramDetail{
     /**
      * register runnable listener.
      * run method of the registered Runnable object will be called every time
        when the state change.
      * @param r Object of type Runnable.
      */
     void addRunnable(Runnable r);
     
     /**
      * return the state of the problem.
      * @return state of the problem.
      */
     int getState();
}
