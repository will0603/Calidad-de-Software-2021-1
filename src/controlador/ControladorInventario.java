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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Accesorio;
import modelo.Conexion;
import vista.frmInventario;

/**
 *
 * @author LENOVO
 */
public class ControladorInventario {
    private frmInventario vista;
    
    public ControladorInventario(frmInventario vista){
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
                vista.txtNombre.setEditable(false);
                //vista.txtNombre.setEnabled(false);
                //vista.txtDescripcion.setEnabled(false);
                vista.txtDescripcion.setEditable(false);
                vista.txtCantidad.setEditable(false);
                vista.txtPrecio.setEditable(false);
                
            }else{
                JOptionPane.showMessageDialog(null,"No existe el producto");
            }
            
        }
    });
    /*
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
    */
    vista.btnCancelar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            vista.dispose();
        }
    });
    
    vista.btnMostrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String[] cabecera = new String[5];
            cabecera[0] = "Código";
            cabecera[1] = "Nombre";
            cabecera[2] = "Descripción";
            cabecera[3] = "Cantidad";
            cabecera[4] = "Precio";
            String[][] datos = null;
            try {
                datos = getDatosProducto(datos);
                DefaultTableModel modelo= new DefaultTableModel(datos, cabecera);
                vista.tblProductos.setModel(modelo);
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorAñadirProducto.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    });
    
    vista.btnLimpiar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            limpiar();
        
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
    
    public String[][] getDatosProducto(String[][] datos) throws SQLException{
        Conexion con = new Conexion();
        String SQL = "{call get_producto}";   
        
        CallableStatement stmt = null;
        //String[][] datos = null;
        try(Connection conn = con.conectarMySQL() ){
           
            stmt = conn.prepareCall(SQL);
            
            ResultSet rs = stmt.executeQuery();
            rs.last();
            datos= new String[rs.getRow()][5];
            rs.beforeFirst();
            int i=0;
            while(rs.next()){
                datos[i][0]= rs.getString("codigo");
                Accesorio producto = new Accesorio(datos[i][0]);
                System.out.println(producto.consultar());
                datos[i][1]= producto.getNombre();               
                datos[i][2]= String.valueOf(producto.getDescripcion());
                datos[i][3]= rs.getString("cantidad");
                datos[i][4]= String.valueOf(producto.getPrecio());
                i++;
            }
        }catch(Exception e){
            System.out.println(e);
        }

        return datos;
    
    }
}
