
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Celular;
import modelo.Cliente;
import modelo.GuiadeServicio;
import vista.frmEditarGuiadeServicio;


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
                    Cliente client = guia.BuscarGServicio();
                    Celular cell = guia.BuscarGServicioC();
                    guia.BuscarGuiadeServicio();
                    if(cell != null){
                    //vista.txtNumeroGuia.setEditable(true);
                    vista.txtnombre.setText(client.getNombre());
                    vista.txtdni.setText(client.getDni());
                    vista.txtcelular.setText(client.getNumeroDeCelular());
                    vista.txtcorreo.setText(client.getCorreo());
                    vista.txtfalladecelular.setText(cell.getFalla());
                    if(cell.getMarca().equalsIgnoreCase("SAMSUNG")){
                        vista.jComboBoxmarca.setSelectedIndex(3);
                    }
                    if(cell.getMarca().equalsIgnoreCase("HUAWEI")){
                        vista.jComboBoxmarca.setSelectedIndex(0);
                    }
                    if(cell.getMarca().equalsIgnoreCase("LG")){
                        vista.jComboBoxmarca.setSelectedIndex(1);
                    }
                    if(cell.getMarca().equalsIgnoreCase("MOTOROLA")){
                        vista.jComboBoxmarca.setSelectedIndex(2);
                    }
                    vista.txtmodelo.setText(cell.getModelo());
                    vista.txttotal.setText(String.valueOf(guia.getTotal()));
                    vista.txadescripcion.setText(guia.getDescripcionAdicional());
                    vista.jCheckBoxconCHIP.setSelected(cell.isConChip());
                    vista.jCheckBoxconMicroSD.setSelected(cell.isConMicroSD());
                    vista.jCheckBoxcaidadeagua.setSelected(cell.isCaidaDeAgua());
                    //vista.jCheckBoxgarantia.setEnabled(false);
                    //vista.jCheckBoxnoprende.setEnabled(false);
                    Inhabilitar();
                    }
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
        
        vista.btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                limpiar();
            
            }
        });
        
        vista.btnregistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(verificar()){
                    GuiadeServicio guia = new GuiadeServicio(Integer.parseInt(vista.txtNumeroGuia.getText()), vista.txadescripcion.getText());
                    guia.updateDescripcion();
                    Cliente client = guia.BuscarGServicio();
                    
                    Cliente clientId = new Cliente(client.getId(),vista.txtcelular.getText());
                    clientId.updateNroCell();
                    
                    Celular cell = guia.BuscarGServicioC();
                    Celular cellId = new Celular(cell.getId(), vista.jCheckBoxconCHIP.isSelected(), vista.jCheckBoxconMicroSD.isSelected(), vista.jCheckBoxnoprende.isSelected(), vista.jCheckBoxcaidadeagua.isSelected(), vista.jCheckBoxgarantia.isSelected());
                    cellId.updateBooleans();
                    
                    JOptionPane.showMessageDialog(vista, "Actualización exitosa");
                    limpiar();
                }else{
                    System.out.println("Ingrese los campos que desea actualizar");
                }
            
            
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
    
    private boolean verificar() {
        boolean resultado = false;
        if (this.vista.txtfalladecelular.getText().length()!= 0 ||
                this.vista.txtcelular.getText().length() != 0)   
        {
            resultado = true;
        }
        return resultado;
    }
    
    public void limpiar(){
        vista.txtNumeroGuia.setEditable(true);
        vista.txtnombre.setText("");
        vista.txtdni.setText("");
        vista.txtcelular.setText("");
        vista.txtcorreo.setText("");
        vista.txtfalladecelular.setText("");
        vista.txtmodelo.setText("");
        vista.txttotal.setText("");
        vista.txadescripcion.setText("");
        vista.jCheckBoxconCHIP.setSelected(false);
        vista.jCheckBoxconMicroSD.setSelected(false);
        vista.jCheckBoxcaidadeagua.setSelected(false);
        vista.jCheckBoxgarantia.setSelected(false);
        vista.jCheckBoxnoprende.setSelected(false);
    }
    
    public void Inhabilitar(){
        vista.txtNumeroGuia.setEditable(false);
        vista.txtnombre.setEditable(false);
        vista.txtdni.setEditable(false);
        vista.txtmodelo.setEditable(false);
        vista.jComboBoxmarca.setEnabled(false);
        vista.txttotal.setEditable(false);
        vista.txtcorreo.setEditable(false);
        vista.txtfalladecelular.setEditable(false);
       
    }
}
