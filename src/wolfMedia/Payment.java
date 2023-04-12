package wolfMedia;
import java.sql.*;
import java.util.Date;

public class Payment {
    private String paymentID;
    private Date date;

    public Payment(String paymentID, Date date) {
        this.paymentID = paymentID;
        this.date = date;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static int createPayment(Payment payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO payments (paymentID, date) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, payment.getPaymentID());
            statement.setDate(2, new java.sql.Date(payment.getDate().getTime()));
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

    public Payment readPayment(String paymentID, Connection connection) throws SQLException {
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
                        resultSet.getDate("date"));
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

    public static int updatePayment(Payment payment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE payments SET date = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(payment.getDate().getTime()));
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
