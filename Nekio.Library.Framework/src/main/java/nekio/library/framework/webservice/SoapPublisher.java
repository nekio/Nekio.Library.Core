package nekio.library.framework.webservice;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * SOAP Publisher
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.ws.Endpoint;
import nekio.library.common.contracts.ws.IWebservice;
import nekio.library.common.contracts.ws.IWebservicePublisher;
import nekio.library.framework.P.Log;
import nekio.library.utils.P;
import nekio.library.utils.helpers.DataHelper;
import nekio.library.utils.reflection.Reflect;
// </editor-fold>

public class SoapPublisher implements IWebservicePublisher{    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Map<String, String> paths;
    private Map<String, Endpoint> endpoints;
    
    private final String MAIN_PATH;
    private final String SERVICE_URI;
    private boolean published;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public SoapPublisher(String host, String port, Class clazz){
        String packageName = clazz.getPackage().getName();
        
        String context = packageName;
        this.MAIN_PATH = "http://" + host + ":" + port + "/" + context + "/method/";
        
        packageName = packageName.replace('.','_');
        this.SERVICE_URI = "http://" + DataHelper.invertedStringByTokens(packageName, "_", ".") + "/";
        
        P.Log.i("Webservice ready to be used: " + this.MAIN_PATH);
        P.Log.i("Service URI: " + this.SERVICE_URI);
        
        this.published = publish(clazz);
        P.Log.i(clazz.getCanonicalName() + " Webservice successfully");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementations">    
    @Override
    public String getPath(String key){
        return paths.get(key);
    }
    
    @Override
    public String getPathWsdl(String key){
        return getPath(key) + "?wsdl";
    }
    
    @Override
    public String getServiceName(String path){
        return String.valueOf(path.charAt(0)).toUpperCase() + path.substring(1) + "Service";
    }
    
    @Override
    public String getMainPath() {
        return MAIN_PATH;
    }

    @Override
    public String getServiceURI() {
        return SERVICE_URI;
    }
    
    @Override
    public Endpoint getEndpoint(String key) {
        return endpoints.get(key);
    }

    @Override
    public boolean isPublished() {
        return published;
    }
    
    @Override
    public void dispose() {
        P.Log.w("Stopping Webservice " + SERVICE_URI);
        
        String endpointName = null;
        for(Entry<String, Endpoint> map : this.endpoints.entrySet()){
            try {
                endpointName = map.getKey();
                map.getValue().stop();
                
                P.Log.i(endpointName + " Webservice URL successfully stopped");
            } catch (Exception e) {
                Log.w("Something wrong with " + endpointName);
                P.Log.x(e);
            }
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    private void setPaths(String[] paths){
        this.paths = new Paths(MAIN_PATH, paths);
    }
    
    private boolean publish(Class clazz){
        boolean result = false;
        
        Endpoint endpoint = null;
        try {
            Object instance = clazz.newInstance();
            if(instance instanceof IWebservice){
                IWebservice ws = (IWebservice)instance;
                
                List<String> webmethods = Reflect.getMethods(clazz);
                setPaths(webmethods.toArray(new String[webmethods.size()]));

                endpoints = new HashMap<>();
                
                String urlMethod = null;
                for(String webmethod : webmethods){
                    try {
                        urlMethod = getPathWsdl(webmethod);
                        endpoint = Endpoint.publish(urlMethod, ws);
                        
                        endpoints.put(webmethod, endpoint);
                        result = true;
                        P.Log.i("Webservice URL published: " + urlMethod);
                    } catch (Exception e) {
                        result = false;
                        P.Log.x(e);

                        break;
                    }            
                }
            }
        } catch (Exception e) {
            P.Log.x(e);
        }
        
        return result;
    }
    // </editor-fold>
}