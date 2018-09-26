package nekio.library.utils.dummy;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/19
 *
 * Dummy JSON Class Represented by a POJO
 * @XmlAttribute must be in the setters or in the instance attributes (Self-contained)
 * @XmlElement must be in the setters or in the instance attributes (Container)
 * @XmlTransient must be in sensitive data
 * 
 * JSON Sample for this Dummy:
 * 
    {
        "id" : 8,
        "name" : "Sinesio Ivan Carrillo Heredia",
        "email" : "nekio@outlook.com",
        "nickname" : "Nekio",
        "hiddenValue" : "XML file will not have this field, but JSON one does",
        "skills" : [ "Java", ".NET" ]
    }
 * 
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import nekio.library.utils.databind.Grants;
// </editor-fold>

public class DummyJsonEntity {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    @JsonView(Grants.Public.class)
    private Integer id;
    
    @JsonView(Grants.Public.class)
    private String name;  
    
    @JsonView(Grants.Public.class)
    private String email;
    
    @JsonView(Grants.Public.class)
    private String nickname;
    
    @JsonView(Grants.Private.class)
    private String hiddenValue;
    
    @JsonView(Grants.Public.class)
    private List<String> skills;
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getHiddenValue() {
        return hiddenValue;
    }

    public void setHiddenValue(String hiddenValue) {
        this.hiddenValue = hiddenValue;
    }
    
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "DummyJsonEntity{" + "id=" + id + ", name=" + name + ", email=" + email + ", nickname=" + nickname + ", hiddenValue=" + hiddenValue + ", skills=" + skills + '}';
    }
}
