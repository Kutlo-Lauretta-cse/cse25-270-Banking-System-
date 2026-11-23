public class SavingsAccount extends Account {
    private double interestRate = 0.0005; 

    public SavingsAccount(String accountNumber, double balance, String branch, Customer customer) {
        super(accountNumber, balance, branch, customer);
    }

    @Override
    public boolean withdraw(double amount) {
        return false; 
    }

    public void applyMonthlyInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
    }
}