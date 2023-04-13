package wolfMedia;
import java.sql.*;

/**
 * The type Payment made.
 */
public class PaymentMade {
    private String userID;
    private String paymentID;

    /**
     * Instantiates a new Payment made.
     *
     * @param userID    the user id
     * @param paymentID the payment id
     */
    public PaymentMade(String userID, String paymentID) {
        this.userID = userID;
        this.paymentID = paymentID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets payment id.
     *
     * @return the payment id
     */
    public String getPaymentID() {
        return paymentID;
    }

    /**
     * Sets payment id.
     *
     * @param paymentID the payment id
     */
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    /**
     * Create payment made int.
     *
     * @param paymentMade the payment made
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createPaymentMade(PaymentMade paymentMade, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO paymentsMade (userID, paymentID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentMade.getUserID());
            statement.setString(2, paymentMade.getPaymentID());
            isInserted = statement.executeUpdate();
            System.out.println("Payment made created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating payment made: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read payment made payment made.
     *
     * @param userID     the user id
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the payment made
     * @throws SQLException the sql exception
     */
    public static PaymentMade readPaymentMade(String userID, String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PaymentMade paymentMade = null;
        try {
            String query = "SELECT * FROM paymentsMade WHERE userID = ? AND paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                paymentMade = new PaymentMade(resultSet.getString("userID"),
                        resultSet.getString("paymentID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return paymentMade;
    }

    /**
     * Update payment made int.
     *
     * @param paymentMade the payment made
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updatePaymentMade(PaymentMade paymentMade, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE paymentsMade SET paymentID = ? WHERE userID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentMade.getPaymentID());
            statement.setString(2, paymentMade.getUserID());
            isUpdated = statement.executeUpdate();
            return isUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Delete payment made int.
     *
     * @param userID     the user id
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deletePaymentMade(String userID, String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM paymentsMade WHERE userID = ? AND paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, paymentID);
            isDeleted = statement.executeUpdate();
            return isDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
}
