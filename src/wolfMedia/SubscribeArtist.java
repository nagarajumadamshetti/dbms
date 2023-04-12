package wolfMedia;
import java.sql.*;
public class SubscribeArtist {

    private String userID;
    private String artistID;
    
    public SubscribeArtist(String userID, String artistID) {
        this.userID = userID;
        this.artistID = artistID;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getArtistID() {
        return artistID;
    }
    
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
    
    public static int createSubscription(SubscribeArtist subscription, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO subscribeArtist (userID, artistID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, subscription.getUserID());
            statement.setString(2, subscription.getArtistID());
            isInserted = statement.executeUpdate();
            System.out.println("Subscription created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating subscription: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static int updateSubscription(String userID, String artistID, String newUserID, String newArtistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE subscribeArtist SET userID = ?, artistID = ? WHERE userID = ? AND artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, newUserID);
            statement.setString(2, newArtistID);
            statement.setString(3, userID);
            statement.setString(4, artistID);
            isUpdated = statement.executeUpdate();
            System.out.println("Subscription updated.");
            return isUpdated;
        } catch (SQLException ex) {
            System.out.println("Error updating subscription: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static int deleteSubscription(String userID, String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM subscribeArtist WHERE userID = ? AND artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, artistID);
            isDeleted = statement.executeUpdate();
            System.out.println("Subscription deleted.");
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
    
    public static boolean hasSubscription(String userID, String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean hasSubscription = false;
        try {
            String query = "SELECT * FROM subscribeArtist WHERE userID = ? AND artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, artistID);
            resultSet = statement.executeQuery();
            hasSubscription = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return hasSubscription;
    }
    
}
