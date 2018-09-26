package nekio.library.framework.dp.prototype;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <T>
 *
 * Design Pattern - Prototype Template
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.lang.reflect.Constructor;
// </editor-fold>

public class Prototype <T> implements IPrototype<T>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    protected T t;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public void load(T t) {
        this.t = t;
    }

    @Override
    public T clone() throws CloneNotSupportedException{
        
        T t = null;
        
        try
        {
            //super.clone();
            Class clazz = this.t.getClass();
            Constructor classConstructor = clazz.getConstructor(clazz.getSuperclass());
            t = (T)classConstructor.newInstance(this.t);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        
        return t;
    }
    // </editor-fold>
}
