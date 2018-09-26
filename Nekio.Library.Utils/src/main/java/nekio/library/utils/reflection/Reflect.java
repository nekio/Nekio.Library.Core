package nekio.library.utils.reflection;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Util for Reflection
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import nekio.library.common.enums.FileType;
import nekio.library.log.Logger;
import nekio.library.utils.helpers.DataHelper;
// </editor-fold>

public class Reflect {
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final String RESOURCES = "res";
    private final String CLASS = ".class";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static">
    public static List<String> getMethods(Class clazz){
        List<String> result = null;
        
        try {
            result = new ArrayList<>();
                    
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                result.add(method.getName());
            }
        } catch (Exception e) {
            Logger.x(e);
        }
        
        return result;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    public List<String> getBinaryFilenames(){
        List<String> filenames = null;
        
        String context = System.getProperty("user.dir") + File.separator + RESOURCES;
        String binariesFolder = context + FileType.JAR.getFoldername();
        
        Logger.i("\nGetting Binary Filenames in - " + binariesFolder);
        File binaries = new File(binariesFolder);
        if(binaries.exists()){
            filenames = new ArrayList<>();
            
            String binaryName = null;
            for(File jar : binaries.listFiles()){
                binaryName = jar.getAbsolutePath();
                filenames.add(binaryName);
            }
        }
        
        DataHelper.printList(filenames);
        
        return filenames;
    }
    
    public List<Class> getBinaryClasses(List<String> filenames){
        List<Class> classes = new ArrayList<>();
        JarFile jar = null;
        
        Class clazz = null;
        for(String filename : filenames){
            Logger.i("\nGetting Binary Classes in - " + filename);
            
            try {
                jar = new JarFile(filename);
                
                URL[] urls = {new URL("jar:file:" + filename + "!/")};
                URLClassLoader ucl = URLClassLoader.newInstance(urls);
                
                Enumeration<JarEntry> jarEntries = jar.entries();               
                while(jarEntries.hasMoreElements()){
                    JarEntry jarEntry = jarEntries.nextElement();
                    String jarEntryName = jarEntry.getName();
                    
                    if(jarEntry.isDirectory()){
                        continue;
                    }else if(jarEntryName.endsWith(CLASS)){
                        String className = jarEntryName.substring(0, jarEntryName.length() - CLASS.length());
                        className = className.replace("/",".");
                        clazz = ucl.loadClass(className);
                        
                        classes.add(clazz);
                    }
                }
            } catch (Exception e) {
                Logger.x(e);
            }
        }
        
        return classes;
    }
    // </editor-fold>
}
