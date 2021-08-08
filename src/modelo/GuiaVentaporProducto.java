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
// tabla sola para ingresar 
public class GuiaVentaporProducto {
    private GuiadeVenta venta;
    private String accesorio;//codigo de producto
    private int cantidad; 
    private final String[] encabezado = {"ID","NOMBRE","PRECIO","CANTIDAD","DESCRIPCION"};

    public GuiaVentaporProducto(GuiadeVenta venta, String accesorio, int cantidad) {
        //setId();
        this.venta = venta;
        this.accesorio = accesorio;
        this.cantidad = cantidad;
    }

    public GuiaVentaporProducto(GuiadeVenta venta) {
        this.venta = venta;
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
    /*
    public boolean consultar() {
        Conexion conexion = new Conexion();
        String SQL = "select * from guiaventaporproducto where guiaventa_idguiaventa='"+this.venta.getId()+"'";
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
    */
     public String[][] getDatos(){
        Conexion con = new Conexion();
        String SQL = "select * from guiaventaporproducto where guiaventa_idguiaventa='"+this.venta.getId()+"'";   //Buscar nick's que empiezen con "filtro"
        ResultSet resultado=con.consultar(SQL);
        String[][] datos = null;
        try{
            resultado.last();
            datos= new String[resultado.getRow()][5];
            resultado.beforeFirst();
            int i=0;
            while(resultado.next()){
                datos[i][0]= resultado.getString("producto_codigo");
                Accesorio producto = new Accesorio(datos[i][0]);
                System.out.println(producto.consultar());
                datos[i][1]= producto.getNombre();
                datos[i][2]= String.valueOf(producto.getPrecio());
                datos[i][3]= resultado.getString("cantidad");
                datos[i][4]= String.valueOf(producto.getDescripcion());
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return datos;
    
    }

    public String[] getEncabezado() {
        return encabezado;
    }
     
}
