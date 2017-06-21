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
import java.util.List;
import lib.db.ObjectDataBase;
import lib.problemDefination.IntObjectBase;
import lib.problemDefination.IntProgramDetail;
import lib.problemDefination.IntTestCase;
import ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public class ProgramDBFlow implements IntProgramDBFlow{

    private String cs="";
    private IntObjectBase ob;
    private IntUI ui;
    private String file="";
    
    public ProgramDBFlow(){
        try{
            file=System.getProperty("program_db", "");
            selectDataBase(Paths.get(file));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void selectDataBase(Path db) {
        try{
            if(db!=null&&!Files.isDirectory(db)&&db.toString().endsWith(".db")){
                this.cs="jdbc:sqlite:"+db;
                ob=new ObjectDataBase(db);
                file=db.toAbsolutePath().toString();
            }
            else{
                System.out.println("caca"+db);
                file="";
                showMessage("invalid DataBase Path!!!");
            }
        }catch(Exception ex){
            file="";
            ex.printStackTrace();
        }
    }

    @Override
    public void addProgramDefination(IntProgramDetail pd) {
        try{
            if(ob!=null){
                if(ob.insertProgramDetail(pd))
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
    public void removeProgramDefination(long pid) {
        try{
            if(ob!=null){
                if(ob.removeProgramDetail(pid))
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
    public void addTestCase(IntTestCase tc) {
        try{
            if(ob!=null){
                if(ob.insertTestCase(tc))
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
    public void removeTestCase(long pid, long index) {
        try{
            if(ob!=null){
                if(ob.removeTestCase(pid, index))
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
    public void importFromOtherDB(Path db, long pid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IntProgramDetail> getAllProgramDetail() {
        try{
            if(ob!=null)
                return ob.getAllProgramDetails();
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
    public List<IntTestCase> getAllTestCase(long pid) {
        try{
            if(ob!=null)
                return ob.getTestCases(pid);
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
    public IntProgramDetail getProgramDetail(long pid) {
        try{
            if(ob!=null)
                return ob.getProgramDetail(pid);
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
    public IntTestCase getTestCase(long pid, long index) {
        try{
            if(ob!=null)
                return ob.getTestCase(pid, index);
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
