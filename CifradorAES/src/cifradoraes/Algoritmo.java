package cifradoraes;
    
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Algoritmo {
    String vector="AUTOMATASCELULAR";
    IvParameterSpec iv = new IvParameterSpec(vector.getBytes());
    
    public BufferedImage abrirImagen(){
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp = null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada=selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
                  
        }
        //Retornamos el valor
        return bmp;
    }
    
    public byte [] leerImagen(BufferedImage image)throws Exception{
            byte[] leerimagen;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(image, "bmp", baos);
                baos.flush();
                leerimagen = baos.toByteArray();
                //System.out.println("tam: "+leerimagen.length);
            }
            return leerimagen;
    }
    
    public byte [] encrypt(String key, byte[] imagen) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),"AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
            return cipher.doFinal(imagen);
    }
 
    public byte[] decrypt(String key, byte[] encrypted) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),"AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
            return cipher.doFinal(encrypted);
    }
    
    
    public byte [] Separar_Cabecera(byte [] leer){
        byte [] cabecera = new byte [54];
        int i;
        for(i=0;i<54;i++)
            cabecera[i]=leer[i];
    return cabecera;    
    }
    
    public byte [] Separar_Datos(byte [] leer){
        int i,j,tam=leer.length-54;
        byte [] datos = new byte [tam];
        for(i=54,j=0;i<=tam;i++,j++){
            datos[j]=leer[i];
        }
    return datos;    
    }
    
    public byte [] Unir(byte [] cabecera, byte[] datos){
        int tam = cabecera.length+datos.length;
        byte [] archivo = new byte [tam];
        int i,j=0;
        for(i=0;i<tam;i++){
            if(i<54)
                archivo[i]=cabecera[i];
            else{
                archivo[i]=datos[j];
                j++;
            }
        }
        
    return archivo;    
    }
}
