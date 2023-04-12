package wolfMedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PodcastPayments {
    private String paymentID;
    private String podcastHostID;

    public PodcastPayments(String paymentID, String podcastHostID) {
        this.paymentID = paymentID;
        this.podcastHostID = podcastHostID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPodcastHostID() {
        return podcastHostID;
    }

    public void setPodcastHostID(String podcastHostID) {
        this.podcastHostID = podcastHostID;
    }

    public int createPodcastPayment(PodcastPayments podcastPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastPayments (paymentID, podcastHostID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastPayment.getPaymentID());
            statement.setString(2, podcastPayment.getPodcastHostID());
            isInserted = statement.executeUpdate();
            System.out.println("Podcast payment created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating podcast payment: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public PodcastPayments readPodcastPayment(String paymentID, String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastPayments podcastPayment = null;
        try {
            String query = "SELECT * FROM podcastPayments WHERE paymentID = ? AND podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            statement.setString(2, podcastHostID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                podcastPayment = new PodcastPayments(resultSet.getString("paymentID"),
                        resultSet.getString("podcastHostID"));
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
        return podcastPayment;
    }
    
    public int updatePodcastPayment(PodcastPayments podcastPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastPayments SET paymentID=? WHERE podcastHostID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastPayment.getPaymentID());
            statement.setString(2, podcastPayment.getPodcastHostID());
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
    
    public int deletePodcastPayment(String paymentID, String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcastPayments WHERE paymentID = ? AND podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            statement.setString(2, podcastHostID);
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
