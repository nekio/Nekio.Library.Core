package nekio.library.framework.webservice.model;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/25
 *
 * Abstract definition for Webservice Requestable Classes
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.List;
import java.util.Map;
// </editor-fold>

public abstract class Requestable {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String wsURL;
    private String xmlns;
    private String webserviceKey;
    private String user;
    private String passcode;
    private String webmethod;
    private String webmethodNS;
    private Boolean responseHasManyRootNodes;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public String getWsURL() {
        return wsURL;
    }

    public void setWsURL(String wsURL) {
        this.wsURL = wsURL;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getWebserviceKey() {
        return webserviceKey;
    }

    public void setWebserviceKey(String webserviceKey) {
        this.webserviceKey = webserviceKey;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasscode() {
        return passcode;
    }
    
    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getWebmethod() {
        return webmethod;
    }

    public void setWebmethod(String webmethod) {
        this.webmethod = webmethod;
    }

    public String getWebmethodNS() {
        return webmethodNS;
    }

    public void setWebmethodNS(String webmethodNS) {
        this.webmethodNS = webmethodNS;
    }

    public Boolean isResponseHasManyRootNodes() {
        return responseHasManyRootNodes;
    }

    public void setResponseHasManyRootNodes(Boolean responseHasManyRootNodes) {
        this.responseHasManyRootNodes = responseHasManyRootNodes;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract Map<String, String> getRequestParameters();
    public abstract List<String> getExpectedResponseNodes();
    // </editor-fold>    

    @Override
    public String toString() {
        return "Requestable{" + "wsURL=" + wsURL + ", xmlns=" + xmlns + ", webserviceKey=" + webserviceKey + ", user=" + user + ", passcode=" + passcode + ", webmethod=" + webmethod + ", webmethodNS=" + webmethodNS + ", responseHasManyRootNodes=" + responseHasManyRootNodes + '}';
    }
}
