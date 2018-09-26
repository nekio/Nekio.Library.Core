package nekio.library.framework.dp.test.commands;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/02
 *
 * Time Command Sample
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Date;
import nekio.library.framework.P;
import nekio.library.framework.dp.command.Command;
// </editor-fold>

public class TimeCommand extends Command{
    public TimeCommand(){
        super.id = "TimeCommand";
    }
    
    @Override
    public void execute() {
        super.execute();
        P.Log.i("TimeCommand Testing: " + new Date());
    }
}
