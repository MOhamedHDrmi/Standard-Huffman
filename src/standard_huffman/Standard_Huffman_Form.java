package standard_huffman;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author HDrmi
 */
public class Standard_Huffman_Form extends javax.swing.JFrame {

    public Standard_Huffman_Form() {
        initComponents();
    }
    static Standard_Huffman obj;
    String path = "C:\\Users\\HDrmi\\Documents\\NetBeansProjects\\Standard_Huffman";

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        Compress = new javax.swing.JButton();
        Decompress = new javax.swing.JButton();
        Input = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        openfolder = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Compress.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        Compress.setText("Compress");
        Compress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompressActionPerformed(evt);
            }
        });

        Decompress.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        Decompress.setText("Decompress");
        Decompress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecompressActionPerformed(evt);
            }
        });

        Output.setEditable(false);
        Output.setColumns(20);
        Output.setRows(5);
        jScrollPane1.setViewportView(Output);

        openfolder.setFont(new java.awt.Font("Sitka Subheading", 2, 14)); // NOI18N
        openfolder.setText("Open Folder");
        openfolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openfolderActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Sitka Subheading", 2, 14)); // NOI18N
        jButton1.setText("Read File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Compress, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(openfolder)
                        .addGap(18, 18, 18)
                        .addComponent(Decompress, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(Input)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Compress, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Decompress, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openfolder)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("static-access")
    private void CompressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompressActionPerformed
        try {
            PrintWriter writer = new PrintWriter("Compress.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Standard_Huffman_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.clear();
        if (Input.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There is no Text", "Error", JOptionPane.OK_CANCEL_OPTION);
            return;
        }
        JFileChooser fl = new JFileChooser();
        fl.setCurrentDirectory(new File("C:\\Users\\HDrmi\\Documents\\NetBeansProjects\\Standard_Huffman"));
        fl.setDialogTitle("Choose File");
        if (fl.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = fl.getSelectedFile().getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "No File Choosen", "Warnning", JOptionPane.CANCEL_OPTION);
            return;
        }
        String res = obj.Compress(Input.getText(), path);
        Output.setText(Standard_Huffman.codeMap + "\n" + res);
    }//GEN-LAST:event_CompressActionPerformed

    private void openfolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openfolderActionPerformed
        // TODO add your handling code here:
        Path p = Paths.get(path);
        try {
            Process builder = Runtime.getRuntime().exec("cmd /c start " + p.getParent());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error", "Warnning", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_openfolderActionPerformed

    @SuppressWarnings("static-access")
    private void DecompressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecompressActionPerformed
        obj.clear();
        JFileChooser fl = new JFileChooser();
        fl.setCurrentDirectory(new File("C:\\Users\\HDrmi\\Documents\\NetBeansProjects\\Standard_Huffman"));
        fl.setDialogTitle("Choose File");
        if (fl.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = fl.getSelectedFile().getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "No File Choosen", "Warnning", JOptionPane.CANCEL_OPTION);
            return;
        }
        String str = obj.Decompress(path);
        Output.setText(str);
    }//GEN-LAST:event_DecompressActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fl = new JFileChooser();
        fl.setCurrentDirectory(new File("C:\\Users\\HDrmi\\Documents\\NetBeansProjects\\Standard_Huffman"));
        fl.setDialogTitle("Choose File");
        if (fl.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = fl.getSelectedFile().getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "No File Choosen", "Warnning", JOptionPane.CANCEL_OPTION);
            return;
        }
        try {
            String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
            Input.setText(content);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Can't Read File", "Warnning", JOptionPane.CANCEL_OPTION);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    @SuppressWarnings("Convert2Lambda")
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Standard_Huffman_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        obj = new Standard_Huffman();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Standard_Huffman_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Compress;
    private javax.swing.JButton Decompress;
    private javax.swing.JTextField Input;
    private javax.swing.JTextArea Output;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton openfolder;
    // End of variables declaration//GEN-END:variables
}
