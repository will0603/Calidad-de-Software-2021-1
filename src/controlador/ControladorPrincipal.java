package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.frmRegistrar;
import modelo.UsuarioArreglo;
import modelo.Usuario;
import utils.Email;
import vista.frmIniciar;
import vista.frmPrincipal;
import vista.frmRecordar;


public class ControladorPrincipal {
    private frmPrincipal vista;
    private UsuarioArreglo modelo;

    public ControladorPrincipal(UsuarioArreglo modelo, frmPrincipal vista) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                frmIniciar fInicio = new frmIniciar();
                ControladorLoginInicio controlador = new ControladorLoginInicio(Sistema.usuarios, fInicio);
                controlador.iniciar();
            }
        }); 
       
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
}