
import controlador.ControladorGuiadeServicio;
import controlador.ControladorLoginInicio;
import javax.swing.UIManager;
import modelo.Usuario;
import vista.frmGuiadeServicio;
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
       
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }
        
        frmIniciar fInicio = new frmIniciar();
        ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
        controlador.iniciar(); 

       
       
    }
}
