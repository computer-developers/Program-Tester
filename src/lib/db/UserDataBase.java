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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modul.com.IntUserData;

/**
 *
 * @author Neel Patel
 */
public class UserDataBase implements IntUserData{

    private String cs;
    
    public UserDataBase(Path db){
        this.cs="jdbc:sqlite:"+db;
    }
    
    @Override
    public List<String> getAllUserName() {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT user_name from user_details;";
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
    public Map<String, String> getAllUser() {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT * from user_details;";
            ResultSet rs=st.executeQuery(createQuery);
            Map<String,String> li=new HashMap<>();
            while(rs.next()){
                String name  = rs.getString("user_name");
                String pass  = rs.getString("passwd");
                li.put(name, pass);
            }
            rs.close();
            return li;
        }catch(Exception e){
            return new HashMap<>();
        }
    }

    @Override
    public String getPasswd(String uName) {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT passwd from user_details"
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

    @Override
    public boolean removeUser(String uName) {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            if(uName.trim().equals(""))
                return false;
            String createQuery = "DELETE FROM user_details " +
                    "WHERE user_name=`"+uName+"`;";
            st.execute(createQuery);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUser(String uName, String passwd) {
        createTables();
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "INSERT INTO user_details (`user_name`,`passwd`) " +
                    "VALUES (`"+uName+"`,`"+passwd+"`);";
            st.execute(createQuery);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    private void createTables(){
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            //st.execute("PRAGMA foreign_keys = ON;");
            String createQuery1 = "CREATE TABLE IF NOT EXISTS user_details "
                    +"(`user_name` varchar(255) NOT NULL, "
                    +"`passwd` varchar(255) NOT NULL, "
                    +"PRIMARY KEY (`user_name`));";
            st.execute(createQuery1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
