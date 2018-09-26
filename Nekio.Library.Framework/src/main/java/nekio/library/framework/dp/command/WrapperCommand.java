package nekio.library.framework.dp.command;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Wrapper Command
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class WrapperCommand {
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public void execute(ICommand command){
        command.execute();
    }
    // </editor-fold>
}
