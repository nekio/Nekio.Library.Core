package nekio.library.common;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Shared variables for all Components
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.File;
import nekio.library.common.enums.Logging;
// </editor-fold>

public class Global {
    static{
        Global.initComplete();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Static Attributes">
    public static String SUGGESTED_LOG_FOLDER = nameLogFolder("Forum", "apps", "log");
    
    public static boolean DEBUG;
    public static boolean DEBUG_IN_IDE;
    public static boolean DEBUG_IN_GUI;
    public static boolean DEBUG_IN_FILE;
    
    public static Logging.Type LOGGING_TYPE;
    public static Logging.Style LOGGING_SYLE;
    public static Logging.Background LOGGING_BACKGROUND;    
    
    public static boolean PARAMETERS_IN_LOG;
    public static boolean TIMESTAMP_IN_LOG;
    
    public static boolean LOG_TIMESTAMP_VERBOSE;
    public static String TIMESTAMP_FORMAT_VERBOSE = "yyyy/MM/dd HH:mm:ss.SSS";
    public static String TIMESTAMP_FORMAT_SIMPLY = "yy/MM/dd HH:mm:ss";
    
    public static int PROJECT_STACKTRACE_LEVEL = 2;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static void initComplete(){
        Global.DEBUG = true;
        Global.DEBUG_IN_IDE = true;
        Global.LOG_TIMESTAMP_VERBOSE = true;
        Global.PARAMETERS_IN_LOG = true;
        Global.LOGGING_TYPE = Logging.Type.Complete;
    }
    
    public static void initConcrete(){
        Global.DEBUG = true;
        Global.DEBUG_IN_IDE = true;
        Global.LOG_TIMESTAMP_VERBOSE = false;
        Global.PARAMETERS_IN_LOG = true;
        Global.LOGGING_TYPE = Logging.Type.Concrete;
    }
    
    public static String nameLogFolder(String root, String domain, String logFolder){
        return  File.separator + root + 
                File.separator + domain + 
                File.separator + logFolder;
    }
    // </editor-fold>
}
