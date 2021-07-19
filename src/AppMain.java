
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmIniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmIniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmIniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmIniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        frmIniciar fInicio = new frmIniciar();
        ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
        controlador.iniciar(); 

       
       
    }
}
