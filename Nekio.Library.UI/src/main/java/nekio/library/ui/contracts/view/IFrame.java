package nekio.library.ui.contracts.view;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Abstract for Frames (View)
 * Optimized for a unique Frame per Component (Manager/Plugin)
 * that could contains N panels.
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import javax.swing.JFrame;
import nekio.library.common.contracts.IProperties;
import nekio.library.log.configuration.ComponentValues;
import nekio.library.log.Logger;
// </editor-fold>

public abstract class IFrame extends JFrame{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    protected ComponentValues config;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    protected IFrame(ComponentValues config, String title){
        this.config = config;
        
        this.activate();
        this.addComponents();
        this.addListeners();
        this.initialize();
        
        super.setVisible(true);
        super.setTitle(title);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    protected abstract void activate();
    protected abstract void addComponents();
    protected abstract void addListeners();
    protected abstract void initialize();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    protected IPanel panelLoader(Class clazz){
        IPanel panel = null;
        
        try{
            String className = clazz.getSimpleName();
            IProperties properties = config.get(className);
            
            panel = (IPanel)clazz.newInstance();
            panel.setProperties(properties);
            panel.activate();
            
            if(properties != null){
                Logger.i("IFrame - Properties Loaded for Panel " + className + ":");
                Logger.f(properties.toString());
            }
        }catch(Exception e){
            Logger.x(e);
        }
        
        return panel;
    }
    
    protected void exit(String message) {
        Logger.w(message);
        Logger.w("Closing " + super.getTitle());
        
        this.setVisible(false);
        this.dispose();
    }
    // </editor-fold>
}