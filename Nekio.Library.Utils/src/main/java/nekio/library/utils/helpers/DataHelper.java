package nekio.library.utils.helpers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/09
 *
 * Helper for usual Data over the Nekio's Core code
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import nekio.library.common.contracts.layers.DTO;
import nekio.library.log.Logger;
// </editor-fold>

public class DataHelper {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static DTO[] listToArrayDTO(List<? extends DTO> list){
        DTO[] array = null;
        
        try {            
            array = list.toArray(new DTO[list.size()]);
        } catch (Exception e) {
            Logger.x(e);
        }        
        
        return array;
    }
    
    public static String invertedStringByTokens(String tokenizedText, String token){
        return invertedStringByTokens(tokenizedText, token, token);
    }
    
    public static String invertedStringByTokens(String tokenizedText, String token, String newToken){
        StringBuilder result = new StringBuilder();
        
        String[] tokens = tokenizedText.split(token);
        int tokensLength = tokens.length;
        
        int lastIndex = tokensLength - 1;
        for (int i = 0; i < tokensLength; i++) {
            result.append(tokens[(lastIndex) - i]);
            
            if(i != lastIndex){
                result.append(newToken);
            }
        }
        
        return result.toString();
    }
    
    public static String printDatetime(LocalDateTime datetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy_HH:mm:ss.SSS");
        
        return formatter.format(datetime);
    }
    
    public static void printList(List list){
        for(Object o : list){
            Logger.i(o.toString());
        }
    }
    
    public static void wait(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {}
    }
    // </editor-fold>
}
