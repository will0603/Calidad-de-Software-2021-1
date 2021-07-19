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
public class GuiadeVenta {
    private int id;
    private float total;
    private float igv;
    private Cliente cliente;

    public GuiadeVenta(float total, float igv, Cliente cliente) {
        setId();
        this.total = total;
        this.igv = igv;
        this.cliente = cliente;
    }

    public GuiadeVenta() {
        setId();
    }
    
    public String insertar(){
        Conexion conexion = new Conexion();
        String SQL = "insert into guiaventa (idguiaventa,total,igv,cliente_idcliente) values ('"+this.id+"','"+this.total+"','"+this.igv+"','"+this.cliente.getId()+"')";
        return conexion.ejecutar(SQL);
    }
    @Override
    public String toString() {
        return "GuiaVenta{" + "id=" + id + ", total=" + total + ", igv=" + igv + ", cliente=" + cliente + '}';
    }
    
    public void setId() {
        Conexion conexion = new Conexion();
        String SQL = "select MAX(idguiaventa) as idGuiaVenta FROM guiaventa";
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
}