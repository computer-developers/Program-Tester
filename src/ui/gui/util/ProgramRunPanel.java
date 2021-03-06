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

import java.io.File;
import java.nio.file.Paths;
import java.util.function.Consumer;

/**
 *
 * @author Neel Patel
 */
public class ProgramRunPanel extends javax.swing.JPanel {

    private Consumer<String> cons=x->{};
    
    /**
     * Creates new form ProgramDetailCreater
     */
    public ProgramRunPanel() {
        //creditErrLabel.setText("");
        initComponents();    
        
    }

    public void init(){
    }
    
    public synchronized void setCon(Consumer<String> cons){
        if(cons!=null)
            this.cons=cons;
    }
    
    private void update(){
        try{
            File file= fileChooser.getSelectedFile();
            if(file==null)
                return;
            String c=buttonGroup.getSelection().getActionCommand();
            String cmd="";
            switch(c){
                case "java":cmd=c+" \""
                        +Paths.get(file.toPath().getParent().toString(),
                                file.toPath().getFileName().toString().split("\\.")[0])
                        +"\"";
                            break;
                case "py":cmd=c+" \""+file.toString()+"\"";
                            break;
                case "":cmd="\""+file.toString()+"\"";
            }
            cmdText.setText(cmd);
        }catch(Exception ex){
            System.err.println("error in ProgramRunPanel. "+ex);
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

        buttonGroup = new javax.swing.ButtonGroup();
        plLabel = new javax.swing.JLabel();
        cmdLabel = new javax.swing.JLabel();
        cmdText = new javax.swing.JTextField();
        executeButton = new javax.swing.JButton();
        javaRadio = new javax.swing.JRadioButton();
        pythonRadio = new javax.swing.JRadioButton();
        cRadio = new javax.swing.JRadioButton();
        fileChooser = new javax.swing.JFileChooser();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(9, 238, 249));
        setToolTipText(""); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(100, 17));
        setName(""); // NOI18N
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));

        plLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        plLabel.setForeground(new java.awt.Color(9, 238, 249));
        plLabel.setText("Programing Language");
        plLabel.setName("titleLable"); // NOI18N

        cmdLabel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        cmdLabel.setForeground(new java.awt.Color(9, 238, 249));
        cmdLabel.setText("Executable Command");
        cmdLabel.setName("cmdLabel"); // NOI18N

        cmdText.setBackground(new java.awt.Color(1, 1, 37));
        cmdText.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        cmdText.setForeground(new java.awt.Color(134, 176, 179));
        cmdText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        cmdText.setName("cmdText"); // NOI18N
        cmdText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTextActionPerformed(evt);
            }
        });

        executeButton.setBackground(new java.awt.Color(60, 196, 72));
        executeButton.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        executeButton.setForeground(new java.awt.Color(255, 255, 255));
        executeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/gui/util/source/Sort Right_48px.png"))); // NOI18N
        executeButton.setToolTipText("");
        executeButton.setBorder(null);
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(javaRadio);
        javaRadio.setForeground(new java.awt.Color(255, 255, 255));
        javaRadio.setText("Java");
        javaRadio.setActionCommand("java");
        javaRadio.setContentAreaFilled(false);
        javaRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javaRadioActionPerformed(evt);
            }
        });

        buttonGroup.add(pythonRadio);
        pythonRadio.setForeground(new java.awt.Color(255, 255, 255));
        pythonRadio.setText("Python");
        pythonRadio.setActionCommand("py");
        pythonRadio.setContentAreaFilled(false);
        pythonRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javaRadioActionPerformed(evt);
            }
        });

        buttonGroup.add(cRadio);
        cRadio.setForeground(new java.awt.Color(255, 255, 255));
        cRadio.setSelected(true);
        cRadio.setText("C or C++");
        cRadio.setActionCommand("");
        cRadio.setContentAreaFilled(false);
        cRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javaRadioActionPerformed(evt);
            }
        });

        fileChooser.setForeground(new java.awt.Color(255, 255, 255));
        fileChooser.setDragEnabled(true);
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdText)
                        .addGap(18, 18, 18)
                        .addComponent(executeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(plLabel)
                                .addGap(55, 55, 55)
                                .addComponent(javaRadio)
                                .addGap(18, 18, 18)
                                .addComponent(pythonRadio)
                                .addGap(18, 18, 18)
                                .addComponent(cRadio)))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plLabel)
                    .addComponent(javaRadio)
                    .addComponent(pythonRadio)
                    .addComponent(cRadio))
                .addGap(18, 18, 18)
                .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cmdLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(executeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdText))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdTextActionPerformed

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        cons.accept(cmdText.getText());
    }//GEN-LAST:event_executeButtonActionPerformed

    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        update();
    }//GEN-LAST:event_fileChooserActionPerformed

    private void javaRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_javaRadioActionPerformed
        update();
    }//GEN-LAST:event_javaRadioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton cRadio;
    private javax.swing.JLabel cmdLabel;
    private javax.swing.JTextField cmdText;
    private javax.swing.JButton executeButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JRadioButton javaRadio;
    private javax.swing.JLabel plLabel;
    private javax.swing.JRadioButton pythonRadio;
    // End of variables declaration//GEN-END:variables
}
