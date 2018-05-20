
package cr.ac.una.moviles.lab5.dao;

import cr.ac.una.moviles.lab5.domain.Producto;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author _Adrián_Prendas_
 */
public class ProductoDAO extends ABaseDAO implements IBaseCRUD<Integer,Producto> {
    private static ProductoDAO uniqueInstance;
    final String CREATE_PRODUCT = "{call insertarProducto(?,?,?,?,?)}";
    final String FIND_PRODUCTO = "{?=call buscarProducto(?)}";
    final String READ_ALL_PRODUCTS = "{?=call listaProducto()}";
    final String UPDATE_PRODUCT = "{call modificarProducto(?,?,?,?,?)}";
    final String DELETE_PRODUCT = "{call eliminarProductos(?)}";
    
    private static final String LIST_PRODUCTS = "{?=call listaProductos()}";
    
    private ProductoDAO(){
        super();
    }
    
    public static ProductoDAO getInstance(){
        if(uniqueInstance == null)
            uniqueInstance = new ProductoDAO();
        return uniqueInstance;
    } 
    
    @Override
    public boolean create(Producto p){
        CallableStatement pstmt = null;
        boolean resp = true;
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
            e.printStackTrace();
            resp=false;
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
            e.printStackTrace();
            resp=false;
        }
        try{
            pstmt = conexion.prepareCall(CREATE_PRODUCT);                                                
            pstmt.setInt(1,p.getId());
            pstmt.setString(2,p.getNombre());
            pstmt.setString(3,p.getImageUri());
            pstmt.setFloat(4,p.getPrecio());
            pstmt.setInt(5,p.getCantidad()); 
            pstmt.execute();//retorna true o false
        } catch (SQLException e) {
            System.out.println("Llave duplicada");
            e.printStackTrace();
            resp=false;
        }
        finally{
            try {
                if (pstmt!=null)
                    pstmt.close();                                    
                desconectar();
            } catch (SQLException e) {
                System.out.println("Estatutos invalidos o nulos");
                e.printStackTrace();
                resp=false;
            }
        }
        if(resp)
            System.out.println("se creo con exito: "+p.toString());
        return resp;
    }

    @Override
    public Producto read(Integer key) {
        ResultSet rs = null;
        ArrayList<Producto> coleccion = new ArrayList();
        Producto p2 = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
        }catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
        }
        try{
            pstmt = conexion.prepareCall(FIND_PRODUCTO);            
            pstmt.setInt(2,key);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                p2 = new Producto();
                p2.setId(rs.getInt("id"));
                p2.setNombre(rs.getString("nombre"));
                p2.setPrecio(rs.getFloat("precio"));
                p2.setImageUri(rs.getString("imageUri"));
                p2.setCantidad(rs.getInt("cantidad"));
                coleccion.add(p2);
            }
        }
        catch (SQLException e) {
            System.out.println("Sentencia no valida");
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                System.out.println("Estatutos invalidos o nulos");
               e.printStackTrace();
            }
        }
        return coleccion.get(0);
    }

    @Override
    public boolean update(Producto p) {
        PreparedStatement pstmt = null;
        boolean resp=true;
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
            e.printStackTrace();
            resp=false;
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
            e.printStackTrace();
            resp=false;
        }
        try {            
            pstmt = conexion.prepareStatement(UPDATE_PRODUCT);
            pstmt.setInt(1,p.getId());
            pstmt.setString(2,p.getNombre());
            pstmt.setString(3,p.getImageUri());
            pstmt.setFloat(4,p.getPrecio());
            pstmt.setInt(5,p.getCantidad());            
            int resultado = pstmt.executeUpdate();
            if (resultado == 0)//si es diferente de 0 es porq si afecto un registro o mas
                resp=false;            
            else
               System.out.println("Modificacion Exitosa");            
        }catch (SQLException e) {
            System.out.println("Sentencia no valida");
            e.printStackTrace();
            resp=false;
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
               resp=false;
            }
        }
        return resp;
    }

    @Override
    public boolean delete(Integer key) {
        boolean resp=true;
        ResultSet rs = null;     
        ArrayList coleccion = new ArrayList();
        Producto elcontacto = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
        } catch (ClassNotFoundException e) {
           System.out.println("No se ha localizado el driver");
           return false;
        } catch (SQLException e) {
           System.out.println("La base de datos no se encuentra disponible");
           return false;
        }
        try {
            pstmt = conexion.prepareCall(DELETE_PRODUCT);                        
            pstmt.setInt(1,key);            
            pstmt.execute();
        }catch (SQLException e) {
            System.out.println("Sdentencia no valida");
           e.printStackTrace();
           resp=false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
                System.out.println("se elimino con exito: "+ key);
                return resp;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public List<Producto> readAll() {
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Producto p2 = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
        }catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
        }
        try{
            pstmt = conexion.prepareCall(READ_ALL_PRODUCTS);                
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                p2 = new Producto();
                p2.setId(rs.getInt("id"));
                p2.setNombre(rs.getString("nombre"));
                p2.setImageUri("imageUri");
                p2.setPrecio(rs.getFloat("precio"));
                coleccion.add(p2);
            }
        }
        catch (SQLException e) {
            System.out.println("Sentencia no valida");
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                System.out.println("Estatutos invalidos o nulos");
               e.printStackTrace();
            }
        }
        return coleccion;
    }
    
}
