/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Celular;
import modelo.Cliente;
import modelo.Conexion;
import modelo.GuiadeServicio;
import modelo.GuiadeVenta;
import ordendetrabajo.frmMenu;
import utils.Email;
import vista.frmBuscarGuiaServicio;

/**
 *
 * @author LENOVO
 */
public class ControladorBuscarServicio {
    private frmBuscarGuiaServicio vista;
    
    public ControladorBuscarServicio(frmBuscarGuiaServicio vista){
        this.vista = vista;
        
        vista.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(validar()){
                    String[] datosGuia = new String[13];
                    int guia = Integer.parseInt(vista.txtGuia.getText());
                    datosGuia(guia,datosGuia);
                    if(datosGuia.length != 0){
                        vista.lbidguia.setText(datosGuia[0]);
                        vista.lbTotal.setText(datosGuia[1]);
                        vista.lbDescripcion.setText(datosGuia[2]);
                        vista.lbNombre.setText(datosGuia[3]);
                        vista.lbDni.setText(datosGuia[4]);
                        vista.lbCelular.setText(datosGuia[5]);
                        vista.lbCorreo.setText(datosGuia[6]);
                        vista.lbMarca.setText(datosGuia[7]);
                        vista.lbModelo.setText(datosGuia[8]);
                        vista.lbFalla.setText(datosGuia[9]);
                        
                        vista.lbChip.setText(datosGuia[10]);
                        vista.lbMicroSD.setText(datosGuia[11]);
                        vista.lbCaidadeAgua.setText(datosGuia[12]);
                    
                    }
                    
                       
                    //String SQL = "select  *  FROM guiaventa WHERE idguiaventa = '"+guia+"'";
                    //ResultSet resultado=conexion.consultar(SQL);
                    //String[] datoGuia = new String[];
                    
                    
                } else{
                
                    JOptionPane.showConfirmDialog(vista,"Ingrese el numero de la guia");
                }
   
            }
        });
        
        vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                vista.dispose();
                frmMenu frmmenu = new frmMenu();
                frmmenu.setLocationRelativeTo(null);
                frmmenu.setVisible(true);
            
            }
        });
        
        vista.btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //Celular cell = new Celular(vista.lbMarca.getText(), vista.lbModelo.getText(), vista.lbFalla, vista.lbChip.getText(), vista.lbMicroSD, vista.lbCaidadeAgua.getText());
                Cliente cliente = new Cliente(vista.lbNombre.getText(), vista.lbDni.getText(), vista.lbCelular.getText(), vista.lbCorreo.getText());
                GuiadeServicio servicio = new GuiadeServicio(Float.parseFloat(vista.lbTotal.getText()),vista.lbDescripcion.getText(), cliente );
                
                String mensaje = "<div style='width: 400px; margin: 100px auto; background: blanchedalmond; text-align: center;'>"
                +"<div style=''>"
                +"</div>"
                +"<div>"
                +"</div>"
                +"<div>"
                +"</div>"        
                + "<h1>Detalles del Servicio</h1>"
                + "<p>"+new Date()+"</p>"
                + "<p>Numero de orden: "+vista.lbidguia.getText()+"</p>"
                + "<div style='border: 1px solid black;'>"
                + "<div style='display:flex; padding-left:20px;'>"
                + "<p>IGV </p>"
                //+ "<p style='margin-left:200px;'>"+venta.getIgv()+"</p>"
                + "</div>"
                + "<p style='text-align:left;padding-left:20px;'></p>"  //3 meses
                + "</div>"
                + "<div style='display:flex;border: 1px solid black;padding-left:20px'>"
                + "<p>Total</p>"
                + "<p style='margin-left:270px;'>"+vista.lbTotal.getText()+"</p>"
                + "</div>"
                + "<div>"
                + "<p>Metodo de pago</p>"
                + "<p>Efectivo)</p>"
                + "<p>Nombre de Cliente</p>"
                + "<p>"+vista.lbNombre.getText()+"</p>"
                + "</div>"
                + "<p style='font-size: 10px;'>Aceptas que, si no cancelas tu deuda antes que termine el<br>periodo de espera, se te debitará automáticamente un cargo mensual<br>"
                + "de S/. "+vista.lbTotal.getText()+" por su compra hasta que canceles tu deuda.<br>Aplican <a>Términos y condiciones</a>. Puedes cancelar tu suscripción de<br>"
                + "Spotify Premium en cualquier momento desde la <a>pagina</a> de tu cuenta<br>siguiendo estas <a>instrucciones</a></p>"
                + "<p style='padding: 10px 0;'>DevCell</p>"
                + "<p style='border-top: 1px solid black; border-bottom: 1px solid black;padding: 7px 0;'>Obtén DevCell para: iPhone | iPad | Android | Otro</p>"
                + "<p style='font-size: 10px;'>Este mensaje fue enviado a <a>"+vista.lbCorreo.getText()+"</a>. Si quieres hacernos alguna<br>pregunta o tienes algun reclamo, no dudes en contactarnos</p>"
                + "<p style='padding: 10px 0;'>Terminos de uso | Requerimientos tecnicos | Contactanos</p>"
                + "<p style='font-size: 10px;'>Direccion y contacto</p>"
                + "</div>";

                Email email =  new Email(vista.lbCorreo.getText(), "Nota de Venta", mensaje);

                Thread enviar = new Thread(email);
                enviar.start();
            }
        });

    }
    public void iniciar(){
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        
    }
    
    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtGuia.getText().length()!= 0)   
        {
            resultado = true;
        }
        return resultado;
    }
    
    public String[] datosGuia(int idguia, String[] datosguia){
        Conexion conectar = new Conexion();
        //String[] datosguia = new String[13];
        String SQL = "{call get_guiaservicio_by_id(?)}";
        CallableStatement stmt = null;
        try(Connection conn = conectar.conectarMySQL() ){
            //System.out.println("Creando sentencia...");
            stmt = conn.prepareCall(SQL);
            stmt.setInt(1,idguia);
            ResultSet rs = stmt.executeQuery();
            //stmt.execute();
            
            if(rs.next()){
            datosguia[0] = String.valueOf(rs.getInt("guiaservicio.idguiaServicio"));
            datosguia[1] = String.valueOf(rs.getFloat("guiaservicio.total"));
            datosguia[2] = rs.getString("guiaservicio.descripAdicional");
            datosguia[3] = rs.getString("cliente.nombre");
            datosguia[4] = String.valueOf(rs.getInt("cliente.dni"));
            datosguia[5] = String.valueOf(rs.getInt("cliente.nroCelular"));
            datosguia[6] = rs.getString("cliente.correo");
            datosguia[7] = rs.getString("celular.marca");
            datosguia[8] = rs.getString("celular.modelo");
            datosguia[9] = rs.getString("celular.falla");
            if(rs.getInt("celular.conChip") == 1){datosguia[10] = "Si";}
            else{ datosguia[10] = "No";  }
            if(rs.getInt("celular.conMicroSD") == 1){datosguia[11] = "Si";}
            else{ datosguia[11] = "No";  }
            if(rs.getInt("celular.caidaAgua") == 1){datosguia[12] = "Si";}
            else{ datosguia[12] = "No";  }
            //datosguia[11] = String.valueOf(rs.getInt("celular.conMicroSD"));
            //datosguia[12] = String.valueOf(rs.getInt("celular.caidaAgua"));
        } else{
                JOptionPane.showMessageDialog(vista, "El numero de guia de servicio no existe");
            }//stmt.close();
            
        }catch(Exception e){
            
            System.out.println(e);
        }
        
        return datosguia;
    }
}
