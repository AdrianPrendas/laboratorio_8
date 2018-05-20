
import cr.ac.una.moviles.lab5.bl.ProductoBL;
import cr.ac.una.moviles.lab5.dao.ProductoDAO;
import cr.ac.una.moviles.lab5.domain.Producto;
import java.util.ArrayList;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Test {
    
    public static void main(String [] args){
        ProductoBL pbl = new ProductoBL();
        Producto p = new Producto();
        p.setId(13);
        p.setNombre("pc");
        p.setImageUri("pc.jpg");
        p.setPrecio(1000);
        p.setCantidad(2);
        System.out.println(p);
        pbl.create(p);
        
        //p = pbl.read(13);
        //System.out.println("Read");
        //System.out.println(p);
        
        ArrayList<Producto> lista_productos = new ArrayList(pbl.findAll());
        for(Producto p2: lista_productos){
            System.out.println(p2);
        }
        
        pbl.delete(13);
        
        lista_productos = new ArrayList(pbl.findAll());
        for(Producto p2: lista_productos){
            System.out.println(p2);
        }
        
        
        
    }
}
