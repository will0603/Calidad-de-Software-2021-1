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
public class GuiaVentaporProducto {
    private GuiadeVenta venta;
    private String accesorio;
    private int cantidad;

    public GuiaVentaporProducto(GuiadeVenta venta, String accesorio, int cantidad) {
        //setId();
        this.venta = venta;
        this.accesorio = accesorio;
        this.cantidad = cantidad;
    }
    /*
    public void setId() {
        Conexion conexion = new Conexion();
        String SQL = "select MAX(idguiaventaporproducto) as idGuiaVentaporProducto FROM guiaventaporproducto";
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
    }*/
    
    public String insertar(){
        Conexion conexion = new Conexion();
        String SQL = "insert into guiaventaporproducto (guiaventa_idguiaventa,producto_codigo, cantidad) values ('"+this.venta.getId()+"','"+this.accesorio+"','"+this.cantidad+"')";
        return conexion.ejecutar(SQL);
    }
}
