package nekio.library.framework.dp.test;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Command Test
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Map;
import nekio.library.framework.P;
import nekio.library.framework.dp.command.CommandQueue;
import nekio.library.framework.dp.command.ICommand;
import nekio.library.framework.dp.command.ReflectCommand;
import nekio.library.framework.dp.command.WrapperCommand;
import nekio.library.framework.dp.test.commands.AnyClass;
import nekio.library.framework.dp.test.commands.CustomCommand;
import nekio.library.framework.dp.test.commands.DummyWrapperCommand;
import nekio.library.framework.dp.test.commands.HelloCommand;
import nekio.library.framework.dp.test.commands.TimeCommand;
import nekio.library.utils.dummy.DummyClass;
// </editor-fold>

public class CommandTest {
    public static void main(String[] args) {
        singleCommand();
        wrapperCommand();
        commandQueue();
        reflectedCommand();
    }
    
    private static void singleCommand(){
        P.Log.plain("");
        P.Log.i("EXECUTE A SINGLE COMMAND");
        ICommand command = new CustomCommand();
        command.execute();
    }
    
    private static void wrapperCommand(){
        P.Log.plain("");
        P.Log.i("EXECUTE A WRAPPER COMMAND");
        WrapperCommand wrapper = new WrapperCommand();
        
        DummyClass dummy = new DummyClass();
        dummy.setName("Nekio");
        ICommand command = new DummyWrapperCommand(dummy);
        
        //executing command by itself
        //command.execute(); 
        //command.execute("concatenate text"); 
        
        // executing command by the wrapper
        wrapper.execute(command); 
    }
    
    private static void commandQueue(){
        P.Log.plain("");
        P.Log.i("EXECUTE A COMMAND QUEUE");
        CommandQueue commandQueue = new CommandQueue();
        commandQueue.add(new CustomCommand());
        commandQueue.add(new TimeCommand());
        commandQueue.add(new HelloCommand(), "World");
        
        commandQueue.workOff();
        
        P.Log.plain("|");
        P.Log.i("Results:");
        for(Map.Entry<ICommand, Object> map : commandQueue.getResults()){
            ICommand cmd = map.getKey();
            Object result = map.getValue();
            
            P.Log.i(cmd.toString() + " returns " + result);
        }
    }
    
    private static void reflectedCommand(){
        P.Log.plain("");
        P.Log.i("EXECUTE COMMANDS BY REFLECTION");
        
        // IMPORTANT NOTE: For reflected Commands, every Class Command MUST to be "public class"
        ReflectCommand[] reflectCommands = {
            new ReflectCommand(new AnyClass(), "add", new Integer[] {8, 3}),
            new ReflectCommand(new HelloCommand(), "execute", new String[] {"World"}),
            new ReflectCommand(new TimeCommand(), "execute", null)
        };
        
        P.Log.plain("|");
        P.Log.i("Reflection results:  ");
        for (ReflectCommand command : reflectCommands) {
            command.execute();
        }
    }
}