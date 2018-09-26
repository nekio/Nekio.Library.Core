package nekio.library.framework.dp.brigde;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <Inner>
 *
 * Design Pattern - Bridge Contract
 */

public interface IBridge<Inner> {
    public abstract void setInner(Inner inner);
    public abstract Inner getInner();
}
