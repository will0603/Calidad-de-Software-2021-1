
import controlador.ControladorLoginInicio;
import vista.frmIniciar;


public class AppMain {

    public static void main(String[] args) {
       
        
        
        
        
        
        frmIniciar fInicio = new frmIniciar();
        ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
        controlador.iniciar(); 
    }
}
