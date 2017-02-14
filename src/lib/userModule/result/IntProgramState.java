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
     void setRunnable(Runnable r);
     int getState();
}
