package cifrador;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.swing.JOptionPane;

public class Interfaz extends javax.swing.JFrame {

    Algoritmo algoritmo = new Algoritmo();
    
    FileOutputStream fileOutput;
    BufferedOutputStream buffer;
    ObjectInputStream inputStream;
    RandomAccessFile archivo;
    FileInputStream file;
    PublicKey Public;
    PrivateKey Private;
    
    
    byte [] Contenido;
    byte [] Encrypt;
    String Decrypt;
    String texto;
    String Firma;
    byte [] FirmaC;
    byte [] bandera = {'J','D'};
            
    public Interfaz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMessage = new javax.swing.JButton();
        btnEncrypt = new javax.swing.JButton();
        btnSign = new javax.swing.JButton();
        btnDecrypt = new javax.swing.JButton();
        btnVerify = new javax.swing.JButton();
        txtResult = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMessage.setText("Load Message");
        btnMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMessageActionPerformed(evt);
            }
        });

        btnEncrypt.setText("Encrypt");
        btnEncrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncryptActionPerformed(evt);
            }
        });

        btnSign.setText("Sign");
        btnSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignActionPerformed(evt);
            }
        });

        btnDecrypt.setText("Decrypt");
        btnDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecryptActionPerformed(evt);
            }
        });

        btnVerify.setText("Verify");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMessage))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEncrypt, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(btnSign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDecrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(txtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMessage)
                .addGap(13, 13, 13)
                .addComponent(txtResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEncrypt)
                    .addComponent(btnDecrypt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSign)
                    .addComponent(btnVerify))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMessageActionPerformed
        try{
            archivo=algoritmo.Message();
            Contenido = new byte[(int)archivo.length()];
            archivo.read(Contenido);
            texto = new String(Contenido, StandardCharsets.UTF_8);
            txtResult.setText("Successful");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnMessageActionPerformed

    private void btnEncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncryptActionPerformed
        try{
            //fileOutput= new FileOutputStream ("Archivos/Archivo-Cifrado");
            //buffer = new BufferedOutputStream(fileOutput);
            file=algoritmo.abrirKey();
            inputStream = new ObjectInputStream(file);
            Public = (PublicKey) inputStream.readObject();
            Encrypt=algoritmo.EncryptMessage(texto, Public);
            //buffer.write(Encrypt);
            //buffer.close();
            
            txtResult.setText("Encryption Successful");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEncryptActionPerformed

    private void btnDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecryptActionPerformed

        try{
            fileOutput= new FileOutputStream ("Archivo-Descifrado");
            buffer = new BufferedOutputStream(fileOutput);
            file=algoritmo.abrirKey();
            inputStream = new ObjectInputStream(file);
            Private = (PrivateKey) inputStream.readObject();
            
            Encrypt=algoritmo.Separar_Datos(Contenido);
            
            Decrypt=algoritmo.DecryptMessage(Encrypt, Private);
            buffer.write(Decrypt.getBytes());
            buffer.close();
            txtResult.setText("Decryption Successful");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDecryptActionPerformed

    private void btnSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignActionPerformed
        try{
            fileOutput= new FileOutputStream ("Archivo-Cifrado");
            buffer = new BufferedOutputStream(fileOutput);
            file=algoritmo.abrirKey();
            inputStream = new ObjectInputStream(file);
            Private = (PrivateKey) inputStream.readObject();
            
            //Calcular Firma
            Firma=algoritmo.MD5(Contenido);
            
            //Cifrar Firma
            FirmaC=algoritmo.EncryptFir(Firma.getBytes(),Private);
           
            buffer.write(Encrypt);
            buffer.write(bandera);
            buffer.write(FirmaC);
            buffer.close();
            txtResult.setText("Sing Successful");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSignActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        try{
            //Calcular Firma
            Firma=algoritmo.MD5(Decrypt.getBytes());
            
            FirmaC=algoritmo.Separar_Firma(Contenido);
            
            file=algoritmo.abrirKey();
            inputStream = new ObjectInputStream(file);
            Public = (PublicKey) inputStream.readObject();
            
            texto=algoritmo.DecryptFir(FirmaC, Public);
            boolean very=algoritmo.Verificar(texto.getBytes(),Firma.getBytes());
            
            if(very)
                txtResult.setText("Sing Successful :)");
            else
                txtResult.setText("Sing Not Successful :(");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDecrypt;
    private javax.swing.JButton btnEncrypt;
    private javax.swing.JButton btnMessage;
    private javax.swing.JButton btnSign;
    private javax.swing.JButton btnVerify;
    private javax.swing.JTextField txtResult;
    // End of variables declaration//GEN-END:variables
}
