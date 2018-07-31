package nekio.library.framework.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Component DTO Model
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.time.LocalDate;
import java.time.LocalDateTime;
import nekio.library.common.contracts.layers.DTO;
import nekio.library.common.enums.ComponentType;
import nekio.library.utils.helpers.DataHelper;
// </editor-fold>

public class ComponentDTO implements DTO{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    protected String code;
    protected String version;
    protected String description;
    protected String comments;
    protected ComponentType type;
    protected String author;
    protected LocalDate creation;
    protected LocalDate lastUpdate;
    protected LocalDateTime instanceCreation;
    protected String binaryLocation;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public String getBinaryLocation() {
        return binaryLocation;
    }

    public void setBinaryLocation(String binaryLocation) {
        this.binaryLocation = binaryLocation;
    }
    
    public LocalDateTime getInstanceCreation() {
        return instanceCreation;
    }

    public void setInstanceCreation(LocalDateTime instanceCreation) {
        this.instanceCreation = instanceCreation;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "\nComponent{" + "code=" + code + ", version=" + version + ", description=" + description + ", comments=" + comments + ", type=" + type + ", author=" + author + ", creation=" + creation + ", lastUpdate=" + lastUpdate + ", binaryLocation=" + binaryLocation + ", instanceCreation=" + DataHelper.printDatetime(instanceCreation) + '}';
    }
}
