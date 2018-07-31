package nekio.library.log.utils.contracts;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Loggeable Contract
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.Global;
import nekio.library.common.Track;
import nekio.library.log.Logger;
// </editor-fold>

public interface ILoggeable {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract String getComponentId();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Default Behaviours">
    public default void i(String message){
        Logger.i(Track.as(getComponentId(), message, Global.PROJECT_STACKTRACE_LEVEL));
    }
    // </editor-fold>
}
