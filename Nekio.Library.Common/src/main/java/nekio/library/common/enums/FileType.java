package nekio.library.common.enums;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Class for File Type constants
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.File;
// </editor-fold>

public enum FileType {
    // <editor-fold defaultstate="collapsed" desc="Enumerations">
    Image("img"),
    JAR("jar"),
    XML("xml"),
    CSS("styles"),
    Javascript("js"),
    Properties("prop"),
    BD("sql"),
    Miscelaneous("misc"),
    Plugins("plugin");
    
    private String acronym;
    
    private FileType(String acronym){
        this.acronym = acronym;
    }

    public String getAcronym() {
        return acronym;
    }
    
    public String getFoldername() {
        return File.separator + acronym;
    }
    // </editor-fold>
}
