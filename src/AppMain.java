
import controlador.ControladorLoginInicio;
import modelo.Usuario;
import vista.frmIniciar;


public class AppMain {

    public static void main(String[] args) {
      
        /*
        create database devcell;
        
        use devcell;
        
            CREATE TABLE `devcell`.`usuario` (
      `codigo` INT NOT NULL AUTO_INCREMENT,
      `username` VARCHAR(45) NOT NULL,
      `contraseña` VARCHAR(45) NOT NULL,
      `email` VARCHAR(45) NOT NULL,
      `conectado` TINYINT NOT NULL,
      PRIMARY KEY (`codigo`));
        
        */
        new Usuario(1).eliminar();
        
        frmIniciar fInicio = new frmIniciar();
        ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
        controlador.iniciar(); 
    }
}
