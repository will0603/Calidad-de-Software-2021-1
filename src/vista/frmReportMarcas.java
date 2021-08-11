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

public class frmReportMarcas extends JFrame {
    
    
    public frmReportMarcas(int[] datos) {
        initGUI();
        PieDataset dataSet = new DefaultPieDataset();
        dataSet = createDataSet(datos);
        JFreeChart chart = ChartFactory.createPieChart("Marcas de celulares recibidos con fallas", dataSet, true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    private void initGUI() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());
    setTitle("Reporte de celulares recibidos con fallas, segun su marca");
    setSize(800, 600);
    }
    
    public PieDataset createDataSet(int[] datos) {
    DefaultPieDataset defaultDataSet =
    new DefaultPieDataset();
    defaultDataSet.setValue("HUAWEI", datos[0]);
    defaultDataSet.setValue("LG", datos[1]);
    defaultDataSet.setValue("MOTOROLA", datos[2]);
    defaultDataSet.setValue("SAMSUNG", datos[3]);
    return defaultDataSet;
    }
}