/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*La siguiente clase muestra una forma de realizar la conexión con una
base de datos en motor MySql*/
public class Conexion {
    private Statement statement;
    private Connection connection;
    private String jdbc;
    private String ruta;
    private String usuario;
    private String contra;

    /*
        create database devcell;

        use devcell;

        CREATE TABLE `devcell`.`usuario` (
          `codigo` INT NOT NULL AUTO_INCREMENT,
          `username` VARCHAR(45) NOT NULL,
          `contraseña` VARCHAR(45) NOT NULL,
          `email` VARCHAR(45) NOT NULL,
          `conectado` TINYINT NOT NULL,
          PRIMARY KEY (`codigo`));
    */
    
    public Conexion(){
        this.connection = null;
        this.statement = null;
        this.jdbc = "com.mysql.jdbc.Driver";
        this.ruta = "jdbc:mysql://localhost/bbdddevcell";
        this.usuario ="root";
        this.contra ="";
    }
    
    /*El método abrirConexión permite que el atributo connection cree
una conexión con la base de datos y que el atributo statement cree un
objeto que permita enviar sentencias SQL a la base de datos.*/
    private void abrirConexion(){
        try {
            Class.forName(this.jdbc);
            this.connection = DriverManager.getConnection(this.ruta,this.usuario,this.contra);
            this.statement = this.connection.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    /*El método ejecutar permite enviar una sentencia SQL que recibe por
parámetro, con base en la conexión abierta por medio del método
excecuteUpdate. Esta sentencia debe ser necesariamente insert,
update o delete. Este método debe manejar la excepción SQLException,
la cual permite controlar casos de excepción*/
    public String ejecutar(String sentencia){
        try{
            this.abrirConexion();
            this.statement.executeUpdate(sentencia);
            return "Op Exitosa";
        }catch(SQLException e){
            return e.toString();
        }
    }
    
    
    /*El método consultar permite enviar una sentencia SQL que recibe por
parámetro, con base en la conexión abierta por medio del método
excecuteQuery. Esta sentencia debe ser necesariamente select, debido
a que retorna información a través de un objeto Resulset. Este método
debe manejar la excepción SQLException, la cual permite controlar
casos de excepción*/
    public ResultSet consultar(String sentencia){
        ResultSet resultado=null;
        try{
            this.abrirConexion();
            resultado = statement.executeQuery(sentencia);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }
    /*
    public CallableStatement llamar(String sentencia){
        ResultSet resultado=null;
        try{
            this.abrirConexion();
            resultado = statement.executeQuery(sentencia);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return (CallableStatement) resultado;
    }*/
    
   public Connection conectarMySQL(){
       Connection conn = null;
       try{
           Class.forName(jdbc);
           conn = DriverManager.getConnection(ruta, usuario, contra);
       }catch(ClassNotFoundException | SQLException e){
           
           e.printStackTrace();
       }
       
       
       return conn;
   }
}
