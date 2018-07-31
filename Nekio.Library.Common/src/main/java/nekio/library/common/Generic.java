package nekio.library.common;

/**
 * @param <T>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Class for Generic Utils
 */

public class Generic<T> {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static <T>T notNull(Object object, T defaultValue){
        T t = null;

        if(object == null){
            t = defaultValue;
        }else{
            t = (T)object;
        }

        return t;
    }
    // </editor-fold>
}
