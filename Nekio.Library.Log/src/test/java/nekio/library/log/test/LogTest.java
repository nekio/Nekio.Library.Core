package nekio.library.log.test;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Class for testing project
 * Nekio.Library.Log
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.HashMap;
import java.util.Map;
import nekio.library.common.Track;
import nekio.library.log.Logger;
import nekio.library.log.Response;
import nekio.library.log.configuration.Properties;
import nekio.library.log.utils.contracts.ILoggeable;
// </editor-fold>

public class LogTest implements ILoggeable{
    // <editor-fold defaultstate="collapsed" desc="Testing Methods">
    public static void main(String[] args) {        
        Logger.d("Ola");
        Logger.i("K");
        Logger.w("ase");
        Logger.e("?");
        
        Logger.x(new NullPointerException("VAYA"), "mensajito");
        
        Logger.f(new Properties().toString());
        Logger.brace("TestPlugin - getInstanceGenericFactoryWay()");
        
        try {
            double a = 6/0;
        } catch (Exception e) {
            Logger.x(e);
        }
        
        Map<String, String> a = new HashMap<>();
        a.put("Hola", "1");
        a.put("Hello", "2");
        a.put("Hey", "3");
        a.put("Que pex", "4");
        a.put("Eu", "5");
        Response r = new Response(5, a, "Pelas!");
        
        Logger.i(Track.as("Nekio.X.Y", "mi mensajito custom"));
        new LogTest().testIloggeable();
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Testing ILoggeable">
    private void testIloggeable(){
        i("ILogeabble testing");
    }
    
    @Override
    public String getComponentId() {
        return "T";
    }
}
