package nekio.library.framework.dp.factory;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Component Factory
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.contracts.component.IComponent;
import nekio.library.framework.component.Component;
import nekio.library.log.Logger;
// </editor-fold>

public class ComponentFactory {
    // <editor-fold defaultstate="collapsed" desc="Static">
    public static <P extends IComponent> IComponent getInstance(Class<P> clazz) {
        P p = null;
        
        try{
            p = clazz.newInstance();
        }catch(InstantiationException | IllegalAccessException e){
            Logger.x(e);
        }
        
        return p;
    }
    // </editor-fold>
}
