package nekio.library.framework.dp.singleton;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/17
 *
 * Generic Singleton
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class GenericSingleton {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private static Object instance;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    private GenericSingleton(){}
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static <T>T getInstance(Class<T> t) throws IllegalAccessException, InstantiationException {
        if (instance == null) {
            instance = t.newInstance();
        }

        return (T)instance;
    }
    
    public static <T>T getInstanceThreadsafe(Class<T> t) throws IllegalAccessException, InstantiationException {
        // to prevent redundant locks
        if(instance == null){
            
            // Mutual exclusion (Lock Thread)
            // NOTE: Always prefer to lock a specific block,
            // instead of the entire method, to keep a better performance
            synchronized(GenericSingleton.class) {
                
                // check that there is no such object
                if(instance == null){
                    instance = t.newInstance();
                }
            }
        }
        
        return (T)instance;
    }
    // </editor-fold>
}
