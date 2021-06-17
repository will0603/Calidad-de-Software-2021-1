 
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import excepciones.InvalidEmailException;
import excepciones.InvalidUsernameException;
import excepciones.InvalidPasswordException;
import vista.frmRegistrar;
import modelo.Usuario;
import vista.frmIniciar;


public class ControladorLoginRegistrar {
    private frmRegistrar vista;

    public ControladorLoginRegistrar(frmRegistrar vista) {
        this.vista = vista;
        
        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               if(validar()){
            
                    try{
                    Usuario u = new Usuario(vista.txtUsuario.getText(),vista.txtContraseña.getText(),vista.txtEmail.getText());
                    
                    System.out.println(u.insertar());
                    System.out.println(u);
                    vista.dispose();
                     frmIniciar fInicio = new frmIniciar();
                     ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
                     controlador.iniciar();
                    }catch(InvalidEmailException e1){
                     System.out.println(e1);
                     JOptionPane.showMessageDialog(vista, "El Email ingresado ya esta registrado o no es valido");
                    }catch(InvalidPasswordException e2){
                     System.out.println(e2);
                     JOptionPane.showMessageDialog(vista, "La contraseña debe contener al menos 6 caracteres");
                    }catch(InvalidUsernameException e3){
                     System.out.println(e3);
                     JOptionPane.showMessageDialog(vista, "El Nombre de Usuario ingresado, ya existe");
                     }
               }
               else{
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
        if (
            this.vista.txtUsuario.getText().length()!= 0 &&
            this.vista.txtContraseña.getText().length()!= 0 &&
            this.vista.txtEmail.getText().length()!= 0
            ){
            resultado = true;
        }
        return resultado;
    }
    
}
