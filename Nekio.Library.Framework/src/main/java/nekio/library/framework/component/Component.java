package nekio.library.framework.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Component Archetype
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Properties;
import nekio.library.common.contracts.component.IComponent;
import nekio.library.common.contracts.IProperties;
import nekio.library.common.contracts.ws.IWebservicePublisher;
import nekio.library.common.enums.ComponentStatus;
import nekio.library.framework.P;
import nekio.library.log.Logger;
import nekio.library.log.utils.contracts.ILoggeable;
// </editor-fold>

public class Component implements IComponent, ILoggeable{
    @Override
    public String getComponentId() {
        return P.ID;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    protected ComponentDTO dto;
    protected ComponentStatus status;
    protected Properties properties;
    protected IWebservicePublisher websersvicePublisher;
    protected boolean isActive;
    protected boolean isInitialized;
    protected boolean canPublish;
    protected boolean isPublished;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public void heartbeat(){
        if(dto != null){
            if(status != null){
                P.Log.i("Status: " + status + " [" + dto.getCode() + "]");
            }else{
                P.Log.w("NO HEARTBEAT!");
            }
        }else{
            P.Log.w("No data values found (ComponentDTO empty)");
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public ComponentDTO getDto() {
        return dto;
    }
    
    public Properties getProperties() {
        return properties;
    }

    public boolean isActive() {
        status = ComponentStatus.Active;
        
        return isActive;
    }

    public boolean isInitialized() {
        status = ComponentStatus.Initialized;
        
        return isInitialized;
    }
    
    public boolean canPublish() {
        return canPublish;
    }

    public boolean isPublished() {
        return isPublished;
    } 
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementations">    
    @Override
    public boolean activate(){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Activating...");
        
        if(status == ComponentStatus.Uninitialized || status == ComponentStatus.Deactivated || status == ComponentStatus.Closed){
            status = ComponentStatus.Activating;
            result = true;
        }else{
            Logger.w("Need Uninitialized/Deactivated/Closed Status to continue");
        }
        
        this.heartbeat();
        return result;
    }    
    
    @Override
    public boolean activate(IProperties properties){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Activating with Properties...");
        
        if(status == ComponentStatus.Uninitialized || status == ComponentStatus.Deactivated || status == ComponentStatus.Closed){
            status = ComponentStatus.Activating;

            this.properties = (Properties) properties;
            result = true;
        }else{
            P.Log.w("Need Uninitialized/Deactivated/Closed Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public boolean initialize(){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Initializing...");
        
        if(status == ComponentStatus.Active || status == ComponentStatus.Reconfiguring){
            status = ComponentStatus.Initializing;
            result = true;
        }else{
            P.Log.w("Need Active/Reconfiguring Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public boolean publish(){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Publishing...");
        
        if(status == ComponentStatus.Initialized){
            canPublish = true;
            P.Log.i("Component now can be published");
            result = true;
        }else{
            P.Log.w("Need Initialized Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public boolean restart(){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Restarting...");
        
        if(status == ComponentStatus.Initialized){
            status = ComponentStatus.Uninitializing;
            result = true;
        }else{
            P.Log.w("Need Initialized Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public boolean reloadProperties(IProperties properties){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Reloading Properties...");
        
        if(status == ComponentStatus.Uninitialized){
            status = ComponentStatus.Reconfiguring;
            
            this.properties = (Properties) properties;
            result = true;
        }else{
            P.Log.plain("\n");
            P.Log.w("Need Uninitialized Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public boolean uninitialize(){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Uninitializing...");
        
        if(status == ComponentStatus.Initialized){
            status = ComponentStatus.Uninitializing;
            result = true;
        }else{
            P.Log.w("Need Initialized Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public boolean deactivate(){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Deactivating...");
        
        if(status == ComponentStatus.Uninitialized){
            status = ComponentStatus.Deactivating;
            result = true;
        }else{
            P.Log.w("Need Uninitializing Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public boolean dispose(){
        boolean result = false;
        
        P.Log.plain("");
        P.Log.i("Disposing...");
        
        if(status == ComponentStatus.Deactivated){
            status = ComponentStatus.Closed;
            result = true;
        }else{
            P.Log.w("Need Deactivated Status to continue");
        }
        
        this.heartbeat();
        return result;
    }
    
    @Override
    public IWebservicePublisher getWebservicePublisher() {
        return websersvicePublisher;
    }
    // </editor-fold>
    
    @Override
    public String toString() {
        return "Component{" + "dto=" + dto + ", status=" + status + ", properties=" + properties + ", isActive=" + isActive + ", isInitialized=" + isInitialized + ", canPublish=" + canPublish + ", isPublished=" + isPublished + '}';
    }
}
