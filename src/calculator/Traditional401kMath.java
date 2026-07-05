package calculator;

public class Traditional401kMath extends Four01k {

    // Constructor
    public Traditional401kMath(double balance, int currentAge, int retirementAge, double returnRate, double salary, double contPercent,
            double salaryIncreasePercent, double employerMatchPercent, double salaryLimitMatchPercent) {
        super(balance, currentAge, retirementAge, returnRate, salary, contPercent, salaryIncreasePercent, employerMatchPercent, salaryLimitMatchPercent);
    }

    // Calculations
    public double calculateSalary() {
        return getSalary() * (1 + getSalaryIncreasePercent());
    }

    public double calculateEmployeeContribution() {
        return getSalary() * getContPercent();
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
        return balance = balance + (calculateEmployeeContribution() + calculateEmployerContribution())
                * (1 + getReturnRate());
    }
}
