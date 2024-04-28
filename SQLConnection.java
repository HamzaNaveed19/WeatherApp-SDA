import java.sql.*;
public class SQLConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/FiveDayForecast", "root", "hamza_7871");
    }
}
