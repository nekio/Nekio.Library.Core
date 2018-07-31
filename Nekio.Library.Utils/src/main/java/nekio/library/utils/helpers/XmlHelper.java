package nekio.library.utils.helpers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Helper for XMLs
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
// </editor-fold>

public class XmlHelper {
    // <editor-fold defaultstate="collapsed" desc="Static Methods">
    public static Map xmlContent(String xml){
        Map<String, Object> xmlMap = new HashMap<>();
        String key = null;
        Object value = null;
        
        Map<String, Object> nestedContent = null;
        boolean nestedProperty = false;
        int nestedCounter = 0;
        String nestedKey = null;
        
        int stack = 0;        
        String[] rows = xml.trim().replaceAll(">", ">\n").replaceAll("<", "\n<").split("\n");
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == null || rows[i].trim().length() == 0) {
                continue;
            }

            String row = rows[i].trim();
            //System.out.println(row);
            
            if (row.startsWith("<?") || row.contains("xmlns:xsi")) { // xml version tag
                continue;
            } else if (row.startsWith("</")) { // closing tag
                if(nestedProperty){
                    if(!nestedContent.containsKey(nestedKey.toUpperCase()))
                        nestedContent.put(nestedKey.toUpperCase(), value);
                }else{
                    if(stack > 0) // Stack == 0 is the main xml tag
                        xmlMap.put(key.toUpperCase(), value);
                }
                
                value = null;
                stack--;
                
                if(nestedProperty && stack == 0){ //end of a nesting
                    nestedProperty = false;                    
                    xmlMap.put(key.toUpperCase() + format(nestedCounter), nestedContent);
                }
            } else if (row.startsWith("<")) { // starting tag
                if(row.endsWith("/>")) // one-line empty tag
                    continue;
                
                if(stack == 0)
                    key = getField(row);
                else{ 
                    if(!nestedProperty){
                        nestedCounter++;
                        nestedContent = new HashMap<>();
                    }
                    
                    nestedProperty = true;
                    nestedKey = getField(row);
                }
                
                stack++;
            } else { // tag data
                    value = row;
            }
        }
        
        return xmlMap;
    }
    
    public static String formatXML(String xml) {        
        if (xml == null || xml.trim().length() == 0) {
            return "";
        }

        int stack = 0;
        StringBuilder pretty = new StringBuilder();
        String[] rows = xml.trim().replaceAll(">", ">\n").replaceAll("<", "\n<").split("\n");

        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == null || rows[i].trim().length() == 0) {
                continue;
            }

            String row = rows[i].trim();
            
            if (row.startsWith("<?")) { // xml version tag
                pretty.append(row).append("\n");
            } else if (row.startsWith("</")) { // closing tag
                String indent = TextHelper.ident(--stack);
                
                if(stack == 0)
                    pretty.append(indent).append(row).append("\n\n");
                else
                    pretty.append(indent).append(row).append("\n");
            } else if (row.startsWith("<")) { // starting tag
                String indent = TextHelper.ident(stack++);
                pretty.append(indent).append(row).append("\n");
            } else { // tag data
                String indent = TextHelper.ident(stack);
                pretty.append(indent).append(row).append("\n");
            }
        }

        return pretty.toString().trim();
    }
    
    private static String getField(String text){
        String field = text.replaceAll("<", "");
        field = field.replaceAll("/", "");
        field = field.replaceAll(">", "");
        
        return field;
    }
    
    private static String format(int number){
        return number < 10 ? "0" + number : "" + number; 
    }
    
    public String beautify(String xml) {
        String result = null;
        
        try {
            InputSource src = new InputSource(new StringReader(xml));
            Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            Boolean keepDeclaration = xml.startsWith("<?xml");

            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            LSSerializer writer = impl.createLSSerializer();

            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
            writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);

            result = writer.writeToString(document);
        } catch (Exception e) {

        }
        
        return result;
    }
    // </editor-fold>
}
