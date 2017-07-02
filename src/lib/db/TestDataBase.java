/*
 * The MIT License
 *
 * Copyright 2017 Neel Patel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lib.db;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lib.problemDefination.IntObjectBase;
import lib.problemDefination.IntProgramDetail;
import lib.problemDefination.IntTestCase;
import modul.com.IntTestData;
import modul.com.IntUserData;
import modul.com.userstatus.IntUserStatus;
import modul.com.userstatus.UserStatusAdapter;

/**
 *
 * @author Neel Patel
 */
public class TestDataBase implements IntTestData{

    private String cs;
    private ObjectDataBase ob;
    private UserDataBase ub;

    public TestDataBase(Path tdb){
        this.cs="jdbc:sqlite:"+tdb;
        ob=new ObjectDataBase(tdb);
        ub=new UserDataBase(tdb);
    }
    
    @Override
    public IntUserStatus getUserStatus(String name) {
        createTables();
        try{
            String pass=getPasswd(name);
            if(pass.trim().equals(""))
                return null;
            Map<Long,Integer> mps=new HashMap<>();
            getAllPids().forEach(x->mps.put(x,getStatus(name, x)));
            return new UserStatusAdapter(name, pass, getAllProgramDetails(), mps);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Map<String,Integer> getProgramStatus(long pid) {
        createTables();
        try{
            Map<String,Integer> mps=new HashMap<>();
            getAllUserName().forEach(u->mps.put(u,getStatus(u, pid)));
            return mps;
        }catch(Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public boolean log(String uName, long pid, int status, LocalDateTime dt) {
        createTables();
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "INSERT INTO user_log(`user_name`,`pid`,`state`) "
                    + "VALUES (\""+uName+"\","+pid+","+status+");";
            st.execute(createQuery);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    private int getStatus(String uName,long pid){
        createTables();
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT MIN(state) AS max_state "
                    + "FROM user_log "
                    + "WHERE ( pid="+pid+" AND `user_name`=\""+uName+"\") ;";
            ResultSet rs=st.executeQuery(createQuery);
            int c=0;
            if(rs.next()){
                c = rs.getInt("max_state");
            }
            rs.close();
            return c;
        }catch(Exception e){
            return 0;
        }
    }
    
    @Override
    public void reset() {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            //st.execute("PRAGMA foreign_keys = ON;");
            String createQuery1 = "DROP TABLE user_log;";
            st.execute(createQuery1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void createTables(){
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            //st.execute("PRAGMA foreign_keys = ON;");
            String createQuery3 = "CREATE TABLE IF NOT EXISTS user_log "
                    +"(`user_name` varchar(255) NOT NULL, "
                    +"`pid` numeric(20) NOT NULL, "
                    +"`state` numeric(5) NOT NULL, "
                    +"PRIMARY KEY (`user_name`,`pid`), "
                    +"FOREIGN KEY(`pid`) REFERENCES problem_defination(`pid`), "
                    +"FOREIGN KEY(`user_name`) REFERENCES user_details(`user_name`));";
            st.execute(createQuery3);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean insertProgramDetail(IntProgramDetail pd) {
        return ob.insertProgramDetail(pd);
    }

    @Override
    public boolean insertTestCase(IntTestCase tc) {
        return ob.insertTestCase(tc);
    }

    @Override
    public boolean removeProgramDetail(long pid) {
        return ob.removeProgramDetail(pid);
    }

    @Override
    public boolean removeTestCase(long pid, long index) {
        return ob.removeTestCase(pid, index);
    }

    @Override
    public IntTestCase getTestCase(long pid, long index) {
        return ob.getTestCase(pid, index);
    }

    @Override
    public IntProgramDetail getProgramDetail(long pid) {
        return ob.getProgramDetail(pid);
    }

    @Override
    public List<Long> getAllPids() {
        return ob.getAllPids();
    }

    @Override
    public List<Long> getAllIndexes(long pid) {
        return ob.getAllIndexes(pid);
    }

    @Override
    public List<String> getAllUserName() {
        return ub.getAllUserName();
    }

    @Override
    public Map<String, String> getAllUser() {
        return ub.getAllUser();
    }

    @Override
    public String getPasswd(String uName) {
        return ub.getPasswd(uName);
    }

    @Override
    public boolean removeUser(String uName) {
        return ub.removeUser(uName);
    }

    @Override
    public boolean insertUser(String uName, String passwd) {
        return ub.insertUser(uName, passwd);
    }

    @Override
    public void pop(IntObjectBase ob) {
        this.ob.pop(ob);
    }
    
    @Override
    public void pop(IntUserData ud) {
        this.ub.pop(ud);
    }

}
