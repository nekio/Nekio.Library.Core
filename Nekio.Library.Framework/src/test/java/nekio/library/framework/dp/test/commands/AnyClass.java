package nekio.library.framework.dp.test.commands;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/02
 *
 * Any Class Sample
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

// A Class that doesn't implement ICommand interface
public class AnyClass {
    public Integer add(Integer number1, Integer number2){
        return number1 + number2;
    }

    @Override
    public String toString() {
        return "AnyClass";
    }
}
