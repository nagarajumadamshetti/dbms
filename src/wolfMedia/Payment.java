package wolfMedia;
import java.sql.*;

/**
 * The type Payment.
 */
public class Payment {
    private String paymentID;
    private String date;

    /**
     * Instantiates a new Payment.
     *
     * @param paymentID the payment id
     * @param date      the date
     */
    public Payment(String paymentID, String date) {
        this.paymentID = paymentID;
        this.date = date;
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
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Create payment int.
     *
     * @param payment    the payment
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createPayment(Payment payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO payments (paymentID, date) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, payment.getPaymentID());
            statement.setString(2, payment.getDate());
            isInserted = statement.executeUpdate();
            System.out.println("Payment created.");
        } catch (SQLException ex) {
            System.out.println("Error creating payment: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    /**
     * Read payment payment.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the payment
     * @throws SQLException the sql exception
     */
    public static Payment readPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Payment payment = null;
        try {
            String query = "SELECT * FROM payments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment = new Payment(resultSet.getString("paymentID"),
                        resultSet.getString("date"));
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
        return payment;
    }

    /**
     * Update payment int.
     *
     * @param payment    the payment
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updatePayment(Payment payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE payments SET date = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, payment.getDate());
            statement.setString(2, payment.getPaymentID());
            isUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isUpdated;
    }

    /**
     * Delete payment int.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deletePayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM payments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            isDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isDeleted;
    }
}
