package nekio.library.common;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Common Utils (Java Natives Data Types Handling)
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class U {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static String nvl(String text){
        return nvl(text, "");
    }
    
    public static String nvl(String text, String wildcard){
        String result = wildcard;
        
        if(text != null){
            result = text;
        }
        
        return result;
    }
    
    public static String nullIfEmpty(String text){
        String result = null;
        
        if(!text.isEmpty()){
            result = text;
        }
        
        return result;
    }
    
    public static String ifNotNull(String text, String wildcard){
        String result = null;
        
        if(text != null){
            result = text + wildcard;
        }
        
        return result;
    }
    // </editor-fold>
}
