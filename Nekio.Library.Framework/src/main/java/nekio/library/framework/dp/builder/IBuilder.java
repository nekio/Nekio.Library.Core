package nekio.library.framework.dp.builder;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <T>
 *
 * Design Pattern - Builder Contract
 */

public interface IBuilder <T> {
    public abstract T build();
}