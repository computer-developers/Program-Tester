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
package ui.gui.util;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lib.db.ObjectDataBase;
import lib.problemDefination.IntObjectBase;
import lib.run.test.util.IntProgramState;
import lib.run.test.util.ProgramStateAdapter;

/**
 *
 * @author Neel Patel
 */
public class SampleFrameTest {
    public static void main(String ... arg) throws InterruptedException{
        IntObjectBase ob=new ObjectDataBase(Paths.get("data.db"));
        AbstractFrame sf=new AbstractFrame();
        List<IntProgramState>li=ob.getAllProgramDetails().stream()
                .map(x->new ProgramStateAdapter(x)).collect(Collectors.toList());
        ProgramStateTablePanel pdtp=new ProgramStateTablePanel(li,
                i->{
                    System.out.println("test "+i);
                    ProgramStateViewPanel pdvp=new ProgramStateViewPanel();
                    pdvp.setPs(i);
                    sf.getContainerPanel().setPanel(pdvp);
                    
                });
        System.out.println(li.size());
        sf.getTitlePanel().setTitle("test");
        sf.getStatusPanel().setStatus("status test");
        sf.getContainerPanel().setPanel(pdtp);
        Thread.sleep(1000);
        sf.init();
        System.out.println("test check");
        Thread.sleep(1000);
        //((ProgramStateAdapter) li.get(0)).setState(1);
        System.out.println("test check 2");
    }
}
