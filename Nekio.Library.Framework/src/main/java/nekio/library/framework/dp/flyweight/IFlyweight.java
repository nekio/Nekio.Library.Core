package nekio.library.framework.dp.flyweight;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <T>
 *
 * Design Pattern - Flyweight Contract
 */

public interface IFlyweight <T> {
    public abstract T getIntrinsic();
}