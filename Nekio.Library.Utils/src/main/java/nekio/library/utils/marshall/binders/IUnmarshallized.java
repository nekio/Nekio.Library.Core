package nekio.library.utils.marshall.binders;

/**
 * @param <U>
 * @param <M>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/18
 *
 * Unmarshallized contract
 */

public interface IUnmarshallized<U, M> {
    public abstract void setUnmarshallizedValue(U u);
    public abstract U getUnmarshallizedValue();
    
    public abstract M marshall(U u);
}
