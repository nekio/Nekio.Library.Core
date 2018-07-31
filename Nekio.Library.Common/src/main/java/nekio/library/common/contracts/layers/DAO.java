package nekio.library.common.contracts.layers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Data Access Object Contract
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.List;
// </editor-fold>

public interface DAO {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract DTO findById(int id);
    public abstract List<DTO> list(String where, String orderBy);
    public abstract boolean insert(DTO dto) throws Exception;
    public abstract boolean update(DTO dto) throws Exception;
    public abstract boolean delete(int id) throws Exception;
    // </editor-fold>    
}
