package nekio.library.framework.dp.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import nekio.library.framework.webservice.model.Requestable;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/25
 *
 * {Class Description}
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

// sample values in previous versions
public class DummySoapRequestable extends Requestable{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String version;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public DummySoapRequestable(String version){
        this.version = version;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Behaviours">

    // </editor-fold>

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    @Override
    public Map<String, String> getRequestParameters() {
        Map<String, String> result = new LinkedHashMap<>();

        result.put("version", this.version);

        return result;
    }
        
    @Override
    public List<String> getExpectedResponseNodes(){
        List<String> result = new ArrayList<>();

        result.add("");

        return result;
    }
    
    @Override
    public String toString() {
            return "SabeRamoRequestDTO [version=" + version + "]";
    }
}
