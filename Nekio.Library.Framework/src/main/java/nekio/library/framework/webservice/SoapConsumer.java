package nekio.library.framework.webservice;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/25
 *
 * Generic SOAP Consumer
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.framework.webservice.model.Requestable;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import nekio.library.framework.P;
import nekio.library.log.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
// </editor-fold>

public class SoapConsumer {
    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final String ENVELOPE_START = "soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:";
    private static final String ENVELOPE_END = "soapenv:Envelope";
    private static final String HEADER = "soapenv:Header";
    private static final String WSSE_START = "wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"";
    private static final String WSSE_END = "wsse:Security";
    private static final String WSSE_TOKEN = "wsse:UsernameToken";
    private static final String WSSE_USER = "wsse:Username";
    private static final String WSSE_CLAVE_START = "wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\"";
    private static final String WSSE_CLAVE_END = "wsse:Password";
    private static final String BODY = "soapenv:Body";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Behaviours">
    public static Map<Integer, Map<String, String>> send(Requestable request) throws DOMException, Exception {
        Logger.brace("ConnectionSOAP Request", "-");
        P.Log.i(request.toString());

        Map<Integer, Map<String, String>> result = null;

        URL url = new URL(request.getWsURL());
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) connection;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        String xmlInput = null;
        try {
            xmlInput = generateXML(request);
            P.Log.i("Request: " + xmlInput);
        } catch (Exception e) {
            P.Log.x(e);
        }

        if (xmlInput != null) {
            bout.write(xmlInput.getBytes());
        }

        byte[] b = bout.toByteArray();

        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        OutputStream out = null;
        try {
            out = httpConn.getOutputStream();
            out.write(b);
        } catch (Exception e) {
            P.Log.x(e, "Error Getting output Stream");
        } finally {
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException ioe) {
                    P.Log.x(ioe, "Error closing output Stream");
                }
            }
        }

        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            in = httpConn.getInputStream();
            isr = new InputStreamReader(in);
            br = new BufferedReader(isr);

            result = readSOAP(br, request);
        } catch (Exception e) {
            P.Log.x(e, "Error Getting input Stream");
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException ioe) {
                    P.Log.x(ioe, "Error closing input Buffered Reader");
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                    isr = null;
                } catch (IOException ioe) {
                    P.Log.x(ioe, "Error closing input Stream Reader");
                }
            }

            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException ioe) {
                    P.Log.x(ioe, "Error closing input Stream");
                }
            }
        }

        return result;
    }

    private static String generateXML(Requestable request) throws Exception {
        StringBuilder xml = new StringBuilder();

        String key = request.getWebserviceKey();
        String webmethod = request.getWebmethod();
        String webmethodNS = request.getWebmethodNS();
        String user = request.getUser();

        xml.append("<" + ENVELOPE_START + key + "=\"" + request.getXmlns() + "\">");
        if (user != null) {
            String passcode = request.getUser();

            if (user == null || user.trim().isEmpty() || passcode == null || passcode.isEmpty()) {
                throw new Exception("Error getting XML Header user or passcode");
            }

            xml.append("<" + HEADER + ">");
            xml.append("<" + WSSE_START + ">");
            xml.append("<" + WSSE_TOKEN + ">");
            xml.append("<" + WSSE_USER + ">");
            xml.append(user);
            xml.append("</" + WSSE_USER + ">");
            xml.append("<" + WSSE_CLAVE_START + ">");
            xml.append(passcode);
            xml.append("</" + WSSE_CLAVE_END + ">");
            xml.append("</" + WSSE_TOKEN + ">");
            xml.append("</" + WSSE_END + ">");
            xml.append("</" + HEADER + ">");
        } else {
            xml.append("<" + HEADER + "/>");
        }

        xml.append("<" + BODY + ">");
        if (webmethodNS != null) {
            xml.append("<" + key + ":" + webmethod + " " + webmethodNS + ">");
        } else {
            xml.append("<" + key + ":" + webmethod + ">");
        }

        String parameterKey = null;
        for (Map.Entry<String, String> parameter : request.getRequestParameters().entrySet()) {
            parameterKey = parameter.getKey().trim();
            xml.append("<" + parameterKey + ">" + parameter.getValue() + "</" + parameterKey + ">");
        }

        xml.append("</" + key + ":" + webmethod + ">");
        xml.append("</" + BODY + ">");
        xml.append("</" + ENVELOPE_END + ">");

        return xml.toString();
    }

    public static Map<Integer, Map<String, String>> readSOAP(BufferedReader br, Requestable request) throws DOMException, Exception {
        String outputString = "";
        String responseString = "";
        Map<Integer, Map<String, String>> response = new HashMap<>();
        while ((responseString = br.readLine()) != null) {
            outputString = outputString + responseString;
        }

        Document doc = convertStringToDocument(outputString);
        doc.getDocumentElement().normalize();
        NodeList error = doc.getElementsByTagName("faultcode");
        if (error.getLength() > 0 && error.item(0).getNodeType() == Node.ELEMENT_NODE) {
            throw new Exception(error.item(0).getTextContent() + " " + doc.getElementsByTagName("faultstring").item(0).getTextContent());
        }

        HashMap<String, String> nodeResponse = getNodeResponse(doc, request.getExpectedResponseNodes(), request.isResponseHasManyRootNodes());
        response.put(0, nodeResponse);

        return response;
    }

    private static HashMap<String, String> getNodeResponse(Document doc, List<String> nodeTags, boolean hasManyRootNodes) {
        HashMap<String, String> nodeResponse = new HashMap<>();

        String lastTag = null;
        String nodeValue = null;
        for (String nodeTag : nodeTags) {

            nodeValue = null;
            if (nodeTag.contains("/")) {
                String[] tags = nodeTag.split("/");

                NodeList nodeList = doc.getElementsByTagName(tags[0]);
                Node node = nodeList.item(0);
                if (node != null) {
                    lastTag = tags[tags.length - 1];

                    if (lastTag.endsWith("*")) {
                        nodeValue = getNodeValues(nodeList, lastTag);
                    } else {
                        nodeValue = getNodeValue(node, lastTag);
                    }
                }

                nodeResponse.put(nodeTag, nodeValue);
            } else {
                NodeList nodeList = doc.getElementsByTagName(nodeTag);

                int childNodes = nodeList.getLength();
                if (childNodes == 1 || !hasManyRootNodes) {
                    Node node = nodeList.item(0);
                    if (node != null) {
                        nodeValue = node.getTextContent();
                    } else {
                        P.Log.e("Tag not found: " + nodeTag);
                    }
                } else if(childNodes > 0){                    
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < childNodes; i++) {
                        Node node = nodeList.item(i);

                        sb.append(i).append(":");
                        if (node != null) {
                            sb.append(node.getTextContent());
                        } else {
                            P.Log.e("Tag not found: " + nodeTag);
                        }
                        sb.append(";");
                    }
                    nodeValue = sb.toString().substring(0, sb.length() - 1);
                }else{
                    nodeValue = null;
                }

                nodeResponse.put(nodeTag, nodeValue);
            }
        }

        return nodeResponse;
    }

    private static String getNodeValue(Node node, String tagName) {
        String result = null;

        if (node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node innerNode = nodeList.item(i);
                if (innerNode.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()) {
                    if (innerNode.getNodeName().equals(tagName)) {
                        result = innerNode.getTextContent();
                        break;
                    }
                    result = getNodeValue(innerNode, tagName);
                }
            }
        }

        return result;
    }

    private static String getNodeValues(NodeList nodeList, String tagName) {
        String result = null;

        tagName = tagName.substring(0, tagName.length() - 1);
        try {
            int acumIndex = 0;
            StringBuilder sb = new StringBuilder();

            for (int x = 0; x < nodeList.getLength(); x++) {
                Node node = nodeList.item(x);

                NodeList nodeListInner = node.getChildNodes();
                for (int i = 0; i < nodeListInner.getLength(); i++) {
                    Node innerNode = nodeListInner.item(i);
                    if (innerNode.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()) {
                        NodeList innerNodeList = innerNode.getChildNodes();
                        for (int j = 0; j < innerNodeList.getLength(); j++) {
                            Node deeperNode = innerNodeList.item(j);
                            if (deeperNode.getNodeType() == Node.ELEMENT_NODE && deeperNode.getNodeName().equals(tagName)) {
                                sb.append(acumIndex).append(":");
                                sb.append(deeperNode.getTextContent()).append(";");
                                break;
                            }
                        }
                    }
                    acumIndex++;
                }
            }

            result = sb.toString().substring(0, sb.length() - 1);
        } catch (Exception e) {
            P.Log.x(e);
        }

        return result;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Local Behaviours">
    private static Document convertStringToDocument(String xmlStr) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setExpandEntityReferences(false);
            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

            DocumentBuilder builder = dbf.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            P.Log.x(e, "ParserConfigurationException was thrown'");
        }
        return doc;
    }
    // </editor-fold>
}
