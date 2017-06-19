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
import lib.problemDefination.IntProgramDetail;
import lib.problemDefination.ProgramDetails;

/**
 *
 * @author Neel Patel
 */
public class ProgramDetailCreaterPanel extends javax.swing.JPanel {

    private Consumer<IntProgramDetail> cons=x->{};
    
    /**
     * Creates new form ProgramDetailCreater
     */
    public ProgramDetailCreaterPanel() {
        //creditErrLabel.setText("");
        initComponents();    
        
    }

    public void init(){
    }
    
    public synchronized void setCon(Consumer<IntProgramDetail> cons){
        if(cons!=null)
            this.cons=cons;
    }
    
    private void make(){
        try{
            String title=titleText.getText();
            List<String> description=Arrays.asList(descriptionText.getText().split("\n"));
            List<String> input=Arrays.asList(inputText.getText().split("\n"));
            List<String> output=Arrays.asList(outputText.getText().split("\n"));
            List<String> sampleInput=Arrays.asList(sampleInputText.getText().split("\n"));
            List<String> sampleOutput=Arrays.asList(sampleOutputText.getText().split("\n"));
            int credit=Integer.parseInt(creditText.getText());
            IntProgramDetail pd=ProgramDetails.getProgramDetail(title, description, input, output, sampleInput, sampleOutput, credit);
            cons.accept(pd);
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
        titleLabel = new javax.swing.JLabel();
        outputLabel = new javax.swing.JLabel();
        DescriptionLabel = new javax.swing.JLabel();
        inputLabel = new javax.swing.JLabel();
        creditLabel = new javax.swing.JLabel();
        titleText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        creditText = new javax.swing.JTextField();
        creditErrLabel = new javax.swing.JLabel();
        createButton = new javax.swing.JButton();
        sampleInputPanel = new javax.swing.JPanel();
        sampleInputLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sampleInputText = new javax.swing.JTextArea();
        sampleOutputPanel = new javax.swing.JPanel();
        sampleOutputLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        sampleOutputText = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(9, 238, 249));
        setToolTipText(""); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(100, 17));
        setName(""); // NOI18N
        setNextFocusableComponent(titleText);
        setPreferredSize(new java.awt.Dimension(800, 600));

        titleLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(9, 238, 249));
        titleLabel.setText("Title");
        titleLabel.setName("titleLable"); // NOI18N

        outputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        outputLabel.setForeground(new java.awt.Color(9, 238, 249));
        outputLabel.setText("Output");
        outputLabel.setName("outputLabel"); // NOI18N

        DescriptionLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        DescriptionLabel.setForeground(new java.awt.Color(9, 238, 249));
        DescriptionLabel.setText("Description");
        DescriptionLabel.setName("descriptionLabel"); // NOI18N

        inputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        inputLabel.setForeground(new java.awt.Color(9, 238, 249));
        inputLabel.setText("Input");
        inputLabel.setName("inputLable"); // NOI18N

        creditLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        creditLabel.setForeground(new java.awt.Color(9, 238, 249));
        creditLabel.setText("Credit");
        creditLabel.setName("creditLable"); // NOI18N

        titleText.setBackground(new java.awt.Color(1, 1, 37));
        titleText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        titleText.setForeground(new java.awt.Color(134, 176, 179));
        titleText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        titleText.setName("titleText"); // NOI18N
        titleText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTextActionPerformed(evt);
            }
        });

        inputText.setBackground(new java.awt.Color(1, 1, 37));
        inputText.setColumns(20);
        inputText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        inputText.setForeground(new java.awt.Color(134, 176, 179));
        inputText.setLineWrap(true);
        inputText.setRows(2);
        inputText.setToolTipText("");
        jScrollPane1.setViewportView(inputText);

        descriptionText.setBackground(new java.awt.Color(1, 1, 37));
        descriptionText.setColumns(20);
        descriptionText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        descriptionText.setForeground(new java.awt.Color(134, 176, 179));
        descriptionText.setLineWrap(true);
        descriptionText.setRows(2);
        descriptionText.setToolTipText("");
        jScrollPane2.setViewportView(descriptionText);

        outputText.setBackground(new java.awt.Color(1, 1, 37));
        outputText.setColumns(20);
        outputText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        outputText.setForeground(new java.awt.Color(134, 176, 179));
        outputText.setLineWrap(true);
        outputText.setRows(2);
        outputText.setToolTipText("");
        outputText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setViewportView(outputText);

        creditText.setBackground(new java.awt.Color(1, 1, 37));
        creditText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        creditText.setForeground(new java.awt.Color(134, 176, 179));
        creditText.setName("titleText"); // NOI18N
        creditText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                creditTextFocusLost(evt);
            }
        });
        creditText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditTextActionPerformed(evt);
            }
        });

        creditErrLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        creditErrLabel.setForeground(new java.awt.Color(255, 77, 16));
        creditErrLabel.setToolTipText("");
        creditErrLabel.setName("creditLable"); // NOI18N

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

        sampleInputPanel.setBackground(new java.awt.Color(0, 0, 0));
        sampleInputPanel.setMinimumSize(new java.awt.Dimension(382, 100));
        sampleInputPanel.setName(""); // NOI18N
        sampleInputPanel.setPreferredSize(new java.awt.Dimension(75, 75));

        sampleInputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        sampleInputLabel.setForeground(new java.awt.Color(9, 238, 249));
        sampleInputLabel.setText("Sample Input");
        sampleInputLabel.setName("sampleInputLable"); // NOI18N

        sampleInputText.setBackground(new java.awt.Color(1, 1, 37));
        sampleInputText.setColumns(20);
        sampleInputText.setFont(new java.awt.Font("Candara", 0, 16)); // NOI18N
        sampleInputText.setForeground(new java.awt.Color(134, 176, 179));
        sampleInputText.setRows(2);
        sampleInputText.setToolTipText("");
        sampleInputText.setMinimumSize(new java.awt.Dimension(382, 17));
        sampleInputText.setName(""); // NOI18N
        jScrollPane4.setViewportView(sampleInputText);

        javax.swing.GroupLayout sampleInputPanelLayout = new javax.swing.GroupLayout(sampleInputPanel);
        sampleInputPanel.setLayout(sampleInputPanelLayout);
        sampleInputPanelLayout.setHorizontalGroup(
            sampleInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sampleInputPanelLayout.createSequentialGroup()
                .addComponent(sampleInputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        sampleInputPanelLayout.setVerticalGroup(
            sampleInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sampleInputPanelLayout.createSequentialGroup()
                .addComponent(sampleInputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );

        sampleOutputPanel.setBackground(new java.awt.Color(0, 0, 0));
        sampleOutputPanel.setToolTipText("");
        sampleOutputPanel.setMinimumSize(new java.awt.Dimension(382, 100));
        sampleOutputPanel.setName(""); // NOI18N
        sampleOutputPanel.setPreferredSize(new java.awt.Dimension(394, 112));

        sampleOutputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        sampleOutputLabel.setForeground(new java.awt.Color(9, 238, 249));
        sampleOutputLabel.setText("Sample Output");
        sampleOutputLabel.setName("sampleOutputLabel"); // NOI18N

        sampleOutputText.setBackground(new java.awt.Color(1, 1, 37));
        sampleOutputText.setColumns(20);
        sampleOutputText.setFont(new java.awt.Font("Candara", 0, 16)); // NOI18N
        sampleOutputText.setForeground(new java.awt.Color(134, 176, 179));
        sampleOutputText.setRows(2);
        sampleOutputText.setToolTipText("");
        sampleOutputText.setMinimumSize(new java.awt.Dimension(100, 17));
        jScrollPane5.setViewportView(sampleOutputText);

        javax.swing.GroupLayout sampleOutputPanelLayout = new javax.swing.GroupLayout(sampleOutputPanel);
        sampleOutputPanel.setLayout(sampleOutputPanelLayout);
        sampleOutputPanelLayout.setHorizontalGroup(
            sampleOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sampleOutputPanelLayout.createSequentialGroup()
                .addComponent(sampleOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
        );
        sampleOutputPanelLayout.setVerticalGroup(
            sampleOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sampleOutputPanelLayout.createSequentialGroup()
                .addComponent(sampleOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(DescriptionLabel)
                            .addComponent(inputLabel)
                            .addComponent(outputLabel))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(titleText)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(creditLabel)
                        .addGap(94, 94, 94)
                        .addComponent(creditText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creditErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sampleInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(sampleOutputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLabel)
                    .addComponent(titleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DescriptionLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputLabel))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sampleInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(sampleOutputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(creditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(creditLabel)
                        .addComponent(creditErrLabel)))
                .addGap(30, 30, 30))
        );

        sampleInputPanel.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void titleTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleTextActionPerformed

    private void creditTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditTextActionPerformed

    private void creditTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_creditTextFocusLost
        String c=creditText.getText();
        try{
            int cr=Integer.parseInt(c);
            if(cr>0)
                creditErrLabel.setText("");
            else
                creditErrLabel.setText("only positive numbers are allowed!!");
        }catch(Exception ex){
            creditErrLabel.setText("only positive numbers are allowed!!");
        }
    }//GEN-LAST:event_creditTextFocusLost

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        make();
    }//GEN-LAST:event_createButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel creditErrLabel;
    private javax.swing.JLabel creditLabel;
    private javax.swing.JTextField creditText;
    private javax.swing.JTextArea descriptionText;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JTextArea inputText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea outputText;
    private javax.swing.JLabel sampleInputLabel;
    private javax.swing.JPanel sampleInputPanel;
    private javax.swing.JTextArea sampleInputText;
    private javax.swing.JLabel sampleOutputLabel;
    private javax.swing.JPanel sampleOutputPanel;
    private javax.swing.JTextArea sampleOutputText;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleText;
    // End of variables declaration//GEN-END:variables
}