package nekio.library.common.model;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/06
 *
 * Log Model
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.model.component.ComponentTracker;
import java.time.LocalDate;
import java.time.LocalTime;
import nekio.library.common.enums.Logging.Severity;
// </editor-fold>

public class Log {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private LocalDate date;
    private LocalTime time;
    private Severity severity;
    private ComponentTracker componentTracker;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Log(ComponentTracker componentTracker) {
        this(Severity.Info, componentTracker);
    }
    
    public Log(Severity severity, String text) {
        this(severity, new ComponentTracker(text));
    }
    
    public Log(Severity severity, ComponentTracker componentTracker) {
        this(LocalDate.now(), LocalTime.now(), severity, componentTracker);
    }
    
    public Log(LocalDate date, LocalTime time, Severity severity, ComponentTracker componentTracker){
        this.date = date;
        this.time = time;
        this.severity = severity;
        this.componentTracker = componentTracker;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Encapsulated">
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public ComponentTracker getComponentTracker() {
        return componentTracker;
    }

    public void setComponentTracker(ComponentTracker componentTracker) {
        this.componentTracker = componentTracker;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Log{" + "date=" + date + ", time=" + time + ", severity=" + severity + ", componentTracker=" + componentTracker + '}';
    }
}