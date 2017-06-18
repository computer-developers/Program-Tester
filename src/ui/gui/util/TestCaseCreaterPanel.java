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

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.LongPredicate;
import lib.problemDefination.IntTestCase;
import lib.problemDefination.ProgramDetails;

/**
 *
 * @author Neel Patel
 */
public class TestCaseCreaterPanel extends javax.swing.JPanel {

    private Consumer<IntTestCase> cons=x->{};
    private LongPredicate prePid=x->true;
    private LongPredicate preIndex=x->true;
    
    /**
     * Creates new form ProgramDetailCreater
     */
    public TestCaseCreaterPanel() {
        //creditErrLabel.setText("");
        initComponents();    
        
    }

    public void init(){
    }
    
    public synchronized void setCon(Consumer<IntTestCase> cons){
        if(cons!=null)
            this.cons=cons;
    }
    
    public synchronized void setPidPredicate(LongPredicate pred){
        if(pred!=null)
            this.prePid=pred;
    }
    
    public synchronized void setIndexPredicate(LongPredicate pred){
        if(pred!=null)
            this.preIndex=pred;
    }
    
    private void make(){
        try{
            List<String> input=Arrays.asList(inputText.getText().split("\n"));
            List<String> output=Arrays.asList(outputText.getText().split("\n"));
            long pid=Long.parseLong(pidText.getText().trim());
            long index=Long.parseLong(indexText.getText().trim());
            IntTestCase tc=ProgramDetails.getTestCase(input, output, 0, pid, index);
            cons.accept(tc);
            /*
            System.out.println(title);
            System.out.println(description);
            System.out.println(input);
            System.out.println(output);
            System.out.println(sampleInput);
            System.out.println(sampleOutput);
            System.out.println(credit);
            */
        }catch(Exception ex){
            System.err.println("error in creating IntProgramDetail object. "+ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        outputLabel = new javax.swing.JLabel();
        inputLabel = new javax.swing.JLabel();
        pidLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        pidText = new javax.swing.JTextField();
        pidErrLabel = new javax.swing.JLabel();
        createButton = new javax.swing.JButton();
        indexLabel = new javax.swing.JLabel();
        indexText = new javax.swing.JTextField();
        indexErrLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(2, 17, 29));
        setForeground(new java.awt.Color(9, 238, 249));
        setToolTipText(""); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(100, 17));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        outputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        outputLabel.setForeground(new java.awt.Color(9, 238, 249));
        outputLabel.setText("Output");
        outputLabel.setName("outputLabel"); // NOI18N

        inputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        inputLabel.setForeground(new java.awt.Color(9, 238, 249));
        inputLabel.setText("Input");
        inputLabel.setName("inputLable"); // NOI18N

        pidLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        pidLabel.setForeground(new java.awt.Color(9, 238, 249));
        pidLabel.setText("Program ID");
        pidLabel.setName("creditLable"); // NOI18N

        inputText.setBackground(new java.awt.Color(19, 47, 61));
        inputText.setColumns(20);
        inputText.setFont(new java.awt.Font("Californian FB", 0, 14)); // NOI18N
        inputText.setForeground(new java.awt.Color(134, 176, 179));
        inputText.setRows(4);
        inputText.setToolTipText("");
        jScrollPane1.setViewportView(inputText);

        outputText.setBackground(new java.awt.Color(19, 47, 61));
        outputText.setColumns(20);
        outputText.setFont(new java.awt.Font("Californian FB", 0, 14)); // NOI18N
        outputText.setForeground(new java.awt.Color(134, 176, 179));
        outputText.setLineWrap(true);
        outputText.setRows(2);
        outputText.setToolTipText("");
        jScrollPane3.setViewportView(outputText);

        pidText.setBackground(new java.awt.Color(19, 47, 61));
        pidText.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        pidText.setForeground(new java.awt.Color(134, 176, 179));
        pidText.setName("titleText"); // NOI18N
        pidText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pidTextFocusLost(evt);
            }
        });
        pidText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pidTextActionPerformed(evt);
            }
        });

        pidErrLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        pidErrLabel.setForeground(new java.awt.Color(255, 77, 16));
        pidErrLabel.setToolTipText("");
        pidErrLabel.setName("creditLable"); // NOI18N

        createButton.setBackground(new java.awt.Color(60, 196, 72));
        createButton.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        createButton.setForeground(new java.awt.Color(255, 255, 255));
        createButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/gui/util/source/Checkmark_48px_1.png"))); // NOI18N
        createButton.setToolTipText("");
        createButton.setBorder(null);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        indexLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        indexLabel.setForeground(new java.awt.Color(9, 238, 249));
        indexLabel.setText("Test Case Index");
        indexLabel.setName("creditLable"); // NOI18N

        indexText.setBackground(new java.awt.Color(19, 47, 61));
        indexText.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        indexText.setForeground(new java.awt.Color(134, 176, 179));
        indexText.setName("titleText"); // NOI18N
        indexText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                indexTextFocusLost(evt);
            }
        });
        indexText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexTextActionPerformed(evt);
            }
        });

        indexErrLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        indexErrLabel.setForeground(new java.awt.Color(255, 77, 16));
        indexErrLabel.setToolTipText("");
        indexErrLabel.setName("creditLable"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pidLabel)
                    .addComponent(inputLabel))
                .addGap(356, 356, 356)
                .addComponent(pidErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createButton)
                .addGap(7, 7, 7))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pidText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(indexLabel)
                                .addGap(18, 18, 18)
                                .addComponent(indexText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(indexErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(outputLabel)
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pidText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pidLabel)
                        .addComponent(pidErrLabel))
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indexLabel)
                    .addComponent(indexText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indexErrLabel))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputLabel))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pidTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pidTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pidTextActionPerformed

    private void pidTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pidTextFocusLost
        String c=pidText.getText();
        try{
            long cr=Long.parseLong(c.trim());
            if(cr>0&&prePid.test(cr))
                pidErrLabel.setText("");
            else
                pidErrLabel.setText("invalid!!");
        }catch(Exception ex){
            pidErrLabel.setText("invalid!!");
        }
    }//GEN-LAST:event_pidTextFocusLost

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        make();
    }//GEN-LAST:event_createButtonActionPerformed

    private void indexTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indexTextFocusLost
        String c=indexText.getText();
        try{
            long cr=Long.parseLong(c.trim());
            if(cr>0&&preIndex.test(cr))
                indexErrLabel.setText("");
            else
                indexErrLabel.setText("invalid!!");
        }catch(Exception ex){
            indexErrLabel.setText("invalid!!");
        }
    }//GEN-LAST:event_indexTextFocusLost

    private void indexTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indexTextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel indexErrLabel;
    private javax.swing.JLabel indexLabel;
    private javax.swing.JTextField indexText;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JTextArea inputText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea outputText;
    private javax.swing.JLabel pidErrLabel;
    private javax.swing.JLabel pidLabel;
    private javax.swing.JTextField pidText;
    // End of variables declaration//GEN-END:variables
}
