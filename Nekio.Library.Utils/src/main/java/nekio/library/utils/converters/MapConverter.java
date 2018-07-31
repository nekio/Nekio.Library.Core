package nekio.library.utils.converters;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/09
 *
 * Class auxiliary for DTO-Map conversion
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import nekio.library.common.contracts.layers.DTO;
import nekio.library.log.Logger;
// </editor-fold>

public class MapConverter {
    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static DTO process(Map containerMap, Class<?> classLoader){
        return process(containerMap, classLoader, "\t");
    }
    
    public static DTO process(Map containerMap, Class<?> classLoader, String logPrefix){
        DTO dto = null;
        try{
            dto = (DTO)classLoader.newInstance();
            Class clazz = dto.getClass();
            
            String methodName = null;
            String MethodOfAttributeParameterType = null;
            String classAttribute = null;
            String classAttributeFirstChar = null;
            String classAttributeUpper = null;
            Object mapKey = null;
    
            Logger.i(logPrefix + "Single Fields:");
            for(Method method : clazz.getDeclaredMethods()){
                methodName = method.getName();

                if(methodName.startsWith("set")){
                    classAttribute = methodName.replace("set", "");
                    classAttributeFirstChar = "" + classAttribute.charAt(0);
                    classAttribute = classAttributeFirstChar.toLowerCase() + classAttribute.substring(1);
                    classAttributeUpper = classAttribute.toUpperCase();
                    MethodOfAttributeParameterType = method.getParameterTypes()[0].toString();                    

                    Logger.i(logPrefix + "[" + classAttributeUpper + "] : " + methodName + "(" + MethodOfAttributeParameterType + ")");
                    
                    try{
                        mapKey = containerMap.get(classAttributeUpper);
                        
                        if(MethodOfAttributeParameterType.endsWith("int"))                    
                            method.invoke(dto, toInt(mapKey));
                        else if(MethodOfAttributeParameterType.endsWith("double"))                    
                            method.invoke(dto, toDouble(mapKey));
                        else if(MethodOfAttributeParameterType.endsWith("Date"))                    
                            method.invoke(dto, toDate(mapKey));
                        else if(MethodOfAttributeParameterType.endsWith("String"))                    
                            method.invoke(dto, toStr(mapKey));
                        else if(MethodOfAttributeParameterType.endsWith("List"))
                             method.invoke(dto, toListDTO(clazz, classAttribute, containerMap, logPrefix));
                    }catch(Exception e){
                        Logger.x(e);
                    }
                }
            }
        }catch(Exception e){
            Logger.x(e);
        }
        
        return dto;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Converters">
    private static String toStr(Object value){
        String cast = null;
        
        try {
            cast = value.toString();
        } catch (Exception e) {}
        
        return cast;
    }
    
    private static int toInt(Object value){
        int cast = 0;
        
        try {
            cast =Integer.parseInt(value.toString());
        } catch (Exception e) {}
        
        return cast;
    }
    
    private static double toDouble(Object value){
        double cast = 0;
        
        try {
            cast =Double.parseDouble(value.toString());
        } catch (Exception e) {}
        
        return cast;
    }
    
    private static Date toDate(Object value){
        Date cast = null;
        
        try {
            cast = new Date(value.toString());
        } catch (Exception e) {}
        
        return cast;
    }
    
    private static List<DTO> toListDTO(Class clazz, String attribute, Map<String, Object>  map, String logPrefix){
        List<DTO> list = new ArrayList<DTO>();
        
        try {
            for(Map.Entry<String, Object> entry : map.entrySet()){
                String entryKey = entry.getKey();
                
                if(entryKey.contains(attribute.toUpperCase())){
                    Map<String, Object> entryValue = (Map<String, Object>)entry.getValue();
                    
                    Field field = clazz.getDeclaredField(attribute);
                    Type type = field.getGenericType();
                    Class dtoClass = Class.forName(getGeneric(type));
                    
                    Logger.i("\n" + logPrefix + "\t######### Generating " + dtoClass.getSimpleName() + " by reflection #########");
                    
                    DTO dto = process(entryValue, dtoClass, logPrefix + "\t");
                    Logger.i("");
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            Logger.x(e);
        }
        
        return list;
    }
    
    private static String getGeneric(Type type){
        String typeString = type.toString();
        
        String generic = typeString.substring(typeString.indexOf('<') + 1, typeString.length());
        generic = generic.replace(">", "");
        
        return generic;
    }
    // </editor-fold>
}
