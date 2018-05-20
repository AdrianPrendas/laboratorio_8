
package cr.ac.una.moviles.lab5.bl;

import java.util.List;

/**
 *
 * @author _Adrian_Prendas_
 */

public interface IBaseBL <K, T> {
    public abstract boolean create(T o);
    public abstract T read(K key);
    public abstract boolean update(T o);
    public abstract boolean delete(K key);
    public abstract List<T> findAll();
}
