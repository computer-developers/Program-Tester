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
import modul.admin.IntProgramDBFlow;
import ui.IntUI;
import ui.gui.util.AbstractPanel;
import ui.gui.util.ProgramDBHomePanel;
import ui.gui.util.ProgramDetailCreaterPanel;
import ui.gui.util.SimpleTab;
import ui.gui.util.SimpleTabPage;

/**
 *
 * @author Neel Patel
 */
public class ProgramDBUI implements IntUI{

    private IntProgramDBFlow pdf;
    private AdminPanel ap;
    private SimpleTab st;
    private AbstractPanel abp;
    private ProgramDBHomePanel pdbhp;
    private ProgramDetailCreaterPanel pdcp;
            
    public ProgramDBUI(IntProgramDBFlow pdf, AdminPanel ap) {
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
        ap.addTab(abp, "Program DataBase");
        st=new SimpleTab(abp);
        pdbhp=new ProgramDBHomePanel();
        config();
        st.setCurrent(new SimpleTabPage(pdbhp));
    }
    
    private void config(){
        programDetailCreaterPanel();
        pdbhp.getSelectDBButton().addActionListener(x->dataBaseSelect());
        pdbhp.getRemoveProgramButton().addActionListener(x->programRemove());
        pdbhp.getRemoveTestCaseButton().addActionListener(x->testCaseRemove());
        
    }
    
    private void dataBaseSelect(){
        try{
            String p=Prompt("enter absolute path of database");
            pdf.selectDataBase(Paths.get(p));
            if(!pdf.getDataBase().trim().equals(""))
                pdbhp.getDatabaseLabel().setText(pdf.getDataBase());
            else
                pdbhp.getDatabaseLabel().setText("none");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void programRemove(){
        try{
            String p=Prompt("enter pid of program you want to delete");
            long l=Long.parseLong(p);
            pdf.removeProgramDefination(l);
        }catch(NumberFormatException e){
            showMessage("invalid PID!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void testCaseRemove(){
        try{
            String p=Prompt("enter pid of testcase you want to delete");
            long pid=Long.parseLong(p);
            String i=Prompt("enter index of testcase you want to delete");
            long index=Long.parseLong(i);
            pdf.removeTestCase(pid, index);
        }catch(NumberFormatException e){
            showMessage("invalid PID or Index!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void programDetailCreaterPanel(){
        pdcp=new ProgramDetailCreaterPanel();
        pdcp.setCon(x->pdf.addProgramDefination(x));
        pdbhp.getAddProgramButton().addActionListener(x->{
            ProgramDBUI.this.st.setCurrent(new SimpleTabPage(pdcp, this.st.getCurrent()));
        });
    }

    @Override
    public void close() {
    }
    
}
