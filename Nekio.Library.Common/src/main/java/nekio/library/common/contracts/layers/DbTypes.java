package nekio.library.common.contracts.layers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Contracts for Oracle Database Types
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class DbTypes {
    // <editor-fold defaultstate="collapsed" desc="Inner Interfaces">
    public interface DbType {
        public abstract String getName();
    }
    
    public interface Object extends DbType {}
    public interface Collection extends DbType{}
    // </editor-fold>
}
