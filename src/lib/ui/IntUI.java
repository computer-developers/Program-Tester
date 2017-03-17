/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ui;

import java.io.Closeable;

/**
 *
 * @author Parth Doshi
 */
public interface IntUI extends Closeable{
     /**
      * implementation of this method should show the {@code String}
        {@code message} to the user.
      * @param message 
      */
     public void showMessage(String message);
     
     /**
      * implementation of this method should prompt for input to the user.
      * {@code message} should be shown to the user when getting input.
      * @param message String message
      * @return input given by user.
      */
     public String Prompt(String message);
     
     /**
      * implementation of this method should initiate the process of execution.
      */
     public void start();
     
     /**
      * {@inheritDoc }
      */
     public void close();
}
