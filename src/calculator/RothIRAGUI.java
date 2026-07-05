package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Abhiraj Singh
 * Last Updated: 5/10/2026
 */

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RothIRAGUI extends JFrame {
    private JTextField balanceField, contributionField, currentAgeField, retirementAgeField, returnRateField, taxRateField;
    private JLabel finalBalanceLabel, contributionLabel;
    private XChartPanel<XYChart> chartPanel;
    private XYChart chart;
    private boolean isMaximized = false;

    public RothIRAGUI() {
        setTitle("Roth IRA Calculator (2026 Rules)");
        setSize(1000,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // start at center


        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        
        inputPanel.add(new JLabel("Starting Balance:")); 
        balanceField = new JTextField("5000"); 
        inputPanel.add(balanceField);

        inputPanel.add(new JLabel("Current Age:")); 
        currentAgeField = new JTextField("20"); 
        inputPanel.add(currentAgeField);

        inputPanel.add(new JLabel("Annual Contribution:")); 
        contributionField = new JTextField("5000"); 
        inputPanel.add(contributionField);

        inputPanel.add(new JLabel("Retirement Age:")); 
        retirementAgeField = new JTextField("65"); 
        inputPanel.add(retirementAgeField); 

        inputPanel.add(new JLabel("Expected Rate of Return (%):")); 
        returnRateField = new JTextField("7"); 
        inputPanel.add(returnRateField);

        inputPanel.add(new JLabel("Marginal Tax Rate (%):")); 
        taxRateField = new JTextField("25"); 
        inputPanel.add(taxRateField);

        inputPanel.add(new JLabel("Maximize Contribution:")); 
        
        JPanel radioWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JRadioButton yesBtn = new JRadioButton("Yes"); 
        JRadioButton noBtn = new JRadioButton("No");
        noBtn.setSelected(true); 

        ButtonGroup group = new ButtonGroup();
        group.add(yesBtn);
        group.add(noBtn);

        radioWrapper.add(yesBtn);
        radioWrapper.add(noBtn);
        inputPanel.add(radioWrapper);

        JButton calcBtn = new JButton("Calculate");
        JButton saveBtn = new JButton("Save Files");
        
        inputPanel.add(calcBtn); 
        inputPanel.add(saveBtn);
       
        finalBalanceLabel = new JLabel("Final Balance After Marginal Tax Deducted: $0.00", SwingConstants.CENTER);
        contributionLabel = new JLabel("Total Contributions: $0.00", SwingConstants.CENTER);
        inputPanel.add(finalBalanceLabel); 
        inputPanel.add(contributionLabel);
        
        add(inputPanel, BorderLayout.NORTH);
 
        chart = GraphForIRA.createIRAChart("Roth IRA Growth");
        chartPanel = new XChartPanel<>(chart);
        add(chartPanel, BorderLayout.CENTER);


        calcBtn.addActionListener(e -> calculate());

        yesBtn.addActionListener(e -> {
            try {
                int age = Integer.parseInt(currentAgeField.getText());
                contributionField.setText(age < 50 ? "7500" : "8600");
                isMaximized = true;
                calculate();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Valid age is required."); }
        });

        noBtn.addActionListener(e -> {
            isMaximized = false;
   
            calculate();
        });

        saveBtn.addActionListener(e -> FileIOSaver.saveReport(this, chart, finalBalanceLabel.getText() + "\n" + contributionLabel.getText()));
    }

    private void calculate() {
        try {
            if (Double.parseDouble(balanceField.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid balance.");
                return;
            }
            if (Integer.parseInt(currentAgeField.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid current age.");
                return;
            }
            if (Integer.parseInt(retirementAgeField.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid retirement age.");
                return;
            }
            if (Double.parseDouble(returnRateField.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid return rate.");
                return;
            }
            if (Double.parseDouble(contributionField.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid contribution amount.");
                return;
            }
            if (Double.parseDouble(taxRateField.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid tax rate.");
                return;
            }
            double bal = Double.parseDouble(balanceField.getText());
            int cur = Integer.parseInt(currentAgeField.getText());
            int ret = Integer.parseInt(retirementAgeField.getText());
            double rate = Double.parseDouble(returnRateField.getText());
            double con = Double.parseDouble(contributionField.getText());
            double tax = Double.parseDouble(taxRateField.getText());

            RothIRAMath math = new RothIRAMath(bal, cur, ret, rate, con, tax, isMaximized);
            finalBalanceLabel.setText("Final Balance After Marginal Tax Deducted: $" + String.format("%.2f", math.calculateFinalBalance(isMaximized)));
            contributionLabel.setText("Total Contributions: $" + String.format("%.2f", math.calculateTotalContributions(isMaximized)));

            List<Integer> ages = new ArrayList<>();
            List<Double> balances = new ArrayList<>();
            List<Double> totalCons = new ArrayList<>();
            double tempBal = bal;
            double tempCumulativeCon = 0;

            for (int age = cur; age <= ret; age++) {
                ages.add(age); 
                balances.add(tempBal); 
                totalCons.add(tempCumulativeCon);
                double yearlyCon = math.getYearlyContribution(age, isMaximized);
                tempCumulativeCon += yearlyCon;
                tempBal = (tempBal + yearlyCon) * (1 + rate / 100);
            }
            GraphForIRA.updateData(chart, ages, balances, totalCons);
            chartPanel.repaint();
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Please Enter Valid Numbers (0-9)"); }
    }

}