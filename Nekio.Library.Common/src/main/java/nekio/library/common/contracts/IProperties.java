package nekio.library.common.contracts;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Properties Contract
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Map;
// </editor-fold>

public interface IProperties {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract String getAbsolutePath();
    public abstract Map getFields();
    // </editor-fold>
}