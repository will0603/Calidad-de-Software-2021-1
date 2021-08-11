/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JOptionPane;

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
    //private String fecha;

    public float getTotal() {
        return total;
    }

    public String getDescripcionAdicional() {
        return descripcionAdicional;
    }
    

    public GuiadeServicio(float total, String descripcionAdicional, Celular celular, Cliente cliente) {
        setId();
        this.total = total;
        this.descripcionAdicional = descripcionAdicional;
        this.celular = celular;
        this.cliente = cliente;
        //this.fecha = fecha;
    }
    public GuiadeServicio(float total, String descripcionAdicional, Cliente cliente) {
        setId();
        this.total = total;
        this.descripcionAdicional = descripcionAdicional;
        //this.celular = celular;
        this.cliente = cliente;
        //this.fecha = fecha;
    }
    
    public GuiadeServicio(int id){
        this.id=id;
    }

    public GuiadeServicio() {
    }

    public int[] getDatosPie2D(){
        Conexion con = new Conexion();
        String SQL = "select * from guiaservicio";   //Buscar nick's que empiezen con "filtro"
        ResultSet resultado=con.consultar(SQL);
        int[] datos = new int[5];
        Arrays.fill(datos, 0);
        try{
            while(resultado.next()){
                int i = resultado.getInt("Celular_idCelular");
                Celular celular = new Celular(i);
                celular.consultar();
                if(celular.isConChip()){
                    datos[0]++;
                }if(celular.isConMicroSD()){
                    datos[1]++;
                }if(celular.isNoPrende()){
                    datos[2]++;
                }if(celular.isCaidaDeAgua()){
                    datos[3]++;
                }if(celular.isGarantía()){
                    datos[4]++;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return datos;
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
    
    public void BuscarGuiadeServicio(){
        Conexion conectar = new Conexion();
        
        String SQL = "{call get_guiaservicio_by_id(?)}";
        CallableStatement stmt = null;
        try(Connection conn = conectar.conectarMySQL() ){
            
            stmt = conn.prepareCall(SQL);
            stmt.setInt(1,this.id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                this.total = rs.getFloat("guiaservicio.total");
                this.descripcionAdicional = rs.getString("guiaservicio.descripAdicional");
            } else{
                JOptionPane.showMessageDialog(null, "El numero de guia de servicio no existe");
            }//stmt.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public Cliente BuscarGServicio(){
        Conexion conectar = new Conexion();
        Cliente client = null;
        String SQL = "{call get_guiaservicio_by_id(?)}";
        CallableStatement stmt = null;
        try(Connection conn = conectar.conectarMySQL() ){
            stmt = conn.prepareCall(SQL);
            stmt.setInt(1,this.id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
            client = new Cliente(rs.getString("cliente.nombre"), rs.getString("cliente.dni"), rs.getString("cliente.nrocelular"),rs.getString("cliente.correo"));
            } 
        }catch(Exception e){
            System.out.println(e);
        }
        return client;
    }
    
    public Celular BuscarGServicioC(){
        Conexion conectar = new Conexion();
        Celular cell = null;
        String SQL = "{call get_guiaservicio_by_id(?)}";
        CallableStatement stmt = null;
        try(Connection conn = conectar.conectarMySQL() ){
            stmt = conn.prepareCall(SQL);
            stmt.setInt(1,this.id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
            cell = new Celular(rs.getString("celular.marca"), rs.getString("celular.modelo"), rs.getString("celular.falla"), 
                        rs.getBoolean("celular.conChip"), rs.getBoolean("celular.conMicroSD"), rs.getBoolean("celular.caidaAgua"));
            } 
        }catch(Exception e){
            System.out.println(e);
        }
        return cell;
    }
    
    public int[] getMarcasPie2D(){
        Conexion con = new Conexion();
        String SQL = "select * from guiaservicio";   //Buscar nick's que empiezen con "filtro"
        ResultSet resultado=con.consultar(SQL);
        int[] datos = new int[4];
        Arrays.fill(datos, 0);
        try{
            while(resultado.next()){
                int i = resultado.getInt("Celular_idCelular");
                Celular celular = new Celular(i);
                celular.consultar();
                if(celular.getMarca().equalsIgnoreCase("HUAWEI")){
                    datos[0]++;
                }if(celular.getMarca().equalsIgnoreCase("LG")){
                    datos[1]++;
                }if(celular.getMarca().equalsIgnoreCase("MOTOROLA")){
                    datos[2]++;
                }if(celular.getMarca().equalsIgnoreCase("SAMSUNG")){
                    datos[3]++;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return datos;
    }
    
}
