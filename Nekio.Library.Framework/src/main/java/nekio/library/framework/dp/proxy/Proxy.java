package nekio.library.framework.dp.proxy;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <P>
 *
 * Design Pattern - Proxy Template
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class Proxy<P> implements IProxy<P>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final P p;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Proxy(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        this(Class.forName(className));
    }
    
    public Proxy(Class clazz) throws InstantiationException, IllegalAccessException{
        this.p = (P)clazz.newInstance();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static <P> P getInstance(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return getInstance((Class<P>)Class.forName(className));
    }
    
    public static <P> P getInstance(Class<P> clazz) throws IllegalAccessException, InstantiationException {
       return (P)clazz.newInstance();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public P unproxy() {
        return p;
    }
    // </editor-fold>
}
