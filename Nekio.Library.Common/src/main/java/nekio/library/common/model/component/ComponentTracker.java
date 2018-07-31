package nekio.library.common.model.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Component Tracker Model
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.U;
// </editor-fold>

public class ComponentTracker {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private String componentId;
    private String className;
    private String methodName;
    private String lineNumber;
    private String snippetDescription;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ComponentTracker(String text){
        this.componentId = null;
        this.className = null;
        this.snippetDescription = text;
    }
    
    public ComponentTracker(String componentId, String className, String lineNumber) {
        this(componentId, className, null, lineNumber, null);
    }
    
    public ComponentTracker(String componentId, String className, String methodName, String lineNumber, String snippetDescription) {
        this.componentId = componentId;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.snippetDescription = snippetDescription;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    
    /**
     * update the component tracking elements
     * @param methodName  The <code>String</code> that represents the Method Name.
     * @param snippetDescription  The <code>String</code> that represents the Snippet Description.
     * @param lineNumber  The <code>String</code> that represents the Line Number.
     * @return self-instance
     */
    public ComponentTracker u(ComponentTracker componentTracker){
        this.componentId = componentTracker.getComponentId();
        this.className = componentTracker.getClassName();
        this.methodName = componentTracker.getMethodName();
        this.lineNumber = componentTracker.getLineNumber();
        this.snippetDescription = componentTracker.getSnippetDescription();
        
        return this;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public String getComponentId() {
        return U.nvl(componentId, ".");
    }

    public String getClassName() {
        return U.nvl(className);
    }

    public String getMethodName() {
        return U.nvl(U.ifNotNull(methodName, "()"));
    }

    public String getLineNumber() {
        return U.nvl(lineNumber);
    }

    public String getSnippetDescription() {
        return U.nvl(snippetDescription);
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "ComponentTracker{" + "componentId=" + componentId + ", className=" + className + ", methodName=" + methodName + ", lineNumber=" + lineNumber + ", snippetDescription=" + snippetDescription + '}';
    }
}
