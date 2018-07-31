package nekio.library.framework.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/22
 *
 * Manager Archetype
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.HashMap;
import java.util.Map;
import nekio.library.common.contracts.IProperties;
import nekio.library.common.contracts.component.IManager;
import nekio.library.common.contracts.component.IPlugin;
import nekio.library.framework.P;
// </editor-fold>

public class Manager extends Component implements IManager{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final Map<String, IPlugin> plugins;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Manager(){
        plugins = new HashMap<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public void addPlugin(String pluginCode, IPlugin plugin) {
        P.Log.plain("");
        P.Log.i("Adding Plugin: " + pluginCode);
        plugins.put(pluginCode, plugin);
    }
    
    @Override
    public IPlugin getPlugin(String pluginCode){
        P.Log.plain("");
        P.Log.i("Getting Plugin: " + pluginCode);
        
        return plugins.get(pluginCode);
    }
    
    @Override
    public void startPlugin(IPlugin plugin) {
        P.Log.plain("");
        P.Log.i("Starting Plugin");
        
        plugin.activate();
        plugin.initialize();
    }
    
    @Override
    public void startPlugin(IPlugin plugin, IProperties properties) {
        P.Log.plain("");
        P.Log.i("Starting Plugin with Properties");
        
        plugin.activate(properties);
        plugin.initialize();
    }
    
    @Override
    public void reloadPlugin(IPlugin plugin, IProperties properties) {
        stopPlugin(plugin);
        startPlugin(plugin, properties);
    }
    
     @Override
    public void restartPlugin(IPlugin plugin) {
        P.Log.plain("");
        P.Log.i("Restarting Plugin");
        
        plugin.restart();
    }

    @Override
    public void stopPlugin(IPlugin plugin) {
        P.Log.plain("");
        P.Log.i("Stopping Plugin");
        
        plugin.deactivate();
        plugin.dispose();
    }
    // </editor-fold>
}
