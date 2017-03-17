package lib.logAna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.*;
import programtester.config.Configuration;
import static lib.logger.LogTools.getLogProperty;

/**
 * Created by Sony on 17-03-2017.
 */
public class LogAnalizer {

    private List<String> file_list;
    private Path p;
    protected LogAnalizer(Path p){
        this.p = p;
        file_list = getAllFiles(p);
    }

    protected  LogAnalizer()
    {
        this.p = Configuration.getDefaultLogDir();
        file_list = getAllFiles(Configuration.getDefaultLogDir());
    }



    private static List<String> getAllFiles(Path dir){
        try {
            if(!Files.isDirectory(dir))
                return null;
            List<String> m=new ArrayList<>();
            Files.list(dir).filter(i->!Files.isDirectory(i))
                    .forEach(p->{
                        try {
                               m.addAll(Files.readAllLines(p));
                        } catch (IOException ex) {
                        }
                    });
            return m;
        } catch (Exception ex) {
            return null;
        }
    }

    public void refresh()
    {
        file_list= getAllFiles(p);
    }

    public Map<Long,Integer> getUserStatus(String user_name){
        HashMap<Long,Integer> m = new  HashMap<Long, Integer>() ;
        for(String update: file_list){
                if (update.contains(user_name)) {
                    Long pid = new Long(getLogProperty(update,"Pid"));
                    Integer state =new Integer(getLogProperty(update,"State"));
                    if (!(m.containsKey(pid)))
                         m.put(new Long(pid),new Integer(state));
                    else{
                        if (m.get(pid) < state){
                            m.put(pid,state);
                        }
                    }
                }

        }
        return m;

    }
}
