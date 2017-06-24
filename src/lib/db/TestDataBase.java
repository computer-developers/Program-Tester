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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lib.problemDefination.IntProgramDetail;
import modul.com.IntTestData;
import modul.com.userstatus.IntUserStatus;
import modul.com.userstatus.UserStatusAdapter;

/**
 *
 * @author Neel Patel
 */
public class TestDataBase implements IntTestData{

    private String cs;

    
    private static class MyProgramDetail implements IntProgramDetail{

        private long pid;
        private int credit;        
        public MyProgramDetail(long pid,int credit) {
            this.credit=credit;
            this.pid=pid;
        }

        @Override
        public String getTitle() {
            return "";
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>();
        }

        @Override
        public List<String> getInput() {
            return new ArrayList<>();
        }

        @Override
        public List<String> getOutput() {
            return new ArrayList<>();
        }

        @Override
        public List<String> getSampleInput() {
            return new ArrayList<>();
        }

        @Override
        public List<String> getSampleOutput() {
            return new ArrayList<>();
        }

        @Override
        public long getProgramID() {
            return pid; 
        }

        @Override
        public int getCredit() {
            return credit; 
        }
        
    }
    
    TestDataBase(Path tdb){
        this.cs="jdbc:sqlite:"+tdb;
    }
    
    @Override
    public List<Long> getAllProgramIds() {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT pid from program_table;";
            ResultSet rs=st.executeQuery(createQuery);
            List<Long> li=new ArrayList<>();
            while(rs.next()){
                long pid  = rs.getLong("pid");
                li.add(pid);
            }
            rs.close();
            return li;
        }catch(SQLException e){
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getAllUserNames() {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT user_name from user_state;";
            ResultSet rs=st.executeQuery(createQuery);
            List<String> li=new ArrayList<>();
            while(rs.next()){
                String name  = rs.getString("user_name");
                li.add(name);
            }
            rs.close();
            return li;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public IntUserStatus getUserStatus(String name) {
        try{
            String pass=getPasswd(name);
            if(pass.trim().equals(""))
                return null;
            Map<Long,Integer> mps=new HashMap<>();
            getAllProgramIds().forEach(x->mps.put(x,getStatus(name, x)));
            return new UserStatusAdapter(name, pass, getProgramDetails(), mps);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Map<String,Integer> getProgramStatus(long pid) {
        try{
            Map<String,Integer> mps=new HashMap<>();
            getAllUserNames().forEach(u->mps.put(u,getStatus(u, pid)));
            return mps;
        }catch(Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public boolean log(String uName, long pid, int status, LocalDateTime dt) {
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
    
    private List<IntProgramDetail> getProgramDetails(){
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT * from program_table;";
            ResultSet rs=st.executeQuery(createQuery);
            List<IntProgramDetail> li=new ArrayList<>();
            while(rs.next()){
                long pid  = rs.getLong("pid");
                int credit  = rs.getInt("credit");
                li.add(new MyProgramDetail(pid, credit));
            }
            rs.close();
            return li;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
    
    private String getPasswd(String uName) {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT passwd from user_state"
                    +"WHERE user_name=`"+uName+"`;";
            ResultSet rs=st.executeQuery(createQuery);
            Map<String,String> li=new HashMap<>();
            if(rs.next()){
                return rs.getString("passwd");
            }
            else
                return "";    
        }catch(Exception e){
            return "";
        }
    }
}
