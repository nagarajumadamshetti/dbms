package  wolfMedia;

import java.sql.*;

/**
 * Connections class
 */
public class Connections {

    private static Connection connection = null;

    /**
     * Open connection.
     *
     * @return the connection
     */
    public static Connection open() {
        try {
            String url = "jdbc:mariadb://localhost:3306/nagaraj";
            String user = "root";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection opened.");
        } catch (SQLException ex) {
            System.out.println("Error opening connection: " + ex.getMessage());
        }
        return connection;
    }

    /**
     * Closes the connection.
     *
     * @param connection the connection
     */
    public static void close(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException ex) {
            System.out.println("Error closing connection: " + ex.getMessage());
        }
    }
}