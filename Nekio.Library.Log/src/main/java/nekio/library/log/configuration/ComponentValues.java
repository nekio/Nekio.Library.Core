package nekio.library.log.configuration;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Component Values Configuration Model
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Map;
import nekio.library.common.contracts.IProperties;
import nekio.library.common.model.component.ComponentConfigurations;
import nekio.library.log.Logger;
// </editor-fold>

public class ComponentValues {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Map<String, IProperties> properties;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public Map<String, IProperties> getProperties() {
        return properties;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    protected void loadConfigurations(ComponentConfigurations configurations){
        ConfigLoader config = new ConfigLoader(configurations);
        properties = config.getProperties();
    }
    
    public IProperties get(String filename) throws Exception{
        IProperties p = null;
        
        try {
            p = properties.get(filename);
            
            if(p == null)
                throw new Exception("ComponentValues - Cannot find/read: " + filename);
        } catch (Exception e) {
            Logger.x(e);
            throw e;
        }
        
        return p;
    }
    // </editor-fold>
}
