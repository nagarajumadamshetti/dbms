package wolfMedia;
import java.sql.*;

/**
 * The type Record label payment.
 */
public class RecordLabelPayment {
    private String paymentID;
    private float paymentAmount;

    /**
     * Instantiates a new Record label payment.
     *
     * @param paymentID     the payment id
     * @param paymentAmount the payment amount
     */
    public RecordLabelPayment(String paymentID, float paymentAmount) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
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
     * Gets payment amount.
     *
     * @return the payment amount
     */
    public float getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets payment amount.
     *
     * @param paymentAmount the payment amount
     */
    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Create payment int.
     *
     * @param payment    the payment
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createPayment(RecordLabelPayment payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO payments (paymentID, paymentAmount) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, payment.getPaymentID());
            statement.setFloat(2, payment.getPaymentAmount());
            isInserted = statement.executeUpdate();
            System.out.println("Payment created.");
        } catch (SQLException ex) {
            System.out.println("Error creating payment: " + ex.getMessage());
            if (statement != null) {
                statement.close();
            }
            return 0;
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    /**
     * Read payment record label payment.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the record label payment
     * @throws SQLException the sql exception
     */
    public static RecordLabelPayment readPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RecordLabelPayment payment = null;
        try {
            String query = "SELECT * FROM payments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment = new RecordLabelPayment(resultSet.getString("paymentID"),
                        resultSet.getFloat("paymentAmount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
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
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updatePayment(RecordLabelPayment payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE payments SET paymentAmount = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setFloat(1, payment.getPaymentAmount());
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
     * @return the int
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
            if (statement != null) {
                statement.close();
            }
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isDeleted;
    }
}
