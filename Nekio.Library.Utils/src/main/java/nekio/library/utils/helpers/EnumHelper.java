package nekio.library.utils.helpers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/02
 *
 * Helper for enums
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Arrays;
// </editor-fold>

public class EnumHelper {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static String[] getEnumValues(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
    // </editor-fold>
}
