package wolfMedia;
import java.sql.*;

public class PaymentMade {
    private String userID;
    private String paymentID;

    public PaymentMade(String userID, String paymentID) {
        this.userID = userID;
        this.paymentID = paymentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

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
