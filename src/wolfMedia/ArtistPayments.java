package wolfMedia;
import java.sql.*;

public class ArtistPayments {
    private String paymentID;
    private float paymentAmount;

    public ArtistPayments(String paymentID, float paymentAmount) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    
    public static int createPayment(ArtistPayments payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO artistPayments (paymentID, paymentAmount) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, payment.getPaymentID());
            statement.setFloat(2, payment.getPaymentAmount());
            isInserted = statement.executeUpdate();
            System.out.println("Payment created.");
        } catch (SQLException ex) {
            System.out.println("Error creating payment: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    public static ArtistPayments readPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArtistPayments payment = null;
        try {
            String query = "SELECT * FROM artistPayments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment = new ArtistPayments(resultSet.getString("paymentID"), resultSet.getFloat("paymentAmount"));
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

    public static int updatePayment(ArtistPayments payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE artistPayments SET paymentAmount = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setFloat(1, payment.getPaymentAmount());
            statement.setString(2, payment.getPaymentID());
            isUpdated =statement.executeUpdate();
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

    public static int deletePayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM artistPayments WHERE paymentID = ?";
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
