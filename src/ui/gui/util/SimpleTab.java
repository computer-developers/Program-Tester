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

/**
 *
 * @author Neel Patel
 */
public class SimpleTab {
    private AbstractPanel ap;
    private SimpleTabPage current;
    public SimpleTab(AbstractPanel ap){
        this.ap=ap;
        
    }

    public SimpleTabPage getCurrent() {
        return current;
    }

    public void setCurrent(SimpleTabPage current) {
        this.current = current;
        update();
    }

    public AbstractPanel getAp() {
        return ap;
    }
    
    private void update(){
        //af.getTitlePanel().setBackRun(null);
        //af.getTitlePanel().setHomeRun(null);
        ap.getContainerPanel().setPanel(current.getCurrent());
        SimpleTabPage home=current.getHome();
        SimpleTabPage previous=current.getPrevious();
        if(previous!=null)
            ap.getTitlePanel().setBackRun(()->SimpleTab.this.setCurrent(previous));
        else
            ap.getTitlePanel().setBackRun(null);
        if(home!=null)
            ap.getTitlePanel().setHomeRun(()->SimpleTab.this.setCurrent(home));
        else
            ap.getTitlePanel().setHomeRun(null);
    }
}
