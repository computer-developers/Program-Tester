/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule.result;

/**
 *
 * @author Neel Patel
 */
public interface IntLiveResult extends IntResult{
     void addRunnable(Runnable r);
     IntResult toIntResult();
     void makeFinal();
}
