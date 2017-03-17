/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow.clientFlow;

/**
 *
 * @author Neel Patel
 */
public interface IntNetClient {
     boolean regErrRunner(Runnable r);
     boolean regMessageRunner(Runnable r);
     boolean init(String uName,String passwd);
     boolean log(String s);
     int credit(long pid);
     int userCredit();
}
