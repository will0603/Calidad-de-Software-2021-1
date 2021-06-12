
package excepciones;

public class InvalidPasswordException extends Exception{
    
    public InvalidPasswordException() {
        super();
    }
    
    public InvalidPasswordException(String password) {
        super("Invalid password: " + password);
    }
    
}
