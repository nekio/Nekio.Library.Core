package nekio.library.framework.dp.composite;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * Composite Util
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public class CompositeUtil {
    // <editor-fold defaultstate="collapsed" desc="Static Attributes">
    private final static String WHITESPACES = "  ";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static String formatComposition(IEntity entity){
        return "{" + formatComposition(entity, 1) + "\n}";
    }
    
    private static <T>String formatComposition(IEntity<T> entity, int level){
        StringBuilder text = new StringBuilder();

        if(entity instanceof CompositeEntity){
            text.append("\n").append(repeat(level)).append("\"").append(entity.getDescription()).append("\":{");
            text.append("\n").append(repeat(level + 1)).append("\"id\":\"").append(entity.getId()).append("\",");
            text.append("\n").append(repeat(level + 1)).append("\"composite\":{");
        
            CompositeEntity<T> composite = (CompositeEntity<T>)entity;
            for(IEntity innerEntity : composite.getEntities()){
                text.append(formatComposition(innerEntity, level + 2));
            }
            
            text.append("\n").append(repeat(level + 1)).append("}");
            text.append("\n").append(repeat(level)).append("}");
        }else if(entity instanceof SingleEntity){
            text.append("\n").append(repeat(level + 1)).append("\"single\":\"[").append(entity.getId()).append("=").append(entity.getDescription()).append("]\",");
        }
        
        return text.toString();
    }
    
    private static String repeat(int level){
        return String.format("%0" + level + "d", 0).replace("0", CompositeUtil.WHITESPACES);
    }
    // </editor-fold>
}
