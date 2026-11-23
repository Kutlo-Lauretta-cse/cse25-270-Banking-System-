import javafx.stage.Stage;

public class LoginController {
    private Stage stage;
    private LoginView loginView;
    private AccountView accountView;

    public LoginController(Stage stage, LoginView loginView, AccountView accountView) {
        this.stage = stage;
        this.loginView = loginView;
        this.accountView = accountView;

        loginView.getLoginButton().setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        String user = loginView.getUsername();
        String pass = loginView.getPassword();
        String role = loginView.getRole();

        if (user.isEmpty() || pass.isEmpty()) {
            loginView.setMessage("Enter username and password.");
            return;
        }

        if ((user.equals("cust1") && pass.equals("1234") && role.equals("Customer")) ||
            (user.equals("teller1") && pass.equals("1234") && role.equals("Bank Teller"))) {
            stage.setScene(accountView.getScene());
        } else {
            loginView.setMessage("Wrong username or password.");
        }
    }
}