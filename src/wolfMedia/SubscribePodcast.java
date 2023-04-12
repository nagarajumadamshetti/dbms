package wolfMedia;
import java.sql.*;

public class SubscribePodcast {
    private String userID;
    private String podcastID;

    public SubscribePodcast(String userID, String podcastID) {
        this.userID = userID;
        this.podcastID = podcastID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPodcastID() {
        return podcastID;
    }

    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    public static int createSubscribePodcast(SubscribePodcast subscribePodcast, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO subscribePodcast (userID, podcastID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, subscribePodcast.getUserID());
            statement.setString(2, subscribePodcast.getPodcastID());
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
    
    public static SubscribePodcast readSubscribePodcast(String userID, String podcastID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SubscribePodcast subscribePodcast = null;
        try {
            String query = "SELECT * FROM subscribePodcast WHERE userID = ? AND podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, podcastID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                subscribePodcast = new SubscribePodcast(resultSet.getString("userID"),
                        resultSet.getString("podcastID"));
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
        return subscribePodcast;
    }
    
    public static int updateSubscribePodcast(SubscribePodcast subscribePodcast, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE subscribePodcast SET podcastID = ? WHERE userID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, subscribePodcast.getPodcastID());
            statement.setString(2, subscribePodcast.getUserID());
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
    
    public static int deleteSubscribePodcast(String userID, String podcastID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM subscribePodcast WHERE userID = ? AND podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, podcastID);
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
