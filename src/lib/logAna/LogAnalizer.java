    package lib.logAna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.*;

import lib.dT.problemManipulate.IntProgramDetail;
import org.jetbrains.annotations.Nullable;
import programtester.config.Configuration;
import static lib.logger.LogTools.getLogProperty;
import static lib.dT.problemManipulate.ProgramDetails.readProgramDetail;
import static programtester.config.Configuration.TEST_PASS;
import static programtester.config.Configuration.TEST_PRESENT_ERROR;

/**
 * Created by Sony on 17-03-2017.
 */
public class LogAnalizer {

    private List<String> file_list;
    private Path p;

    public LogAnalizer(Path p) {
        this.p = p;
        file_list = getAllFiles(p);
    }

    public LogAnalizer() {
        this.p = Configuration.getDefaultLogDir();
        file_list = getAllFiles(Configuration.getDefaultLogDir());
    }


    private synchronized  static List<String> getAllFiles(Path dir) {
        try {
            if (!Files.isDirectory(dir))
                return null;
            List<String> m = new ArrayList<>();
            Files.list(dir).filter(i -> !Files.isDirectory(i))
                    .forEach(p -> {
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

    public synchronized void refresh() {
        file_list = getAllFiles(p);
    }

    public synchronized Map<Long, Integer> getUserStatus(String user_name) {
        HashMap<Long, Integer> m = new HashMap<Long, Integer>();
        for (String update : file_list) {
            if (update!= null && update.contains(user_name)) {

                Long pid =null;
                Integer state = null;
                try {
                    pid = new Long(getLogProperty(update, "Pid"));
                    state = new Integer(getLogProperty(update, "State"));
                    if(pid!=null && state!=null) {
                        if (!(m.containsKey(pid)))
                            m.put(new Long(pid), new Integer(state));
                        else {
                            if (m.get(pid) < state) {
                                m.put(pid, state);
                            }
                        }
                    }
                }catch(Exception e)
                {
                    System.err.println("Invalid format");
                }
            }

        }
        return m;

    }


    private synchronized Map<Long, Integer> stateToCredit(Map<Long, Integer> lm) {
        Map<Long, Integer> cre = new HashMap<Long, Integer>();
        if(lm.size() > 0) {
            for (Long pid_list : lm.keySet()) {

                IntProgramDetail ipd = null;
                try {
                    ipd = readProgramDetail(pid_list);
                } catch (Exception e) {
                    System.err.println("Reading error - File Not Found !");
                    return null;
                }
                Integer val = ipd.getCredit();


                switch (lm.get(pid_list)) {
                    case TEST_PRESENT_ERROR:
                        cre.put(pid_list, val - 1);
                        break;
                    case TEST_PASS:
                        cre.put(pid_list, val);
                        break;
                    default:
                        cre.put(pid_list, 0);
                        break;
                }


            }
            return cre;
        }
        else
            return null;
    }

    public void log(String...s)
    {

        if(s.length > 0) {
            for (String h : s)
                if(h!=null) {
                    file_list.add(h);
                }
        }
    }

    public Map<String, Integer> getAllUserStatus() throws IOException {
        Set<String> s = new HashSet<String>();
        Map<String, Integer> hm = new HashMap<String, Integer>();
        for (String update : file_list) {
            String st = getLogProperty(update, "User");
            if (st!=null && !(s.contains(st)))
                s.add(st);
        }
        if(s.size() > 0)
        {
        for (String set_it : s) {
            Map<Long, Integer> lm = getUserStatus(set_it);
            lm = stateToCredit(lm);
            Integer credit = 0;
            for (Integer cre : lm.values())
                credit += cre;
            hm.put(set_it, credit);
        }
            return hm;
        }
        else
            return null;

    }
}


