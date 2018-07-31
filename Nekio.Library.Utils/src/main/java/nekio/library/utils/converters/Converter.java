package nekio.library.utils.converters;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/09
 *
 * Class with auxiliary methods for data type conversion
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
// </editor-fold>

public class Converter {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static byte[] toByteArray(InputStream input) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = input.read();
       
        while(reads != -1){
            baos.write(reads);
            reads = input.read();
        }
      
        return baos.toByteArray();
    }
    
    public static Date toDatetime(String textDate){
        Date date = null;
        
        if(textDate != null){
            String[] datetime = textDate.split("T");
            String[] datePart = datetime[0].split("-");
            String[] timePart = datetime[1].split(":");

            int year = Integer.parseInt(datePart[0]);
            int month = Integer.parseInt(datePart[1]);
            int day = Integer.parseInt(datePart[2]);

            int hour = Integer.parseInt(timePart[0]);
            int minute = Integer.parseInt(timePart[1]);
            int second = Integer.parseInt(timePart[2]);

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);

            date = calendar.getTime();
        }
      
        return date;
    }
    
    public static String toDateFormatted(String textDate){
        String dateFormatted = null;
        
        if(textDate != null){
            String[] datetime = textDate.split(" ");
            String[] datePart = datePart = datetime[0].split("/");

            int day = Integer.parseInt(datePart[0]);
            int month = Integer.parseInt(datePart[1]);
            int year = Integer.parseInt(datePart[2]);
            
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            dateFormatted = formatter.format(calendar.getTime());
        }
      
        return dateFormatted;
    }
    
    public static String toPercent(double value){
        return Converter.toPercent(value, null);
    }
    
    public static String toPercent(double value, String plus){
        String percentText = "";
        
        String strValue = String.valueOf(value) + "0";
        int period = strValue.indexOf(".");
        
        String integerPart = strValue.substring(0, period);
        String decimalPart = strValue.substring(period, period + 3);

        if(decimalPart.equals(".00"))
            percentText += integerPart;
        else
            percentText += integerPart + decimalPart;
        
        percentText += "%";
        
        if(plus != null){
            if(plus.equals("()")){
                percentText = "(" + percentText;
                percentText += ")";
            }else if(plus.equals("[]")){
                percentText = "[" + percentText;
                percentText += "]";
            }else if(plus.equals("{}")){
                percentText = "{" + percentText;
                percentText += "}";
            }else if(plus.equals(" ")){
                percentText = " " + percentText;
            }
        }
        
        return percentText;
    }
    // </editor-fold>
}
