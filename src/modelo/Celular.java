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
 * @author Cjuro
 */
public class Celular {
    private int id;
    private String marca;
    private String modelo;
    private String falla;
    private boolean conChip;
    private boolean conMicroSD;
    private boolean noPrende;
    private boolean caidaDeAgua;
    private boolean garantía;
    //private String emailCliente;

    /*public Celular(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        //this.emailCliente = emailCliente;
    }*/

    public Celular(String marca, String modelo, String falla, boolean conChip, boolean conMicroSD, boolean noPrende, boolean caidaDeAgua, boolean garantía) {
        setId();
        this.marca = marca;
        this.modelo = modelo;
        this.falla = falla;
        this.conChip = conChip;
        this.conMicroSD = conMicroSD;
        this.noPrende = noPrende;
        this.caidaDeAgua = caidaDeAgua;
        this.garantía = garantía;
    }
    
    public Celular(String marca, String modelo, String falla, boolean conChip, boolean conMicroSD, boolean caidaDeAgua) {
        setId();
        this.marca = marca;
        this.modelo = modelo;
        this.falla = falla;
        this.conChip = conChip;
        this.conMicroSD = conMicroSD;
        this.caidaDeAgua = caidaDeAgua;
        
    }
    
    public Celular(String marca, String modelo, String falla, String Chip, String MicroSd, String CaidadeAgua){
        
    }

    public Celular(int id) {
        this.id = id;
    }
    
    public String insertar(){
        Conexion conexion = new Conexion();
        String SQL = "insert into celular (idcelular,marca,modelo,falla,conChip,conMicroSD,noPrende,caidaAgua,garantia) values ('"+this.id+"','"+this.marca+"','"+this.modelo+"','"+this.falla+"',"+this.conChip+","+
                                                                                    this.conMicroSD+","+this.noPrende+","+this.caidaDeAgua+","+this.garantía+")";
        return conexion.ejecutar(SQL);
    }
    
     public boolean consultar() {
        Conexion conexion = new Conexion();
        String SQL = "select * from celular where idcelular='"+this.id+"'";
        ResultSet resultado = conexion.consultar(SQL);
        try {
            if (resultado.next()){ 
            this.marca = resultado.getString("marca");
            this.modelo = resultado.getString("modelo");
            this.falla = resultado.getString("falla");
            this.conChip = resultado.getBoolean("conChip");
            this.conMicroSD = resultado.getBoolean("conMicroSD");
            this.noPrende = resultado.getBoolean("noPrende");
            this.caidaDeAgua = resultado.getBoolean("caidaAgua");
            this.garantía = resultado.getBoolean("garantia");
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
        String SQL = "select MAX(idCelular) as idCelular FROM celular";
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

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFalla() {
        return falla;
    }

    public boolean isConChip() {
        return conChip;
    }

    public boolean isConMicroSD() {
        return conMicroSD;
    }

    public boolean isNoPrende() {
        return noPrende;
    }

    public boolean isCaidaDeAgua() {
        return caidaDeAgua;
    }

    public boolean isGarantía() {
        return garantía;
    }
    
    
    
}
