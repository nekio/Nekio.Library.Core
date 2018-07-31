package nekio.library.utils.helpers;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.DatatypeConverter;
import nekio.library.common.Tools;
import nekio.library.log.Logger;
import nekio.library.log.utils.contracts.ILoggeable;
import nekio.library.utils.P;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/09
 *
 * Helper for Images
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class ImageHelper implements ILoggeable{
    @Override
    public String getComponentId() {
        return P.ID;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Static Attributes">
    public static String PATH_BASE;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static String getImgBase64(String imagePath){
        String result = null;
        
        try {
            File file = new File(imagePath);
            
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fis.read(bytes);

                result = Base64.encode(bytes);
            }
        } catch (Exception e) {
            Logger.x(e);
        }
        
        return result;
    }
    
    public static String getImagePath(String imageName){
        String imagePath = PATH_BASE;
        
        if(!imagePath.endsWith(File.separator)){
            if(!imageName.startsWith(File.separator))
                imagePath += File.separator;
        }
        
        imagePath += imageName;
        
        return imagePath;
    }
    
    public static String saveImage(String encodedImage, String filename, String extension, String folder){
        String result = null;
        
        if(encodedImage != null){
            if(encodedImage.length() > 0){
                result = saveImage(DatatypeConverter.parseBase64Binary(encodedImage), filename, extension, folder);
            }
        }
        
        return result;
    }
    
    public static String saveImage(byte[] bytes, String filename, String extension, String folder){
        return saveImage(new ByteArrayInputStream(bytes), filename, extension, folder);
    }
    
    public static String saveImage(InputStream inputStream, String filename, String extension, String folder){
        String result = null;

        try{
            File file = new File(getFilename(filename, extension, folder));
            OutputStream outputStream = new FileOutputStream(file);
            
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            
            inputStream.close();
            outputStream.close();
            
            inputStream = null;
            outputStream = null;
            
            result = file.getAbsolutePath();
            P.Log.i("\tImage Successfully saved. " + result);
        }catch(Exception e){
            Logger.x(e);
        }
        
        return result;
    }
    
    public static String getFilename(String file, String extension, String folder){
        String result = null;
        
        if(PATH_BASE != null){
            String pathDestination = PATH_BASE + File.separator + folder;

            Tools.checkPath(PATH_BASE);
            Tools.checkPath(pathDestination);

            if(!extension.startsWith("."))
                extension = "." + extension;
            
            result = pathDestination + File.separator + file + extension;
        }else{
            Logger.i("ImageHelper - PATH_BASE static variable is not setted");
        }
        
        return result;
    }
    // </editor-fold>
}
