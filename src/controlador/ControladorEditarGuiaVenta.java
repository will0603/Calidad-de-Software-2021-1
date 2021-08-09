/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.frmEditarGuiaVenta;

/**
 *
 * @author LENOVO
 */
public class ControladorEditarGuiaVenta {
    private frmEditarGuiaVenta vista;
    
    public ControladorEditarGuiaVenta(frmEditarGuiaVenta vista){
        this.vista = vista;
        
        vista.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(validar()){
                    int id = Integer.parseInt(vista.txtNumerodeGuia.getText());
                }else{
                    JOptionPane.showMessageDialog(vista, "Ingrese el numero de guia");
                }
            
            }
        });
        
        vista.btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
            }
        });
        
        vista.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        vista.btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                vista.dispose();
            }
        });
        
        vista.btnEnviar.setEnabled(false);
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    public boolean validar(){
        boolean resultado=false;
        
        if(vista.txtNumerodeGuia.getText().length() != 0){
            resultado = true;
            return resultado;
        }else{
            return resultado;
        }
    
    }
    
}
