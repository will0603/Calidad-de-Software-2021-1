/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Cjuro
 */
public class ProductoDAO {
    private int id;
    private String nombre;
    private int cantidad;
    private float precio;

    public ProductoDAO(int id, String nombre, int cantidad, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public ProductoDAO(int id) {
        this.id = id;
    }

    ProductoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String insertar(){
    return "insert into producto (id,nombre,cantidad,precio) values ('"+this.id+"','"+this.nombre+"','"+this.cantidad+"','"+this.precio+"')";
    }
    
    public String consultar(){
    return "select * from producto where id='"+this.id+"'";
    }

    public String actualizar(){
        return "update producto set nombre='"+this.nombre
        +"', cantidad='"+this.cantidad+"', precio='"+this.precio
        +"' where id='"+this.id+"'";
    }
    
    public String buscar(String filtro){
        return "select * from producto where nombre like '"+ filtro+"%'";
    }
    
    public String eliminar(){
        return "delete from producto where id='"+this.id+"'";
    }
    
}
