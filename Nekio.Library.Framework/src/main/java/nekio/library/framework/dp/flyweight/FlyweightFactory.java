package nekio.library.framework.dp.flyweight;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Flyweight Factory
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.ArrayList;
import java.util.List;
// </editor-fold>

public class FlyweightFactory<T>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private static List<IFlyweight> flyweightPool;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public FlyweightFactory(){
        flyweightPool = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public void removeFlyweightElement(IFlyweight flyweight){
        flyweightPool.remove(flyweight);
    }
    
    public void clearFlyweights(){
        flyweightPool.clear();
    }
    
    public IFlyweight create(T t) {
        IFlyweight result = null;
        
        //check if we've already created an object with this Intrinsic state
        T intrinsic = null;
        for (IFlyweight flyweightPoolElement : flyweightPool) {
            intrinsic = (T)flyweightPoolElement.getIntrinsic();
            
            if (intrinsic.equals(t)) {
                result = flyweightPoolElement;
                break;
            }
        }

        if(result == null){
            result = new Flyweight(t);
            flyweightPool.add(result);
        }
        
        return result;
    }
    // </editor-fold>
}
