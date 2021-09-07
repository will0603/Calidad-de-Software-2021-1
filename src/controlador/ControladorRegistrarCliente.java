/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Accesorio;
import modelo.Cliente;
import modelo.Conexion;
//import listas.ListaPacientes;
//import listas.NodoPaciente;
import vista.frmRegistrarCliente;

/**
 *
 * @author LENOVO
 */
public class ControladorRegistrarCliente {
    private frmRegistrarCliente vista;
    
    public ControladorRegistrarCliente (frmRegistrarCliente vista){
    this.vista = vista;
    //ListaPacientes lista = new ListaPacientes();
    
    DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("N°");
        modelo.addColumn("Dni");
        modelo.addColumn("Nombre");
        //modelo.addColumn("Apellidos");
        modelo.addColumn("Celular");
        modelo.addColumn("Correo");
                     
        vista.tblInfo.setModel(modelo);
        
        vista.btnBuscar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(vista.txtDni.getText().length() != 0){
                Conexion con = new Conexion();
                String SQL = "{call get_cliente_by_dni(?)}";   
                
                
                CallableStatement stmt = null;
                String[] datos = null;
            try(Connection conn = con.conectarMySQL() ){
           
                stmt = conn.prepareCall(SQL);
                stmt.setString(1,vista.txtDni.getText());
                
                ResultSet rs = stmt.executeQuery();
                
                if(rs.getRow() <= 1){
                    if(rs.next()){
                    vista.txtNombre.setText(rs.getString("nombre"));
                    vista.txtDni.setText(rs.getString("dni"));
                    vista.txtCelular.setText(rs.getString("nroCelular"));
                    vista.txtCorreo.setText(rs.getString("correo"));
                    }else{
                        JOptionPane.showMessageDialog(null, "El cliente no se encuentra registrado");
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "El cliente no puede ser mostrado");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
            }
        
        }
    });
        
        
        
        vista.btnAñadir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(Verificar()){
                if(check(vista.txtDni.getText())){
                    JOptionPane.showMessageDialog(vista, "El cliente ya se encuentra registrado");
                } else{
                    Conexion con = new Conexion();
                    String SQL = "{call insert_cliente(?,?,?,?)}";   
                
                
                    CallableStatement stmt = null;
                    //String[] datos = null;
                    try(Connection conn = con.conectarMySQL() ){
           
                        stmt = conn.prepareCall(SQL);
                        stmt.setString(1, vista.txtNombre.getText());
                        stmt.setString(2,vista.txtDni.getText());
                        stmt.setString(3, vista.txtCelular.getText());
                        stmt.setString(4, vista.txtCorreo.getText());
                        ResultSet rs = stmt.executeQuery();
                        JOptionPane.showMessageDialog(vista, "El cliente ha sido registrado con exito.");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            }
        
        }
    });
        vista.btnMostrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          
                Conexion con = new Conexion();
                String SQL = "{call get_cliente}";   
        
                CallableStatement stmt = null;
                String[] datos = null;
            try(Connection conn = con.conectarMySQL() ){
           
                stmt = conn.prepareCall(SQL);
            
                ResultSet rs = stmt.executeQuery();
                rs.last();
                datos= new String[5];
                rs.beforeFirst();
                //int i=0;
                while(rs.next()){
                    Cliente client = new Cliente(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("dni"), rs.getString("nroCelular"), rs.getString("correo"));
                    datos[0] = String.valueOf(client.getId());
                    datos[1] = client.getDni();
                    datos[2] = client.getNombre();
                    datos[3] = client.getNumeroDeCelular();
                    datos[4] = client.getCorreo();
                    modelo.addRow(datos);
                }
            }catch(Exception e){
                System.out.println(e);
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
        vista.btnLimpiar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            limpiar();
        
        }
    });
        /*
        vista.btnGuardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try{  
                lista.guardarLista();
                vista.dispose();
                JOptionPane.showMessageDialog(vista, "Operacion exitosa");
            }catch(Exception e){
                System.out.println(e);
            }   
        }
    });
    */
    }
    
    public void iniciar(){
    vista.setLocationRelativeTo(null);
    vista.setVisible(true);
    }
    
    public Boolean Verificar(){
        boolean resultado = false;
        
        if(vista.txtNombre.getText().length() !=0 &&
                //vista.txtApellidoP.getText().length() != 0 &&
                ///vista.txtApellidoM.getText().length() != 0 &&
              //  vista.txtEdad.getText().length() != 0 &&
                vista.txtCelular.getText().length() != 0 &&
              //  vista.txtDireccion.getText().length() != 0 &&
                vista.txtCorreo.getText().length() != 0
                ){
            
           resultado = true;
        }
        
        return resultado;
    }
    
    public void limpiar(){
        vista.txtNombre.setText("");
        //vista.txtApellidoP.setText("");
        //vista.txtApellidoM.setText("");
        vista.txtCelular.setText("");
        //vista.txtEdad.setText("");
        vista.txtCorreo.setText("");
       // vista.txtDireccion.setText("");
        //vista.jckFemenino.setSelected(false);
       // vista.jckMasculino.setSelected(false);
    }
    
    public boolean check(String dni){
        boolean result=false;
        Conexion con = new Conexion();
            String SQL = "{call get_cliente_by_dni(?)}";   
                
                
            CallableStatement stmt = null;
            //String[] datos = null;
            try(Connection conn = con.conectarMySQL() ){
           
                stmt = conn.prepareCall(SQL);
                stmt.setString(1,vista.txtDni.getText());
                
                ResultSet rs = stmt.executeQuery();
                
                if(rs.getRow() <= 1){
                    if(rs.next()){
                        if(dni.equalsIgnoreCase(rs.getString("dni"))){
                            result = true;
                        }
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
        return result;
    }
    
    
    
}
