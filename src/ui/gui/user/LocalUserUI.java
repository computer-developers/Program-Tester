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
package ui.gui.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import lib.run.test.util.IntLiveResultSet;
import lib.run.test.util.IntProgramState;
import modul.user.IntUserFlow;
import ui.IntUI;
import ui.gui.util.AbstractFrame;
import ui.gui.util.ProgramRunPanel;
import ui.gui.util.ProgramStateViewPanel;
import ui.gui.util.SimpleFrame;
import ui.gui.util.SimpleFramePage;
import ui.gui.util.TestCaseTablePanel;
import ui.gui.util.UserHomePanel;

/**
 *
 * @author Neel Patel
 */
public class LocalUserUI implements IntUI{

    private UserHomePanel uhp;
    private SimpleFrame sf;
    private IntUserFlow uf;
    private List<? extends IntProgramState> ps;
    private Map<IntProgramState, ProgramStateViewPanel> map=new HashMap<>();
    
    public LocalUserUI(IntUserFlow uf){
        this.uf=uf;
        uf.register(this);
        
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
        ps=uf.getAllProgramDetail();
        ps.forEach(x->{
            ProgramStateViewPanel psvp=new ProgramStateViewPanel();
            psvp.setPs(x);
            map.put(x,psvp);
        });
        uhp=new UserHomePanel();
        uhp.getProgramDetailPanel().setPdtm(ps);
        uhp.getProgramDetailPanel().setPdc(x->this.displayProgramState(map.get(x)));
        AbstractFrame af=new AbstractFrame();
        sf=new SimpleFrame(af);
        sf.setCurrent(new SimpleFramePage(uhp, null, null, "Program Tester"));
        af.init();
    }

    @Override
    public void close() {
    }
    
    private void displayProgramState(ProgramStateViewPanel psvp){
        SimpleFramePage spf=new SimpleFramePage(psvp, sf.getCurrent(),sf.getCurrent());
        sf.setCurrent(spf);
        psvp.setCon(x->this.programRun(x.getProgramID()));
    }
    
    private void programRun(long pid){
        ProgramRunPanel prp=new ProgramRunPanel();
        SimpleFramePage spf=new SimpleFramePage(prp, sf.getCurrent());
        sf.setCurrent(spf);
        prp.setCon(x->execute(pid,x));
    }
    
    private void execute(long pid, String cmd){
        IntLiveResultSet lrs=uf.execute(pid, cmd);
        TestCaseTablePanel tctp=new TestCaseTablePanel(lrs);
        SimpleFramePage spf=new SimpleFramePage(tctp, sf.getCurrent());
        sf.setCurrent(spf);
    }
}
