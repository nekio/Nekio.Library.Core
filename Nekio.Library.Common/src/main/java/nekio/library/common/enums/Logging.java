package nekio.library.common.enums;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Class for Logging constants
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.awt.Color;
// </editor-fold>

public class Logging {
    // <editor-fold defaultstate="collapsed" desc="Enumerations">
    public enum Type{
        Complete,
        Concrete
    }
    
    public enum Style{
        InLine,
        MultiLine
    }
    
    public enum Background{
        Dark,
        Light
    }
    
    public enum Severity{
        None(-1, '.', null),
        Debug(0, 'D', Color.LIGHT_GRAY),
        Info(1, 'I', Color.WHITE),
        Warn(2, 'W', Color.YELLOW),
        Error(3, 'E', Color.RED),
        Exception(4, 'X', Color.RED);   
        
        private final int id;
        private final char initial;
        private final Color color;
        
        private Severity(int id, char initial, Color color){
            this.id = id;
            this.initial = initial;
            this.color = color;
        }

        public int getId() {
            return id;
        }

        public char getInitial() {
            return initial;
        }

        public Color getColor() {
            return color;
        }
    }
    // </editor-fold>
}
