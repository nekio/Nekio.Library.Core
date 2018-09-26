package nekio.library.utils.helpers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/26
 *
 * Helper for Files
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.File;
// </editor-fold>

public class FileHelper {
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public static String getFilename(String outputPath, String outputFilename, String extension){
        StringBuilder result = new StringBuilder(outputPath);

        result.append(File.separator);
        result.append(outputFilename);
        result.append(extension);

        return result.toString();
     }
    // </editor-fold>
}
