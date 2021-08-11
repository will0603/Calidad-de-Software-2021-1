/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import excepciones.InvalidNombreException;
import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import modelo.Accesorio;
import modelo.Cliente;
import modelo.Conexion;
import modelo.GuiaVentaporProducto;
import modelo.GuiadeVenta;
import ordendetrabajo.frmMenu;
import utils.Email;
import vista.frmGuiaVenta;

/**
 *
 * @author LENOVO
 */
public class ControladorGuiadeVenta {
    private frmGuiaVenta vista;
    
    public void iniciar(){
        vista.txtNumerodeGuia.setText(String.valueOf(new GuiadeVenta().getId()));
        vista.txtNumerodeGuia.setEnabled(false);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    public ControladorGuiadeVenta(frmGuiaVenta vista){
        this.vista = vista;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        vista.txtFechaActual.setText(timeStamp);
        
        vista.btnGuardar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae){
            
            if(validar()){
                Cliente cliente = new Cliente(vista.txtNombre.getText(), vista.txtDni.getText(), vista.txtCelular.getText(), vista.txtCorreo.getText());
                System.out.println(cliente.insertar());
                GuiadeVenta venta = new GuiadeVenta(Float.parseFloat(vista.txtTotal.getText()),Float.parseFloat(vista.txtIGV.getText()), cliente );
                System.out.println(venta.insertar());
                
                if(vista.tblLista.getRowCount() != 0){
                    for(int i=0; i < vista.tblLista.getRowCount(); i++){
                        String texto = vista.tblLista.getValueAt(i, 0).toString();
                        //int idproducto = Integer.parseInt(texto);
                        String texto2 = vista.tblLista.getValueAt(i, 3).toString();
                        int cant = Integer.parseInt(texto2);
                        GuiaVentaporProducto ventaporproducto = new GuiaVentaporProducto(venta,texto,cant);
                        System.out.println(ventaporproducto.insertar());
                    }
                
                }
                
                
                vista.dispose();
                frmMenu frmmenu = new frmMenu();
                frmmenu.setLocationRelativeTo(null);
                frmmenu.setVisible(true);
                
                
            
            }else{
                    JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Error" , JOptionPane.WARNING_MESSAGE );
            }
            
            
            
        }

        });
        
        vista.btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Accesorio acce = new Accesorio(vista.txtCodigo.getText());
                if(validarCodigo()){
                    
                    try {
                        String[] datos = new String[5]; 
                        acce.setDatos(datos);
                        int vacio = Integer.parseInt(datos[2]);
                        if(vacio != 0){
                        vista.txtNombreProducto.setText(datos[1]);
                        vista.txtCodigo.setText(datos[0]);
                        vista.txtCantidad.setText(datos[2]);
                        vista.txtDescripcion.setText(datos[4]);}
                        else{
                            JOptionPane.showMessageDialog(vista, "Producto no disponible");
                        }
                    } catch (InvalidNombreException ex) {
                        Logger.getLogger(ControladorGuiadeVenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }else{
                    JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Error" , JOptionPane.WARNING_MESSAGE );
            }
                /*
                if(validarCodigo()){
                    Conexion conexion = new Conexion();
                    String SQL = "select *  FROM producto WHERE codigo='"+vista.txtCodigo.getText()+"'";
                    String[] datos = new String[6];
                try{
                    ResultSet resultado = conexion.consultar(SQL);
                    if(resultado.next()){
                        datos[0]=resultado.getString(1);
                        datos[1]=resultado.getString(2);
                        datos[2]=resultado.getString(3);
                        datos[3]=resultado.getString(4);
                        datos[4]=resultado.getString(5);
                        datos[5]=resultado.getString(6);
                        vista.txtNombreProducto.setText(datos[1]);
                        vista.txtCodigo.setText(datos[2]);
                        vista.txtCantidad.setText(datos[3]);
                        vista.txtDescripcion.setText(datos[5]);
                
                }else{
                    JOptionPane.showMessageDialog(null, "Este producto no existe");
                    System.out.println("Este producto no existe");
                    throw new InvalidNombreException(vista.txtCodigo.getText());
                }
                }catch(SQLException e){
                    e.printStackTrace();
                }catch (InvalidNombreException ex) {
                        Logger.getLogger(ControladorGuiadeVenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else{
                    JOptionPane.showMessageDialog(vista, "Debe ingresar un codigo", "Error", JOptionPane.WARNING_MESSAGE);
                }*/
                
            }
            
            
        });
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Código");
        modelo.addColumn("Nombre del Producto");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("total");
                        //modelo.addColumn("Descripcion");
                        //modelo.addColumn("Condición");
                        //modelo.addColumn("Precio");
        vista.tblLista.setModel(modelo);
        
        vista.btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                if(validarProducto()){
                     
                    try {
                        Accesorio acce = new Accesorio(vista.txtCodigo.getText());
                        String[] datosProducto = new String[6];
                        String[] datosTabla = new String[6];
                        acce.setDatos(datosProducto);
                        datosTabla[0] = datosProducto[0];
                        datosTabla[1] = datosProducto[1];
                        datosTabla[2] = datosProducto[4];
                        datosTabla[4] = datosProducto[3];
                        datosTabla[3] = vista.txtCantidad.getText(); // cantidad
                        datosTabla[5] = Float.toString(subtotal(datosTabla[4], datosTabla[3])); //total
                        //acumularTotal(datosTabla[5]);
                        
                        //vista.txtTotal.setText(datosTabla[5]);
                        modelo.addRow(datosTabla);
                        totalizarIGV();
                        totalizar(vista.txtIGV.getText());
                    } catch (InvalidNombreException ex) {
                        JOptionPane.showMessageDialog(vista, "No existe el producto a añadir");
                        Logger.getLogger(ControladorGuiadeVenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                
            }
        });
        //float costoTotal = Float.parseFloat(vista.txtTotal.getText());
        
        
        
        vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                vista.dispose();
                /*
                frmMenu frmmenu = new frmMenu();
                frmmenu.setLocationRelativeTo(null);
                frmmenu.setVisible(true);
                */
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                DefaultTableModel dtm = (DefaultTableModel) vista.tblLista.getModel(); //TableProducto es el nombre de mi tabla ;)
                dtm.removeRow(vista.tblLista.getSelectedRow());
                //totalizarIGV();
                //totalizar(vista.txtIGV.getText());
                
            }
        });
        
        vista.btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cliente cliente = new Cliente(vista.txtNombre.getText(), vista.txtDni.getText(), vista.txtCelular.getText(), vista.txtCorreo.getText());
                GuiadeVenta venta = new GuiadeVenta(Float.parseFloat(vista.txtTotal.getText()),Float.parseFloat(vista.txtIGV.getText()), cliente );
        
                String mensaje = "<div style='width: 400px; margin: 100px auto; background: blanchedalmond; text-align: center;'>"
                + "<h1>Tu nota de venta de DevCell</h1>"
                + "<p>"+new Date()+"</p>"
                + "<p>Numero de orden: "+venta.getId()+"</p>"
                + "<div style='border: 1px solid black;'>"
                + "<div style='display:flex; padding-left:20px;'>"
                + "<p>IGV </p>"
                + "<p style='margin-left:200px;'>"+venta.getIgv()+"</p>"
                + "</div>"
                + "<p style='text-align:left;padding-left:20px;'></p>"  //3 meses
                + "</div>"
                + "<div style='display:flex;border: 1px solid black;padding-left:20px'>"
                + "<p>Total</p>"
                + "<p style='margin-left:270px;'>"+venta.getTotal()+"</p>"
                + "</div>"
                + "<div>"
                + "<p>Metodo de pago</p>"
                + "<p>Efectivo)</p>"
                + "<p>Nombre de Cliente</p>"
                + "<p>"+venta.getCliente().getNombre()+"</p>"
                + "</div>"
                + "<p style='font-size: 10px;'>Aceptas que, si no cancelas tu deuda antes que termine el<br>periodo de espera, se te debitará automáticamente un cargo mensual<br>"
                + "de S/. "+venta.getTotal()+" por su compra hasta que canceles tu deuda.<br>Aplican <a>Términos y condiciones</a>. Puedes cancelar tu suscripción de<br>"
                + "Spotify Premium en cualquier momento desde la <a>pagina</a> de tu cuenta<br>siguiendo estas <a>instrucciones</a></p>"
                + "<p style='padding: 10px 0;'>DevCell</p>"
                + "<p style='border-top: 1px solid black; border-bottom: 1px solid black;padding: 7px 0;'>Obtén DevCell para: iPhone | iPad | Android | Otro</p>"
                + "<p style='font-size: 10px;'>Este mensaje fue enviado a <a>"+venta.getCliente().getCorreo()+"</a>. Si quieres hacernos alguna<br>pregunta o tienes algun reclamo, no dudes en contactarnos</p>"
                + "<p style='padding: 10px 0;'>Terminos de uso | Requerimientos tecnicos | Contactanos</p>"
                + "<p style='font-size: 10px;'>Direccion y contacto</p>"
                + "</div>";

                Email email =  new Email(vista.txtCorreo.getText(), "Nota de Venta", mensaje);

                Thread enviar = new Thread(email);
                enviar.start();
                    
            }
        });
        
        
    }
    
    /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }*/
    
    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtNombre.getText().length()!= 0 &&
                this.vista.txtNombre.getText().length()!= 0 &&
                this.vista.txtDni.getText().length()!= 0 &&
                this.vista.txtCelular.getText().length()!= 0 &&
                this.vista.txtCorreo.getText().length()!= 0)
                //this.vista.txtfalladecelular.getText().length()!= 0 &&
                //this.vista.txtmodelo.getText().length()!= 0 &&
                //this.vista.txttotal.getText().length()!= 0 && 
                //this.vista.jComboBoxmarca.getSelectedIndex() != -1)
        {
            resultado = true;
        }
        return resultado;
    }
    private boolean validarCodigo(){
        boolean result = false;
        if(vista.txtCodigo.getText().length()!= 0){
            result = true;
            return result;
        } else {
            return result;
        }
            
    }
    private boolean validarProducto(){
        boolean resultPro = false;
        if(vista.txtCodigo.getText().length()!= 0 &&
                this.vista.txtNombreProducto.getText().length() != 0 &&
                this.vista.txtCantidad.getText().length() != 0 &&
                this.vista.txtDescripcion.getText().length() !=0)
        {
            resultPro = true;
            return resultPro;
        } else {
            return resultPro;
        }
    
    }
    private float subtotal(String dato1, String dato2){
        
        float total = 0;
        float precio = Float.parseFloat(dato1);
        int cantidad = Integer.parseInt(dato2);
        total = precio*cantidad;
        
        return total;
    }
    /*
    private float acumularTotal(String dato){
        float total=0;
        float subtotal = Float.parseFloat(dato);
        total = subtotal + total;
        return total;
    }*/
    private void totalizarIGV(){
        float t =0;
        float p =0;
        if(vista.tblLista.getRowCount() != 0){
            for(int i=0; i < vista.tblLista.getRowCount(); i++){
                p = Float.parseFloat(vista.tblLista.getValueAt(i, 5).toString());
                t = t + p;
            }
            t = (float) (t*0.18);
            vista.txtIGV.setText(Float.toString((float) t));
        }
        //return t;
    }
    
    private void totalizar(String dato){
        float t =0;
        float p =0;
        if(vista.tblLista.getRowCount() != 0){
            for(int i=0; i < vista.tblLista.getRowCount(); i++){
                p = Float.parseFloat(vista.tblLista.getValueAt(i, 5).toString());
                t = t + p;
                
            }
            t = t + Float.parseFloat(dato);
            vista.txtTotal.setText(Float.toString((float) t));
        }
        //return t;
    }
    
}
