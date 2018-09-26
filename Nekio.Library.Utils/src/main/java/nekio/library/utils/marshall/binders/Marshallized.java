package nekio.library.utils.marshall.binders;

/**
 * @param <U>
 * @param <M>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/18
 *
 * Marshallized class
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public abstract class Marshallized<U, M> implements IMarshallized<U, M>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private M m;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementations">    
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
