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
package modul.admin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lib.db.UserDataBase;
import modul.com.IntUserData;
import ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public class UserDBFlow implements IntUserDBFlow{

    private String cs="";
    private IntUserData ob;
    private IntUI ui;
    private String file="";
    
    public UserDBFlow() {
        try{
            file=System.getProperty("user_db", "");
            selectDataBase(Paths.get(file));
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    
    @Override
    public void selectDataBase(Path db) {
        try{
            if(db!=null&&(!Files.isDirectory(db))&&db.toString().endsWith(".db")){
                this.cs="jdbc:sqlite:"+db;
                ob=new UserDataBase(db);
            }
            else{
                showMessage("invalid DataBase Path!!!");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void addUser(String uName, String passwd) {
        try{
            if(ob!=null){
                if(ob.insertUser(uName, passwd))
                    showMessage("Inserted successfully");
                else
                    showMessage("Error in insertion!!!");
            }
            else
                showMessage("please select database");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<String> getAllUserName() {
        try{
            if(ob!=null)
                return ob.getAllUserName();
            else{
                showMessage("please select database");
                return new ArrayList<>();
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, String> getAllUser() {
        try{
            if(ob!=null)
                return ob.getAllUser();
            else{
                showMessage("please select database");
                return new HashMap<>();
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public void removeUser(String UserName) {
        try{
            if(ob!=null){
                if(ob.removeUser(UserName))
                    showMessage("Removed successfully");
                else
                    showMessage("Error in removing!!!");
            }
            else
                showMessage("please select database");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void registerUI(IntUI ui) {
        if(ui!=null){
            this.ui=ui;
            ui.start();
        }
    }
    
    private void showMessage(String message){
        try{
            ui.showMessage(message);
        }catch(NullPointerException ex){
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getDataBase() {
        return file;
    }
}
