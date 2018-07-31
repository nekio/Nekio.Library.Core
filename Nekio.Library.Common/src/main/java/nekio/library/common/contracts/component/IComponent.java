package nekio.library.common.contracts.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Component Contract
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.contracts.IProperties;
import nekio.library.common.contracts.ws.IWebservicePublisher;
// </editor-fold>

public interface IComponent {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract boolean activate(IProperties properties);
    public abstract boolean activate();
    public abstract boolean initialize();
    public abstract boolean publish();
    
    public abstract boolean restart();
    public abstract boolean reloadProperties(IProperties properties);
    public abstract IWebservicePublisher getWebservicePublisher();
    
    public abstract boolean uninitialize();
    public abstract boolean deactivate();
    public abstract boolean dispose();
    // </editor-fold>
}
