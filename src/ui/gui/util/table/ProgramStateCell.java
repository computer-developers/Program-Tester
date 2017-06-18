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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import lib.run.test.util.IntProgramState;
import programTester.config.Configuration;

/**
 *
 * @author Neel Patel
 */
public class ProgramStateCell extends AbstractCellEditor implements TableCellRenderer,TableCellEditor{

    
    
    public static final ImageIcon UNSOLVED=
            new ImageIcon("\\ui\\gui\\util\\source\\Question Mark_48px.png"),
            SOLVED=new ImageIcon("\\ui\\gui\\util\\source\\Checkmark_48px_3.png"),
            PRE_ERROR=new ImageIcon("\\ui\\gui\\util\\source\\Exclamation Mark_48px.png");
    private Consumer<IntProgramState> cons=x->{};
    
    public ProgramStateCell(Consumer<IntProgramState> cons){
        this.cons=cons;
        //System.out.println("PDC Construct");
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        //System.out.println("Renderer");
        IntProgramState ps=(IntProgramState)value;
        ProgramStateStripePanel pds=new ProgramStateStripePanel();
        pds.getIndexLabel().setText(row+1+"");
        pds.getCreditLabel().setText(ps.getCredit()+"");
        pds.getTitleLabel().setText(ps.getTitle()+"");
        switch (ps.getState()) {
            case Configuration.TEST_PASS:
                pds.getStatusLabel().setForeground(new java.awt.Color(30, 229, 0));
                pds.getStatusLabel().setText(ps.getCredit()+"");
                break;
            case Configuration.TEST_PRESENT_ERROR:
                pds.getStatusLabel().setForeground(new java.awt.Color(187, 250, 9));
                pds.getStatusLabel().setText(ps.getCredit()-1+"");
                break;
            default:
                pds.getStatusLabel().setForeground(new java.awt.Color(187,187,187));
                pds.getStatusLabel().setText("0");
                break;
        }
        pds.getExpButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("test");
                ProgramStateCell.this.cons.accept(ps);
            }
        });
        ps.addRunnable(()->{
            switch (ps.getState()) {
                case Configuration.TEST_PASS:
                    pds.getStatusLabel().setForeground(new java.awt.Color(30, 229, 0));
                    pds.getStatusLabel().setText(ps.getCredit()+"");
                    break;
                case Configuration.TEST_PRESENT_ERROR:
                    pds.getStatusLabel().setForeground(new java.awt.Color(187, 250, 9));
                    pds.getStatusLabel().setText(ps.getCredit()-1+"");
                    break;
                default:
                    pds.getStatusLabel().setForeground(new java.awt.Color(187,187,187));
                    pds.getStatusLabel().setText("0");
                    break;
            }
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
        IntProgramState ps=(IntProgramState)value;
        ProgramStateStripePanel pds=new ProgramStateStripePanel();
        pds.getIndexLabel().setText(row+1+"");
        pds.getCreditLabel().setText(ps.getCredit()+"");
        pds.getTitleLabel().setText(ps.getTitle()+"");
        switch (ps.getState()) {
            case Configuration.TEST_PASS:
                pds.getStatusLabel().setForeground(new java.awt.Color(30, 229, 0));
                pds.getStatusLabel().setText(ps.getCredit()+"");
                break;
            case Configuration.TEST_PRESENT_ERROR:
                pds.getStatusLabel().setForeground(new java.awt.Color(187, 250, 9));
                pds.getStatusLabel().setText(ps.getCredit()-1+"");
                break;
            default:
                pds.getStatusLabel().setForeground(new java.awt.Color(187,187,187));
                pds.getStatusLabel().setText("0");
                break;
        }
        pds.getExpButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("test");
                ProgramStateCell.this.cons.accept(ps);
            }
        });
        ps.addRunnable(()->{
            switch (ps.getState()) {
                case Configuration.TEST_PASS:
                    pds.getStatusLabel().setForeground(new java.awt.Color(30, 229, 0));
                    pds.getStatusLabel().setText(ps.getCredit()+"");
                    break;
                case Configuration.TEST_PRESENT_ERROR:
                    pds.getStatusLabel().setForeground(new java.awt.Color(187, 250, 9));
                    pds.getStatusLabel().setText(ps.getCredit()-1+"");
                    break;
                default:
                    pds.getStatusLabel().setForeground(new java.awt.Color(187,187,187));
                    pds.getStatusLabel().setText("0");
                    break;
            }
            table.updateUI();
        });
        return pds;
    }
    
}
