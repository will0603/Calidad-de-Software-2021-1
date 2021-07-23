/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import excepciones.InvalidNombreException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Accesorio {
    private int id;
    private String codigo;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private float precio;

    public Accesorio(String codigo, String nombre, int cantidad, String descripcion, float precio) {
        setId();
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        
    }
    public Accesorio(String codigo) {
        this.codigo = codigo;
    }
    
    public void setId() {
        Conexion conexion = new Conexion();
        String SQL = "select MAX(idproducto) as idProducto FROM producto";
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
    
    public int getId() {
        return id;
    }
    
    public String insertar(){
        Conexion conexion = new Conexion();
        String SQL = "insert into producto (idproducto,nombre,codigo,cantidad,precio_unitario,descripcion) values ('"+this.id+"','"+this.nombre+"','"+this.codigo+"','"+this.cantidad+"',"+this.precio+","+
                                                                                    this.descripcion+")";
        return conexion.ejecutar(SQL);
    }
    
    public String[] setDatos(String[] data) throws InvalidNombreException{
        Conexion conexion = new Conexion();
        String SQL = "select *  FROM producto WHERE codigo='"+codigo+"'";
        //String[] datos = new String[6];
        try{
            ResultSet resultado = conexion.consultar(SQL);
            if(resultado.next()){
                data[0]=resultado.getString(1);//id
                data[1]=resultado.getString(2);//nombre
                data[2]=resultado.getString(3);//codigo
                data[3]=resultado.getString(4);//cantidad
                data[4]=resultado.getString(5);//precio_unitario
                data[5]=resultado.getString(6);//descripcion
                
            }else{
                JOptionPane.showMessageDialog(null, "Este producto no existe");
                System.out.println("Este producto no existe");
                throw new InvalidNombreException(codigo);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return data;
    }
}