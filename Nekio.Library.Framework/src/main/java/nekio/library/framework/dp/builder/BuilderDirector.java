package nekio.library.framework.dp.builder;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <T>
 *
 * Builder Director
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class BuilderDirector<T>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final IBuilder<T> builder;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public BuilderDirector(final IBuilder<T> builder){
        this.builder = builder;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public T construct(){        
        return (T)builder.build();
    }
    // </editor-fold>
}
