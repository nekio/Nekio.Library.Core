package nekio.library.framework.dp.prototype;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 * @param <T>
 *
 * Design Pattern - Prototype Contract
 */

public interface IPrototype<T> extends Cloneable{
    public abstract void load(T t);
    public abstract T clone() throws CloneNotSupportedException;
}