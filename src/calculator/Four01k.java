package calculator;


public abstract class Four01k extends RetirementAccount {
    // Instance fields
    private double salary;
    private double contPercent;
    private double salaryIncreasePercent;
    private double employerMatchPercent;
    private double salaryLimitMatchPercent;

    // Constructor
    public Four01k(double balance, int currentAge, int retirementAge, double returnRate, double salary, double contPercent,
            double salaryIncreasePercent, double employerMatchPercent, double salaryLimitMatchPercent) {
        super(balance, currentAge, retirementAge, returnRate);
        this.salary = salary;
        this.contPercent = contPercent;
        this.salaryIncreasePercent = salaryIncreasePercent;
        this.employerMatchPercent = employerMatchPercent;
        this.salaryLimitMatchPercent = salaryLimitMatchPercent;
    }

    // Getters
    public double getSalary() {
        return salary;
    }

    public double getContPercent() {
        return contPercent;
    }

    public double getSalaryIncreasePercent() {
        return salaryIncreasePercent;
    }

    public double getEmployerMatchPercent() {
        return employerMatchPercent;
    }

    public double getSalaryLimitMatchPercent() {
        return salaryLimitMatchPercent;
    }

    // Setters
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setContPercent(double contPercent) {
        this.contPercent = contPercent;
    }

    public void setSalaryIncreasePercent(double salaryIncreasePercent) {
        this.salaryIncreasePercent = salaryIncreasePercent;
    }

    public void setEmployerMatchPercent(double employerMatchPercent) {
        this.employerMatchPercent = employerMatchPercent;
    }

    public void setSalaryLimitMatchPercent(double salaryLimitMatchPercent) {
        this.salaryLimitMatchPercent = salaryLimitMatchPercent;
    }

    // abstract method
    public abstract double calculateFinalBalance();
}
