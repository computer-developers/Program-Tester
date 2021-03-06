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
public class ProgramDetailTableTest extends javax.swing.JFrame {

    private List<IntProgramState> li;
    
    /**
     * Creates new form ProgramDetailTableTest
     */
    public ProgramDetailTableTest() {
        IntObjectBase ob=new ObjectDataBase(Paths.get("data.db"));
        li=ob.getAllProgramDetails().stream()
                .map(x->new ProgramStateAdapter(x)).collect(Collectors.toList());
        
        System.out.println(li.size());
        initComponents();
        jTable.setPdtm(li);
        jTable.setPdc(ProgramDetailTableTest::func);
        /*jTable.setModel(new ProgramDetailTableModel(li));
        jTable.setDefaultRenderer(Object.class,
                new ProgramDetailCell(x->func(x)));
        jTable.setDefaultEditor(Object.class,
                new ProgramDetailCell(x->func(x)));
        //this.pack();*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTable = new ui.gui.util.ProgramStateTablePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jTable.setPreferredSize(new java.awt.Dimension(600, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTable, javax.swing.GroupLayout.PREFERRED_SIZE, 651, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTable, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgramDetailTableTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgramDetailTableTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgramDetailTableTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgramDetailTableTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProgramDetailTableTest().setVisible(true);
            }
        });
    }

    public static void func(IntProgramState l){
        System.out.println("func "+l);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ui.gui.util.ProgramStateTablePanel jTable;
    // End of variables declaration//GEN-END:variables
}
