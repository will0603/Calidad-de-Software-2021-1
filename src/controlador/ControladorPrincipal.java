package controlador;

import general.Sistema;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.frmIniciar;
import vista.frmPrincipal;

public class ControladorPrincipal {
    private frmPrincipal vista;
    
    public ControladorPrincipal(frmPrincipal vista) {
        this.vista = vista;
        
        this.vista.btnVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "tabla");
              vista.lblTituloPestaña.setText("Vendedor");
            }
        });
        
        this.vista.btnTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "tabla");
              vista.lblTituloPestaña.setText("Tecnico");
            }
        });
        
        this.vista.btnAdministrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "imagenes");
             vista.lblTituloPestaña.setText("Admministrador");
            }
        });
        
        this.vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sistema.usuario.salir();
                vista.dispose();
                frmIniciar fInicio = new frmIniciar();
                ControladorLoginInicio controlador = new ControladorLoginInicio(fInicio);
                controlador.iniciar();
            }
        });
        
        this.vista.btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vista,Sistema.usuario);
            }
        });
        
       this.vista.rbtnAudioEpisodio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)vista.panMantenimientoCard.getLayout()).show(vista.panMantenimientoCard,"episodio");
                vista.rbtnAudioCancion.setSelected(false);
            }
        });
       
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "imagenes");
        vista.lblTituloPestaña.setText("Administrador");
        limpiarControles();
    }
    
     private void limpiarControles() {
        vista.txtNombre.setText("");
        vista.cboGenero.setSelectedIndex(-1);
        vista.txtNombreAlbum.setText("");
        vista.txtNombreArtista.setText("");
        vista.txtNombreAutor.setText("");
        vista.txtDescripcionPodcasts.setText("");
    }
     
}
