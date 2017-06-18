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

import javax.swing.JPanel;

/**
 *
 * @author Neel Patel
 */
public class SimpleFrame {
    //private List<JPanel> back=new ArrayList<>();
    private AbstractFrame af;
    private SimpleFramePage current;
    public SimpleFrame(AbstractFrame af){
        this.af=af;
    }
    
    public String getStatus(){
        return af.getStatusPanel().getStatus();
    }
    
    public void setStatus(String s){
        af.getStatusPanel().getStatus();
    }
    
    public AbstractFrame getAbstractFrame() {
        return af;
    }

    public SimpleFramePage getCurrent() {
        return current;
    }

    public synchronized void setCurrent(SimpleFramePage current) {
        this.current = current;
        update();
    }

    private void update(){
        //af.getTitlePanel().setBackRun(null);
        //af.getTitlePanel().setHomeRun(null);
        af.getTitlePanel().setTitle(current.getTitle());
        af.getContainerPanel().setPanel(current.getCurrent());
        SimpleFramePage home=current.getHome();
        SimpleFramePage previous=current.getPrevious();
        if(previous!=null)
            af.getTitlePanel().setBackRun(()->SimpleFrame.this.setCurrent(previous));
        else
            af.getTitlePanel().setBackRun(null);
        if(home!=null)
            af.getTitlePanel().setHomeRun(()->SimpleFrame.this.setCurrent(home));
        else
            af.getTitlePanel().setHomeRun(null);
        
    }
    
}
