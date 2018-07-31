package nekio.library.common;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Common Tools (Java Native API Handling)
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
// </editor-fold>

public class Tools {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static Object getContext(String value) throws NamingException{
        Object object = null;

        InitialContext context = new InitialContext();    
        object = context.lookup(value);
        
        return object;
    }
    
    public static void checkPath(String path){
        File destinationPath = new File(path);

        // Si no existe la carpeta
        if (!destinationPath.isDirectory()){
            // Si no pudo crear la carpeta
            if(!destinationPath.mkdir()){
                // Llamar recursivamente hasta crear la carpeta menos profunda
                checkPath(destinationPath.getParent());

                // Una vez aseguradas la carpeta padre, creamos la hija
                destinationPath.mkdir();
            }
        }
    }
    
    public static String fileToText(String path) throws FileNotFoundException, IOException{
        StringBuilder text = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(path));
        
        String currentLine = null;
        while ((currentLine = br.readLine()) != null) {
            text.append(currentLine);
        }

        return text.toString();
    }
    
    public static List<File> getAllFiles(File folder, String extension){
        List<File> result = new ArrayList<>();
        
        for(File file: folder.listFiles()){
            if(file.isFile()){
                if(file.getName().endsWith(extension)){
                    result.add(file);
                }
            }
        }
    
        return result;
    }
    // </editor-fold>
}
