package cifrador;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Algoritmo {
    KeyPairGenerator keyGen;
    KeyPair key;
    PublicKey Public;
    RSAPrivateKey Private;
    ObjectInputStream inputStream;
    ObjectOutputStream publicKeyOS;
    ObjectOutputStream privateKeyOS;
    
    byte [] J={'J','D'};
    byte [] Datos;
    byte [] Firma;
    
    public FileInputStream abrirKey(){
        //Creamos la variable que será devuelta (la creamos como null)
        FileInputStream key = null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Select Key");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroKey = new FileNameExtensionFilter("KEY & key", "KEY", "key");
        selector.setFileFilter(filtroKey);
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File keyselect =selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                key = new FileInputStream(keyselect);
            } catch (Exception e) {
            }
                  
        }
        //Retornamos el valor
        return key;
    }
    
    public RandomAccessFile Message(){
        //Creamos la variable que será devuelta (la creamos como null)
        RandomAccessFile message = null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Select Message");
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File menselect =selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                message = new RandomAccessFile(menselect,"r");
            } catch (Exception e) {
            }
        }
        //Retornamos el valor
        return message;
    }
    
    public byte[] EncryptMessage(String text, PublicKey key ) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            Cipher cipher = Cipher.getInstance("RSA");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }
    
    public byte[] EncryptFir(byte [] text, PrivateKey key) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            Cipher cipher = Cipher.getInstance("RSA");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cipherText;
    }
    
    public String DecryptMessage(byte[] text, PrivateKey key) {
        byte[] dectyptedText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    return new String(dectyptedText);
    }
    
    public String DecryptFir(byte[] text, PublicKey key) {
        byte[] dectyptedText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return new String(dectyptedText);
    }
    
    public String MD5(byte [] bytes){
        try{
            char[] HEXADECIMALES = { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F' };
            MessageDigest msgdgt = MessageDigest.getInstance("MD5");
            StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++){
                int low = (int)(bytes[i] & 0x0f);
                int high = (int)((bytes[i] & 0xf0) >> 4);
                strCryptMD5.append(HEXADECIMALES[high]);
                strCryptMD5.append(HEXADECIMALES[low]);
            }
            return strCryptMD5.toString();
        }catch(Exception e){
            return null;
        }
    }
    
    public byte [] Separar_Datos(byte [] Contenido){
        int i;
        for(i=0;(Contenido[i]!=J[0])&&(Contenido[i+1]!=J[1]);i++){}
        Datos= new byte [i];
        for(i=0;(Contenido[i]!=J[0])&&(Contenido[i+1]!=J[1]);i++)
            Datos[i]=Contenido[i];
    return Datos;    
    }
    
    public byte [] Separar_Firma(byte [] Contenido){
        int i,j=0,k;
        for(i=0;(Contenido[i]!=J[0])&&(Contenido[i+1]!=J[1]);i++){}
        k=Contenido.length-i-2;
        Firma= new byte [k];
        
        for(i=k+2;i<Contenido.length;i++,j++){
            Firma[j]=Contenido[i];
        }  
    return Firma;    
    }
    
    public boolean Verificar(byte [] Firma1, byte [] Firma2 ){
        for(int i=0; i<Firma1.length;i++)
            if(Firma1[i]!=Firma2[i])
                return false;
    return true;
    }
}
