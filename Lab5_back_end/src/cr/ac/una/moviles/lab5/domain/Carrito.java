
package cr.ac.una.moviles.lab5.domain;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Carrito implements Jsonable{
    Hashtable<Integer, Producto> hastableProductos;
    float precioTotal = 0;
    
    public Carrito(){this.hastableProductos = new Hashtable();
}
    
    public void addProducto(Producto p){
        this.hastableProductos.put(p.getId(),p);
    }
    
    public void removeProducto(Producto p){
        this.hastableProductos.remove(p.getId());
    }
    
    public ArrayList<Producto> toList(){
        return new ArrayList(this.hastableProductos.values());
    }
}
