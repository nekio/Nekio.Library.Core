package nekio.library.framework.component;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Component Test Archetype
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.common.contracts.component.IComponent;
import nekio.library.common.contracts.component.IComponentTest;
import nekio.library.common.contracts.IFactory;
import nekio.library.framework.P;
import nekio.library.framework.adapters.ComponentAdapter;
import nekio.library.framework.dp.factory.ComponentFactory;
import nekio.library.framework.dp.factory.GenericFactory;
import nekio.library.log.Logger;
// </editor-fold>

public class ComponentTest implements IComponentTest{    
    // <editor-fold defaultstate="collapsed" desc="Implementations">  
    @Override
    public IComponent getInstanceGenericFactoryWay(){
        Logger.brace("ComponentTest");
        P.Log.i("Testing...");
        
        IFactory<IComponent> factory = new GenericFactory<>(Component.class);
        Component component = (Component)factory.create();
        
        return component;
    }
    
    @Override
    public IComponent getInstanceComponentFactoryWay(){
        Logger.brace("ComponentTest");
        P.Log.i("Testing...");
        
        IComponent component = ComponentFactory.getInstance(ComponentAdapter.class);
        
        return component;
    }
    
    @Override
    public void processComponent(IComponent component){
        Logger.brace("Process Component");
        P.Log.i("Testing...");
        
        component.activate();
        component.initialize();
        component.restart();

        component.uninitialize();
        component.deactivate();
        component.dispose();
    }
    
    @Override
    public void publishComponent(IComponent component){
        Logger.brace("Publish Component");
        P.Log.i("Testing...");
        
        component.activate();
        component.initialize();
        component.publish();
    }
    // </editor-fold>
}