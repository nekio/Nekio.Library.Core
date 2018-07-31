package nekio.library.common.contracts.layers;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Business Rules Contract
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">

// </editor-fold>

public abstract class BR {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    protected DTO dto;
    protected boolean ready;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    protected BR(DTO dto){
        this.dto = dto;
        this.ready = false;
        
        initialize();
        processDTO();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    /**
     * For DTO logging, attributes initialization and local parameters passing to class child
     */
    protected abstract void initialize();
    
    /**
     * For setting values and encapsulated attributes
     */
    protected abstract void processDTO();
    
    /**
     * For class child cleaning
     */
    public abstract void dispose();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public boolean isReady() {
        return ready;
    }
    // </editor-fold>
    
}
