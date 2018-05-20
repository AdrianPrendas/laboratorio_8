package cr.ac.una.moviles.lab5.bl;

import cr.ac.una.moviles.lab5.domain.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _Adrian_Prendas
 */
public class ProductoBL extends BaseBL implements IBaseBL<Integer, Producto>{
    
    public ProductoBL(){
        super();
    }

    @Override
    public boolean create(Producto o) {
        return this.getDao(o.getClass().getName()).create(o);
    }

    @Override
    public boolean update(Producto o) {
        return this.getDao(o.getClass().getName()).update(o);
    }

    @Override
    public boolean delete(Integer key) {
        return this.getDao(Producto.class.getName()).delete(key);
    }
    
    
    @Override
    public Producto read(Integer id) {
        return (Producto)this.getDao(Producto.class.getName()).read(id);
    }

    @Override
    public List<Producto> findAll() {
        return new ArrayList(this.getDao(Producto.class.getName()).readAll());
    }

}
