package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Usuario;
import vista.frmIniciar;
import vista.frmRegistrar;
import vista.frmRecordar;
import utils.Email;
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
                       frmPrincipal fprincipal = new frmPrincipal();
                       ControladorPrincipal controladorprincipal = new ControladorPrincipal(fprincipal);
                       controladorprincipal.iniciar();
                    }else {
                        JOptionPane.showMessageDialog(vista, "Error de credenciales");
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
    
        this.vista.btnAbrirRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  vista.dispose();
                  frmRegistrar fRegistro = new frmRegistrar();
                  ControladorLoginRegistrar controlregistro = new ControladorLoginRegistrar(fRegistro) ;
                  controlregistro.iniciar();
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
