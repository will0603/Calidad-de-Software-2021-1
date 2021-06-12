
package excepciones;

public class InvalidLoginException extends Exception{
    
    public InvalidLoginException() {
        super();
    }
    
    public InvalidLoginException(String login) {
        super("Invalid ID: " + login);
    }
    
}
