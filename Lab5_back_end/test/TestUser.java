
import cr.ac.una.moviles.lab5.bl.UsuarioBL;
import cr.ac.una.moviles.lab5.domain.Usuario;
import java.util.ArrayList;

/**
 *
 * @author _Adrián_Prendas_
 */
public class TestUser {
    public static Usuario user = new Usuario(604140422,"Adrian Prendas", "adrian","a6r1an@hotmail.com","adr",0);
    public static Usuario user2 = new Usuario(2,"Oscar Espinoza", "espinoza","espinozaOscar@hotmail.com","osc",1);
    public static UsuarioBL userBL = new UsuarioBL();
    
    public static void print(String mem){
        System.out.println(mem);
    }
    
    public static void create(){
        //userBL.create(user);
        userBL.create(user2);
    }
    
    public static void read(Integer key){
        Usuario u = userBL.read(key);
        print(u.toString());
    }
    
    public static void readAll(){
        ArrayList<Usuario> lista_usuarios = new ArrayList(userBL.findAll());
        print("Lista de usuarios");
        for(Usuario u: lista_usuarios){
            print(u.toString());
        }
    }
    
    public static void update(){
        Usuario u = new Usuario(604140420,"Adrian Prendas Araya", "adrian","a6r2an@gmail.com","prendas",0);
        userBL.update(u);
    }
    
    public static void delete(Integer key){
        userBL.delete(key);
    }
    
    public static void main(String [] args){
        //create();
        //read(604140420);
        //readAll();
        //update();
        delete(604140422);
        
    }
}
