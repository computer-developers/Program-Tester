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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Neel Patel
 */
public class ObjectHandler {
    
    public static <O> byte[] objectToBytes(O obj){
        try(ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out=new ObjectOutputStream(bout)){
            out.writeObject(obj);
            out.flush();
            return bout.toByteArray();
        } catch (Exception ex) {
        }
        return null;
    }
    
    public static <O> O bytesToObject(byte ar[]){
        try(ByteArrayInputStream bin = new ByteArrayInputStream(ar);
        ObjectInputStream in=new ObjectInputStream(bin)){
            return (O)in.readObject();
        } catch (Exception ex) {
        }
        return null;
    }
    
    private String cs=""; // connection string
    
    public ObjectHandler(Path db){
        this.cs="jdbc:sqlite:"+db;
    }
    
    public String getConnectionString(){
        return cs;
    }
    
    public boolean writeObject(String id,Object o){
        try(Connection con=DriverManager.getConnection(cs)){
            con.setAutoCommit(true);
            Statement st=con.createStatement();
            byte[] data = objectToBytes(o);
            String createQuery = "CREATE TABLE IF NOT EXISTS objects "
                    +"(`id` varchar(255) NOT NULL, `object` longblob NOT NULL, "
                    +"PRIMARY KEY (`id`));";
            st.execute(createQuery);
            String sql="insert into objects (id,object) values(?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setObject(1, id);
            ps.setObject(2, data);
            ps.execute();
            return true;
        }catch(SQLException e){
        }
        return false;
    }
    
    public Object readObject(String id){
        try(Connection con=DriverManager.getConnection(cs)){
            con.setAutoCommit(true);
            Statement st=con.createStatement();
            //byte[] data = objectToBytes(o);
            String sql="select * from objects where id='"+id+"';";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                return bytesToObject(rs.getBytes("object"));
        }catch(Exception e){
            //e.printStackTrace();
        }
        return null;
    }
    
    public boolean removeObject(String id){
        try(Connection con=DriverManager.getConnection(cs)){
            con.setAutoCommit(true);
            Statement st=con.createStatement();
            //byte[] data = objectToBytes(o);
            String sql="DELETE FROM objects " +
                    "WHERE id=\""+id+"\";";
            st.execute(sql);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
