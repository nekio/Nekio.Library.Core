package nekio.library.ui.contracts.view;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Abstract for Panels (View)
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.awt.BorderLayout;
import javax.swing.JPanel;
import nekio.library.common.contracts.IProperties;
// </editor-fold>

public abstract class IPanel extends JPanel{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    protected IProperties properties;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    protected abstract void addComponents();
    protected abstract void addListeners();
    protected abstract void initialize();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    protected void activate(){
        this.setLayout(new BorderLayout());
        
        addComponents();
        addListeners();
        initialize();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public IProperties getProperties() {
        return properties;
    }
    
    public void setProperties(IProperties properties) {
        this.properties = properties;
    }
    // </editor-fold>
}