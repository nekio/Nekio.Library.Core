package nekio.library.framework.dp.factory;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Generic Factory
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.contracts.IFactory;
import nekio.library.log.Logger;
// </editor-fold>

public class GenericFactory <P> implements IFactory{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Class clazz = null;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public GenericFactory(Class clazz){
        this.clazz = clazz;
        Logger.i("PluginFactory is loading: " + clazz.getCanonicalName() );        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public P create() {
        P p = null;
        
        try{
            p = (P) clazz.newInstance();
        }catch(InstantiationException | IllegalAccessException e){
            Logger.x(e);
        }
        
        return p;
    }
    // </editor-fold>
}