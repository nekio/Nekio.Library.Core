package nekio.library.framework.dp.composite;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Single Entity
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class SingleEntity<T> implements IEntity<T>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final T t;
    private final Integer id;
    private final String description;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public SingleEntity(T t){
        this(t, 0, null);
    }
    
    public SingleEntity(T t, Integer id, String description){     
        this.t = t;
        this.id = id;
        this.description = description;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implementations">
     @Override
    public T getEntity() {
        return t;
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }
    // </editor-fold>
    
    @Override
    public String toString() {
        return "Single Entity{" + "id=" + id + ", description=" + description + '}';
    }
}
