/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Accesorio;
import vista.frmAñadirProducto;

/**
 *
 * @author LENOVO
 */
public class ControladorAñadirProducto {
    private frmAñadirProducto vista;
    
    public ControladorAñadirProducto(frmAñadirProducto vista){
    this.vista = vista;
    
    vista.btnGuardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(validar()){
                Accesorio producto = new Accesorio(vista.txtCodigo.getText(), vista.txtNombre.getText(), Integer.parseInt(vista.txtCantidad.getText()), 
                                                vista.txtDescripcion.getText(), Float.parseFloat(vista.txtPrecio.getText()));
                producto.insert2();
                
                JOptionPane.showMessageDialog(null, "Operación exitosa");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(vista, "Llene todos los campos");
            }
        }
    });
    
    vista.btnCancelar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            vista.dispose();
        }
    });
    
    
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    public boolean validar(){
        boolean result = false;
        
        if(this.vista.txtCodigo.getText().length() !=0 &&
            this.vista.txtNombre.getText().length() !=0 &&
            this.vista.txtCantidad.getText().length() !=0 &&
            this.vista.txtDescripcion.getText().length() !=0 &&
            this.vista.txtPrecio.getText().length() !=0 ){
        
                result = true;
                return result;
        }else{
            return result;
        }
    }
    
    public void limpiar(){
        vista.txtCantidad.setText("");
        vista.txtCodigo.setText("");
        vista.txtDescripcion.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
    }
}
