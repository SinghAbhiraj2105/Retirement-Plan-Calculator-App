package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Abhiraj Singh
 * Last Updated: 5/10/2026
 */

public class RothIRAMath extends IRA {
    private boolean isMaximized;

    public RothIRAMath(double balance, int currentAge, int retirementAge,
                       double returnRate, double annualContribution, 
                       double taxRate, boolean isMaximized) {
        super(balance, currentAge, retirementAge, returnRate, annualContribution,taxRate);
        this.taxRate = taxRate;
        this.isMaximized = isMaximized;
    }

    @Override
    public double calculateFinalBalance(boolean isMaximized) {
        double totalBalance = balance;
        for (int age = currentAge; age < retirementAge; age++) {
            double currentCon = getYearlyContribution(age, isMaximized);
            
           
            totalBalance = (totalBalance + currentCon) * (1 + returnRate / 100);
        }
        return totalBalance;
    }

    @Override
    public double calculateTotalContributions(boolean isMaximized) {
        return super.calculateTotalContributions(isMaximized);
    }

    public String getAccountType() { return "Roth IRA"; }
}