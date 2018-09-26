package nekio.library.framework.dp.flyweight;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <T>
 *
 * Design Pattern - Flyweight Template
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class Flyweight<T> implements IFlyweight{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final T intrinsic;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Flyweight(){
        this(null);
    }
    
    public Flyweight(T intrinsic){
        this.intrinsic = intrinsic;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public T getIntrinsic() {
        return intrinsic;
    }
    // </editor-fold>
}
