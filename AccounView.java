import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AccountView {
    private ComboBox<String> accountTypeBox;
    private TextField initialDepositField;
    private TextField branchField;
    private TextField companyNameField;
    private TextField companyAddressField;
    private Button openAccountButton;
    private Button transactionButton;
    private Button payInterestButton;
    private Button historyButton;
    private TextArea outputArea;

    public Scene createScene() {
        Label title = new Label("Account Dashboard");

        accountTypeBox = new ComboBox<>();
        accountTypeBox.getItems().addAll("Savings", "Investment", "Cheque");
        accountTypeBox.setValue("Savings");

        initialDepositField = new TextField();
        initialDepositField.setPromptText("Initial Deposit (BWP)");

        branchField = new TextField();
        branchField.setPromptText("Branch");

        companyNameField = new TextField();
        companyNameField.setPromptText("Company Name (Cheque only)");

        companyAddressField = new TextField();
        companyAddressField.setPromptText("Company Address (Cheque only)");

        openAccountButton = new Button("Open Account");
        transactionButton = new Button("Deposit / Withdraw");
        payInterestButton = new Button("Pay Monthly Interest");
        historyButton = new Button("View History");

        HBox buttons = new HBox(10, openAccountButton, transactionButton, payInterestButton, historyButton);

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(150);

        VBox root = new VBox(8);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
            title,
            new Label("Account Type:"), accountTypeBox,
            initialDepositField,
            branchField,
            companyNameField,
            companyAddressField,
            buttons,
            outputArea
        );

        return new Scene(root, 540, 430);
    }

    public String getAccountType() {
        return accountTypeBox.getValue();
    }

    public String getInitialDeposit() {
        return initialDepositField.getText();
    }

    public String getBranch() {
        return branchField.getText();
    }

    public String getCompanyName() {
        return companyNameField.getText();
    }

    public String getCompanyAddress() {
        return companyAddressField.getText();
    }

    public Button getOpenAccountButton() {
        return openAccountButton;
    }

    public Button getTransactionButton() {
        return transactionButton;
    }

    public Button getPayInterestButton() {
        return payInterestButton;
    }

    public Button getHistoryButton() {
        return historyButton;
    }

    public void setOutput(String text) {
        outputArea.setText(text);
    }
}