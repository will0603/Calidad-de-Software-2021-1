/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelo.Celular;
import modelo.Cliente;
import modelo.GuiadeServicio;
import ordendetrabajo.frmMenu;
import vista.frmGuiadeServicio;

/**
 *
 * @author LENOVO
 */
public class ControladorGuiadeServicio {
    private frmGuiadeServicio vista;

    public ControladorGuiadeServicio(frmGuiadeServicio vista) {
        this.vista = vista;
        
        vista.btnvolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.dispose();
                //frmMenu vista1 = new frmMenu();
                //vista1.setLocationRelativeTo(null);
                //vista1.setVisible(true);
            }
        });
        
        vista.btnregistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(validar()){
                    
                    Cliente cliente = new Cliente(vista.txtnombre.getText(), vista.txtdni.getText(), vista.txtcelular.getText(), vista.txtcorreo.getText());
                    Celular celular = new Celular(vista.jComboBoxmarca.getSelectedItem().toString(), vista.txtmodelo.getText(), vista.txtfalladecelular.getText(), vista.jCheckBoxconCHIP.isSelected(), vista.jCheckBoxconMicroSD.isSelected(), vista.jCheckBoxnoprende.isSelected(), vista.jCheckBoxnoprende.isSelected(), vista.jCheckBoxcaidadeagua.isSelected());
                    GuiadeServicio guia = new GuiadeServicio(Float.parseFloat(vista.txttotal.getText()), vista.txadescripcion.getText(), 
                           celular, cliente);
                    System.out.println(cliente.insertar());
                    System.out.println(celular.insertar());
                    System.out.println(guia.insertar());
                    vista.dispose();
                    
                    //frmMenu frmmenu = new frmMenu();
                    //frmmenu.setLocationRelativeTo(null);
                    //frmmenu.setVisible(true);
                    
                    //JOptionPane.showMessageDialog(frmmenu, "La guia de servicio se registro, 100% ectivo.");
                }else{
                    JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Error" , JOptionPane.WARNING_MESSAGE );
                }
            }
        });
        
        vista.btnmostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(validar()){
                    GuiadeServicio guia = new GuiadeServicio(Float.parseFloat(vista.txttotal.getText()), vista.txadescripcion.getText(), 
                            new Celular(vista.jComboBoxmarca.getSelectedItem().toString(), vista.txtmodelo.getText(), vista.txtfalladecelular.getText(), vista.jCheckBoxconCHIP.isSelected(), vista.jCheckBoxconMicroSD.isSelected(), vista.jCheckBoxnoprende.isSelected(), vista.jCheckBoxnoprende.isSelected(), vista.jCheckBoxcaidadeagua.isSelected()), 
                            new Cliente(vista.txtnombre.getText(), vista.txtdni.getText(), vista.txtcelular.getText(), vista.txtcorreo.getText()
                            ));//,timeStamp);
                    guia.toString();
                }else{
                    JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Error" , JOptionPane.WARNING_MESSAGE );
                }
            }
        });
        
        
        
        vista.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
               int opcion = JOptionPane.showConfirmDialog(vista, "¿Desea salir de la app?",
                                "Sistema de Personal", 0, JOptionPane.OK_CANCEL_OPTION);
                if( opcion == JOptionPane.OK_OPTION){
                    vista.dispose();
                    frmMenu vista1 = new frmMenu();
                    vista1.setLocationRelativeTo(null);
                    vista1.setVisible(true);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
    }
    
    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtnombre.getText().length()!= 0 &&
                this.vista.txtnombre.getText().length()!= 0 &&
                this.vista.txtdni.getText().length()!= 0 &&
                this.vista.txtcelular.getText().length()!= 0 &&
                this.vista.txtcorreo.getText().length()!= 0 &&
                this.vista.txtfalladecelular.getText().length()!= 0 &&
                this.vista.txtmodelo.getText().length()!= 0 &&
                this.vista.txttotal.getText().length()!= 0 && 
                this.vista.jComboBoxmarca.getSelectedIndex() != -1)
        {
            resultado = true;
        }
        return resultado;
    }
}
