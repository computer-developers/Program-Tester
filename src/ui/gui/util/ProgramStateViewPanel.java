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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.function.Consumer;
import lib.problemDefination.IntProgramDetail;
import lib.run.test.util.IntProgramState;
import static programTester.config.Configuration.TEST_FAIL;
import static programTester.config.Configuration.TEST_FILE_ERROR;
import static programTester.config.Configuration.TEST_PASS;
import static programTester.config.Configuration.TEST_PRESENT_ERROR;
import static programTester.config.Configuration.TEST_TIME_ERROR;

/**
 *
 * @author Neel Patel
 */
public class ProgramStateViewPanel extends javax.swing.JPanel {

    private Consumer<IntProgramDetail> cons=x->{};
    private IntProgramState ps;
    //private Image background= new javax.swing.ImageIcon(getClass().getResource("/ui/gui/util/source/light-minimalistic-soft-shading-gradient-background-800x600-wallpaper.jpg")).getImage();
    
    /**
     * Creates new form ProgramDetailCreater
     */
    public ProgramStateViewPanel() {
        //creditErrLabel.setText("");
        initComponents();    
        
    }

    public IntProgramState getProgramState() {
        return ps;
    }

    public void setPs(IntProgramState ps) {
        this.ps = ps;
        update();
    }
    
    private void update(){
        titleText.setText(ps.getTitle());
        creditText.setText(ps.getCredit()+"");
        ps.addRunnable(this::statusUpdate);
        statusUpdate();
        descriptionText.setText("");
        ps.getDescription().forEach(i->descriptionText.append(i+"\n"));
        inputText.setText("");
        ps.getInput().forEach(i->inputText.append(i+"\n"));
        outputText.setText("");
        ps.getOutput().forEach(i->outputText.append(i+"\n"));
        sampleInputText.setText("");
        ps.getSampleInput().forEach(i->sampleInputText.append(i+"\n"));
        sampleOutputText.setText("");
        ps.getSampleOutput().forEach(i->sampleOutputText.append(i+"\n"));
    }
    
    private void statusUpdate(){
        switch(ps.getState()){
            case TEST_PASS:statusText.setText("Solved");break;
            case TEST_PRESENT_ERROR:statusText.setText("Presentation Error");break;
            case TEST_FAIL:statusText.setText("Unsolved");break;
            case TEST_TIME_ERROR:statusText.setText("Time Limit Exceed");break;
            case TEST_FILE_ERROR:statusText.setText("Unsolved");break;
        }
    }
    
    public synchronized void setCon(Consumer<IntProgramDetail> cons){
        if(cons!=null)
            this.cons=cons;
        
    }
    
    /*@Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Image background= Toolkit.getDefaultToolkit()
        //    .createImage("light-minimalistic-soft-shading-gradient-background-800x600-wallpaper.jpg");
        System.out.println("test "+background);
        g.drawImage(background,0 , 0, this);
        System.out.println("test 2 "+background);
    }*/
    
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
        createButton = new javax.swing.JButton();
        sampleInputPanel = new javax.swing.JPanel();
        sampleInputLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sampleInputText = new javax.swing.JTextArea();
        sampleOutputPanel = new javax.swing.JPanel();
        sampleOutputLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        sampleOutputText = new javax.swing.JTextArea();
        statusText = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(9, 238, 249));
        setToolTipText(""); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(100, 17));
        setName(""); // NOI18N
        setOpaque(false);
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

        titleText.setEditable(false);
        titleText.setBackground(new java.awt.Color(0, 0, 0));
        titleText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        titleText.setForeground(new java.awt.Color(134, 176, 179));
        titleText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        titleText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        titleText.setName("titleText"); // NOI18N
        titleText.setOpaque(false);
        titleText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTextActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);

        inputText.setEditable(false);
        inputText.setBackground(new java.awt.Color(0, 0, 0));
        inputText.setColumns(20);
        inputText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        inputText.setForeground(new java.awt.Color(134, 176, 179));
        inputText.setLineWrap(true);
        inputText.setRows(2);
        inputText.setToolTipText("");
        inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(inputText);

        jScrollPane2.setOpaque(false);

        descriptionText.setEditable(false);
        descriptionText.setBackground(new java.awt.Color(0, 0, 0));
        descriptionText.setColumns(20);
        descriptionText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        descriptionText.setForeground(new java.awt.Color(134, 176, 179));
        descriptionText.setLineWrap(true);
        descriptionText.setRows(2);
        descriptionText.setToolTipText("");
        descriptionText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(descriptionText);

        jScrollPane3.setOpaque(false);

        outputText.setEditable(false);
        outputText.setBackground(new java.awt.Color(0, 0, 0));
        outputText.setColumns(20);
        outputText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        outputText.setForeground(new java.awt.Color(134, 176, 179));
        outputText.setLineWrap(true);
        outputText.setRows(2);
        outputText.setToolTipText("");
        outputText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setViewportView(outputText);

        creditText.setEditable(false);
        creditText.setBackground(new java.awt.Color(0, 0, 0));
        creditText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        creditText.setForeground(new java.awt.Color(134, 176, 179));
        creditText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        creditText.setName("titleText"); // NOI18N
        creditText.setOpaque(false);
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

        createButton.setBackground(new java.awt.Color(60, 196, 72));
        createButton.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        createButton.setForeground(new java.awt.Color(255, 255, 255));
        createButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/gui/util/source/Sort Right_48px.png"))); // NOI18N
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
        sampleInputPanel.setOpaque(false);
        sampleInputPanel.setPreferredSize(new java.awt.Dimension(75, 75));

        sampleInputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        sampleInputLabel.setForeground(new java.awt.Color(9, 238, 249));
        sampleInputLabel.setText("Sample Input");
        sampleInputLabel.setName("sampleInputLable"); // NOI18N

        jScrollPane4.setOpaque(false);

        sampleInputText.setEditable(false);
        sampleInputText.setBackground(new java.awt.Color(0, 0, 0));
        sampleInputText.setColumns(20);
        sampleInputText.setFont(new java.awt.Font("Candara", 0, 16)); // NOI18N
        sampleInputText.setForeground(new java.awt.Color(134, 176, 179));
        sampleInputText.setRows(2);
        sampleInputText.setToolTipText("");
        sampleInputText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
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
        sampleOutputPanel.setOpaque(false);
        sampleOutputPanel.setPreferredSize(new java.awt.Dimension(394, 112));

        sampleOutputLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        sampleOutputLabel.setForeground(new java.awt.Color(9, 238, 249));
        sampleOutputLabel.setText("Sample Output");
        sampleOutputLabel.setName("sampleOutputLabel"); // NOI18N

        jScrollPane5.setOpaque(false);

        sampleOutputText.setEditable(false);
        sampleOutputText.setBackground(new java.awt.Color(0, 0, 0));
        sampleOutputText.setColumns(20);
        sampleOutputText.setFont(new java.awt.Font("Candara", 0, 16)); // NOI18N
        sampleOutputText.setForeground(new java.awt.Color(134, 176, 179));
        sampleOutputText.setRows(2);
        sampleOutputText.setToolTipText("");
        sampleOutputText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sampleOutputText.setMinimumSize(new java.awt.Dimension(100, 17));
        jScrollPane5.setViewportView(sampleOutputText);

        javax.swing.GroupLayout sampleOutputPanelLayout = new javax.swing.GroupLayout(sampleOutputPanel);
        sampleOutputPanel.setLayout(sampleOutputPanelLayout);
        sampleOutputPanelLayout.setHorizontalGroup(
            sampleOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sampleOutputPanelLayout.createSequentialGroup()
                .addComponent(sampleOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
        );
        sampleOutputPanelLayout.setVerticalGroup(
            sampleOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sampleOutputPanelLayout.createSequentialGroup()
                .addComponent(sampleOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5))
        );

        statusText.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        statusText.setForeground(new java.awt.Color(255, 255, 0));
        statusText.setText("Not Solved");
        statusText.setName("creditLable"); // NOI18N

        statusLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(9, 238, 249));
        statusLabel.setText("Status :-");
        statusLabel.setName("creditLable"); // NOI18N

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sampleInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(creditLabel)
                                .addGap(30, 30, 30)
                                .addComponent(creditText, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sampleOutputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))))
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
                .addGap(18, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(creditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(creditLabel)
                        .addComponent(statusLabel)
                        .addComponent(statusText))
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
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
        
    }//GEN-LAST:event_creditTextFocusLost

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        cons.accept(ps);
    }//GEN-LAST:event_createButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton createButton;
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
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusText;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleText;
    // End of variables declaration//GEN-END:variables
}
