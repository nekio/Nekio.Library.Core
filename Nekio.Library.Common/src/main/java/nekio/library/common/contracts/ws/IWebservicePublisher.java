package nekio.library.common.contracts.ws;

import javax.xml.ws.Endpoint;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Webservice Publisher Contract
 */

public interface IWebservicePublisher {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract String getPath(String key);
    public abstract String getPathWsdl(String key);
    public abstract String getServiceName(String path);
    public abstract String getMainPath();
    public abstract String getServiceURI();
    public abstract Endpoint getEndpoint(String key);
    public abstract boolean isPublished();
    public abstract void dispose();
    // </editor-fold>
}
