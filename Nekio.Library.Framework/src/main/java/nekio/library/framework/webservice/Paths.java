package nekio.library.framework.webservice;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Map class for webservice path encapsulation
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.HashMap;
// </editor-fold>

public class Paths extends HashMap<String, String>{
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public Paths(String mainPath, String [] paths){     
        for(String path : paths){
            this.put(path, mainPath + path);
        }
    }
    // </editor-fold>
}
