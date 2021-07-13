package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import utils.Email;
import vista.frmIniciar;
import vista.frmRecordar;


public class ControladorLoginRecordar {
    private frmRecordar vista;

    public ControladorLoginRecordar(frmRecordar vista) {
        this.vista = vista;
        
        this.vista.btnRecordarCredenciales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                if(validar()){
                    Sistema.usuario = new Usuario().getUsuarioxEmail(vista.txtUsuarioEmail.getText());
                    if(Sistema.usuario == null){
                        Sistema.usuario = new Usuario().getUsuarioxUsername(vista.txtUsuarioEmail.getText());
                    }
                    if ( Sistema.usuario != null){    
                       String mensaje = "<b>Estimado Señor(a)</b><br>";

                            mensaje += "<font color=black>Le recordamos las credenciales de su cuenta: </font><br>";
                            mensaje += "<font color=red>ID: "+ Sistema.usuario.getLogin() +"</font><br>";
                            mensaje += "<font color=red>Contraseña: "+ Sistema.usuario.getContraseña() +" </font><br>";

                        Email email =  new Email(Sistema.usuario.getEmail(), "Envio de Credenciales", mensaje);

                        Thread enviar = new Thread(email);
                        enviar.start();

                        vista.dispose();
                        frmIniciar fInicio = new frmIniciar();
                        ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
                        controlador.iniciar();
                     }else {
                         JOptionPane.showMessageDialog(vista, "Error de credenciales");
                     }
                }else{
                JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Error" , JOptionPane.WARNING_MESSAGE );             
                }
            }
        });
        
        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    
    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtUsuarioEmail.getText().length()!= 0
                ){
            resultado = true;
        }
        return resultado;
    }
    
}