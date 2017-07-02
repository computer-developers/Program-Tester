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
import java.util.stream.Collectors;
import lib.db.ObjectDataBase;
import lib.db.TestDataBase;
import lib.db.UserDataBase;
import modul.com.IntTestData;
import modul.com.userstatus.IntUserStatus;
import ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public class TestDBFlow implements IntTestDBFlow{

    private String cs="";
    private IntTestData ob;
    private IntUI ui;
    private String file="";
    
    public TestDBFlow(){
        try{
            file=System.getProperty("test_db", "");
            selectDataBase(Paths.get(file));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void popDataBase(Path programDB, Path userDB) {
        try{
            if(ob!=null){
                if(programDB!=null&&!Files.isDirectory(programDB)&&programDB.toString().endsWith(".db")&&
                        userDB!=null&&!Files.isDirectory(userDB)&&userDB.toString().endsWith(".db")){
                    ob.pop(new ObjectDataBase(programDB));
                    ob.pop(new UserDataBase(userDB));
                }
                else{
                    showMessage("invalid DataBase Path!!!");
                }
            }
            else
                showMessage("please select database");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void selectDataBase(Path db) {
        try{
            if(db!=null&&!Files.isDirectory(db)&&db.toString().endsWith(".db")){
                this.cs="jdbc:sqlite:"+db;
                ob=new TestDataBase(db);
                file=db.toAbsolutePath().toString();
            }
            else{
                file="";
                showMessage("invalid DataBase Path!!!");
            }
        }catch(Exception ex){
            file="";
            ex.printStackTrace();
        }
    }

    @Override
    public void reset() {
        try{
            if(ob!=null)
                ob.reset();
            else
                showMessage("please select database");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<IntUserStatus> getAllUserStatus() {
        try{
            if(ob!=null){
                return ob.getAllUserName().stream().map(x->ob.getUserStatus(x))
                        .collect(Collectors.toList());
            }
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
    public IntUserStatus getUserStatus(String uName) {
        try{
            if(ob!=null)
                return ob.getUserStatus(uName);
            else{
                showMessage("please select database");
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Integer> getProgramStatus(long pid) {
        try{
            if(ob!=null)
                return ob.getProgramStatus(pid);
            else{
                showMessage("please select database");
                return new HashMap<>();
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return new HashMap<>();
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
    
    @Override
    public void registerUI(IntUI ui) {
        if(ui!=null){
            this.ui=ui;
            ui.start();
        }
    }
    
}
