package cr.ac.una.moviles.lab5.dao;

import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public interface IBaseCRUD<K, T> {
    boolean create(T t);
    T read(K k);
    List<T> readAll();
    boolean update(T t);
    boolean delete(K t); 
}
