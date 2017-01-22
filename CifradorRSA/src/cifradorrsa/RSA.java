package cifradorrsa;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.swing.JOptionPane;

public class RSA extends javax.swing.JFrame {
    Algoritmo algoritmo = new Algoritmo();
    
    RandomAccessFile f;
    FileOutputStream fileOutput;
    BufferedOutputStream bufferedOutput;
    PublicKey Public;
    PrivateKey Private;
    FileInputStream file;
    ObjectInputStream inputStream;
    String Contenido;
    char [] Cadena;
    byte [] Datos;
    byte [] Aux = new byte [128];
    //Firma
    String Firma;
    byte [] verificar;
    byte [] aux;
    String firD;
    byte[] fir;
    byte [] firE;
    
    public RSA() {
        initComponents();
        this.setTitle("RSA Cipher");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKeyPublic = new javax.swing.JButton();
        txtMessage = new javax.swing.JTextField();
        btnEncrypt = new javax.swing.JButton();
        btnDecrypt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnPrivateKey = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtResult = new javax.swing.JTextField();
        btnMessage = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        btnFirma = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        btnES = new javax.swing.JButton();
        btnDS = new javax.swing.JButton();
        btnGKey = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnKeyPublic.setText("Select Public Key");
        btnKeyPublic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeyPublicActionPerformed(evt);
            }
        });

        btnEncrypt.setText("Encrypt Message");
        btnEncrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncryptActionPerformed(evt);
            }
        });

        btnDecrypt.setText("Decrypt Message");
        btnDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecryptActionPerformed(evt);
            }
        });

        jLabel1.setText("Message:");

        btnPrivateKey.setText("Select Private Key");
        btnPrivateKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrivateKeyActionPerformed(evt);
            }
        });

        jLabel2.setText("Result:");

        btnMessage.setText("Select Message");
        btnMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMessageActionPerformed(evt);
            }
        });

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        btnFirma.setText("Select Signature");
        btnFirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirmaActionPerformed(evt);
            }
        });

        btnCalcular.setText("Signature Calculate");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        btnES.setText("Encrypt Signature");
        btnES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnESActionPerformed(evt);
            }
        });

        btnDS.setText("Decrypt Signature");
        btnDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSActionPerformed(evt);
            }
        });

        btnGKey.setText("Generate Key");
        btnGKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGKeyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnFirma)
                                    .addComponent(btnVerificar)
                                    .addComponent(btnCalcular))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMessage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGKey)
                                .addGap(101, 101, 101)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnKeyPublic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrivateKey, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMessage)
                            .addComponent(txtResult)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnEncrypt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDecrypt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnES)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDS)))
                                .addGap(0, 15, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnKeyPublic)
                        .addComponent(btnMessage)
                        .addComponent(btnGKey))
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnPrivateKey))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnFirma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCalcular)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerificar)
                .addGap(18, 18, 18)
                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDecrypt)
                    .addComponent(btnEncrypt)
                    .addComponent(btnES)
                    .addComponent(btnDS))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeyPublicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeyPublicActionPerformed
        try{
            file=algoritmo.abrirKey();
            inputStream = new ObjectInputStream(file);
            Public = (PublicKey) inputStream.readObject();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnKeyPublicActionPerformed

    private void btnPrivateKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrivateKeyActionPerformed
        try{
            file=algoritmo.abrirKey();
            inputStream = new ObjectInputStream(file);
            Private = (PrivateKey) inputStream.readObject();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPrivateKeyActionPerformed

    private void btnEncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncryptActionPerformed
        Contenido=txtMessage.getText();
        try{
            fileOutput = new FileOutputStream ("archivo/archivo-cifrado");
            bufferedOutput = new BufferedOutputStream(fileOutput);
            Datos=algoritmo.EncryptMessage(Contenido,Public);
            System.out.println(Datos.length);
            bufferedOutput.write(Datos);
            bufferedOutput.close();
            txtResult.setText(new String(Datos, "UTF-8"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_btnEncryptActionPerformed

    private void btnDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecryptActionPerformed

        try{
            int i;
            fileOutput = new FileOutputStream ("archivo/archivo-descifrado");
            bufferedOutput = new BufferedOutputStream(fileOutput);
            
            if((Datos.length)%128==0)
                Contenido=algoritmo.DecryptMessage(Datos, Private);
      
            else{
                for(i=0;i<129;i++){
                    if(i<=Datos.length)
                        Aux[i]=Datos[i];
                    else
                        Aux[i]=0;
                }
                Contenido=algoritmo.DecryptMessage(Aux, Private);
            }    
            bufferedOutput.write(Contenido.getBytes());
            bufferedOutput.close();
            txtResult.setText(Contenido);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDecryptActionPerformed

    private void btnMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMessageActionPerformed
        try{
            f = algoritmo.Message();
            Datos = new byte[(int)f.length()];
            f.read(Datos);
            Contenido = new String(Datos, StandardCharsets.UTF_8);
            txtMessage.setText(Contenido);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnMessageActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        
        try{
            int i;
            for(i=0;i<verificar.length;i++){
                if(fir[i]==verificar[i])
                    txtResult.setText("Correct :)");
                else{
                    txtResult.setText("Incorrect :(");
                    break;
                }    
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnFirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirmaActionPerformed
        try{
            f = algoritmo.Message();
            fir = new byte[(int)f.length()];
            f.read(fir);
            Firma = new String(fir, StandardCharsets.UTF_8);
            txtMessage.setText(Firma);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFirmaActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Firma/Firma")));
            Firma = algoritmo.MD5(Datos);
            bw.write(Firma);
            System.out.println("T: "+Firma.length());
            verificar = Firma.getBytes();
            txtResult.setText(Firma);
            bw.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnESActionPerformed
        try{
            fileOutput = new FileOutputStream ("Firma/Firma-Cifrada");
            bufferedOutput = new BufferedOutputStream(fileOutput);
            firE=algoritmo.EncryptFir(fir, Private);
            bufferedOutput.write(firE);
            bufferedOutput.close();
            txtResult.setText(new String(firE, "UTF-8"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnESActionPerformed

    private void btnDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSActionPerformed
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Firma/Firma-Descifrada")));
            firD=algoritmo.DecryptFir(firE, Public);
            bw.write(firD);
            bw.close();
            txtResult.setText(firD);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_btnDSActionPerformed

    private void btnGKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGKeyActionPerformed
        algoritmo.GenerarKeys();
    }//GEN-LAST:event_btnGKeyActionPerformed

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
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RSA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnDS;
    private javax.swing.JButton btnDecrypt;
    private javax.swing.JButton btnES;
    private javax.swing.JButton btnEncrypt;
    private javax.swing.JButton btnFirma;
    private javax.swing.JButton btnGKey;
    private javax.swing.JButton btnKeyPublic;
    private javax.swing.JButton btnMessage;
    private javax.swing.JButton btnPrivateKey;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtMessage;
    private javax.swing.JTextField txtResult;
    // End of variables declaration//GEN-END:variables
}
