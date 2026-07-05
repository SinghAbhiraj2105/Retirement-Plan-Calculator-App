package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Abhiraj Singh
 * Last Updated: 5/10/2026
 */


public class TraditionalIRAMath extends IRA {
    private boolean isMaximized;

    public TraditionalIRAMath(double balance, int currentAge, int retirementAge, 
                              double returnRate, double annualContribution, double taxRate, boolean isMaximized) {
        super(balance, currentAge, retirementAge, returnRate, annualContribution, taxRate);
        this.isMaximized = isMaximized;
    }

    @Override
    public double calculateFinalBalance(boolean isMaximized) {
        double totalBalance = balance;
        for (int age = currentAge; age < retirementAge; age++) {

            totalBalance = totalBalance * (1 + returnRate / 100);
            
            double currentCon = getYearlyContribution(age, isMaximized);
            totalBalance += currentCon;
        }
        
        return totalBalance * (1 - taxRate / 100);
    }

    @Override
    public double calculateTotalContributions(boolean isMaximized) {
        double total = 0;
        for (int age = currentAge; age < retirementAge; age++) {
            total += getYearlyContribution(age, isMaximized);
        }
        return total;
    }
}