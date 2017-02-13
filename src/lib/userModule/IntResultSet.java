/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule;
import java.util.*;

/**
 *
 * @author Parth Doshi
 */
public interface IntResultSet {
    int getCount();
    List<? extends IntResult> getAllResult();
    IntResult getResult(int index);
}
