package nekio.library.utils.test;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/19
 *
 * Class for testing Databind purposes
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nekio.library.log.Logger;
import nekio.library.utils.P;
import nekio.library.utils.databind.DatabBindConverter;
import nekio.library.utils.databind.Grants;
import nekio.library.utils.databind.JacksonConverter;
import nekio.library.utils.databind.JaxbConverter;
import nekio.library.utils.dummy.DummyJsonEntity;
import nekio.library.utils.dummy.DummyXmlEntity;
// </editor-fold>

public class DatabindTest {
    // <editor-fold defaultstate="collapsed" desc="Testing Methods">
    public static void main(String[] args) {
        DummyXmlEntity dummyXmlEntity = getDummyXmlEntity();
        DummyJsonEntity dummyJsonEntity = getDummyJsonEntity();
        
        String outputPath = "C:\\test";
        String outputFilename = "sample";
        
        testJaxb(dummyXmlEntity, outputPath, outputFilename);
        testJackson(dummyJsonEntity, outputPath, outputFilename);
        
        testGeneric(new JaxbConverter(), dummyXmlEntity, outputPath, outputFilename);
        testGeneric(new JacksonConverter(), dummyJsonEntity, outputPath, outputFilename);
    }
    
    private static DummyXmlEntity getDummyXmlEntity(){
        DummyXmlEntity dummyXmlEntity = new DummyXmlEntity();
        
        dummyXmlEntity.setId(8);
        dummyXmlEntity.setName("Sinesio Ivan Carrillo Heredia");
        dummyXmlEntity.setEmail("nekio@outlook.com");
        dummyXmlEntity.setNickname("Nekio");
        dummyXmlEntity.setHiddenValue("File will not have this field");
        
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add(".NET");
        dummyXmlEntity.setSkills(skills);
        
        return dummyXmlEntity;
    }
    
    private static DummyJsonEntity getDummyJsonEntity(){
        DummyJsonEntity dummyJsonEntity = new DummyJsonEntity();
        
        dummyJsonEntity.setId(8);
        dummyJsonEntity.setName("Sinesio Ivan Carrillo Heredia");
        dummyJsonEntity.setEmail("nekio@outlook.com");
        dummyJsonEntity.setNickname("Nekio");
        dummyJsonEntity.setHiddenValue("File will not have this field");
        
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add(".NET");
        dummyJsonEntity.setSkills(skills);
        
        return dummyJsonEntity;
    }
    
    private static void testJaxb(DummyXmlEntity dummyXmlEntity, String outputPath, String outputFilename){
        Logger.brace("Testing JAXB");
        DummyXmlEntity response = null;
        
        DatabBindConverter<DummyXmlEntity> jaxbConverter = new JaxbConverter<>();
        try {
            jaxbConverter.setGrant(Grants.Public.class);
            jaxbConverter.createFile(dummyXmlEntity, outputPath, outputFilename);
            response = jaxbConverter.getPojo(DummyXmlEntity.class, jaxbConverter.getFilename());
            
            String xmlString = jaxbConverter.getDatabindedString(dummyXmlEntity);
            P.Log.i(xmlString);
        } catch (Exception e) {
            P.Log.x(e);
        }
    }
    
    private static void testJackson(DummyJsonEntity dummyJsonEntity, String outputPath, String outputFilename){
        Logger.brace("Testing Jackson");
        DummyJsonEntity response = null;
        
        DatabBindConverter<DummyJsonEntity> jacksonConverter = new JacksonConverter<>();
        try {
            jacksonConverter.setGrant(Grants.Public.class);
            jacksonConverter.createFile(dummyJsonEntity, outputPath, outputFilename);
            response = jacksonConverter.getPojo(DummyJsonEntity.class, new File(jacksonConverter.getFilename()));
            
            String jsonString = jacksonConverter.getDatabindedString(dummyJsonEntity);
            P.Log.i(jsonString);
            
            // Specific JacksonConverter methods
            Map<String, Object> map = ((JacksonConverter)jacksonConverter).jsonStringToMap(jsonString);
            P.Log.i("jsonStringToMap: " + map.toString());
            
            jsonString = "[{\"name\":\"sinesio\"}, {\"name\":\"ivan\"}]";
            List<DummyJsonEntity> list = ((JacksonConverter)jacksonConverter).jsonStringToList(jsonString);
            P.Log.i("jsonStringToList: " + list.toString());            
        } catch (Exception e) {
            P.Log.x(e);
        }
    }
    
    private static <T> void testGeneric(DatabBindConverter<T> genericConverter, T t, String outputPath, String outputFilename){
        Logger.brace("Testing Generic DatabindConverter");
        T response = null;
        
        try {
            genericConverter.setGrant(Grants.Public.class);
            genericConverter.createFile(t, outputPath, outputFilename);
            response = genericConverter.getPojo(t.getClass(), new File(genericConverter.getFilename()));
            
            String databindedString = genericConverter.getDatabindedString(t);
            P.Log.i(databindedString);
        } catch (Exception e) {
            P.Log.x(e);
        }
    }
    // </editor-fold>
}
