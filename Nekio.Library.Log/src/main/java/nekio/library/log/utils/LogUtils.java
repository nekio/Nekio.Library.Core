package nekio.library.log.utils;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Utilities for Logs
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
// </editor-fold>

public class LogUtils {
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
        
        int totalRows = rows.length;
        for (int i = 0; i < totalRows; i++) {
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
            if(i != totalRows - 1){
                formatedText.append("\n");
            }
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

    public static String formatDateTime(LocalDate date, LocalTime time, String format){
        String result = null;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        result = dateTime.format(formatter);
        
        return result;
    }
    
    public static String getTimestampForFile(){
        String result = getTimestamp().replace("/", "_").replace(":", "_").replace(".", "_");
        
        return result;
    }
    
    public static String getTimestamp(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        int millisec = calendar.get(Calendar.MILLISECOND);
        
        return  year + "/" + format(month, 2) + "/" + format(day, 2) + " " + 
                format(hour, 2) + ":" + format(minutes, 2) + ":" + format(seconds, 2) + "." + format(millisec, 3);
    }
    
    public static String format(int number, int padding){
        return format("" + number, padding);
    }
    
    public static String format(String text, int padding){
        if(text.length() < padding){            
            text = format("0" + text, padding);
        }
        
        return text;
    }
    // </editor-fold>
}
