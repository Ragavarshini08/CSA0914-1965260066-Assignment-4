
import java.sql.*;

public class DBConnection {

    public static Connection getConnection()
            throws SQLException {

        String url =
            "jdbc:mysql://localhost:3306/campus_event";

        String user = "root";
        String password = "YOUR_MYSQL_PASSWORD";

        return DriverManager.getConnection(
            url, user, password
        );
    }
}
