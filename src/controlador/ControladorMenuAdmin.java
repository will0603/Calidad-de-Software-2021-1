/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Action;
import modelo.GuiadeServicio;
import vista.frmAñadirProducto;
import vista.frmEditarGuiaVenta;
import vista.frmEditarGuiadeServicio;
import vista.frmEditarProducto;
import vista.frmIniciar;
import vista.frmMantenimientoUsuario;
import vista.frmMenuAdmin;
import vista.frmReportEstadoRecepcion;
import vista.frmReportMarcas;

/**
 *
 * @author LENOVO
 */
public class ControladorMenuAdmin {
    private frmMenuAdmin vista;
    
    public ControladorMenuAdmin(frmMenuAdmin vista){
        this.vista = vista;
        
        vista.menuSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                vista.dispose();
                Sistema.usuario.salir();
                frmIniciar inicio = new frmIniciar();
                ControladorLoginInicio controlador = new ControladorLoginInicio(inicio);
                controlador.iniciar();        
            }
        });
        
        vista.menuAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmMantenimientoUsuario crear = new frmMantenimientoUsuario();
                crear.iniciar();
            }
        });
        
        vista.menuProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmAñadirProducto producto = new frmAñadirProducto();
                ControladorAñadirProducto controlador = new ControladorAñadirProducto(producto);
                controlador.iniciar();
            }
        });
        
        vista.menuPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmEditarProducto productoEdi = new frmEditarProducto();
                ControladorEditarProducto controlador =  new ControladorEditarProducto(productoEdi);
                controlador.iniciar();
            
            }
        });
        
        vista.menuGS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmEditarGuiadeServicio gs = new frmEditarGuiadeServicio();
                ControladorEditarGuiaServicio controlador = new ControladorEditarGuiaServicio(gs);
                controlador.iniciar();
            }
        });
        
        vista.menuGV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmEditarGuiaVenta gv = new frmEditarGuiaVenta();
                ControladorEditarGuiaVenta controlador = new ControladorEditarGuiaVenta(gv);
                controlador.iniciar();
            
            }
        });
        
        vista.menuEstadosRecepcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GuiadeServicio guia = new GuiadeServicio();
                int[] datos = guia.getDatosPie2D();
                System.out.println(Arrays.toString(datos));
                frmReportEstadoRecepcion torta = new frmReportEstadoRecepcion(datos);
                torta.setVisible(true);
                torta.setLocationRelativeTo(null);
            }
        });
        
        vista.menuMarcas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                GuiadeServicio guia = new GuiadeServicio();
                int[] datos = guia.getMarcasPie2D();
                System.out.println(Arrays.toString(datos));
                frmReportMarcas torta = new frmReportMarcas(datos);
                torta.setVisible(true);
                torta.setLocationRelativeTo(null);
            
            }
        });
        
    }
    
    public void iniciar(){
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    
    }
}
