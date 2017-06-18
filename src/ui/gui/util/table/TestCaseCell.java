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
package ui.gui.util.table;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import lib.run.test.util.IntLiveResult;

/**
 *
 * @author Neel Patel
 */
public class TestCaseCell extends AbstractCellEditor implements TableCellRenderer,TableCellEditor{

    public TestCaseCell(){
        //System.out.println("PDC Construct");
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        //System.out.println("Renderer");
        IntLiveResult ps=(IntLiveResult)value;
        TestCaseStripePanel pds=new TestCaseStripePanel();
        pds.getIndexLabel().setText(row+1+"");
        pds.getStatusLabel().setText(ps.getMessage());
        ps.addRunnable(()->{
            pds.getStatusLabel().setText(ps.getMessage());
            try{
                if(ps.getRunTime()>0)
                    pds.getTimeLabel().setText(""+ps.getRunTime()/1000);
            }catch(Exception ex){}
            table.updateUI();
        });
        return pds;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        //System.out.println("editor");
        IntLiveResult ps=(IntLiveResult)value;
        TestCaseStripePanel pds=new TestCaseStripePanel();
        pds.getIndexLabel().setText(row+1+"");
        pds.getStatusLabel().setText(ps.getMessage());
        ps.addRunnable(()->{
            pds.getStatusLabel().setText(ps.getMessage());
            try{
                if(ps.getRunTime()>0)
                    pds.getTimeLabel().setText(""+ps.getRunTime()/1000);
            }catch(Exception ex){}
            table.updateUI();
        });
        return pds;
    }
    
}
