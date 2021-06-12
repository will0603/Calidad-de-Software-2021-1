
package excepciones;

public class InvalidEmailException extends Exception{
    
    public InvalidEmailException() {
        super();
    }
    
    public InvalidEmailException(String email) {
        super("Invalid email: " + email);
    }
    
    
}
