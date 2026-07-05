package calculator;
/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Abhiraj Singh
 * Last Updated: 5/10/2026
 */

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FileIOSaver {
    public static void saveReport(JFrame parent, XYChart chart, String reportText) {
        JFileChooser chooser = new JFileChooser();
        
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            
            try {
                BitmapEncoder.saveBitmap(chart, file.getAbsolutePath(), BitmapEncoder.BitmapFormat.PNG);
                
                JOptionPane.showMessageDialog(parent, "Graph is saved successfully!");
            } 
            catch (IOException e) {
                JOptionPane.showMessageDialog(parent, "Error in saving graph: " + e.getMessage());
            }
        }
    }
}