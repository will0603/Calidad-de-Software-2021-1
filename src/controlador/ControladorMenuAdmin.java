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
import vista.frmBuscarGuiaServicio;
import vista.frmBuscarGuiaVentas;
import vista.frmEditarGuiaVenta;
import vista.frmEditarGuiadeServicio;
import vista.frmEditarProducto;
import vista.frmGuiaVenta;
import vista.frmGuiadeServicio;
import vista.frmIniciar;
import vista.frmInventario;
import vista.frmMantenimientoUsuario;
import vista.frmMenuAdmin;
import vista.frmRegistrarCliente;
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
        /*
        vista.menuGV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmEditarGuiaVenta gv = new frmEditarGuiaVenta();
                ControladorEditarGuiaVenta controlador = new ControladorEditarGuiaVenta(gv);
                controlador.iniciar();
            
            }
        });
        */
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
        
        vista.menuCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               frmRegistrarCliente client = new frmRegistrarCliente();
               ControladorRegistrarCliente control = new ControladorRegistrarCliente(client);
               control.iniciar();
            
            }
        });
        
        vista.menuServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               frmGuiadeServicio serv = new frmGuiadeServicio();
               ControladorGuiadeServicio control = new ControladorGuiadeServicio(serv);
               control.iniciar();
            }
        });
        
        vista.menuVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               frmGuiaVenta vent = new frmGuiaVenta();
               ControladorGuiadeVenta control = new ControladorGuiadeVenta(vent);
               control.iniciar();
            
            }
        });
        
        vista.menuBuscarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmBuscarGuiaServicio buscaS = new frmBuscarGuiaServicio();
                ControladorBuscarServicio control = new ControladorBuscarServicio(buscaS);
                control.iniciar();
            
            }
        });
        vista.menuBuscarV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmBuscarGuiaVentas buscaV = new frmBuscarGuiaVentas();
                buscaV.setLocationRelativeTo(null);
                buscaV.setVisible(true);
                //ControladorBuscarServicio control = new ControladorBuscarServicio(buscaS);
                //control.iniciar();
            
            }
        });    
        
        vista.menuInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                frmInventario invent = new frmInventario();
                ControladorInventario control = new ControladorInventario(invent);
                control.iniciar();
            
            }
        });
        
    }
    
    public void iniciar(){
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    
    }
}
