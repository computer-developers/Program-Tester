/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dataSer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static programtester.config.Configuration.getDefaultDir;
import static programtester.config.Configuration.getDefaultProDir;

/**
 *
 * @author Neel Patel
 */
public class DataSerHandler {
     private DataSerHandler(){}
     public static IntDataSer makeObject(){
          try {
               Path td=getDefaultDir();
               Path pd=getDefaultProDir();
               if(!(Files.isDirectory(pd)&&Files.isDirectory(td)))
                    return null;
               Map<String,byte[]> pm=getAllFiles(pd);
               Map<String,byte[]> tm=getAllFiles(td);
               IntDataSer a=new DataSer(pm,tm);
               return a;
          } catch (Exception ex) {
               return null;
          }
     }
     
     private static Map<String,byte[]> getAllFiles(Path dir){
          try {
               if(!Files.isDirectory(dir))
                    return null;
               Map<String,byte[]> m=new HashMap<>();
               Files.list(dir).filter(i->!Files.isDirectory(i))
                         .forEach(p->{
                              try {
                                   m.put(p.getFileName().toString(),
                                           Files.readAllBytes(p));
                              } catch (IOException ex) {
                              }
                         });
               return m;
          } catch (Exception ex) {
               return null;
          }
     }
}
