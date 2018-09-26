package nekio.library.framework.dp.composite;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Composite Entity
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.ArrayList;
import java.util.List;
// </editor-fold>

public class CompositeEntity<T> implements IEntity<T>{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final T t;
    private final List<IEntity> entities;
    private final Integer id;
    private final String description;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public CompositeEntity(T t){
        this(t, 0, null);
    }
    
    public CompositeEntity(T t, Integer id, String description){
        this.t = t;
        this.entities = new ArrayList<>();
        
        this.id = id;
        this.description = description;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public void add(IEntity entity){
        entities.add(entity);
    }
    
    public void remove(IEntity entity){
        entities.remove(entity);
    }
    
    public void clear(){
        entities.clear();
    }
    
    public List<IEntity> getEntities() {
        return entities;
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
        StringBuilder text = new StringBuilder("Composite Entity{");
        text.append("id=").append(id);
        text.append(", description=").append(description);
        text.append(", entities=").append(entities.size());        
        text.append("}");
        
        return text.toString();
    }
}
