package nekio.library.framework.dp.command;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
  *@param <R> Returning Type
 * @param <A> Argument Type
 * 
 * Design Pattern - Command Contract
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public interface ICommand<R,A>{
    public abstract String getId();
    public abstract void execute();
    public abstract R execute(A a);
}
