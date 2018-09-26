package nekio.library.framework.dp.composite;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Design Pattern - Composite Contract for Entities
 */

public interface IEntity <T> {
    public abstract T getEntity();
    public abstract Integer getId();
    public abstract String getDescription();
}