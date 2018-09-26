package nekio.library.utils.dummy;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/08/01
 *
 * {Class Description}
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.util.Date;
import nekio.library.utils.P;
// </editor-fold>

public class DummyClass implements IDummy{
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private int id;
    private String name;
    private Date date;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public DummyClass(){
        this(0, "N/A");
    }
    
    public DummyClass(int id, String name){
        this(id, name, new Date());
    }
    
    public DummyClass(int id, String name, Date date){
        this.id = id;
        this.name = name;
        this.date = date;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Implementation">
    @Override
    public void printDummy() {
        P.Log.i(this.toString());
    }
    // </editor-fold>
    
    @Override
    public String toString() {
        return "DummyClass{" + "id=" + id + ", name=" + name + ", date=" + date + "}, memory address: " + super.toString();
    }
}
