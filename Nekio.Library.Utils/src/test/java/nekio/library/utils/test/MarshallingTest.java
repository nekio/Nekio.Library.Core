package nekio.library.utils.test;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/18
 *
 * Test Marshalling
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import nekio.library.log.Logger;
import nekio.library.utils.P;
import nekio.library.utils.marshall.Marshaller;
import nekio.library.utils.marshall.binders.Marshallized;
import nekio.library.utils.marshall.binders.Unmarshallized;
import nekio.library.utils.marshall.binders.IMarshallized;
import nekio.library.utils.marshall.binders.IUnmarshallized;
import nekio.library.utils.marshall.binders.MarshallPack;
// </editor-fold>

public class MarshallingTest {
    // <editor-fold defaultstate="collapsed" desc="Testing Methods">
    public static void main(String[] args) {
        // This Marshall Pack is focused on getting (un)marshall values directly
        packagedMarshalling();
        
        // When you have Isolated objects is well-oriented to use the Marshaller util
        isolatedMarshalling();
        isolatedUnmarshalling();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Test using Packaged Marshalls">
    private static void packagedMarshalling(){
        Logger.brace("Packaged Marshalling");
        StringIntegerMarshallPack marshallPack = new StringIntegerMarshallPack();
        
        Integer marshalledValue = marshallPack.marshall("1");
        P.Log.i("Marshalled: " + marshalledValue);
        
        String unmarshalledValue = marshallPack.unmarshall(2);
        P.Log.i("UnmarshalledValue: " + unmarshalledValue);
    }
    
    private static class StringIntegerMarshallPack extends MarshallPack<String, Integer>{                
        @Override
        public Integer marshall(String u) {
            super.setUnmarshallizedValue(u);
            
            Integer marshallized = Integer.valueOf(u);
            super.setMarshallizedValue(marshallized);
            
            return marshallized;
        }

        @Override
        public String unmarshall(Integer m) {
            String unmarshalled = null;
            
            unmarshalled = m.toString();
            super.setUnmarshallizedValue(unmarshalled);
            
            return unmarshalled;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Test using contracts and static Marshaller util">
    private static void isolatedMarshalling(){
        Logger.brace("Isolated Marshalling");
        
        String unmarshalledValue = "3";
        IUnmarshallized<String, Integer> unmarshallized = new IntegerUnmarshallized();        
        unmarshallized.setUnmarshallizedValue(unmarshalledValue);
        
        Integer marshalized = Marshaller.marshallize(unmarshallized);
        
        P.Log.i(marshalized.toString());
    }
    
    private static void isolatedUnmarshalling(){
        Logger.brace("Isolated Unmarshalling");
        
        Integer marshalledValue = 4;
        IMarshallized<String, Integer> marshallized = new IntegerMarshallized();        
        marshallized.setMarshallizedValue(marshalledValue);
        
        String unmarshalized = Marshaller.unmarshallize(marshallized);
        
        P.Log.i(unmarshalized);
    }
    
    private static class IntegerMarshallized extends Marshallized<String, Integer>{
        private IntegerMarshallized(){}
        
        @Override
        public String unmarshall(Integer m) {
            String unmarshalled = null;
            
            unmarshalled = m.toString();
            
            return unmarshalled;
        }
    }
    
    private static class IntegerUnmarshallized extends Unmarshallized<String, Integer>{
        @Override
        public Integer marshall(String u) {
            Integer marshalled = null;
            
            marshalled = Integer.valueOf(u);
            
            return marshalled;
        }
    }
    // </editor-fold>
}
