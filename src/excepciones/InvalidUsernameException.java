
package excepciones;

public class InvalidUsernameException extends Exception{
    
    public InvalidUsernameException() {
        super();
    }
    
    public InvalidUsernameException(String username) {
        super("Invalid Username: " + username);
    }
    
}
