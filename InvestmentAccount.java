public class InvestmentAccount extends Account {
    private double interestRate = 0.05; // 5% monthly

    public InvestmentAccount(String accountNumber, double balance, String branch, Customer customer) {
        super(accountNumber, balance, branch, customer);
        if (balance < 500.00) {
            throw new IllegalArgumentException("Minimum initial deposit is 500.00");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    public void applyMonthlyInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
    }
}