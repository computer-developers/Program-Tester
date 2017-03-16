/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.logSer;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
public interface IntLogProc {
     List<String> getLogs(LocalDateTime start,LocalDateTime end);
}
