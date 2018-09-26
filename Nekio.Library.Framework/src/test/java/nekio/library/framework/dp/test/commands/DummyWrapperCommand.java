package nekio.library.framework.dp.test.commands;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/02
 *
 * Dummy Wrapper Command Sample
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.framework.P;
import nekio.library.framework.dp.command.Command;
import nekio.library.utils.dummy.DummyClass;
// </editor-fold>

public class DummyWrapperCommand extends Command<Void,String>{
    private DummyClass dummy;
    
    public DummyWrapperCommand(DummyClass dummy){
        super.id = "DummyWrapperCommand";
        this.dummy = dummy;
    }
    
    @Override
    public Void execute(String concatenate) {
        P.Log.i("DummyWrapperCommand Executing");
        
        dummy.setName("Dummy Class in a Wrapper Command: " + dummy.getName() + " " + concatenate); // concatenate
        dummy.setId(8); // replacing 
        
        P.Log.i(dummy.toString());
        
        return null;
    }
}