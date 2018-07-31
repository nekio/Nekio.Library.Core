package nekio.library.common.model;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Result Model (Immutable)
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Map;
// </editor-fold>

public class Result<V> {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    protected final V value;
    protected final Map<String, String> parameters;
    protected final Log log;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Result(V value, Map<String, String> parameters, Log log) {
        this.value = value;        
        this.parameters = parameters;
        this.log = log;
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public V getValue() {
        return value;
    }
    
    public Map<String, String> getParameters() {
        return parameters;
    }
    
    public Log getLog() {
        return log;
    }
    // </editor-fold>
}