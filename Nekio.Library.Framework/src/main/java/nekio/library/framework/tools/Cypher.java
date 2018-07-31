package nekio.library.framework.tools;

import java.security.SecureRandom;
import nekio.library.utils.helpers.TextHelper;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/07/20
 *
 * Tool for text encryption / decryption
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class Cypher {
    // <editor-fold defaultstate="collapsed" desc="Constants">
     private static final String SYMBOLS = 
        "abcdefghijklmnopqrstuvwxyz" +
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + 
        "0123456789@¿?<^>+|'"; 
    
    private static final int SYMBOLS_QTY = SYMBOLS.length();
    private static final int MAX_CLEAN_TEXT = 15;
    private static final int MAX_INTERLOP = 3;
    private static final int MAX_ENCRYPTED_TEXT = MAX_CLEAN_TEXT + (MAX_CLEAN_TEXT * MAX_INTERLOP) + 2;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static String encrypt(){
        String result = null;
        
        return result;
    }
    
    public static String decrypt(){
        String result = null;
        
        return result;
    }
    
    public static String hide(String text, int interloping, int shift){
        String result = null;
	
        if(text.length() <= MAX_CLEAN_TEXT){
            // Ever take the first position from the parameter and then get its value
            interloping = Integer.parseInt(String.valueOf(interloping).charAt(0) + "");
            interloping = getInterloppingValue(interloping);

            shift = Integer.parseInt(String.valueOf(shift).charAt(0) + "");

            SecureRandom randomizer = new SecureRandom();
            StringBuilder sb = new StringBuilder();
            char charValue;
            for(int i=0; i<text.length(); i++){
                sb.append((char)(text.charAt(i) + shift));

                for(int j=0; j<interloping; j++){
                    charValue = SYMBOLS.charAt(randomizer.nextInt(SYMBOLS_QTY));
                    sb.append(charValue);
                }
            }

            sb.insert(0, interloping);
            sb.append(shift);

            result = sb.toString();
        }
        
        return result;
    }
    
    public static String unhide(String text){
        String result = null;
		
        if(text.length() <= MAX_ENCRYPTED_TEXT){
            int interloping = TextHelper.intFromChar(text.charAt(0));
            interloping = getInterloppingValue(interloping) + 1;

            int shift = TextHelper.intFromChar(text.charAt(text.length() - 1));

            String criticalText = text.substring(1, text.length() - 1);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<criticalText.length(); i++){
                if(i % interloping == 0){
                    sb.append((char)(criticalText.charAt(i) - shift));
                }
            }

            result = sb.toString();
        }
        
        return result;
    }
    
    private static int getInterloppingValue(int interlop){
        int result = 0;
        
        switch(interlop){
            case 0:
            case 4:
            case 7:
                result = 1;
                break;
            case 5:
            case 8:
                result = 2;
                break;
            case 6:
            case 9:
                result = 3;
                break;
            default: 
                result = interlop;
        }
        
        return result;
    }
    // </editor-fold>
}
