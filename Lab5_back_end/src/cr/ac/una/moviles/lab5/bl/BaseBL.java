package cr.ac.una.moviles.lab5.bl;

import cr.ac.una.moviles.lab5.dao.*;
import java.util.LinkedHashMap;

/**
 *
 * @author _Adrian_Prendas_
 */

public class BaseBL {
    private final LinkedHashMap<String, IBaseCRUD> daos;

    public BaseBL() {
        daos = new LinkedHashMap();
        daos.put("cr.ac.una.moviles.lab5.domain.Producto", ProductoDAO.getInstance());
        daos.put("cr.ac.una.moviles.lab5.domain.Usuario", UsuarioDAO.getInstance());
    }

    public IBaseCRUD getDao(String className) {
        return daos.get(className);
    }
}
