package nekio.library.log.configuration;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Class for load configurations
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nekio.library.common.Tools;
import nekio.library.common.contracts.IProperties;
import nekio.library.common.model.component.ComponentConfigurations;
import nekio.library.log.Logger;
import nekio.library.log.utils.LogUtils;
// </editor-fold>

public class ConfigLoader {
    // <editor-fold defaultstate="collapsed" desc="Attributes">     
    private Map<String, IProperties> properties;
    private ComponentConfigurations config;
    private String logFolder;
    private String logFile;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ConfigLoader(ComponentConfigurations config){
        this.config = config;
        
        this.logFolder = config.getLogFolder() + File.separator + config.getComponentProjectName();
        this.logFile = config.getLogFolder() + File.separator + config.getComponentProjectName() + " " + LogUtils.getTimestampForFile() + ".txt";
        
        loadPropertiesFolder();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    private void printGlobals(){
        StringBuilder globals = new StringBuilder("COMPONENT CONFIGURATIONS:\n");
        
        globals.append("\tComponent Project Name: ").append(config.getComponentProjectName()).append("\n");
        globals.append("\tComponent Name: ").append(config.getComponentName()).append("\n");
        globals.append("\tComponent Developer: ").append(config.getComponentDeveloper()).append("\n");
        globals.append("\tComponent Edition: ").append(config.getComponentEdition()).append("\n");
        globals.append("\tComponent Version: ").append(config.getComponentVersion()).append("\n");
        globals.append("\tProperties Folder: ").append(config.getPropertiesFolder()).append("\n");
        globals.append("\tLog Folder: ").append(logFolder).append("\n");
        globals.append("\tLog File: ").append(logFile).append("\n");
        globals.append("\tDebug: ").append(config.isDebug()).append("\n");
        
        Logger.i(globals.toString());
    }
    
    private void checkLog(){
        try {
            Tools.checkPath(logFolder);
            new File(logFile).createNewFile();
        } catch (IOException e) {
            Logger.x(e);
        }
    }
    
    private void loadPropertiesFolder(){
        checkLog();
        
        Logger.i("#######################################################################################");
        Logger.i("       New " + config.getComponentName() + " initializing at " + new Date());
        Logger.i("#######################################################################################\n");
        printGlobals();
        
        properties = new HashMap<String, IProperties>();        
        
        List<File> files = Tools.getAllFiles(new File(config.getPropertiesFolder()), ".properties");
        String filepath = null;
        String filename = null;
        Properties propertiesFile = null;
        
        for(File file : files){
            filepath = file.getAbsolutePath();
            filename = file.getName();
            
            propertiesFile = new Properties();
            propertiesFile.setAbsolutePath(filepath);
            propertiesFile.setFields(loadProperties(filepath, filename));
            
            properties.put(filename.replace(".properties", ""), propertiesFile);
        }
    }
    
    private Map<String, String> loadProperties(String filepath, String filename){
        Map<String, String> result = null;
        
        String content = "";
        String line=null; 
        
        try{
            FileReader leerArchivo = new FileReader(filepath);
            BufferedReader buffer = new BufferedReader(leerArchivo);
            
            result = new HashMap<String, String>();
            while((line = buffer.readLine()) != null){
                if(!line.contains("#") && line.length() > 0){
                    content += "\t" + line + "\n";

                    String key = line.substring(0, line.indexOf("="));
                    String value = line.substring(line.indexOf("=") + 1);

                    result.put(key, value);
                }
            }
            
            buffer.close();   
            buffer = null;
            
            if(result.isEmpty())
                Logger.i(filename + " - Configuration file has no properties");
            else
                Logger.i(filename + ":\n" + content);
        }
        catch(IOException e){
            Logger.x(e);
        }
        
        return result;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public String getLogFolder() {
        return logFolder;
    }

    public String getLogFile() {
        return logFile;
    }
    
    public Map<String, IProperties> getProperties() {
        return properties;
    }
    // </editor-fold>
}
