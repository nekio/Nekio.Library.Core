package nekio.library.utils.databind;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/26
 *
 * Class with auxiliary methods for Jackson Binding
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import nekio.library.log.Logger;
import nekio.library.utils.P;
import nekio.library.utils.helpers.FileHelper;
// </editor-fold>

public class JacksonConverter<T> implements DatabBindConverter<T>{
    // <editor-fold defaultstate="collapsed" desc="Constants">
    private final String FILE_EXTENSION = ".json";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String filename;
    private Class<? extends Grants.Public> grant;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public JacksonConverter(){
        grant = Grants.Public.class;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public void setGrant(Class<? extends Grants.Public> grant){
        this.grant = grant;
    }
    
    @Override
    public void createFile(T t, String outputPath, String outputFilename) throws Exception{
        P.Log.i("Marshalling POJO " + t.getClass().getCanonicalName());        
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        filename = FileHelper.getFilename(outputPath, outputFilename, FILE_EXTENSION);
        mapper.writerWithView(grant).writeValue(new File(filename), t);
        Logger.f(t.toString());
        
        P.Log.i("JSON Successfully created in\n" + filename + ". (Grant: " + grant.getSimpleName() + ")");
    }
    
    @Override
    public String getDatabindedString(T t) throws Exception{
        String result = null;
        
        P.Log.i("Marshalling POJO " + t.getClass().getCanonicalName() + ". Grant: " + grant.getSimpleName()); 
        Logger.f(t.toString());
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        result = mapper.writerWithView(grant).writeValueAsString(t);

        P.Log.i("Successfully marshalled to JSON");
        P.Log.plain(result);  
        
        return result;
    }

    @Override
    public T getPojo(Class clazz, Object dataBindSource) throws Exception{
        T t = null;
        
        P.Log.i("Unmarshalling JSON. Grant: " + grant.getSimpleName()); 
        ObjectMapper mapper = new ObjectMapper();

        if(dataBindSource instanceof String){
            String jsonString = (String)dataBindSource;
            t = (T)mapper.readerWithView(grant).forType(clazz).readValue(jsonString);
        }else if(dataBindSource instanceof URL){
            URL jsonUrl = (URL)dataBindSource;
            t = (T)mapper.readerWithView(grant).forType(clazz).readValue(jsonUrl);
        }else if(dataBindSource instanceof File){
            File jsonFile = (File)dataBindSource;
            t = (T)mapper.readerWithView(grant).forType(clazz).readValue(jsonFile);
        }else{
            P.Log.e("Data Bind Source must be an instance of String, URL or File");
        }

        if(t != null){
            P.Log.i("POJO Successfully unmarshalled");
            Logger.f(t.toString());
        }else{
            P.Log.e("POJO was not unmarshalled");
        }

        return t;
    }
    
    @Override
    public String getFilename() {
        return filename;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public List<T> jsonStringToList(String jsonString) throws IOException{
        List<T> result = null;
        
        ObjectMapper mapper = new ObjectMapper();
        result = mapper.readValue(jsonString, new TypeReference<List<T>>(){});
        
        return result;
    }
    
    public Map<String, Object> jsonStringToMap(String jsonString) throws IOException{
        Map<String, Object> result = null;
        
        ObjectMapper mapper = new ObjectMapper();
        result = mapper.readValue(jsonString, new TypeReference<Map<String,Object>>(){});
        
        return result;
    }
    // </editor-fold>
}
