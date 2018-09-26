package nekio.library.utils.marshall.binders;

/**
 * @param <U>
 * @param <M>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/18
 *
 * {Class Description}
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public abstract class MarshallPack <U, M> implements IMarshallized<U, M>, IUnmarshallized<U, M>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private U u;
    private M m;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public void setUnmarshallizedValue(U u) {
        this.u = u;
    }
    
    @Override
    public U getUnmarshallizedValue(){
        return u;
    }
    
    @Override
    public void setMarshallizedValue(M m) {
        this.m = m;
    }
    
    @Override
    public M getMarshallizedValue(){
        return m;
    }
    // </editor-fold>
}
