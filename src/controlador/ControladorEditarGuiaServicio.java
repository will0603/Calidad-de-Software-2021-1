/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.GuiadeServicio;
import vista.frmEditarGuiadeServicio;

/**
 *
 * @author LENOVO
 */
public class ControladorEditarGuiaServicio {
    private frmEditarGuiadeServicio vista;
    
    public ControladorEditarGuiaServicio(frmEditarGuiadeServicio vista){
        this.vista = vista;
        
        vista.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(validar()){
                    GuiadeServicio guia = new GuiadeServicio(Integer.parseInt(vista.txtNumeroGuia.getText()));
                    
                }else{
                    JOptionPane.showMessageDialog(vista, "Ingrese el numero de guia correcto");
                }
            
            
            }
        });
        
        vista.btnvolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                vista.dispose();
            
            }
        });
    }
    public void iniciar(){
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
    
    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtNumeroGuia.getText().length()!= 0)   
        {
            resultado = true;
        }
        return resultado;
    }
}
