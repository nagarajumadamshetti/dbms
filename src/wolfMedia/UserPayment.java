package wolfMedia;
import java.sql.*;

/**
 * The type User payment.
 */
public class UserPayment {
    private String paymentID;
    private int paymentAmount;

    /**
     * Instantiates a new User payment.
     *
     * @param paymentID     the payment id
     * @param paymentAmount the payment amount
     */
    public UserPayment(String paymentID, int paymentAmount) {
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
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets payment amount.
     *
     * @param paymentAmount the payment amount
     */
    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Create user payment int.
     *
     * @param userPayment the user payment
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createUserPayment(UserPayment userPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO userPayments (paymentID, paymentAmount) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, userPayment.getPaymentID());
            statement.setInt(2, userPayment.getPaymentAmount());
            isInserted = statement.executeUpdate();
            System.out.println("User payment created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating user payment: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read user payment user payment.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the user payment
     * @throws SQLException the sql exception
     */
    public static UserPayment readUserPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserPayment userPayment = null;
        try {
            String query = "SELECT * FROM userPayments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userPayment = new UserPayment(resultSet.getString("paymentID"),
                        resultSet.getInt("paymentAmount"));
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
        return userPayment;
    }

    /**
     * Update user payment int.
     *
     * @param userPayment the user payment
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateUserPayment(UserPayment userPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE userPayments SET paymentAmount = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userPayment.getPaymentAmount());
            statement.setString(2, userPayment.getPaymentID());
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
     * Delete user payment int.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteUserPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM userPayments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
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
