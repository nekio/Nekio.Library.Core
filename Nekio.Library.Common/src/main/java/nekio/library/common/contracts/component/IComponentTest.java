package nekio.library.common.contracts.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Component Test Contract
 */

public interface IComponentTest {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract IComponent getInstanceGenericFactoryWay();
    public abstract IComponent getInstanceComponentFactoryWay();
    public abstract void processComponent(IComponent component);
    public abstract void publishComponent(IComponent component);
    // </editor-fold>
}
