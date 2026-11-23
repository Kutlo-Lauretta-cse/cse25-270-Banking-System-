import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView {
    private ComboBox<String> roleBox;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label statusLabel;

    public Scene createScene() {
        Label title = new Label("Banking System Login");

        roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Customer", "Bank Teller");
        roleBox.setValue("Customer");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        loginButton = new Button("Login");
        statusLabel = new Label();

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
            title,
            new Label("Select Role:"), roleBox,
            new Label("Username:"), usernameField,
            new Label("Password:"), passwordField,
            loginButton,
            statusLabel
        );

        return new Scene(root, 350, 320);
    }

    public String getRole() {
        return roleBox.getValue();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }
}