package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Neel Debnath
 * Last Updated: 5/12/2026
 */

//imports
import javax.swing.*;
import org.knowm.xchart.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Roth401kGUI extends JFrame{
    //instance variables
    private final JLabel balanceLabel;
    private final JTextField balanceField;
    private final JLabel currentAgeLabel;
    private final JTextField currentAgeField;
    private final JLabel retirementAgeLabel;
    private final JTextField retirementAgeField;
    private final JLabel returnRateLabel;
    private final JTextField returnRateField;
    private final JLabel salaryLabel;
    private final JTextField salaryField;
    private final JLabel contPercentLabel;
    private final JTextField contPercentField;
    private final JLabel salaryIncreasePercentLabel;
    private final JTextField salaryIncreasePercentField;
    private final JLabel employerMatchPercentLabel;
    private final JTextField employerMatchPercentField;
    private final JLabel salaryLimitMatchLabel;
    private final JTextField salaryLimitMatchField;
    private final JLabel taxRateLabel;
    private final JTextField taxRateField;
    private final JButton calculateButton;
    private XChartPanel<XYChart> chartPanel;
    private final JButton saveButton;
    
    //constructor
    public Roth401kGUI() {
        this.balanceLabel = new JLabel("Balance (in dollars)");
        this.balanceField = new JTextField("25000");
        this.currentAgeLabel = new JLabel("Current Age (in years)");
        this.currentAgeField = new JTextField("25");
        this.retirementAgeLabel = new JLabel("Retirement Age (in years)");
        this.retirementAgeField = new JTextField("65");
        this.returnRateLabel = new JLabel("Return Rate (as a percentage in decimal form)");
        this.returnRateField = new JTextField("0.07");
        this.salaryLabel = new JLabel("Salary (in dollars)");
        this.salaryField = new JTextField("50000");
        this.contPercentLabel = new JLabel("Contribution Percent (as a percentage of salary in decimal form)");
        this.contPercentField = new JTextField("0.10");
        this.salaryIncreasePercentLabel = new JLabel("Salary Increase Percent (as a percentage in decimal form)");
        this.salaryIncreasePercentField = new JTextField("0.03");
        this.employerMatchPercentLabel = new JLabel("Employer Match Percent (as a percentage of salary in decimal form)");
        this.employerMatchPercentField = new JTextField("0.05");
        this.salaryLimitMatchLabel = new JLabel("Salary Limit Match (as a percentage of salary in decimal form)");
        this.salaryLimitMatchField = new JTextField("0.05");
        this.taxRateLabel = new JLabel("Tax Rate (as a percentage in decimal form)");
        this.taxRateField = new JTextField("0.25");
        this.calculateButton = new JButton("Calculate");
        this.saveButton = new JButton("Save");
        this.chartPanel = new XChartPanel<>(Graphfor401k.createChart("Roth 401k Calculator"));
        
        
        // Set up the frame layout
        this.setTitle("Roth 401k Calculator");
        this.setLayout(new BorderLayout());
        
        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(24,1,5,5));
        inputPanel.add(balanceLabel);
        inputPanel.add(balanceField);
        inputPanel.add(currentAgeLabel);
        inputPanel.add(currentAgeField);
        inputPanel.add(retirementAgeLabel);
        inputPanel.add(retirementAgeField);
        inputPanel.add(returnRateLabel);
        inputPanel.add(returnRateField);
        inputPanel.add(salaryLabel);
        inputPanel.add(salaryField);
        inputPanel.add(contPercentLabel);
        inputPanel.add(contPercentField);
        inputPanel.add(salaryIncreasePercentLabel);
        inputPanel.add(salaryIncreasePercentField);
        inputPanel.add(employerMatchPercentLabel);
        inputPanel.add(employerMatchPercentField);
        inputPanel.add(salaryLimitMatchLabel);
        inputPanel.add(salaryLimitMatchField);
        inputPanel.add(taxRateLabel);
        inputPanel.add(taxRateField);
        inputPanel.add(calculateButton);
        inputPanel.add(saveButton);
        
        // Add input panel to the left side
        this.add(inputPanel, BorderLayout.WEST);
        calculateButton.setBackground(Color.CYAN); //yq changed it to cyan
        calculateButton.setForeground(Color.BLACK);
        calculateButton.setHorizontalAlignment(JButton.CENTER);
        calculateButton.addActionListener(e -> {
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
                if (Double.parseDouble(salaryField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid salary.");
                    return;
                }
                if (Double.parseDouble(contPercentField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid contribution percentage.");
                    return;
                }
                if (Double.parseDouble(salaryIncreasePercentField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid salary increase percentage.");
                    return;
                }
                if (Double.parseDouble(employerMatchPercentField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid employer match percentage.");
                    return;
                }
                if (Double.parseDouble(salaryLimitMatchField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid salary limit for match.");
                    return;
                }
                if (Double.parseDouble(taxRateField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid tax rate.");
                    return;
                }
                
                Roth401kMath roth401kMath = new Roth401kMath(
                    Double.parseDouble(balanceField.getText()), 
                    Integer.parseInt(currentAgeField.getText()), 
                    Integer.parseInt(retirementAgeField.getText()), 
                    Double.parseDouble(returnRateField.getText()), 
                    Double.parseDouble(salaryField.getText()), 
                    Double.parseDouble(contPercentField.getText()), 
                    Double.parseDouble(salaryIncreasePercentField.getText()), 
                    Double.parseDouble(employerMatchPercentField.getText()), 
                    Double.parseDouble(salaryLimitMatchField.getText()),
                    Double.parseDouble(taxRateField.getText())
                );
                calculateAndDisplayChart(roth401kMath);
                // if the inputs are invalid, show an error message
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for all fields.");
            }

        });
        
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.BLACK);
        saveButton.setHorizontalAlignment(JButton.CENTER);
        saveButton.addActionListener(e -> {
            FileIOSaver.saveReport(this, chartPanel.getChart(), "");
        });

        // Set up the other half of the frame for the graph and save
        XYChart chart = Graphfor401k.createChart("Roth 401k Balance Projection");
        chartPanel = new XChartPanel<>(chart);
        this.add(chartPanel, BorderLayout.EAST);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size to the preferred size of the frame (allows for the best look for the Frame)
        this.setSize(this.getPreferredSize());
        this.setResizable(false);
        setLocationRelativeTo(null); // start at center

    }

    private void calculateAndDisplayChart(RetirementAccount account) {
        List<Integer> ages = new ArrayList<>();
        List<Double> savingsWithMatch = new ArrayList<>();
        List<Double> savingsWithoutMatch = new ArrayList<>();
        List<Double> employeeContributions = new ArrayList<>();
        
        int currentAge = account.currentAge;
        int retirementAge = account.retirementAge;
        double balanceWithMatch = account.balance;
        double balanceWithoutMatch = account.balance;
        double totalEmployeeContributions = 0;
        double totalEmployerContributions = 0;
        
        // Calculate projections year by year
        for (int age = currentAge; age <= retirementAge; age++) {
            ages.add(age);
            
            if (age == currentAge) {
                savingsWithMatch.add(balanceWithMatch);
                savingsWithoutMatch.add(balanceWithoutMatch);
                employeeContributions.add(totalEmployeeContributions);
            } else {
                double yearlyContribution = 0;
                double employerContribution = 0;
                
                Roth401kMath roth401kMath = (Roth401kMath) account;
                yearlyContribution = roth401kMath.calculateEmployeeContribution();
                employerContribution = roth401kMath.calculateEmployerContribution();
                roth401kMath.setSalary(roth401kMath.calculateSalary()); // Update salary for next year
                
                totalEmployeeContributions += yearlyContribution;
                totalEmployerContributions += employerContribution;
                balanceWithMatch = balanceWithMatch * (1 + account.getReturnRate()) + yearlyContribution + employerContribution;
                balanceWithoutMatch = balanceWithoutMatch * (1 + account.getReturnRate()) + yearlyContribution;
                
                savingsWithMatch.add(balanceWithMatch);
                savingsWithoutMatch.add(balanceWithoutMatch);
                employeeContributions.add(totalEmployeeContributions);
            }
        }
        
        // Create and update chart
        XYChart chart = Graphfor401k.createChart("Roth 401k Balance Projection");
        Graphfor401k.updateData(chart, ages, savingsWithMatch, savingsWithoutMatch, employeeContributions);

        JPanel summaryPanel = new JPanel();
        
        // Remove old chart panel and summary panel if they exist
        if (chartPanel != null) {
            this.remove(chartPanel);
        }
        if (summaryPanel != null) {
            this.remove(summaryPanel);
        }
        
        // Create summary panel
        summaryPanel = createSummaryPanel(totalEmployeeContributions, totalEmployerContributions, balanceWithMatch);
        
        // Create right panel with summary on top and chart below
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(summaryPanel, BorderLayout.NORTH);
        
        // Create new chart panel
        chartPanel = new XChartPanel<>(chart);
        topPanel.add(chartPanel, BorderLayout.CENTER);
        
        this.add(topPanel, BorderLayout.EAST);
        
        topPanel.revalidate();
        topPanel.repaint();
    }

    // create the summary panel for adding information
    private JPanel createSummaryPanel(double totalEmployeeContributions, double totalEmployerContributions, double finalBalance) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        
        panel.add(new JLabel("Total Employee Contributions (after taxes): " + String.format("$%,.2f", totalEmployeeContributions)));
        panel.add(new JLabel("Total Employer Contributions: " + String.format("$%,.2f", totalEmployerContributions)));
        panel.add(new JLabel("Estimated Retirement Balance: " + String.format("$%,.2f", finalBalance)));
        
        return panel;
    }


}
