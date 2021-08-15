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
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Cliente {
    private int id;
    private String nombre;
    private String dni;
    private String numeroDeCelular;
    private String correo;

    public Cliente(String nombre, String dni, String numeroDeCelular, String correo) {
        setId();
        this.nombre = nombre;
        this.dni = dni;
        this.numeroDeCelular = numeroDeCelular;
        this.correo = correo;
    }
    
    public Cliente(int id,String nombre, String dni, String numeroDeCelular, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.numeroDeCelular = numeroDeCelular;
        this.correo = correo;
    }

    public Cliente(int id) {
        this.id = id;
    }
    public Cliente(int id, String nrocelular) {
        this.id = id;
        this.numeroDeCelular = nrocelular;
    }
    
    public void setId() {
        Conexion conexion = new Conexion();
        String SQL = "select MAX(idCliente) as idCliente FROM cliente";
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
        String SQL = "insert into cliente (idcliente,nombre,dni,nroCelular,correo) values ('"+this.id+"','"+this.nombre+"','"+this.dni+"','"+this.numeroDeCelular+"','"+this.correo+"')";
        return conexion.ejecutar(SQL);
    }
    
    public boolean consultar() {
        Conexion conexion = new Conexion();
        String SQL = "select * from cliente where idcliente='"+this.id+"'";
        ResultSet resultado = conexion.consultar(SQL);
        int fk_cliente;
        try {
            if (resultado.next()){ 
            this.nombre=resultado.getString("nombre");
            this.dni=resultado.getString("dni");
            this.numeroDeCelular=resultado.getString("nroCelular");
            this.correo=resultado.getString("correo");
            return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public void updateNroCell(){
        Conexion conectar = new Conexion();
        String SQL = "{call updated_cliente_nrocelular(?,?)}";
        CallableStatement stmt = null;
        
        try(Connection conn = conectar.conectarMySQL()){
            stmt = conn.prepareCall(SQL);
            stmt.setInt(1,this.id);
            stmt.setString(2, this.numeroDeCelular);
            ResultSet rs = stmt.executeQuery();
            
            stmt.executeUpdate();
        
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getNumeroDeCelular() {
        return numeroDeCelular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNumeroDeCelular(String numeroDeCelular) {
        this.numeroDeCelular = numeroDeCelular;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
