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
import lib.problemDefination.IntProgramDetail;
import lib.problemDefination.IntTestCase;
import modul.admin.IntProgramDBFlow;
import ui.IntUI;
import ui.gui.util.AbstractPanel;
import ui.gui.util.ProgramDBHomePanel;
import ui.gui.util.ProgramDetailCreaterPanel;
import ui.gui.util.ProgramDetailTablePanel;
import ui.gui.util.ProgramDetailViewPanel;
import ui.gui.util.SimpleTab;
import ui.gui.util.SimpleTabPage;
import ui.gui.util.TestCaseCreaterPanel;
import ui.gui.util.TestCaseViewPanel;

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
    private TestCaseCreaterPanel tccp;
    
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
        testcaseCreaterPanel();
        pdbhp.getSelectDBButton().addActionListener(x->dataBaseSelect());
        if(!pdf.getDataBase().trim().equals(""))
            pdbhp.getDatabaseLabel().setText(pdf.getDataBase());
        else
            pdbhp.getDatabaseLabel().setText("none");
        pdbhp.getRemoveProgramButton().addActionListener(x->programRemove());
        pdbhp.getRemoveTestCaseButton().addActionListener(x->testCaseRemove());
        pdbhp.getShowProgramButton().addActionListener(x->showAllProgram());
        pdbhp.getShowTestCaseButton().addActionListener(x->testCaseShow());
        
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
    
    private void testCaseShow(){
        try{
            String p=Prompt("enter pid of testcase");
            long pid=Long.parseLong(p);
            String i=Prompt("enter index of testcase");
            long index=Long.parseLong(i);
            IntTestCase tc=pdf.getTestCase(pid, index);
            TestCaseViewPanel tcvp=new TestCaseViewPanel();
            tcvp.setTc(tc);
            ProgramDBUI.this.st.setCurrent(new SimpleTabPage(tcvp, this.st.getCurrent()));
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

    private void testcaseCreaterPanel(){
        tccp= new TestCaseCreaterPanel();
        tccp.setCon(x->pdf.addTestCase(x));
        pdbhp.getAddTestCaseButton().addActionListener(x->{
            ProgramDBUI.this.st.setCurrent(new SimpleTabPage(tccp, this.st.getCurrent()));
        });
    }
    
    private void showAllProgram(){
        ProgramDetailTablePanel pdvp=new ProgramDetailTablePanel(pdf.getAllProgramDetail(),
                ProgramDBUI.this::showProgram);
        ProgramDBUI.this.st.setCurrent(new SimpleTabPage(pdvp, this.st.getCurrent()));
    }
    
    private void showProgram(IntProgramDetail pd){
        ProgramDetailViewPanel pdvp=new ProgramDetailViewPanel();
        pdvp.setPs(pd);
        ProgramDBUI.this.st.setCurrent(new SimpleTabPage(pdvp, this.st.getCurrent()));
    }
    
    @Override
    public void close() {
    }
    
}
