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
package ui.gui.admin;

import java.nio.file.Paths;
import javax.swing.JOptionPane;
import modul.admin.IntUserDBFlow;
import ui.IntUI;
import ui.gui.util.AbstractPanel;
import ui.gui.util.SimpleTab;
import ui.gui.util.SimpleTabPage;
import ui.gui.util.UserDBHomePanel;
import ui.gui.util.UserTablePanel;

/**
 *
 * @author Neel Patel
 */
public class UserDBUI implements IntUI{

    private IntUserDBFlow pdf;
    private AdminPanel ap;
    private SimpleTab st;
    private AbstractPanel abp;
    private UserDBHomePanel pdbhp;
    //private ProgramDetailCreaterPanel pdcp;
    //private TestCaseCreaterPanel tccp;
    
    public UserDBUI(IntUserDBFlow pdf, AdminPanel ap) {
        this.pdf=pdf;
        this.ap=ap;
        pdf.registerUI(this);
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String Prompt(String message) {
        String mes =(JOptionPane.showInputDialog(message));
        return mes;
    }

    @Override
    public void start() {
        abp=new AbstractPanel();
        ap.addTab(abp, "User DataBase");
        st=new SimpleTab(abp);
        pdbhp=new UserDBHomePanel();
        config();
        st.setCurrent(new SimpleTabPage(pdbhp));
    }
    
    private void config(){
        pdbhp.getSelectDBButton().addActionListener(x->dataBaseSelect());
        if(!pdf.getDataBase().trim().equals(""))
            pdbhp.getDatabaseLabel().setText(pdf.getDataBase());
        else
            pdbhp.getDatabaseLabel().setText("none");
        pdbhp.getRemoveUserButton().addActionListener(x->userRemove());
        pdbhp.getAddUserButton().addActionListener(x->addUser());
        pdbhp.getShowUserButton().addActionListener(x->showAllUser());
    }
    
    private void dataBaseSelect(){
        try{
            String p="";
            p=Prompt("enter absolute path of database");
            pdf.selectDataBase(Paths.get(p));
            if(!pdf.getDataBase().trim().equals(""))
                pdbhp.getDatabaseLabel().setText(pdf.getDataBase());
            else
                pdbhp.getDatabaseLabel().setText("none");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void userRemove(){
        try{
            String p=Prompt("enter username you want to delete");
            pdf.removeUser(p);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void addUser(){
        try{
            String u=Prompt("enter Username of new user");
            String p=Prompt("enter Password of new User");
            pdf.addUser(u, p);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void showAllUser(){
        UserTablePanel pdvp=new UserTablePanel();
        System.out.println(pdf.getAllUser());
        pdvp.setPdtm(pdf.getAllUser());
        UserDBUI.this.st.setCurrent(new SimpleTabPage(pdvp, this.st.getCurrent()));
    }
    
    @Override
    public void close() {
    }
    
}
