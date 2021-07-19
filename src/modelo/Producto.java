/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import excepciones.InvalidNombreException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cjuro
 */
public class Producto {
    private int id;
    private String nombre;
   // private String codigo;
    private int cantidad;
    private float precio; //precio unitario

    public Producto(String nombre, int cantidad, float precio) throws InvalidNombreException {
        setId();
        setNombre(nombre);
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto() {
    }

    public Producto(int id) {
        this.id = id;
    }

    public Producto(int id, String nombre, int cantidad, float precio) throws InvalidNombreException {
        this.id = id;
        setNombre(nombre);
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public String insertar() {
        Conexion conexion = new Conexion();
        ProductoDAO productoDAO = new ProductoDAO(this.id,
        this.nombre, this.cantidad, this.precio);
        String result = conexion.ejecutar(productoDAO.insertar());
        setId();
        return result;
    }
    
    public String eliminar() {
        Conexion conexion = new Conexion();
        ProductoDAO productoDAO = new ProductoDAO(this.id);
        return conexion.ejecutar(productoDAO.eliminar());
    }
    
    public boolean consultar() {
        Conexion conexion = new Conexion();
        ProductoDAO productoDAO = new ProductoDAO(this.id);
        ResultSet resultado = conexion.consultar(
        productoDAO.consultar());
        try {
            if (resultado.next()){
            this.nombre=resultado.getString("nombre");
            this.cantidad=resultado.getInt("cantidad");
            this.precio=resultado.getLong("precio");
            return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public String actualizar() {
        Conexion conexion = new Conexion();
        ProductoDAO productoDAO = new ProductoDAO(this.id,
        this.nombre, this.cantidad, this.precio);
        return conexion.ejecutar(productoDAO.actualizar());
    }
    
    public String[][] buscar(String filtro){
        Conexion con = new Conexion();
        ProductoDAO productoDAO = new ProductoDAO();
        ResultSet resultado=con.consultar(productoDAO.buscar(filtro));
        String[][] datos = null;
        try{
            resultado.last();
            datos= new String[resultado.getRow()][4];
            resultado.beforeFirst();
            int i=0;
            while(resultado.next()){
                datos[i][0]= resultado.getString("id");
                datos[i][1]= resultado.getString("nombre");
                datos[i][2]= resultado.getString("nombre");
                datos[i][3]= resultado.getString("precio");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return datos;
    
    }

    public int getId() {
        return id;
    }

    private void setId() {
        Conexion conexion = new Conexion();
        String SQL = "select * from producto where nombre='"+this.nombre+"' and cantidad='"+this.cantidad+"' and precio='"+this.precio+"'";
        
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if(resultado.next()){
                this.id = resultado.getInt("id");
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void setNombre(String nombre) throws InvalidNombreException{
        Conexion conexion = new Conexion();
        String SQL = "select * from producto where nombre='"+nombre+"'";
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if(resultado.next()){
                System.out.println("Este producto ya existe");
                throw new InvalidNombreException(nombre);
            }else{
                this.nombre = nombre;
                System.out.println("Nuevo producto");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    
}
