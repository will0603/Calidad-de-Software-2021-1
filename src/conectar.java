/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public class conectar {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO = "root";
    private static String PASSWORD = "unmsm20211";
    private static String URL = "jdbc:mysql://35.226.79.115:3306/projectCS";
    
    static{
        try{
            Class.forName(DRIVER);
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERROR EN EL DRIVE: "+e);
        }
    }
    
    public Connection getConnetion(){
        
        Connection con=null;
        try{
            con=DriverManager.getConnection(URL,USUARIO,PASSWORD);
            JOptionPane.showMessageDialog(null,"Coneccion Exitosa");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en la coneccion"+e);
        }
        return con;
    }
    
}
