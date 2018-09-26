package nekio.library.framework.dp.brigde;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <Inner>
 *
 * Design Pattern - Bridge Template
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class Bridge<Inner> implements IBridge<Inner> {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Inner inner;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Bridge(Inner inner){
        this.inner = inner;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public Inner getInner() {
        return inner;
    }

    @Override
    public void setInner(Inner inner) {
        this.inner = inner;
    }
    // </editor-fold>
}
