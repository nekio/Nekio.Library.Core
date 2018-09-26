package nekio.library.utils.marshall.binders;

/**
 * @param <U>
 * @param <M>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/18
 *
 * Unmarshallized class
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public abstract class Unmarshallized <U, M> implements IUnmarshallized<U, M>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private U u;
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
    // </editor-fold>
}