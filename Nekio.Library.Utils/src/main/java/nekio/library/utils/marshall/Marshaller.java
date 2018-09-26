package nekio.library.utils.marshall;

/**
 * @param <U>
 * @param <M>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/18
 *
 * Util for Marshal/Unmarshal any remote serializable Object 
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.utils.P;
import nekio.library.utils.marshall.binders.IMarshallized;
import nekio.library.utils.marshall.binders.IUnmarshallized;
// </editor-fold>

public class Marshaller {    
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static <U, M> M marshallize(IUnmarshallized<U, M> unmarshallized) {
        M m = null;        
        
        try{
            P.Log.i("Trying to Transform <" + unmarshallized.getClass().getSimpleName() + ">");
            m = unmarshallized.marshall(unmarshallized.getUnmarshallizedValue());
            P.Log.i("Succesfully <" + m.getClass().getSimpleName() + "> Marshallized");
        }catch(Exception e){
            P.Log.x(e, "Error while processing the formatted object representation");
        }
        
        return m;
    }
    
    public static <U, M> U unmarshallize(IMarshallized<U, M> marshallized){
        U u = null;
        
        try{
            P.Log.i("Trying to Transform <" + marshallized.getClass().getSimpleName() + ">");
            u = marshallized.unmarshall(marshallized.getMarshallizedValue());
            P.Log.i("Succesfully <" + u.getClass().getSimpleName() + "> Unmarshallized");
        }catch(Exception e){
            P.Log.e("Error while processing the unformatted object representation");
            P.Log.x(e);
        }
        
        return u;
    }
    // </editor-fold>   
}
