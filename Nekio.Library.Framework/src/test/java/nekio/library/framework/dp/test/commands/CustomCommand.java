package nekio.library.framework.dp.test.commands;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/02
 *
 * Custom Command Sample
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.framework.P;
import nekio.library.framework.dp.command.Command;
// </editor-fold>

public class CustomCommand extends Command{
    public CustomCommand(){
        super.id = "CustomCommand";
    }
    
    @Override
    public void execute() {
        super.execute();
        P.Log.i("CustomCommand Testing");
    }
}