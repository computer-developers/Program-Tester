/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flow.clientFlow;

import java.util.function.Consumer;

/**
 *
 * @author Neel Patel
 */
public interface IntNetClient {
     boolean regErrRunner(Consumer<String> r);
     boolean regMessageRunner(Consumer<String> r);
     boolean init(String uName,String passwd);
     boolean log(String s);
     int credit(long pid);
     int userCredit();
}
