import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;

public class AccountController {
    private Stage stage;
    private AccountView accountView;
    private DepositView depositView;
    private Customer customer;
    private List<String> history = new ArrayList<>();
    private int nextAccNum = 1001;

    public AccountController(Stage stage, AccountView accountView, DepositView depositView, Customer customer) {
        this.stage = stage;
        this.accountView = accountView;
        this.depositView = depositView;
        this.customer = customer;

        setupButtons();
    }

    private void setupButtons() {
        accountView.getOpenButton().setOnAction(e -> openAccount());
        accountView.getTxScreenButton().setOnAction(e -> {
            stage.setScene(depositView.getScene());
            setupButtons();
        });
        accountView.getInterestButton().setOnAction(e -> payInterest());
        accountView.getHistoryButton().setOnAction(e -> showHistory());

        depositView.getDepositButton().setOnAction(e -> makeDeposit());
        depositView.getWithdrawButton().setOnAction(e -> makeWithdrawal());
        depositView.getBackButton().setOnAction(e -> {
            stage.setScene(accountView.getScene());
            setupButtons();
            showAccounts();
        });
    }

    private void openAccount() {
        try {
            String type = accountView.getAccountType();
            double amount = Double.parseDouble(accountView.getInitialDeposit());
            String branch = accountView.getBranch();
            String accNum = "ACC" + nextAccNum++;

            Account acc = null;
            if (type.equals("Savings")) {
                acc = new SavingsAccount(accNum, amount, branch, customer);
            } else if (type.equals("Investment")) {
                acc = new InvestmentAccount(accNum, amount, branch, customer);
            } else if (type.equals("Cheque")) {
                String comp = accountView.getCompanyName();
                String addr = accountView.getCompanyAddress();
                acc = new ChequeAccount(accNum, amount, branch, customer, comp, addr);
            }

            if (acc != null) {
                customer.addAccount(acc);
                history.add("Opened " + type + " (" + accNum + ") - BWP " + amount);
                showAccounts();
            }
        } catch (Exception ex) {
            accountView.setOutput("Error: " + ex.getMessage());
        }
    }

    private void makeDeposit() {
        Account acc = findAccount(depositView.getAccountNumber());
        if (acc == null) {
            depositView.setMessage("Account not found.");
            return;
        }
        try {
            double amount = Double.parseDouble(depositView.getAmount());
            acc.deposit(amount);
            history.add("Deposit BWP " + amount + " to " + acc.getAccountNumber());
            depositView.setMessage("Deposited! Balance: BWP " + acc.getBalance());
        } catch (Exception ex) {
            depositView.setMessage("Enter a valid number.");
        }
    }

    private void makeWithdrawal() {
        Account acc = findAccount(depositView.getAccountNumber());
        if (acc == null) {
            depositView.setMessage("Account not found.");
            return;
        }
        try {
            double amount = Double.parseDouble(depositView.getAmount());
            if (acc.withdraw(amount)) {
                history.add("Withdrew BWP " + amount + " from " + acc.getAccountNumber());
                depositView.setMessage("Withdrawn! Balance: BWP " + acc.getBalance());
            } else {
                depositView.setMessage("Withdrawal not allowed or low balance.");
            }
        } catch (Exception ex) {
            depositView.setMessage("Enter a valid number.");
        }
    }

    private void payInterest() {
        for (Account acc : customer.getAccounts()) {
            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).applyMonthlyInterest();
                history.add("Paid 0.05% interest to " + acc.getAccountNumber());
            } else if (acc instanceof InvestmentAccount) {
                ((InvestmentAccount) acc).applyMonthlyInterest();
                history.add("Paid 5% interest to " + acc.getAccountNumber());
            }
        }
        showAccounts();
    }

    private void showHistory() {
        String text = "Transaction History:\n";
        for (String item : history) {
            text += item + "\n";
        }
        accountView.setOutput(text);
    }

    private void showAccounts() {
        String text = "Customer: " + customer.getFirstName() + " " + customer.getSurname() + "\n";
        for (Account acc : customer.getAccounts()) {
            text += acc.getAccountNumber() + " - Balance: BWP " + acc.getBalance() + "\n";
        }
        accountView.setOutput(text);
    }

    private Account findAccount(String accNum) {
        for (Account acc : customer.getAccounts()) {
            if (acc.getAccountNumber().equalsIgnoreCase(accNum)) {
                return acc;
            }
        }
        return null;
    }
}