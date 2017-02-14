/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.userModule.result;

import java.util.List;

/**
 *
 * @author admin
 */
public interface IntLiveResultSet extends IntResultSet{
     IntLiveResult getLiveResult(int index);
     List<IntLiveResult> getAllLiveResult();
}
