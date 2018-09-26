package nekio.library.framework.dp.command;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Command Queue
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import nekio.library.framework.P;
// </editor-fold>

public class CommandQueue {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Map<ICommand, Object> commandsQueue;
    private Map<ICommand, Object> results;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public CommandQueue(){
        this.commandsQueue = new HashMap<>();
        this.results = new HashMap<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public void add(ICommand command){
        this.add(command, null);
    }
    
    public void add(ICommand command, Object parameter){
        commandsQueue.put(command, parameter);
    }
        
    public void removeCommand(ICommand command){
        commandsQueue.remove(command);
    }
    
    public void removeResult(ICommand command){
        results.remove(command);
    }
    
    public void clearAll(){
        commandsQueue.clear();
        results.clear();
    }
    
    public void clearCommandsQueue(){
        commandsQueue.clear();
    }
    
    public void clearResults(){
        results.clear();
    }

    public void workOff() {
        try{
            for(Map.Entry<ICommand, Object> map : commandsQueue.entrySet()) {
                P.Log.plain("|");
                ICommand command = map.getKey();
                Object parameter = map.getValue();

                dispatch(command, parameter);
            }
        }catch(Exception e){
            P.Log.x(e);
        }finally{
            clearCommandsQueue();
        }
    }
    
    private void dispatch(ICommand command, Object parameter){
        String idCommand = command.getId();
        
        P.Log.w(idCommand + " Starts...");
        
        Object result = null;
        if(parameter == null){
            command.execute();
        }else{
            result = command.execute(parameter);
        }
        
        results.put(command, result);
    }

    public Set<Map.Entry<ICommand, Object>> getResults() {
        return results.entrySet();
    }
    // </editor-fold>
}
