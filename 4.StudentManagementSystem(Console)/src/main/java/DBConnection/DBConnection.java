
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Load JDBC driver (MySQL)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database URL
            String url = "http://localhost/phpmyadmin/index.php?route=/sql&pos=0&db=student_management&table=students"; // URL for your MySQL database
            String username = "root";  // MySQL username (default is 'root')
            String password = "";  // Replace with your MySQL password

            // Establish the connection
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Database connected successfully!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }

        return connection;
    }

    public static void main(String[] args) {
        getConnection();  // Testing connection to the database
    }
}
