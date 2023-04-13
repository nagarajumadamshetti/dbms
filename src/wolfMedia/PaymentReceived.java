package wolfMedia;
import java.sql.*;
import java.util.*;

public class PaymentReceived {
    private String recordLabelID;
    private String paymentID;

    public PaymentReceived(String recordLabelID, String paymentID) {
        this.recordLabelID = recordLabelID;
        this.paymentID = paymentID;
    }

    public String getRecordLabelID() {
        return recordLabelID;
    }

    public void setRecordLabelID(String recordLabelID) {
        this.recordLabelID = recordLabelID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public static List<RecordLabelPayment> getRecordLabelPayments(String recordLabelID, Connection conn) throws SQLException {

        List<RecordLabelPayment> recordLabelPayments = new ArrayList<>();

        String sql = "SELECT rP.* FROM paymentsReceived pR JOIN recordLabelPayments rP ON pR.paymentID = rP.paymentID WHERE pR.recordLabelID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, recordLabelID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            RecordLabelPayment recordLabelPayment = new RecordLabelPayment(rs.getString("paymentID"), rs.getInt("paymentAmount"));
            recordLabelPayments.add(recordLabelPayment);
        }

        rs.close();
        pstmt.close();

        return recordLabelPayments;
    }

    public static int createPaymentsReceived(PaymentReceived paymentsReceived, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO paymentsReceived (recordLabelID, paymentID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentsReceived.getRecordLabelID());
            statement.setString(2, paymentsReceived.getPaymentID());
            isInserted = statement.executeUpdate();
            System.out.println("Payments Received created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating payment received: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static PaymentReceived readPaymentsReceived(String recordLabelID, String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PaymentReceived paymentsReceived = null;
        try {
            String query = "SELECT * FROM paymentsReceived WHERE recordLabelID = ? AND paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, recordLabelID);
            statement.setString(2, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                paymentsReceived = new PaymentReceived(resultSet.getString("recordLabelID"),
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
        return paymentsReceived;
    }
    
    public static int updatePaymentsReceived(PaymentReceived paymentsReceived, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE paymentsReceived SET paymentID = ? WHERE recordLabelID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentsReceived.getPaymentID());
            statement.setString(2, paymentsReceived.getRecordLabelID());
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
    
    public static int deletePaymentsReceived(String recordLabelID, String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM paymentsReceived WHERE recordLabelID = ? AND paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, recordLabelID);
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
