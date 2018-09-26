package nekio.library.framework.dp.proxy;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <P>
 *
 * Design Pattern - Proxy Contract
 */

public interface IProxy<P>{
    public abstract P unproxy();
}
