package nekio.library.framework.dp.test;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/25
 *
 * {Class Description}
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Map;
import nekio.library.framework.webservice.SoapConsumer;
import nekio.library.log.Logger;
import nekio.library.utils.P;
// </editor-fold>

public class SoapConsumerTest {
    // <editor-fold defaultstate="collapsed" desc="Testing Methods">
    public static void main(String[] args) {
        DummySoapRequestable request = new DummySoapRequestable(
            "0"
        );

        // sample values in previous versions
        request.setWsURL("");
        request.setWebserviceKey("");
        request.setXmlns("");
        request.setWebmethod("");
        request.setResponseHasManyRootNodes(true);

        try {
            Map<Integer, Map<String, String>> result = SoapConsumer.send(request);

            Map<String,String> resultMap = result.get(0);			
            if(resultMap != null) {
                Logger.f(resultMap.toString());
            }
        } catch (Exception e) {
            P.Log.x(e);
        }
    }
    // </editor-fold>
}
