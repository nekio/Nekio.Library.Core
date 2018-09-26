package nekio.library.utils.marshall.binders;

/**
 * @param <U>
 * @param <M>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/18
 *
 * Marshallized contract
 */

public interface IMarshallized<U, M> {
    public abstract void setMarshallizedValue(M M);
    public abstract M getMarshallizedValue();
    
    public abstract U unmarshall(M m);
}
