package nekio.library.common;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Tracker creator
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.model.component.ComponentTracker;
// </editor-fold>

public class Track {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    
    /**
     * Use for direct calls to log
     * @param componentId  The <code>String</code> Component Id.
     * @param snippetDescription  The <code>String</code> Snippet Description.
     * @return <code>ComponentTracker</code>
     */
    public static ComponentTracker as(String componentId, String snippetDescription){
        return Track.as(componentId, snippetDescription, 1);
    }
    
    /**
     * Use for calls from class P (Project Component class)
     * @param componentId  The <code>String</code> Component Id.
     * @param snippetDescription  The <code>String</code> Snippet Description.
     * @param parentLevel The <code>int</code> Parent Level (Class caller).
     * @return <code>ComponentTracker</code>
     */
    public static ComponentTracker as(String componentId, String snippetDescription, int parentLevel){
        StackTraceElement traceElement = new Exception().getStackTrace()[parentLevel];
        
        String className = traceElement.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        
        ComponentTracker result = new ComponentTracker(
            componentId,
            className,
            traceElement.getMethodName(),
            String.valueOf(traceElement.getLineNumber()),
            snippetDescription
        );
        
        return result;
    }
    // </editor-fold>
}
