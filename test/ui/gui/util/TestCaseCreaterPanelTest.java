/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.util;

import java.nio.file.Paths;
import lib.db.ObjectHandler;
import lib.problemDefination.IntTestCase;

/**
 *
 * @author Neel Patel
 */
public class TestCaseCreaterPanelTest extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public TestCaseCreaterPanelTest() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        testCaseCreaterPanel1 = new ui.gui.util.TestCaseCreaterPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(getPreferredSize());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(837, 100));
        jScrollPane1.setViewportView(testCaseCreaterPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        jScrollPane1.getAccessibleContext().setAccessibleName("");

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestCaseCreaterPanelTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestCaseCreaterPanelTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestCaseCreaterPanelTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestCaseCreaterPanelTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        TestCaseCreaterPanelTest t= new TestCaseCreaterPanelTest();
        t.setVisible(true);
        t.getProgramDetailCreaterPanel1().setCon(TestCaseCreaterPanelTest::cons);
        print(152,65);
    }

    public TestCaseCreaterPanel getProgramDetailCreaterPanel1() {
        return testCaseCreaterPanel1;
    }

    public static void cons(IntTestCase pd){
        ObjectHandler oh = new ObjectHandler(Paths.get("data.db"));
        oh.writeObject("p"+pd.programID()+"i"+pd.index(), pd);
    }
    
    public static void print(long pid,long index){
        ObjectHandler oh = new ObjectHandler(Paths.get("data.db"));
        IntTestCase pd=(IntTestCase)oh.readObject("p"+pid+"i"+index);
        System.out.println(pd.getAllInput());
        System.out.println(pd.getAllOutput());
        System.out.println(pd.getTime());
        System.out.println(pd.index());
        System.out.println(pd.programID());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private ui.gui.util.TestCaseCreaterPanel testCaseCreaterPanel1;
    // End of variables declaration//GEN-END:variables
}