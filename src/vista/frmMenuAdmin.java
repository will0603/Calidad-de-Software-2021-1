/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorAñadirProducto;
import controlador.ControladorEditarProducto;

/**
 *
 * @author LENOVO
 */
public class frmMenuAdmin extends javax.swing.JFrame {

    /**
     * Creates new form frmMenuAdmin
     */
    public frmMenuAdmin() {
        initComponents();
    }
    
    public void iniciar(){
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuSalir = new javax.swing.JMenuItem();
        menuCrear = new javax.swing.JMenu();
        menuAddUser = new javax.swing.JMenuItem();
        menuAñadir = new javax.swing.JMenu();
        menuProducto = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenu();
        menuGS = new javax.swing.JMenuItem();
        menuGV = new javax.swing.JMenuItem();
        menuPro = new javax.swing.JMenuItem();
        menuArchivo1 = new javax.swing.JMenu();
        menuEstadosRecepcion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/WILLCELL.jpeg"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel1)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        menuArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/folder.png"))); // NOI18N
        menuArchivo.setText("Archivo");
        menuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArchivoActionPerformed(evt);
            }
        });

        menuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/exit.png"))); // NOI18N
        menuSalir.setText("Salir");
        menuArchivo.add(menuSalir);

        jMenuBar1.add(menuArchivo);

        menuCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/addemployee.png"))); // NOI18N
        menuCrear.setText("Crear");
        menuCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCrearActionPerformed(evt);
            }
        });

        menuAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/addemployee.png"))); // NOI18N
        menuAddUser.setText("Usuario");
        menuCrear.add(menuAddUser);

        jMenuBar1.add(menuCrear);

        menuAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/job.png"))); // NOI18N
        menuAñadir.setText("Añadir");

        menuProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/job.png"))); // NOI18N
        menuProducto.setText("Productos");
        menuProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductoActionPerformed(evt);
            }
        });
        menuAñadir.add(menuProducto);

        jMenuBar1.add(menuAñadir);

        menuEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/edit.png"))); // NOI18N
        menuEditar.setText("Editar");

        menuGS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/edit.png"))); // NOI18N
        menuGS.setText("Guia de Servicio");
        menuEditar.add(menuGS);

        menuGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/edit.png"))); // NOI18N
        menuGV.setText("Guia de Venta");
        menuEditar.add(menuGV);

        menuPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/edit.png"))); // NOI18N
        menuPro.setText("Productos");
        menuPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProActionPerformed(evt);
            }
        });
        menuEditar.add(menuPro);

        jMenuBar1.add(menuEditar);

        menuArchivo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/folder.png"))); // NOI18N
        menuArchivo1.setText("Reporte");
        menuArchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArchivo1ActionPerformed(evt);
            }
        });

        menuEstadosRecepcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ordendetrabajo/exit.png"))); // NOI18N
        menuEstadosRecepcion.setText("Estados de Recepcion");
        menuArchivo1.add(menuEstadosRecepcion);

        jMenuBar1.add(menuArchivo1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductoActionPerformed
        // TODO add your handling code here:
        //frmAñadirProducto producto = new frmAñadirProducto();
        //ControladorAñadirProducto controlador = new ControladorAñadirProducto(producto);
        //controlador.iniciar();        
    }//GEN-LAST:event_menuProductoActionPerformed

    private void menuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArchivoActionPerformed
        // TODO add your handling code here:
        //dispose();
        //frmIniciar inicio = new frmIniciar();
        //inicio.setVisible(true);
        //inicio.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuArchivoActionPerformed

    private void menuCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCrearActionPerformed
        // TODO add your handling code here:
        //frmMantenimientoUsuario crear = new frmMantenimientoUsuario();
        //crear.iniciar();
    }//GEN-LAST:event_menuCrearActionPerformed

    private void menuProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProActionPerformed
        // TODO add your handling code here:
        //frmEditarProducto productoEdi = new frmEditarProducto();
        //ControladorEditarProducto controlador =  new ControladorEditarProducto(productoEdi);
        //controlador.iniciar();
    }//GEN-LAST:event_menuProActionPerformed

    private void menuArchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArchivo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuArchivo1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JMenuItem menuAddUser;
    public javax.swing.JMenu menuArchivo;
    public javax.swing.JMenu menuArchivo1;
    public javax.swing.JMenu menuAñadir;
    public javax.swing.JMenu menuCrear;
    public javax.swing.JMenu menuEditar;
    public javax.swing.JMenuItem menuEstadosRecepcion;
    public javax.swing.JMenuItem menuGS;
    public javax.swing.JMenuItem menuGV;
    public javax.swing.JMenuItem menuPro;
    public javax.swing.JMenuItem menuProducto;
    public javax.swing.JMenuItem menuSalir;
    // End of variables declaration//GEN-END:variables
}
