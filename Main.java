import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        DatabaseConnection.initDatabase();

        Customer sampleCustomer = new Customer("C001", "Thabo", "Mokoena", "Plot 123, Gaborone");

        LoginView loginView = new LoginView();
        AccountView accountView = new AccountView();
        DepositView depositView = new DepositView();

        new LoginController(primaryStage, loginView, accountView);
        new AccountController(primaryStage, accountView, depositView, sampleCustomer);

        primaryStage.setTitle("Banking System");
        primaryStage.setScene(loginView.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}