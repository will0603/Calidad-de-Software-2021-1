package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.frmIniciar;
import vista.frmPrincipal;


public class ControladorPrincipal {
    private frmPrincipal vista;

    public ControladorPrincipal(frmPrincipal vista) {
        this.vista = vista;
        
        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sistema.usuario.salir();
                vista.dispose();
                frmIniciar fInicio = new frmIniciar();
                ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
                controlador.iniciar();
            }
        }); 
       
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
}