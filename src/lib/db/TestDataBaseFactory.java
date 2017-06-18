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
import java.sql.Statement;
import lib.problemDefination.IntObjectSource;
import modul.com.IntUserData;

/**
 *
 * @author Neel Patel
 */
public class TestDataBaseFactory {
    private TestDataBaseFactory(){}
    
    public static void makeDataBase(Path testDB,IntObjectSource os,IntUserData ud){
        createTables(testDB);
        popProgramTable(testDB, os);
        popUserState(testDB, ud);
    }
    
    public static void makeDataBase(Path testDB,Path programDB,Path userDB){
        makeDataBase(testDB,new ObjectDataBase(programDB),new UserDataBase(userDB));
    }
    
    public static void resetDataBase(Path testDB){
        createTables(testDB);
        String cs="jdbc:sqlite:"+testDB;
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            //st.execute("PRAGMA foreign_keys = ON;");
            String createQuery1 = "DELETE FROM user_log;";
            st.execute(createQuery1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void popProgramTable(Path tdb,IntObjectSource os){
        createTables(tdb);
        String cs="jdbc:sqlite:"+tdb;
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            //st.execute("PRAGMA foreign_keys = ON;");
            String createQuery1 = "DELETE FROM program_table;";
            st.execute(createQuery1);
            os.getAllProgramDetails().forEach(pd->{
                try{
                    String createQuery2 = "INSERT INTO program_table (`pid`,`credit`) " +
                    "VALUES ("+pd.getProgramID()+","+pd.getCredit()+");";
                    st.execute(createQuery2);
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void popUserState(Path tdb,IntUserData ud){
        createTables(tdb);
        String cs="jdbc:sqlite:"+tdb;
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            //st.execute("PRAGMA foreign_keys = ON;");
            String createQuery1 = "DELETE FROM user_state;";
            st.execute(createQuery1);
            ud.getAllUser().forEach((u,p)->{
                try{
                    String createQuery2 = "INSERT INTO user_state (`user_name`,`passwd`,`credit`) " +
                    "VALUES (`"+u+"`,`"+p+"`,0);";
                    st.execute(createQuery2);
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void createTables(Path tdb){
        String cs="jdbc:sqlite:"+tdb;
        try(Connection con=DriverManager.getConnection(cs);
                Statement st=con.createStatement();){
            con.setAutoCommit(true);
            //st.execute("PRAGMA foreign_keys = ON;");
            String createQuery1 = "CREATE TABLE IF NOT EXISTS user_state "
                    +"(`user_name` varchar(255) NOT NULL, "
                    +"`passwd` varchar(255) NOT NULL, "
                    +"`credit` numeric(5) NOT NULL, "
                    +"PRIMARY KEY (`user_name`));";
            st.execute(createQuery1);
            String createQuery2 = "CREATE TABLE IF NOT EXISTS program_table "
                    +"(`pid` numeric(20) NOT NULL, "
                    +"`credit` numeric(5) NOT NULL, "
                    +"PRIMARY KEY (`pid`));";
            st.execute(createQuery2);
            String createQuery3 = "CREATE TABLE IF NOT EXISTS user_log "
                    +"(`user_name` varchar(255) NOT NULL, "
                    +"`pid` numeric(20) NOT NULL, "
                    +"`state` numeric(5) NOT NULL, "
                    +"PRIMARY KEY (`user_name`,`pid`), "
                    +"FOREIGN KEY(`pid`) REFERENCES program_table(`pid`), "
                    +"FOREIGN KEY(`user_name`) REFERENCES user_state(`user_name`));";
            st.execute(createQuery3);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
