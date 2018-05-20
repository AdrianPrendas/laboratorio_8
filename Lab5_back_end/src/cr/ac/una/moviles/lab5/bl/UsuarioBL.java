package cr.ac.una.moviles.lab5.bl;

import cr.ac.una.moviles.lab5.domain.Usuario;
import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public class UsuarioBL extends BaseBL implements IBaseBL<Integer, Usuario>{
    
    public Usuario login(Usuario u){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Usuario o) {
        return this.getDao(o.getClass().getName()).create(o);
    }

    @Override
    public Usuario read(Integer key) {
        return (Usuario)this.getDao(Usuario.class.getName()).read(key);
    }

    @Override
    public boolean update(Usuario o) {
        return this.getDao(o.getClass().getName()).update(o);
    }

    @Override
    public boolean delete(Integer key) {
        return this.getDao(Usuario.class.getName()).delete(key);
    }

    @Override
    public List<Usuario> findAll() {
        return this.getDao(Usuario.class.getName()).readAll();
    }

    private Exception RuntimeException(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
