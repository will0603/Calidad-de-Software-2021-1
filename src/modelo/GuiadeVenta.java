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

    public GuiadeVenta(int id) {
        this.id = id;
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
    
    public boolean consultar() {
        Conexion conexion = new Conexion();
        String SQL = "select * from guiaventa where idguiaventa='"+this.id+"'";
        ResultSet resultado = conexion.consultar(SQL);
        int fk_cliente;
        try {
            if (resultado.next()){ 
            this.total=resultado.getFloat("total");
            this.igv=resultado.getFloat("igv");
            fk_cliente = resultado.getInt("cliente_idcliente");
            this.cliente = new Cliente(fk_cliente);
            this.cliente.consultar();
            return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
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

    public float getTotal() {
        return total;
    }

    public float getIgv() {
        return igv;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    
}