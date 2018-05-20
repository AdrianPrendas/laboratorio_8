package cr.ac.una.moviles.lab5.dao;

import cr.ac.una.moviles.lab5.domain.Usuario;
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
public class UsuarioDAO extends ABaseDAO implements IBaseCRUD<Integer, Usuario>{
    private static UsuarioDAO uniqueInstance;
    final String CREATE_USER = "{call insertarUsuario(?,?,?,?,?,?)}";
    final String FIND_USER = "{?=call buscarUsuario(?)}";
    final String READ_ALL_USERS = "{?=call listaUsuario()}";
    final String UPDATE_USER = "{call modificarUsuarios(?,?,?,?,?,?)}";
    final String DELETE_USER = "{call eliminarUsuarios(?)}";
    
    private UsuarioDAO(){
        super();
    }
    public static UsuarioDAO getInstance(){
        if(uniqueInstance==null)
            uniqueInstance = new UsuarioDAO();
        return uniqueInstance;
    }
    @Override
    public boolean create(Usuario t) {
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
            pstmt = conexion.prepareCall(CREATE_USER);                                                
            pstmt.setInt(1,t.getId());
            pstmt.setString(2,t.getNombre());
            pstmt.setString(3,t.getImageUri());
            pstmt.setString(4,t.getEmail());
            pstmt.setString(5,t.getPassword());
            pstmt.setInt(6,t.getTipo()); 
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
            System.out.println("se creo con exito: "+t.toString());
        return resp;
    }

    @Override
    public Usuario read(Integer k) {
        ResultSet rs = null;
        ArrayList<Usuario> coleccion = new ArrayList();
        Usuario u = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
        }catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
        }
        try{
            pstmt = conexion.prepareCall(FIND_USER);            
            pstmt.setInt(2,k);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setImageUri(rs.getString("imageUri"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setTipo(rs.getInt("tipo"));
                coleccion.add(u);
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
    public List<Usuario> readAll() {
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Usuario u = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
        }catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
        }
        try{
            pstmt = conexion.prepareCall(READ_ALL_USERS);                
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setImageUri(rs.getString("imageUri"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setTipo(rs.getInt("tipo"));
                coleccion.add(u);
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

    @Override
    public boolean update(Usuario t) {
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
            pstmt = conexion.prepareStatement(UPDATE_USER);
            pstmt.setInt(1,t.getId());
            pstmt.setString(2,t.getNombre());
            pstmt.setString(3,t.getImageUri());
            pstmt.setString(4,t.getEmail());
            pstmt.setString(5,t.getPassword());
            pstmt.setInt(6,t.getTipo());        
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
    public boolean delete(Integer t) {
        boolean resp=true;
        ResultSet rs = null;     
        ArrayList coleccion = new ArrayList();
        Usuario user = null;
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
            pstmt = conexion.prepareCall(DELETE_USER);                        
            pstmt.setInt(1,t);            
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
                System.out.println("se elimino con exito: "+ t);
                return resp;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    
    
}
