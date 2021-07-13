/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public int getId() {
        return id;
    }
    
}
