package nekio.library.framework.dp.command;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Reflect Command
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.lang.reflect.Method;
import nekio.library.framework.P;
import nekio.library.framework.P.Log;
// </editor-fold>

public class ReflectCommand {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Object reflectionObject;
    private Method method;
    private Object[] arguments;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ReflectCommand(Object reflectionObject, String action, Object[] arguments) {
        this.reflectionObject = reflectionObject;
        this.arguments = arguments;
        
        Class clazz = reflectionObject.getClass();
        Class[] argumentsTypes = null;
        if(arguments != null){
            argumentsTypes = new Class[this.arguments.length];
            for (int i = 0; i < this.arguments.length; i++) {
                argumentsTypes[i] = this.arguments[i].getClass();
            }
        }
        
        try {
            this.method = clazz.getMethod(action, argumentsTypes);
        } catch(NoSuchMethodException e) {
            P.Log.x(e);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public Object execute() {
        Object result = null;
        
        P.Log.plain("|");
        try {
            result = method.invoke(reflectionObject, arguments);
        }
        catch(Exception e) {
            P.Log.x(e);
        }
        
        StringBuilder argumentsList = new StringBuilder();
        if(arguments != null){
            for(int i=0 ; i < arguments.length; i++){
                if(i != 0){
                    argumentsList.append(",");
                }
                
                argumentsList.append(arguments[i].toString());
            }
        }
        
        P.Log.i(
            reflectionObject + "." +
            method.getName() +
            "(" + argumentsList.toString() + ")"
        );
        
        return result;
    }
    // </editor-fold>
}
