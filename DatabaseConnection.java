import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("schema.sql")));
            for (String command : sql.split(";")) {
                if (!command.trim().isEmpty()) {
                    stmt.execute(command);
                }
            }
        } catch (Exception e) {
            System.out.println("Database init note: " + e.getMessage());
        }
    }
}