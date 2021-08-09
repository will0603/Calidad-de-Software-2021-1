/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class GuiadeServicio {
    private int id;
    private float total;
    private String descripcionAdicional;
    private Celular celular;
    private Cliente cliente;
    //private String fecha;
    

    public GuiadeServicio(float total, String descripcionAdicional, Celular celular, Cliente cliente) {
        setId();
        this.total = total;
        this.descripcionAdicional = descripcionAdicional;
        this.celular = celular;
        this.cliente = cliente;
        //this.fecha = fecha;
    }
    public GuiadeServicio(float total, String descripcionAdicional, Cliente cliente) {
        setId();
        this.total = total;
        this.descripcionAdicional = descripcionAdicional;
        //this.celular = celular;
        this.cliente = cliente;
        //this.fecha = fecha;
    }
    
    public GuiadeServicio(int id){
        this.id=id;
    }

    /*public GuiadeServicio(String nombre, String dni, String numeroDeCelular, String correo, Celular celular, float saldo, float aCuenta, float total, String descripcionAdicional) {
        setId();
        this.nombre = nombre;
        this.dni = dni;
        this.numeroDeCelular = numeroDeCelular;
        this.correo = correo;
        this.celular = celular;
        this.saldo = saldo;
        this.aCuenta = aCuenta;
        this.total = total;
        this.descripcionAdicional = descripcionAdicional;
        
        
    }
    */
    public void setId() {
        Conexion conexion = new Conexion();
        String SQL = "select MAX(idGuiaServicio) as idGuiaServicio FROM GuiaServicio";
        try{
            ResultSet resultado = conexion.consultar(SQL);
            if(resultado.next()){
                if(resultado.getInt(1)!= -1){
                    this.id=resultado.getInt(1)+1;
                }else{
                    this.id = 1;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public String insertar(){
        
        Conexion conexion = new Conexion();
        String SQL = "insert into guiaservicio (idguiaServicio,total,descripAdicional,Cliente_idCliente,Celular_idCelular) values ('"+this.id+"','"+this.total+"','"+this.descripcionAdicional+"','"+this.cliente.getId()+"','"+this.celular.getId()+"')";
        return conexion.ejecutar(SQL);
    }
    
    
    
    @Override
    public String toString() {
        return "GuiadeServicio{" + "id=" + id + ", total=" + total + ", descripcionAdicional=" + descripcionAdicional + ", celular=" + celular + ", cliente=" + cliente + '}';
    }
    
    public void getGuiadeServicio(){
        Conexion conectar = new Conexion();
        //String[] datosguia = new String[13];
        String SQL = "{call get_guiaservicio_by_id(?)}";
        CallableStatement stmt = null;
        try(Connection conn = conectar.conectarMySQL() ){
            //System.out.println("Creando sentencia...");
            stmt = conn.prepareCall(SQL);
            stmt.setInt(1,this.id);
            ResultSet rs = stmt.executeQuery();
            //stmt.execute();
            
            if(rs.next()){
                //datosguia[0] = String.valueOf(rs.getInt("guiaservicio.idguiaServicio"));
                this.total = rs.getFloat("guiaservicio.total");
                this.descripcionAdicional = rs.getString("guiaservicio.descripAdicional");
                //datosguia[3] = rs.getString("cliente.nombre");
                //datosguia[4] = String.valueOf(rs.getInt("cliente.dni"));
                //datosguia[5] = String.valueOf(rs.getInt("cliente.nroCelular"));
                //datosguia[6] = rs.getString("cliente.correo");
                //datosguia[7] = rs.getString("celular.marca");
                //datosguia[8] = rs.getString("celular.modelo");
                //datosguia[9] = rs.getString("celular.falla");
                /*
            if(rs.getInt("celular.conChip") == 1){datosguia[10] = "Si";}
            else{ datosguia[10] = "No";  }
            if(rs.getInt("celular.conMicroSD") == 1){datosguia[11] = "Si";}
            else{ datosguia[11] = "No";  }
            if(rs.getInt("celular.caidaAgua") == 1){datosguia[12] = "Si";}
            else{ datosguia[12] = "No";  }*/
            //datosguia[11] = String.valueOf(rs.getInt("celular.conMicroSD"));
            //datosguia[12] = String.valueOf(rs.getInt("celular.caidaAgua"));
        } else{
                JOptionPane.showMessageDialog(null, "El numero de guia de servicio no existe");
            }//stmt.close();
        }catch(Exception e){
            System.out.println(e);
        }
    
    
    }
    
    
}
