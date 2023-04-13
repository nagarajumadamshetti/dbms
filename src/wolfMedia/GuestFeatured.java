package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Guest featured.
 */
public class GuestFeatured {

    private String guestID;
    private String podcastEpisodeID;

    /**
     * Instantiates a new Guest featured.
     *
     * @param guestID          the guest id
     * @param podcastEpisodeID the podcast episode id
     */
    public GuestFeatured(String guestID, String podcastEpisodeID) {
        this.guestID = guestID;
        this.podcastEpisodeID = podcastEpisodeID;
    }

    /**
     * Gets guest id.
     *
     * @return the guest id
     */
    public String getGuestID() {
        return guestID;
    }

    /**
     * Sets guest id.
     *
     * @param guestID the guest id
     */
    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    /**
     * Gets podcast episode id.
     *
     * @return the podcast episode id
     */
    public String getPodcastEpisodeID() {
        return podcastEpisodeID;
    }

    /**
     * Sets podcast episode id.
     *
     * @param podcastEpisodeID the podcast episode id
     */
    public void setPodcastEpisodeID(String podcastEpisodeID) {
        this.podcastEpisodeID = podcastEpisodeID;
    }

    /**
     * Create guest featured int.
     *
     * @param guestFeatured the guest featured
     * @param connection    the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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

    /**
     * Gets guests by podcast episode id.
     *
     * @param podcastEpisodeID the podcast episode id
     * @param connection       the connection
     * @return the guests by podcast episode id
     * @throws SQLException the sql exception
     */
    public static List<Guest> getGuestsByPodcastEpisodeID( String podcastEpisodeID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Guest> guests = new ArrayList<>();
        
            String query = "SELECT g.* FROM guestsFeatured gf join guests g on g.guestID= gf.guestID  WHERE gf.podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastEpisodeID);
            rs = statement.executeQuery();
            while (rs.next()) {
                // String podcastEpisodeID, String episodeTitle, Time duration, Date releaseDate,
                // int listeningCount, int advertisementCount
                Guest guest = new Guest(rs.getString("guestID"), rs.getString("name"));
                        guests.add(guest);
            }
    
            rs.close();
            statement.close();
    
            return guests;
    }

    /**
     * Read guest featured guest featured.
     *
     * @param guestID          the guest id
     * @param podcastEpisodeID the podcast episode id
     * @param connection       the connection
     * @return the guest featured
     * @throws SQLException the sql exception
     */
    public static GuestFeatured readGuestFeatured(String guestID, String podcastEpisodeID, Connection connection)
            throws SQLException {
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
                guestFeatured = new GuestFeatured(resultSet.getString("guestID"),
                        resultSet.getString("podcastEpisodeID"));
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

    /**
     * Update guest featured int.
     *
     * @param guestFeatured the guest featured
     * @param connection    the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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

    /**
     * Delete guest featured int.
     *
     * @param guestID          the guest id
     * @param podcastEpisodeID the podcast episode id
     * @param connection       the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteGuestFeatured(String guestID, String podcastEpisodeID, Connection connection)
            throws SQLException {
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
