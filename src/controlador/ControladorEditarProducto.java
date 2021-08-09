/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.Accesorio;
import modelo.Conexion;
import vista.frmEditarProducto;

/**
 *
 * @author LENOVO
 */
public class ControladorEditarProducto {
    private frmEditarProducto vista;
    
    public ControladorEditarProducto(frmEditarProducto vista){
    this.vista = vista;
    
    vista.btnBuscar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(validar()){
                Accesorio producto = new Accesorio(vista.txtCodigo.getText());
                String[] datos = new String[5];
                producto.getProdcuto(datos, vista.txtCodigo.getText());
                vista.txtNombre.setText(datos[1]);
                vista.txtDescripcion.setText(datos[4]);
                vista.txtCantidad.setText(datos[2]);
                vista.txtPrecio.setText(datos[3]);
                vista.txtNombre.setEnabled(false);
                vista.txtDescripcion.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null,"No existe el producto");
            }
            
        }
    });
    
    vista.btnActualizar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(validar()){
                Conexion conectar = new Conexion();
                String SQL = "{call updated_producto(?,?,?)}";
                CallableStatement stmt = null;
                
                try(Connection conn = conectar.conectarMySQL() ){
            
                    stmt = conn.prepareCall(SQL);
                    stmt.setString(1,vista.txtCodigo.getText());
                    stmt.setInt(2,Integer.parseInt(vista.txtCantidad.getText()));
                    stmt.setFloat(3, Float.parseFloat(vista.txtPrecio.getText()));
                    //ResultSet rs = stmt.executeQuery();
                    stmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(vista, "Operacion exitosa");
                    limpiar();
                }catch(Exception e){
                    System.out.println(e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese los datos que desea actualizar");
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
        boolean resultado = false;
        
        if(this.vista.txtCodigo.getText().length() != 0){
            resultado = true;
            return resultado;
        }else{
            return resultado;
        }
        
    }
    
    public boolean validarT(){
        boolean resultado = false;
        
        if(this.vista.txtCodigo.getText().length() != 0 &&
                this.vista.txtCantidad.getText().length() != 0 &&
                this.vista.txtPrecio.getText().length() != 0){
            resultado = true;
            return resultado;
        }else{
            return resultado;
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
