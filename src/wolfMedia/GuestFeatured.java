package wolfMedia;
import java.sql.*;

public class GuestFeatured {

    private String guestID;
    private String podcastEpisodeID;

    public GuestFeatured(String guestID, String podcastEpisodeID) {
        this.guestID = guestID;
        this.podcastEpisodeID = podcastEpisodeID;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getPodcastEpisodeID() {
        return podcastEpisodeID;
    }

    public void setPodcastEpisodeID(String podcastEpisodeID) {
        this.podcastEpisodeID = podcastEpisodeID;
    }

    public static int createGuestFeatured(GuestFeatured guestFeatured, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO guestsFeatured (guestID, podcastEpisodeID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, guestFeatured.getGuestID());
            statement.setString(2, guestFeatured.getPodcastEpisodeID());
            isInserted = statement.executeUpdate();
            System.out.println("Guest featured created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating guest featured: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static GuestFeatured readGuestFeatured(String guestID, String podcastEpisodeID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        GuestFeatured guestFeatured = null;
        try {
            String query = "SELECT * FROM guestsFeatured WHERE guestID = ? AND podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, guestID);
            statement.setString(2, podcastEpisodeID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                guestFeatured = new GuestFeatured(resultSet.getString("guestID"), resultSet.getString("podcastEpisodeID"));
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
        return guestFeatured;
    }
    
    public static int updateGuestFeatured(GuestFeatured guestFeatured, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE guestsFeatured SET guestID = ?, podcastEpisodeID = ? WHERE guestID = ? AND podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, guestFeatured.getGuestID());
            statement.setString(2, guestFeatured.getPodcastEpisodeID());
            statement.setString(3, guestFeatured.getGuestID());
            statement.setString(4, guestFeatured.getPodcastEpisodeID());
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
    
    public static int deleteGuestFeatured(String guestID, String podcastEpisodeID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM guestsFeatured WHERE guestID = ? AND podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, guestID);
            statement.setString(2, podcastEpisodeID);
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
