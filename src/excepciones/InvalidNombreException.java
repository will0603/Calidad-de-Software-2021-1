
package excepciones;

public class InvalidNombreException extends Exception{
    
    public InvalidNombreException() {
        super();
    }
    
    public InvalidNombreException(String nombre) {
        super("Invalid Nombre: " + nombre);
    }
    
}
