package calculator;

public abstract class IRA extends RetirementAccount {
    protected double annualContribution;
    protected double taxRate;

    protected IRA(double balance, int currentAge, int retirementAge, double returnRate, double annualContribution, double taxRate) {
        super(balance, currentAge, retirementAge, returnRate);
        this.annualContribution = annualContribution;
        this.taxRate=taxRate;
    }

    
    public double getYearlyContribution(int age, boolean isMaximized) {
        
        if (isMaximized) {
            
            
            if (age < 50) {
                return 7500.0; 
            } 
            
            else {
                return 8600.0; 
            }
            
        } else {
            
            return this.annualContribution;
        }
    }

    public abstract double calculateFinalBalance(boolean isMaximized);

    
    public double calculateTotalContributions(boolean isMaximized) {
        double total = 0;
        for (int age = currentAge; age < retirementAge; age++) {
            total += getYearlyContribution(age, isMaximized);
        }
        return total;
    }

    @Override
    public double calculateFinalBalance() {
        return calculateFinalBalance(false);
    }
}