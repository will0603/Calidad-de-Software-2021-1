package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Usuario;
import ordendetrabajo.frmMenu;
import ordendetrabajo.OrdenDeTrabajo;
import vista.frmIniciar;
import vista.frmRegistrar;
import vista.frmRecordar;
import utils.Email;
import vista.frmMenuAdmin;
import vista.frmPrincipal;

public class ControladorLoginInicio {
    private frmIniciar vista;

    public ControladorLoginInicio(frmIniciar vista) {
        this.vista = vista;
        
        this.vista.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validar()){
                    Sistema.usuario = new Usuario(vista.txtUsuario.getText() , vista.txtContraseña.getText()).loguear();
                    if (Sistema.usuario != null ){
                    
                        String mensaje = "<b>Señor(a):"+ Sistema.usuario.getLogin()+"</b><br>";

                           mensaje += "<font color=red>Usted inicio sesion ("+ new Date() +") en la app DevCell </font><br>";

                       Email email =  new Email(Sistema.usuario.getEmail(), "Inicio de sesión Exitoso", mensaje);

                       Thread enviar = new Thread(email);
                       enviar.start();
                       vista.dispose();
                       if(Sistema.usuario.getPerfil().equalsIgnoreCase("administrador")){
                           frmMenuAdmin menuAdmin = new frmMenuAdmin();
                           //menuAdmin.iniciar();
                           ControladorMenuAdmin controlador = new ControladorMenuAdmin(menuAdmin);
                           controlador.iniciar();
                       }else{
                           new OrdenDeTrabajo().iniciar();
                       }
                       
                       /*
                       frmPrincipal fprincipal = new frmPrincipal();
                       ControladorPrincipal controladorprincipal = new ControladorPrincipal(fprincipal);
                       controladorprincipal.iniciar();
                       */
                    }else {
                        JOptionPane.showMessageDialog(vista, "Error de credenciales o Usuario invalido");
                    }
                 }else{
                    JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Error" , JOptionPane.WARNING_MESSAGE );             
                }
            }
        });
        
        this.vista.btnSalir.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    
        this.vista.btnAbrirRecordarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                frmRecordar fRecordar = new frmRecordar();
                ControladorLoginRecordar controlrecordar = new ControladorLoginRecordar(fRecordar);
                controlrecordar.iniciar();
            }
        });
             
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtUsuario.getText().length()!= 0 &&
                this.vista.txtContraseña.getText().length()!= 0
                ){
            resultado = true;
        }
        return resultado;
    }
    
}
