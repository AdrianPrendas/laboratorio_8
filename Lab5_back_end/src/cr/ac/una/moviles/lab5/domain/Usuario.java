package cr.ac.una.moviles.lab5.domain;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Usuario implements Jsonable{
    int id;
    String nombre;
    String imageUri;
    String email;
    String password;
    int tipo;
    
    public String toString(){
        return String.format("{id:%d,"
                + " nombre:%s,"
                + " imageUri:%s,"
                + " email:%s,"
                + " password:%s,"
                + " tipo:%s}",
                id, nombre, imageUri, email, password, ((tipo==0)?"Admin":"User"));
    }
    
    public Usuario(){}

    public Usuario(int id, String nombre, String imageUri, String email, String password, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.imageUri = imageUri;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
