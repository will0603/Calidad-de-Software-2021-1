/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import excepciones.InvalidNombreException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Accesorio {
    //private int id;
    private String codigo;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private float precio;

    public Accesorio(String codigo, String nombre, int cantidad, String descripcion, float precio) {
        //setId();
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        
    }/*
    public Accesorio(int id) {
        this.id = id;
    }*/

    public Accesorio(String codigo) {
        this.codigo = codigo;
    }
    /*
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
    */
    public String insertar(){
        Conexion conexion = new Conexion();
        String SQL = "insert into producto (codigo,nombre,cantidad,precio_unitario,descripcion) values ('"+this.codigo+"','"+this.nombre+"','"+this.cantidad+"','"+this.precio+"','"+
                                                                                    this.descripcion+"')";
        return conexion.ejecutar(SQL);
    }
    /*
    public String insertar(){
        Conexion conexion = new Conexion();
        String SQL = "insert into producto (idproducto,nombre,codigo,cantidad,precio_unitario,descripcion) values ('"+this.id+"','"+this.nombre+"','"+this.codigo+"','"+this.cantidad+"',"+this.precio+","+
                                                                                    this.descripcion+")";
        return conexion.ejecutar(SQL);
    }*/
    
       public boolean consultar() {
        Conexion conexion = new Conexion();
        String SQL = "select * from producto where codigo='"+this.codigo+"'";
        ResultSet resultado = conexion.consultar(SQL);
        try {
            if (resultado.next()){ 
            this.nombre=resultado.getString("nombre");
            this.codigo=resultado.getString("codigo");
            this.cantidad=resultado.getInt("cantidad");
            this.precio=resultado.getFloat("precio_unitario");
            this.descripcion=resultado.getString("descripcion");
            return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
       
    public String[] setDatos(String[] data) throws InvalidNombreException{
        Conexion conexion = new Conexion();
        String SQL = "select *  FROM producto WHERE codigo='"+codigo+"'";
        //String[] datos = new String[6];
        try{
            ResultSet resultado = conexion.consultar(SQL);
            if(resultado.next()){
                //data[0]=resultado.getString(1);//id
                data[0]=resultado.getString(1);//codigo
                data[1]=resultado.getString(2);//nombre
                data[2]=resultado.getString(3);//cantidad
                data[3]=resultado.getString(4);//precio_unitario
                data[4]=resultado.getString(5);//descripcion
                
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
    
    public String[] getProdcuto(String[] data, String codigo){
        Conexion conectar = new Conexion();
        String SQL = "{call get_producto_by_codigo(?)}";
        CallableStatement stmt = null;
        
        try(Connection conn = conectar.conectarMySQL() ){
            //System.out.println("Creando sentencia...");
            stmt = conn.prepareCall(SQL);
            stmt.setString(1,codigo);
            ResultSet rs = stmt.executeQuery();
            //stmt.execute();
            
            if(rs.next()){
            data[0] = rs.getString("codigo");
            data[1] = rs.getString("nombre");
            data[2] = String.valueOf(rs.getInt("cantidad"));
            data[3] = String.valueOf(rs.getFloat("precio_unitario"));
            data[4] = rs.getString("descripcion");
            
            } else{
                JOptionPane.showMessageDialog(null, "El producto no existe");
            }//stmt.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public void insert2(){
        Conexion conectar = new Conexion();
        String SQL = "{call insert_producto(?,?,?,?,?)}";
        CallableStatement stmt = null;
        
        try(Connection conn = conectar.conectarMySQL()){
            stmt = conn.prepareCall(SQL);
            stmt.setString(1,this.codigo);
            stmt.setString(2,this.nombre);
            stmt.setInt(3,this.cantidad);
            stmt.setFloat(4,this.precio);
            stmt.setString(5,this.descripcion);
            stmt.executeUpdate();
            
        } catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ocurrió un problema con la Base de Datos");
        }
    }
    
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }
    
    
}
