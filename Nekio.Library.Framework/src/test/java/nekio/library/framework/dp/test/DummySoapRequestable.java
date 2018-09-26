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

public class DummySoapRequestable extends Requestable{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String version;
    private String numeroPoliza;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public DummySoapRequestable(String numeroPoliza){
        this.version = "v1";
        this.numeroPoliza = numeroPoliza;
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

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }
    
    @Override
    public Map<String, String> getRequestParameters() {
        Map<String, String> result = new LinkedHashMap<>();

        result.put("version	", this.version);
        result.put("npoliza", this.numeroPoliza);

        return result;
    }
        
    @Override
    public List<String> getExpectedResponseNodes(){
        List<String> result = new ArrayList<>();

        result.add("remove");
        result.add("workgroup");
        result.add("responseStatus");
        result.add("contrat");
        result.add("finivig");
        result.add("ftervig");
        result.add("idcliente");
        result.add("nagente");
        result.add("nomagte");
        result.add("npoliza");
        result.add("npolorg");
        result.add("ramsubramo");
        result.add("rfc");
        result.add("stpoliza");
        result.add("tpramo");
        result.add("tpramoi");

        return result;
    }
    
    @Override
    public String toString() {
            return "SabeRamoRequestDTO [version=" + version + ", numeroPoliza=" + numeroPoliza + "]";
    }
}
