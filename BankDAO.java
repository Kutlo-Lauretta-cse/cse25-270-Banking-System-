import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {

    public void saveAccount(Account acc, String type, String companyName, String companyAddress) {
        String sql = "INSERT OR REPLACE INTO accounts VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, acc.getAccountNumber());
            ps.setString(2, type);
            ps.setDouble(3, acc.getBalance());
            ps.setString(4, acc.getBranch());
            ps.setString(5, acc.getCustomer().getCustomerId());
            ps.setString(6, companyName);
            ps.setString(7, companyAddress);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Save account error: " + e.getMessage());
        }
    }

    public void updateBalance(String accountNumber, double balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, balance);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update balance error: " + e.getMessage());
        }
    }

    public void saveTransaction(String accountNumber, String txType, double amount) {
        String sql = "INSERT INTO transactions (account_number, tx_type, amount) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            ps.setString(2, txType);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Save transaction error: " + e.getMessage());
        }
    }

    public List<String> getTransactions() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT account_number, tx_type, amount FROM transactions";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("account_number") + " | " +
                         rs.getString("tx_type") + " | BWP " +
                         rs.getDouble("amount"));
            }
        } catch (Exception e) {
            System.out.println("Read transactions error: " + e.getMessage());
        }
        return list;
    }
}