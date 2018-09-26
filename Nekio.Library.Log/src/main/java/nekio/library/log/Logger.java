package nekio.library.log;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Class for Logging purposes
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nekio.library.common.Global;
import nekio.library.common.enums.Logging.Severity;
import nekio.library.common.model.component.ComponentTracker;
import nekio.library.common.model.Log;
import nekio.library.log.ide.ConsoleColors;
import nekio.library.log.utils.LogUtils;
// </editor-fold>

public class Logger {    
    // <editor-fold defaultstate="collapsed" desc="Log Methods">
    
    /**
     * Log debug text message
     * @param text  The <code>String</code> to be simply logged.
     */
    public static void d(String text){
        print(new Log(Severity.Debug, text));
    }
    
    /**
     * Log debug text message
     * @param componentTracker  The <code>ComponentTracker</code> to be logged.
     */
    public static void d(ComponentTracker componentTracker){
        print(new Log(Severity.Debug, componentTracker));
    }
    
    /**
     * Log info text message
     * @param text  The <code>String</code> to be simply logged.
     */
    public static void i(String text){
        print(new Log(Severity.Info, text));
    }
    
    /**
     * Log info text message
     * @param componentTracker  The <code>ComponentTracker</code> to be logged.
     */
    public static void i(ComponentTracker componentTracker){
        print(new Log(Severity.Info, componentTracker));
    }
    
    /**
     * Log warning text message
     * @param text  The <code>String</code> to be simply logged.
     */
    public static void w(String text){
        print(new Log(Severity.Warn, text));
    }
    
    /**
     * Log warning text message
     * @param componentTracker  The <code>ComponentTracker</code> to be logged.
     */
    public static void w(ComponentTracker componentTracker){
        print(new Log(Severity.Warn, componentTracker));
    }
    
    /**
     * Log error text message
     * @param text  The <code>String</code> to be simply logged.
     */
    public static void e(String text){
        print(new Log(Severity.Error, text));
    }
    
    /**
     * Log error text message 
     * @param componentTracker  The <code>ComponentTracker</code> to be logged.
     */
    public static void e(ComponentTracker componentTracker){
        print(new Log(Severity.Error, componentTracker));
    }
    
    /**
     * Log exception stack trace
     * @param exception  The <code>Exception</code> to be logged.
     */
    public static void x(Exception exception){
        x(exception, null);
    }
    
    /**
     * Log exception stack trace
     * @param exception  The <code>Exception</code> to be logged.
     * @param message  The plus <code>String</code> to be logged.
     */
    public static void x(Exception exception, String message){
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();

        print(new Log(Severity.Exception, new ComponentTracker(message, null, stackTrace)));
    }
    
    /**
     * Log plain text message
     * @param text  The <code>String</code> to be plain logged.
     */
    public static void p(String text){
        print(Severity.Info, text);
    }
    
    /**
     * Log line of tokens  
     * @param repeat  The <code>int</code> times to repeat
     */
    public static void line(int repeat){
        line(repeat, "-");
    }
    
    /**
     * Log line of tokens  
     * @param repeat  The <code>int</code> times to repeat
     * @param token  The <code>String</code> token.
     */
    public static void line(int repeat, String token){
        i(LogUtils.repeatString(repeat, token));
    }
    
    /**
     * Log a text in brace lines
     * @param text  The <code>String</code> to be logged.
     */
    public static void brace(String text){
        brace(text, "-");
    }
    
    /**
     * Log a text in brace lines 
     * (Specially used for highlight a process beginning)
     * @param text  The <code>String</code> to be logged.
     * @param token  The <code>String</code> token.
     */
    public static void brace(String text, String token){
        int maxLength = 60;
        
        int times = text.length();
        if(times > maxLength){
            times = maxLength;
            text = text.substring(0, maxLength);
        }
        
        i("");
        line(times, token);
        i(text);
        line(times, token);
    }
    
    /**
     * Log formatted text message.
     * (Specially for overrides on toString() method)
     * @param text  The <code>String</code> to be formatted and logged.
     */
    public static void f(String text){
        if(Global.DEBUG){
            List<String> separators = new ArrayList<>();
            separators.add(",");

            List<String> starters = new ArrayList<>();
            starters.add("{");
            starters.add("[");

            List<String> enders = new ArrayList<>();
            enders.add("}");
            enders.add("]");

            text = LogUtils.formatText(text, separators, starters, enders);

            i(text);
        }
    }
    
    private static void print(Log log){
        if(Global.DEBUG){
            String text = track(log) + ";";
            
            print(log.getSeverity(), text);
        }
    }
    
    private static void print(Severity severity, String text){
        if(Global.DEBUG_IN_IDE){
            
            switch(severity){
                case Debug:
                    System.out.println(ConsoleColors.GRAY + text);
                    break;
                case Info:
                    System.out.println(text);
                    break;
                case Warn:
                    System.out.println(ConsoleColors.YELLOW + text);
                    break;
                case Error:
                    System.out.println(ConsoleColors.PURPLE + text);
                    break;
                case Exception:
                    System.out.println(ConsoleColors.RED + text);
                    break;
            }
        }

        if(Global.DEBUG_IN_GUI){
            // TO-DO
        }

        if(Global.DEBUG_IN_FILE){
            // TO-DO
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Log Utils">
    public static void trackResponse(Response response){        
        if(Global.DEBUG){
            StringBuilder logText = new StringBuilder();
            
            try{
                logText.append(track(response.getLog()));
                logText.append("\n{");                
                logText.append("\n\tValue: ").append(response.getValue());
                
                if(Global.PARAMETERS_IN_LOG){
                    Map<String, String> parameters = response.getParameters();
                    if(parameters != null){
                        int paramCount = parameters.size();
                        if(paramCount > 0){
                            logText.append("\n\tParameters: ").append(paramCount);
                            for(Map.Entry<String,String> parameter : parameters.entrySet()){                        
                                logText.append("\n\t\t").append(parameter.getKey()).append("=").append(parameter.getValue());
                            }
                        }
                    }
                }

                if(response.getMessage() != null){
                    logText.append("\n\tMessage: " + response.getMessage());
                }
                logText.append("\n}");
                
                p(logText.toString());
            }catch(Exception e){
                x(e, "Logger.trackResponse() ");
            }
        }
        
        
    }
    
    public static String track(Log log){
        String result = null;
        
        if(Global.DEBUG){
            ComponentTracker componentTracker = log.getComponentTracker();
            StringBuilder logText = new StringBuilder();
            
            if(Global.LOG_TIMESTAMP_VERBOSE){
                logText.append(LogUtils.formatDateTime(log.getDate(), log.getTime(), Global.TIMESTAMP_FORMAT_VERBOSE));
            }else{
                 logText.append(LogUtils.formatDateTime(log.getDate(), log.getTime(), Global.TIMESTAMP_FORMAT_SIMPLY));
            }
            
            logText.append(" ").append(log.getSeverity().getInitial());
            logText.append(" [").append(componentTracker.getComponentId()).append("]");
            logText.append(" ").append(componentTracker.getClassName());
            
            switch(Global.LOGGING_TYPE){
                case Complete:
                    if(!componentTracker.getMethodName().isEmpty()){
                        logText.append(".").append(componentTracker.getMethodName());
                    }
                    if(!componentTracker.getLineNumber().isEmpty()){
                        logText.append(":").append(componentTracker.getLineNumber());
                    }
                    if(!componentTracker.getSnippetDescription().isEmpty()){
                        logText.append(" - ").append(componentTracker.getSnippetDescription());
                    }
                    
                    break;
                case Concrete:
                    if(!componentTracker.getLineNumber().isEmpty()){
                        logText.append(":").append(componentTracker.getLineNumber());
                    }                    
                    if(!componentTracker.getSnippetDescription().isEmpty()){
                        logText.append(" - ").append(componentTracker.getSnippetDescription());
                    }
                    
                    break;
                default:
                    break;
            }
            componentTracker = null;
            
            result = logText.toString();
        }
        
        return result;
    }
    // </editor-fold>
}