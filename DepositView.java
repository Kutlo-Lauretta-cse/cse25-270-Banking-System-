import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DepositView {
    private TextField accountNumberField;
    private TextField amountField;
    private Button depositButton;
    private Button withdrawButton;
    private Button backButton;
    private Label messageLabel;

    public Scene createScene() {
        Label title = new Label("Deposit / Withdraw");

        accountNumberField = new TextField();
        accountNumberField.setPromptText("Account Number");

        amountField = new TextField();
        amountField.setPromptText("Amount (BWP)");

        depositButton = new Button("Deposit");
        withdrawButton = new Button("Withdraw");
        backButton = new Button("Back");
        messageLabel = new Label();

        HBox buttons = new HBox(10, depositButton, withdrawButton, backButton);

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
            title,
            new Label("Account Number:"), accountNumberField,
            new Label("Amount:"), amountField,
            buttons,
            messageLabel
        );

        return new Scene(root, 380, 280);
    }

    public String getAccountNumber() {
        return accountNumberField.getText();
    }

    public String getAmount() {
        return amountField.getText();
    }

    public Button getDepositButton() {
        return depositButton;
    }

    public Button getWithdrawButton() {
        return withdrawButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}