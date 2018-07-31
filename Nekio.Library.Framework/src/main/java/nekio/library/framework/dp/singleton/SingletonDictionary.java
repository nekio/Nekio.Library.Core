package nekio.library.framework.dp.singleton;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/17
 *
 * Generic Singleton Dictionary
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.HashMap;
import java.util.Map;
// </editor-fold>

public class SingletonDictionary {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private static final Map<String, Object> ELEMENTS = new HashMap<>();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    private SingletonDictionary(){}
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static <T> T getInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T singleton = (T)ELEMENTS.get(clazz.getName());
        if (singleton == null) {
            singleton = clazz.newInstance();
            ELEMENTS.put(clazz.getName(), singleton);
        }
        
        return singleton;
    }
    
    public static Map<String, Object> getElements() {
        return ELEMENTS;
    }
    // </editor-fold>
}
