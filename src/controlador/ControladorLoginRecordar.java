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
import vista.frmRecordar;


public class ControladorLoginRecordar {
    private frmRecordar vista;
    private UsuarioArreglo modelo;

    public ControladorLoginRecordar(UsuarioArreglo modelo, frmRecordar vista) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.btnRecordarCredenciales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                Sistema.usuarioConectado = modelo.getUsuarioxEmail(vista.txtUsuarioEmail.getText());
                if(Sistema.usuarioConectado == null){
                 Sistema.usuarioConectado = modelo.getUsuarioxNombre(vista.txtUsuarioEmail.getText());
                }
                if ( Sistema.usuarioConectado != null){    
                   String mensaje = "<b>Estimado Señor(a)</b><br>";
                       
                        mensaje += "<font color=black>Le recordamos las credenciales de su cuenta: </font><br>";
                        mensaje += "<font color=red>ID: "+ Sistema.usuarioConectado.getLogin() +"</font><br>";
                        mensaje += "<font color=red>Contraseña: "+ Sistema.usuarioConectado.getContraseña() +" </font><br>";
                        
                    Email email =  new Email(Sistema.usuarioConectado.getEmail(), "Envio de Credenciales", mensaje);
                    
                    Thread enviar = new Thread(email);
                    enviar.start();
                    
                    vista.dispose();
                    
                    frmIniciar fInicio = new frmIniciar();
                    ControladorLoginInicio controlador = new ControladorLoginInicio(Sistema.usuarios, fInicio);
                    controlador.iniciar();
                 }else {
                     JOptionPane.showMessageDialog(vista, "Error de credenciales");
                 }
            }
        });
        
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