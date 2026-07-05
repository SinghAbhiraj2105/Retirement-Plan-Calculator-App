package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Neel Debnath
 * Last Updated: 5/10/2026
 */

public class Roth401kMath extends Four01k {
    // Instance field
    private final double marginalTaxRate;

    // Constructor
    public Roth401kMath(double balance, int currentAge, int retirementAge, double returnRate, double salary,
            double contPercent,
            double salaryIncreasePercent, double employerMatchPercent, double salaryLimitMatchPercent,
            double marginalTaxRate) {
        super(balance, currentAge, retirementAge, returnRate, salary, contPercent, salaryIncreasePercent,
                employerMatchPercent, salaryLimitMatchPercent);
        this.marginalTaxRate = marginalTaxRate;
    }

    // Getter
    public double getMarginalTaxRate() {
        return marginalTaxRate;
    }

    // Calculations
    public double calculateSalary() {
        return getSalary() * (1 + getSalaryIncreasePercent());
    }

    public double calculateEmployeeContribution() {
        return getSalary() * getContPercent() * (1 - getMarginalTaxRate());
    }

    public double calculateMatchBase() {
        return Math.min(getSalary() * getSalaryLimitMatchPercent(), getSalary() * getContPercent());
    }

    public double calculateEmployerContribution() {
        return calculateMatchBase() * getEmployerMatchPercent();
    }

    // Abstract method implementation
    @Override
    public double calculateFinalBalance() {
        balance += (calculateEmployeeContribution() + calculateEmployerContribution())
                * (1 + getReturnRate());
        return balance;
    }
}
