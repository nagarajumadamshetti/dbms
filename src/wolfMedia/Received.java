package wolfMedia;
import java.sql.*;
public class Received {
    private String paymentID;
    private String artistID;

    public Received(String paymentID, String artistID) {
        this.paymentID = paymentID;
        this.artistID = artistID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public static int createReceived(Received received, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO received (paymentID, artistID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, received.getPaymentID());
            statement.setString(2, received.getArtistID());
            isInserted = statement.executeUpdate();
            System.out.println("Received created.");
        } catch (SQLException ex) {
            System.out.println("Error creating received: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    public Received readReceived(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Received received = null;
        try {
            String query = "SELECT * FROM received WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                received = new Received(resultSet.getString("paymentID"),
                        resultSet.getString("artistID"));
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
        return received;
    }

    public static int updateReceived(Received received, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE received SET artistID = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, received.getArtistID());
            statement.setString(2, received.getPaymentID());
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

    public static int deleteReceived(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM received WHERE paymentID = ?";
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
