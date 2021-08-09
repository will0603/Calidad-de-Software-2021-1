/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author Cjuro
 */
import org.jfree.data.general.PieDataset;

public class frmReportEstadoRecepcion extends JFrame {
    
    
    public frmReportEstadoRecepcion(int[] datos) {
        initGUI();
        PieDataset dataSet = new DefaultPieDataset();
        dataSet = createDataSet(datos);
        JFreeChart chart = ChartFactory.createPieChart("Estado de recepcion de los celulares", dataSet, true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    private void initGUI() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());
    setTitle("Reporte estados de repecion");
    setSize(800, 600);
    }
    
    public PieDataset createDataSet(int[] datos) {
    DefaultPieDataset defaultDataSet =
    new DefaultPieDataset();
    defaultDataSet.setValue("Con Chip", datos[0]);
    defaultDataSet.setValue("Con microSD", datos[1]);
    defaultDataSet.setValue("No prende", datos[2]);
    defaultDataSet.setValue("Caida de agua", datos[3]);
    defaultDataSet.setValue("Garantia", datos[4]);
    return defaultDataSet;
    }
}