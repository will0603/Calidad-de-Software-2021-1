 
package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import excepciones.InvalidEmailException;
import excepciones.InvalidLoginException;
import excepciones.InvalidPasswordException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import vista.frmRegistrar;
import modelo.UsuarioArreglo;
import modelo.Usuario;
import utils.Email;
import vista.frmIniciar;


public class ControladorLoginRegistrar {
    private frmRegistrar vista;
    private UsuarioArreglo modelo;

    public ControladorLoginRegistrar(UsuarioArreglo modelo, frmRegistrar vista) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               if(validar()){
            
                    Date fechaNac = new Date(Integer.parseInt(vista.txtAnho.getText())-1900, vista.cboMes.getSelectedIndex(), Integer.parseInt(vista.txtDia.getText()));
                    
                    try{
                    Usuario u = new Usuario(vista.txtNombres.getText(),vista.txtApellidos.getText(), vista.txtUsuario.getText(), vista.txtContraseña.getText(), 
                            vista.txtEmail.getText(),vista.cboPais.getSelectedItem().toString(), fechaNac, vista.cboGenero.getSelectedItem().toString());

                    Sistema.usuarios.add(u);
                    System.out.println(u);
                    vista.dispose();
                     frmIniciar fInicio = new frmIniciar();
                     ControladorLoginInicio controlador = new ControladorLoginInicio(Sistema.usuarios, fInicio);
                     controlador.iniciar();
                    }catch(InvalidEmailException e1){
                     System.out.println(e1);
                     JOptionPane.showMessageDialog(vista, "El Email ingresado ya esta registrado o no es valido");
                    }catch(InvalidPasswordException e2){
                     System.out.println(e2);
                     JOptionPane.showMessageDialog(vista, "La contraseña debe contener al menos 6 caracteres");
                    }catch(InvalidLoginException e3){
                     JOptionPane.showMessageDialog(vista, "El Nombre de Usuario ingresado, ya existe");
                     }
               }
               else{
                   JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Actualizar Persona" , JOptionPane.WARNING_MESSAGE );             
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
    
    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtDia.getText().length()!= 0 &&
                this.vista.txtAnho.getText().length()!= 0 &&
                this.vista.txtNombres.getText().length()!= 0 &&
                this.vista.txtApellidos.getText().length()!= 0 &&
                this.vista.txtContraseña.getText().length()!= 0 &&
                this.vista.txtEmail.getText().length()!= 0 &&
                this.vista.cboMes.getSelectedIndex() != -1 &&
                this.vista.cboPais.getSelectedIndex() != -1 &&
                this.vista.cboGenero.getSelectedIndex() != -1
                ){
            resultado = true;
        }
        return resultado;
    }
    
}
