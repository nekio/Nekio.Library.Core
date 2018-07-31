package nekio.library.utils.helpers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Helper for Texts
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.List;
// </editor-fold>

public class TextHelper {
    // <editor-fold defaultstate="collapsed" desc="Constants">
    private final static String IDENTS = "   ";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static String formatText(String text, List<String> separators, List<String> starters, List<String> enders) {        
        if (text == null || text.trim().length() == 0) {
            return "";
        }
        
        String aux = text.replace("\t", "");
        aux = aux.replace(" ", "");
        
        for(String separator : separators)
            aux = trim(aux, separator);
        for(String starter : starters)
            aux = trim(aux, starter);
        for(String ender : enders)
            aux = trimEnd(aux, ender);
        
        String[] rows = aux.split("\n");
        
        StringBuilder formatedText = new StringBuilder();
        int stack = 0;
        boolean openTokenCatched = false;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == null || rows[i].trim().length() == 0) {
                continue;
            }

            String row = rows[i].trim();            
            
            for(String starter : starters){
                if(row.contains(starter)){
                    openTokenCatched = true;
                    stack++;
                }
            }
            
            for(String ender : enders){
                if(row.contains(ender)){
                    stack--;
                }
            }
            
            if(openTokenCatched){
                formatedText.append(ident(stack - 1));
                openTokenCatched = false;
            }else{
                formatedText.append(ident(stack));
            }
            
            formatedText.append(row);
            formatedText.append("\n");
            
        }

        return formatedText.toString();
    }
    
    public static String ident(int times) {
        return repeatString(times, IDENTS);
    }
    
    private static String trim(String value, String token){
        return value.replace(token, token + "\n");
    }
    
    private static String trimEnd(String value, String token){
        return value.replace(token, "\n" + token);
    }
    
    public static int countChar(char token, String text) {
        int counter = 0;
        
        char[] chars = text.toCharArray();
        for(int i = 0; i <= chars.length - 1; i++){
            if(token == chars[i]){
                counter++;
            }
        }
        
        return counter;
    }
    
    public static String repeatString(int times, String text) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < times; i++) {
            indent.append(text);
        }

        return indent.toString();
    }
    
    public static String sampleText(String text){
        String sampleText = null;
        
        if(text != null){
            int maxTextsize = 20;
            
            if(text.length() > maxTextsize)
                sampleText = text.substring(0, maxTextsize) + "...";
            else
                sampleText = text;
        }
        
        return sampleText;
    }
    
    public static String shift(String s) {
        return s.charAt(s.length()-1)+s.substring(0, s.length()-1);
    }
    
    public static Integer intFromChar(char character){
        Integer result = null;
        
        try {
            result = Integer.parseInt(String.valueOf(character));
        } catch (Exception e) {
        }
        
        return result;
    }
    // </editor-fold>
}
