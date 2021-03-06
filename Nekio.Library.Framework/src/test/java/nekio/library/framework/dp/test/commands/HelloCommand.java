package nekio.library.framework.dp.test.commands;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/02
 *
 * Hello Command Sample
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.framework.P;
import nekio.library.framework.dp.command.Command;
// </editor-fold>

public class HelloCommand extends Command<String, String>{
    public HelloCommand(){
        super.id = "HelloCommand";
    }
    
    @Override
    public String execute(String text) {
        super.execute(text);
        P.Log.i("HelloCommand Testing");        
        
        return "Hello " + text;
    }
}
