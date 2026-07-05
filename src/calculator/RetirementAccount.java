package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Abhiraj Singh
 * Last Updated: 5/10/2026
 */

public abstract class RetirementAccount {

    protected double balance;
    protected int currentAge;
    protected int retirementAge;
    protected double returnRate;

    
    protected RetirementAccount(double balance,int currentAge,int retirementAge,double returnRate){

        this.balance = balance;
        this.currentAge = currentAge;
        this.retirementAge = retirementAge;
        this.returnRate = returnRate;
    }

    public abstract double calculateFinalBalance();

    //getters
    public double getBalance(){
        return balance;
    }

    public int getCurrentAge(){
        return currentAge;
    }

    public int getRetirementAge(){
        return retirementAge;
    }

    public double getReturnRate(){
        return returnRate;
    }

    //setters
    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setCurrentAge(int currentAge){
        this.currentAge = currentAge;
    }

    public void setRetirementAge(int retirementAge){
        this.retirementAge = retirementAge;
    }

    public void setReturnRate(double returnRate){
        this.returnRate = returnRate;
    }
}