package nekio.library.log.configuration;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Properties Model
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Map;
import nekio.library.common.contracts.IProperties;
// </editor-fold>

public class Properties implements IProperties{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String absolutePath;
    private Map<String, String> fields;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
    // </editor-fold>
    
    @Override
    public String toString() {
        return "Properties{" + "absolutePath=" + absolutePath + ", fields=" + fields + '}';
    }
}