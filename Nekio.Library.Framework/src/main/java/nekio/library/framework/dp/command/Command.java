package nekio.library.framework.dp.command;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <R> Returning Type
 * @param <A> Argument Type
 * 
 * Design Pattern - Command Template
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.framework.P;
// </editor-fold>

public class Command<R,A> implements ICommand<R,A>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private R r;
    protected String id;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public void execute(){
        execute(null);
    }
    
    @Override
    public R execute(A a) {
        P.Log.i("Result:" + r + ", Argument:" + a);
        
       return r; 
    }
    // </editor-fold>
    
    @Override
    public String toString() {
        return id;
    }
}
