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
import java.util.ArrayList;
import java.util.List;
import lib.problemDefination.IntObjectBase;
import lib.problemDefination.IntProgramDetail;
import lib.problemDefination.IntTestCase;

/**
 *
 * @author Neel Patel
 */
public class ObjectDataBase implements IntObjectBase{
    
    private String cs="";
    private ObjectHandler oh;
    
    public ObjectDataBase(Path db){
        this.cs="jdbc:sqlite:"+db;
        this.oh=new ObjectHandler(db);
    }
    
    @Override
    public IntTestCase getTestCase(long pid, long index) {
        return (IntTestCase)oh.readObject("p"+pid+"i"+index);
    }

    @Override
    public IntProgramDetail getProgramDetail(long pid) {
        return (IntProgramDetail)oh.readObject("p"+pid);
    }

    @Override
    public List<Long> getAllPids() {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT pid from problem_defination;";
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
    public List<Long> getAllIndexes(long pid) {
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "SELECT index from testcase "+
                    "WHERE pid="+pid+";";
            ResultSet rs=st.executeQuery(createQuery);
            List<Long> li=new ArrayList<>();
            while(rs.next()){
                long index  = rs.getLong("pid");
                li.add(index);
            }
            rs.close();
            return li;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertProgramDetail(IntProgramDetail pd) {
        createTables();
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "INSERT INTO problem_defination (pid,title) " +
                    "VALUES ("+pd.getProgramID()+",'"+pd.getTitle()+"');";
            st.execute(createQuery);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return oh.writeObject("p"+pd.getProgramID(), pd);
    }

    @Override
    public boolean insertTestCase(IntTestCase tc) {
        createTables();
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "INSERT INTO testcase (`pid`,`index`) " +
                    "VALUES ("+tc.programID()+","+tc.index()+");";
            st.execute(createQuery);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return oh.writeObject("p"+tc.programID()+"i"+tc.index(),tc);
    }
    

    @Override
    public boolean removeProgramDetail(long pid) {
        createTables();
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "DELETE FROM problem_defination " +
                    "WHERE (pid="+pid+");";
            st.execute(createQuery);
            return oh.removeObject("p"+pid);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeTestCase(long pid, long index) {
        createTables();
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            String createQuery = "DELETE FROM testcase " +
                    "WHERE ( pid="+pid+" AND `index`="+index+") ;";
            System.out.println(createQuery);
            st.execute(createQuery);
            return oh.removeObject("p"+pid+"i"+index);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    private void createTables(){
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            st.execute("PRAGMA foreign_keys = ON;");
            String createQuery1 = "CREATE TABLE IF NOT EXISTS problem_defination "
                    +"(`pid` numeric(20) NOT NULL,`title` varchar(10000), "
                    +"PRIMARY KEY (`pid`));";
            st.execute(createQuery1);
            String createQuery2 = "CREATE TABLE IF NOT EXISTS testcase "
                    +"(`pid` numeric(20) NOT NULL, `index` numeric(20) NOT NULL, "
                    +"PRIMARY KEY (`pid`,`index`), "
                    +"FOREIGN KEY(`pid`) REFERENCES problem_defination(`pid`));";
            st.execute(createQuery2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
