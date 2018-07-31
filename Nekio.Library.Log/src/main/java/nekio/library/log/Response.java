package nekio.library.log;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Response Model (Immutable)
 * Every time that a response is created, it can implicitly be logged.
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Map;
import nekio.library.common.enums.Logging;
import nekio.library.common.model.Log;
import nekio.library.common.model.Result;
// </editor-fold>

public class Response<V> extends Result<V>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String message;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Response(V value) {
        this(value, null, null);
    }
    
    public Response(V value, String message) {
        this(value, null, message);
    }
    
    public Response(V value, Map<String, String> parameters, String message) {
        this(value, parameters, new Log(Logging.Severity.Info, "Response"), message);
    }
    
    public Response(V value, Map<String, String> parameters, Log log, String message) {
        super(value, parameters, log);
        this.setMessage(message);
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public String getMessage() {
        return message;
    }
    
    private void setMessage(String message) {
        this.message = message;
        
        Logger.trackResponse(this);
    }
    // </editor-fold>
}
