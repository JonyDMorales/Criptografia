package cifradoraes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ImagenAES extends javax.swing.JFrame {
    
    Algoritmo algoritmo =  new Algoritmo();
    String key;
    byte [] leer;
    byte [] cifrada;
    byte [] cabecera;
    byte [] datos;
    byte[] descifrada;
    InputStream in;
    BufferedImage imagen;
    ImageIcon icon;
    
    public ImagenAES() {
        initComponents();
        this.setTitle("AES Cipher");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    public void Key(String llave){
        key=llave;
        if(key.getBytes().length!=16)
            JOptionPane.showMessageDialog(null, "Key Error", "Error: ", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSelect = new javax.swing.JButton();
        btnEncryp = new javax.swing.JButton();
        btnDecrypt = new javax.swing.JButton();
        Panel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSelect.setText("Select Image");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });

        btnEncryp.setText("Encrypt");
        btnEncryp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncrypActionPerformed(evt);
            }
        });

        btnDecrypt.setText("Decrypt");
        btnDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecryptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(264, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEncryp)
                        .addGap(53, 53, 53)
                        .addComponent(btnDecrypt)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSelect)
                            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEncryp)
                    .addComponent(btnDecrypt))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        try{
            imagen = algoritmo.abrirImagen();
            icon = new ImageIcon(imagen.getScaledInstance(300, -1, Image.SCALE_DEFAULT));
        //leer Imagen  
            leer = algoritmo.leerImagen(imagen);
           
        //leer cabecera    
            cabecera = algoritmo.Separar_Cabecera(leer);
            
        //leer datos
            datos = algoritmo.Separar_Datos(leer);
            System.out.println(datos.length);
            Panel.setIcon(icon);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error"+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelectActionPerformed

    private void btnEncrypActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncrypActionPerformed
        
        try{
        //algoritmo y cifrado
            cifrada = algoritmo.encrypt(key,datos);
        System.out.println(cifrada.length);
        //Unir Datos
            byte [] ima = algoritmo.Unir(cabecera, cifrada);
        
         //Crear imagen cifrada
            in = new ByteArrayInputStream(ima);
            imagen = ImageIO.read(in);
            ImageIO.write(imagen, "bmp",  new File("Cifrada.bmp"));
            
        //Imprimimos imagen
            icon = new ImageIcon(imagen.getScaledInstance(300, -1, Image.SCALE_DEFAULT));
            Panel.setIcon(icon);
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error"+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEncrypActionPerformed

    private void btnDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecryptActionPerformed
        
        try{
        //Algoritmo descifrado
            if(cifrada==null)
                descifrada =algoritmo.decrypt(key, datos);
            else
                descifrada =algoritmo.decrypt(key, cifrada);
            System.out.println(descifrada.length);
        //Unir Datos
            byte [] im = algoritmo.Unir(cabecera, descifrada);    
        
         //Crear imagen descifrada
            in = new ByteArrayInputStream(im);
            imagen = ImageIO.read(in);
            ImageIO.write(imagen, "bmp",  new File("Descifrada.bmp"));
            
        //Imprimimos imagen 
            icon = new ImageIcon(imagen.getScaledInstance(300, -1, Image.SCALE_DEFAULT));
            Panel.setIcon(icon);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error"+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDecryptActionPerformed

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
            java.util.logging.Logger.getLogger(ImagenAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImagenAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImagenAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImagenAES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImagenAES().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Panel;
    private javax.swing.JButton btnDecrypt;
    private javax.swing.JButton btnEncryp;
    private javax.swing.JButton btnSelect;
    // End of variables declaration//GEN-END:variables
}
