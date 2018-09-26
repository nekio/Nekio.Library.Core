package nekio.library.utils.databind;

/**
 * @param <T>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/09/26
 *
 * Contract for DataBind Converter
 */

public interface DatabBindConverter<T> {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    public abstract void setGrant(Class<? extends Grants.Public> grant);
    public abstract void createFile(T t, String outputPath, String outputFilename) throws Exception;
    public abstract String getDatabindedString(T t) throws Exception;
    public abstract T getPojo(Class clazz, Object dataBindSource) throws Exception;
    public abstract String getFilename();
    // </editor-fold>
}
