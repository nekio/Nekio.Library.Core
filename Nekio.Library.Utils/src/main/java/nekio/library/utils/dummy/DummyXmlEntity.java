package nekio.library.utils.dummy;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/19
 *
 * Dummy XML Class Represented by a POJO
 * @XmlAttribute must be in the setters or in the instance attributes (Self-contained)
 * @XmlElement must be in the setters or in the instance attributes (Container)
 * @XmlTransient must be in sensitive data
 * 
 * XML Sample for this Dummy:
 * 
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <user id="88">
        <name>Sinesio Ivan Carrillo Heredia</name>
        <email>nekio@outlook.com</email>
        <nickname>Nekio</nickname>
        <skills>
            <skill>Java</skill>
            <skill>.NET</skill>
        </skills>
    </user>
 * 
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
// </editor-fold>

@XmlRootElement
public class DummyXmlEntity {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private Integer id;   
    private String name;  
    private String email;
    private String nickname;
    private String hiddenValue;
    private List<String> skills;
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNickname() {
        return nickname;
    }

    @XmlElement
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getHiddenValue() {
        return hiddenValue;
    }

    @XmlTransient
    public void setHiddenValue(String hiddenValue) {
        this.hiddenValue = hiddenValue;
    }
    
    public List<String> getSkills() {
        return skills;
    }

    @XmlElementWrapper
    @XmlElement(name="skill")
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "DummyXmlEntity{" + "id=" + id + ", name=" + name + ", email=" + email + ", nickname=" + nickname + ", hiddenValue=" + hiddenValue + ", skills=" + skills + '}';
    }
}
