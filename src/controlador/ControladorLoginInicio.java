package controlador;

import general.Sistema;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.UsuarioArreglo;
import vista.frmIniciar;
import vista.frmRegistrar;
import vista.frmRecordar;
import utils.Email;
import vista.frmPrincipal;

public class ControladorLoginInicio {
    private UsuarioArreglo modelo;
    private frmIniciar vista;

    public ControladorLoginInicio(UsuarioArreglo modelo, frmIniciar vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Sistema.usuarioConectado = modelo.ingresar(vista.txtUsuario.getText() , vista.txtContraseña.getText());
                 if (Sistema.usuarioConectado != null ){
                    
                     String mensaje = "<b>Señor(a):"+ Sistema.usuarioConectado.getLogin()+"</b><br>";
                       
                        mensaje += "<font color=red>Usted inicio sesion ("+ new Date() +") en la app MovilTech </font><br>";
                  
                    Email email =  new Email(Sistema.usuarioConectado.getEmail(), "Inicio de sesión Exitoso", mensaje);
                    
                    Thread enviar = new Thread(email);
                    enviar.start();
                    vista.dispose();
                    
                    frmPrincipal fprincipal = new frmPrincipal();
                    ControladorPrincipal controladorprincipal = new ControladorPrincipal(Sistema.usuarios, fprincipal);
                    controladorprincipal.iniciar();
                 }else {
                     JOptionPane.showMessageDialog(vista, "Error de credenciales");
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
                  ControladorLoginRegistrar controlregistro = new ControladorLoginRegistrar(Sistema.usuarios, fRegistro) ;
                  controlregistro.iniciar();
            }
        });
        
        this.vista.btnAbrirRecordarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                frmRecordar fRecordar = new frmRecordar();
                ControladorLoginRecordar controlrecordar = new ControladorLoginRecordar(Sistema.usuarios,fRecordar);
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
