package nekio.library.common.contracts;

/**
 * @param <T>
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/10
 *
 * Factory Contract
 */

public interface IFactory<T> {
    // <editor-fold defaultstate="collapsed" desc="Abstract">
    T create();
    // </editor-fold>
}