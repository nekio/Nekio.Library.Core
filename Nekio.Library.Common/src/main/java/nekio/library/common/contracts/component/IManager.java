package nekio.library.common.contracts.component;

import nekio.library.common.contracts.IProperties;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/16
 *
 * Manager Contract
 */

// <editor-fold defaultstate="collapsed" desc="Abstract">

// </editor-fold>

public interface IManager extends IComponent{
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract void addPlugin(String pluginCode, IPlugin plugin);
    public abstract IPlugin getPlugin(String pluginCode);
    public abstract void startPlugin(IPlugin plugin);
    public abstract void startPlugin(IPlugin plugin, IProperties properties);
    public abstract void reloadPlugin(IPlugin plugin, IProperties properties);
    public abstract void restartPlugin(IPlugin plugin);
    public abstract void stopPlugin(IPlugin plugin);
    // </editor-fold>
}
