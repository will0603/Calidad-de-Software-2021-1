package controlador;

import excepciones.InvalidNombreException;
import general.Sistema;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import vista.frmIniciar;
import vista.frmPrincipal;

public class ControladorPrincipal {
    private frmPrincipal vista;
    
    public ControladorPrincipal(frmPrincipal vista) {
        this.vista = vista;
        
        this.vista.btnProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "tabla");
            }
        });
        
        this.vista.btnVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "tabla");
            }
        });
        
        this.vista.btnTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "tabla");
            }
        });
        
        this.vista.btnProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "panRegistro");
            }
        });
        
        this.vista.btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vista.txtNombreProducto.getText();
                int cantidad = Integer.parseInt(vista.txtCantidadProducto.getText());
                float precio = Float.parseFloat(vista.txtPrecioProducto.getText());
                try {
                    Producto product = new Producto(nombre, cantidad, precio);
                    product.insertar();
                } catch (InvalidNombreException ex) {
                    JOptionPane.showMessageDialog(vista, "Ese producto ya esta registrado");
                }
            }
        });
        
        this.vista.btnEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto product = new Producto(Integer.parseInt(vista.txtCodigoProducto.getText()));
                product.eliminar();
                vista.btnActualizarProducto.setEnabled(false);
                vista.btnEliminarProducto.setEnabled(false);
                vista.btnAgregarProducto.setEnabled(true);
            }
        });
        
        this.vista.btnActualizarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(vista.txtCodigoProducto.getText());
                String nombre = vista.txtNombreProducto.getText();
                int cantidad = Integer.parseInt(vista.txtCantidadProducto.getText());
                float precio = Float.parseFloat(vista.txtPrecioProducto.getText());
                try {
                    Producto product = new Producto(id, nombre, cantidad, precio);
                    product.actualizar();
                } catch (InvalidNombreException ex) {
                    JOptionPane.showMessageDialog(vista, "Ese producto ya esta registrado");
                }
                vista.btnActualizarProducto.setEnabled(false);
                vista.btnEliminarProducto.setEnabled(false);
                vista.btnAgregarProducto.setEnabled(true);
            }
        });
        
        this.vista.btnConsultarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto product = new Producto(Integer.parseInt(vista.txtCodigoProducto.getText()));
                product.consultar();
                vista.btnActualizarProducto.setEnabled(true);
                vista.btnEliminarProducto.setEnabled(true);
                vista.btnAgregarProducto.setEnabled(false);
                
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
        ((CardLayout)vista.panPrincipal.getLayout()).show(vista.panPrincipal, "panRegistro");
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
    /* 
    void Habilitar(){
        vista.btnActualizar.setEnabled(true);
        vista.btnEliminar.setEnabled(true);
        vista.btnGuardar.setEnabled(false);
    }
    
    void Deshabilitar(){
        vista.btnActualizar.setEnabled(false);
        vista.btnEliminar.setEnabled(false);
        vista.btnGuardar.setEnabled(true);
    }
    */
}
