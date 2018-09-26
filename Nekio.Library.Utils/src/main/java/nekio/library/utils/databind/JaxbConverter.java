package nekio.library.utils.databind;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/26
 *
 * Class with auxiliary methods for Java Architecture for XML Binding (JAXB)
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import nekio.library.log.Logger;
import nekio.library.utils.P;
import nekio.library.utils.helpers.FileHelper;
// </editor-fold>

public class JaxbConverter<T> implements DatabBindConverter<T>{
    // <editor-fold defaultstate="collapsed" desc="Constants">
    private final String FILE_EXTENSION = ".xml";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String filename;
    private Class<? extends Grants.Public> grant;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public JaxbConverter(){
        grant = Grants.Unsupported.class;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementations">
    @Override
    public void setGrant(Class<? extends Grants.Public> grant){
        P.Log.w(
            "Attribute Grants is not supported for XML. Marshalled Object will be literally formed from the POJO annotations"
        );
    }
    
    @Override
    public void createFile(T t, String outputPath, String outputFilename) throws Exception{
        P.Log.i("Marshalling POJO " + t.getClass().getCanonicalName()); 

        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = context.createMarshaller();

        // output the XML in pretty format
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // display the output in the console
        StringWriter sw = new StringWriter();
        marshaller.marshal(t, new PrintWriter(sw));
        Logger.f(t.toString());

        // put the XML to the file
        filename = FileHelper.getFilename(outputPath, outputFilename, FILE_EXTENSION);
        marshaller.marshal(t, new File(filename));

        P.Log.i("XML Successfully created in\n" + filename);
    }
    
    @Override
    public String getDatabindedString(T t) throws Exception{
        String result = null;
        
        P.Log.i("Marshalling POJO " + t.getClass().getCanonicalName()); 

        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = context.createMarshaller();

        // output the XML in pretty format
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // display the output in the console
        StringWriter sw = new StringWriter();
        marshaller.marshal(t, new PrintWriter(sw));
        Logger.f(t.toString());

        P.Log.i("Successfully marshalled to XML");

        result = sw.toString();
        P.Log.plain(result);      
        
        return result;
    }
    
    @Override
    public T getPojo(Class clazz, Object dataBindSource) throws Exception{
        T t = null;        
        P.Log.i("Unmarshalling XML"); 

        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        t = (T) jaxbUnmarshaller.unmarshal(new File(dataBindSource.toString()));

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
}
