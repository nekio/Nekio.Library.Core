package nekio.library.common.model.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Component Configurations Model
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.File;
// </editor-fold>

public class ComponentConfigurations {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String componentId;
    private String componentProjectName;
    private String componentName;
    private String componentDeveloper;
    private String componentEdition;
    private String componentVersion;
    private String projectFolder;
    private String configurationClassFolder;
    private String propertiesFolder;
    private String logFolder;
    private boolean debug;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ComponentConfigurations(String componentId, String componentProjectName, String componentName) {
        this(
            componentId,
            componentProjectName, componentName,
            "Nekio", "Beta",
            "1.0", null,
            null, null,
            null, true
        );
    }
    
    public ComponentConfigurations(
            String componentId,
            String componentProjectName, String componentName, 
            String componentDeveloper, String componentEdition, 
            String componentVersion, String projectFolder, 
            String configurationClassFolder, String propertiesFolder,
            String logFolder, boolean debug
    ) {
        this.componentId = componentId;
        this.componentProjectName = componentProjectName;
        this.componentName = componentName;
        this.componentDeveloper = componentDeveloper;
        this.componentEdition = componentEdition;
        this.componentVersion = componentVersion;
        this.projectFolder = projectFolder;
        this.configurationClassFolder = configurationClassFolder;
        this.propertiesFolder = propertiesFolder;
        this.logFolder = logFolder;
        this.debug = debug;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
    
    public String getComponentProjectName() {
        return componentProjectName;
    }

    public void setComponentProjectName(String componentProjectName) {
        this.componentProjectName = componentProjectName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentDeveloper() {
        return componentDeveloper;
    }

    public void setComponentDeveloper(String componentDeveloper) {
        this.componentDeveloper = componentDeveloper;
    }

    public String getComponentEdition() {
        return componentEdition;
    }

    public void setComponentEdition(String componentEdition) {
        this.componentEdition = componentEdition;
    }

    public String getComponentVersion() {
        return componentVersion;
    }

    public void setComponentVersion(String componentVersion) {
        this.componentVersion = componentVersion;
    }

    public String getProjectFolder() {
        return projectFolder;
    }

    public void setProjectFolder(String projectFolder) {
        this.projectFolder = projectFolder;
        
        check();
    }

    public String getConfigurationClassFolder() {
        return configurationClassFolder;
    }

    public void setConfigurationClassFolder(String configurationClassFolder) {
        this.configurationClassFolder = configurationClassFolder;
        
        check();
    }

    public String getPropertiesFolder() {
        return propertiesFolder;
    }

    public void setPropertiesFolder(String propertiesFolder) {
        this.propertiesFolder = propertiesFolder;
    }

    public String getLogFolder() {
        return logFolder;
    }

    public void setLogFolder(String logFolder) {
        this.logFolder = logFolder;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Local Behaviours">   
    private void check(){
        if(projectFolder != null && configurationClassFolder != null)
            propertiesFolder = projectFolder + File.separator + "src" + File.separator + configurationClassFolder;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "ComponentConfigurations{" + "componentProjectName=" + componentProjectName + ", componentName=" + componentName + ", componentDeveloper=" + componentDeveloper + ", componentEdition=" + componentEdition + ", componentVersion=" + componentVersion + ", projectFolder=" + projectFolder + ", configurationClassFolder=" + configurationClassFolder + ", propertiesFolder=" + propertiesFolder + ", logFolder=" + logFolder + ", debug=" + debug + '}';
    }
}
