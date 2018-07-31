package nekio.library.framework.adapters;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/22
 *
 * Class Adapter for simplify Manager processing.
 * 
 * Use this class for generic Manager handling,
 * If not, use Manager class instead and it must be handle specifically by some business rules.
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.contracts.IProperties;
import nekio.library.common.contracts.component.IPlugin;
import nekio.library.common.enums.ComponentStatus;
import nekio.library.framework.P;
import nekio.library.framework.component.ComponentDTO;
import nekio.library.framework.component.Manager;
import nekio.library.log.Logger;
// </editor-fold>

public class ManagerAdapter extends Manager {
    @Override
    public String getComponentId() {
        return P.ID;
    }  

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ManagerAdapter(){
        this(null);
    }
    
    protected ManagerAdapter(ComponentDTO dto){
        if(dto != null){
            super.dto = dto;
            super.status = ComponentStatus.Closed;
            
            Logger.brace(dto.getCode() + " Instance successfully created");
            Logger.f(dto.toString());
        }else{
            P.Log.i("Create an empty instance");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IComponent Overrides">
    @Override
    public boolean activate(){
        boolean result = false;
        
        if(super.activate()){
            P.Log.i("Activating...");

            status = ComponentStatus.Active;
            super.heartbeat();
            result = true;
        }
        
        return result;
    }    
    
    @Override
    public boolean activate(IProperties properties){
        boolean result = false;
        
        if(super.activate(properties)){
            P.Log.i("Activating with Properties...");

            status = ComponentStatus.Active;
            super.heartbeat();
            result = true;
        }
        
        return result;
    }
    
    @Override
    public boolean initialize(){
        boolean result = false;
        
        if(super.initialize()){
            P.Log.i("Initializing...");

            // Initialize properties values in component (if requiered)
            // TO - DO

            status = ComponentStatus.Initialized;
            super.heartbeat();
            result = true;
        }
        
        return result;
    }
    
    @Override
    public boolean publish(){
        boolean result = false;
        
        if(super.publish()){
            P.Log.i("Publishing...");

            // Initialize properties values in component (if requiered)
            // TO - DO

            result = true;
        }
        
        return result;
    }
    
    @Override
    public boolean restart(){
        boolean result = false;
        
        if(super.restart()){
            P.Log.i("Restarting...");

            status = ComponentStatus.Uninitialized;
            super.heartbeat();

            if(this.activate()){
                if(this.initialize()){
                    result = true;
                }
            }
            
        }
        
        return result;
    }
    
    @Override
    public boolean reloadProperties(IProperties properties){
        boolean result = false;
        
        P.Log.i("Reloading Properties...");        
        if(super.uninitialize()){
            if(super.reloadProperties(properties)){
                if(super.initialize()){
                    P.Log.i("Properties Reloaded");
                    result = true;
                }
            }
        }
        
        return result;
    }
    
    @Override
    public boolean uninitialize(){
        boolean result = false;
        
        if(super.uninitialize()){
            P.Log.i("Uninitializing...");

            status = ComponentStatus.Uninitialized;
            super.heartbeat();
            result = true;
        }
        
        return result;
    }
    
    @Override
    public boolean deactivate(){
        boolean result = false;
        
        if(super.deactivate()){
            P.Log.i("Deactivating...");

            status = ComponentStatus.Deactivated;
            super.heartbeat();
            result = true;
        }
        
        return result;
    }
    
    @Override
    public boolean dispose(){
        boolean result = false;
        
        if(super.dispose()){
            P.Log.i("Disposing...");        
            result = true;
        }
        
        return result;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="IManager Overrides">
    @Override
    public void addPlugin(String pluginCode, IPlugin plugin) {
        super.addPlugin(pluginCode, plugin);
    }
    
    @Override
    public IPlugin getPlugin(String pluginCode){
        return super.getPlugin(pluginCode);
    }
    
    @Override
    public void startPlugin(IPlugin plugin) {
        super.startPlugin(plugin);
    }
    
    @Override
    public void startPlugin(IPlugin plugin, IProperties properties) {
        super.startPlugin(plugin, properties);
    }
    
    @Override
    public void reloadPlugin(IPlugin plugin, IProperties properties) {
        super.reloadPlugin(plugin, properties);
    }
    
     @Override
    public void restartPlugin(IPlugin plugin) {
        super.restartPlugin(plugin);
    }

    @Override
    public void stopPlugin(IPlugin plugin) {
        super.stopPlugin(plugin);
    }
    // </editor-fold>
}
