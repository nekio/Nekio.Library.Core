package nekio.library.utils.test;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Class for testing project
 * Nekio.Library.Utils
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.utils.converters.Converter;
// </editor-fold>

public class UtilsTest {
    // <editor-fold defaultstate="collapsed" desc="Testing Methods">
    public static void main(String[] args) {
        safeCasting();
    }
    
    private static void safeCasting(){
        int x = 7;
        Object object = x;
        
        Integer i = Converter.safeCast(object, Integer.class);
        System.out.println(i);
    }
}
