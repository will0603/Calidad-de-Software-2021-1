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
public class GuiadeServicio {
    private int id;
    private float total;
    private String descripcionAdicional;
    private Celular celular;
    private Cliente cliente;

    public GuiadeServicio(float total, String descripcionAdicional, Celular celular, Cliente cliente) {
        setId();
        this.total = total;
        this.descripcionAdicional = descripcionAdicional;
        this.celular = celular;
        this.cliente = cliente;
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
    
    
    
}
