package wolfMedia;
import java.sql.*;

/**
 * The type Owned by.
 */
public class OwnedBy {

    private String podcastID;
    private String podcastHostID;

    /**
     * Instantiates a new Owned by.
     *
     * @param podcastID     the podcast id
     * @param podcastHostID the podcast host id
     */
    public OwnedBy(String podcastID, String podcastHostID) {
        this.podcastID = podcastID;
        this.podcastHostID = podcastHostID;
    }

    /**
     * Gets podcast id.
     *
     * @return the podcast id
     */
    public String getPodcastID() {
        return podcastID;
    }

    /**
     * Sets podcast id.
     *
     * @param podcastID the podcast id
     */
    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    /**
     * Gets podcast host id.
     *
     * @return the podcast host id
     */
    public String getPodcastHostID() {
        return podcastHostID;
    }

    /**
     * Sets podcast host id.
     *
     * @param podcastHostID the podcast host id
     */
    public void setPodcastHostID(String podcastHostID) {
        this.podcastHostID = podcastHostID;
    }

    /**
     * Create owned by int.
     *
     * @param ownedBy    the owned by
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createOwnedBy(OwnedBy ownedBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO ownedBy (podcastID, podcastHostID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, ownedBy.getPodcastID());
            statement.setString(2, ownedBy.getPodcastHostID());
            isInserted = statement.executeUpdate();
            System.out.println("OwnedBy relationship created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating OwnedBy relationship: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read owned by owned by.
     *
     * @param podcastID     the podcast id
     * @param podcastHostID the podcast host id
     * @param connection    the connection
     * @return the owned by
     * @throws SQLException the sql exception
     */
    public static OwnedBy readOwnedBy(String podcastID, String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OwnedBy ownedBy = null;
        try {
            String query = "SELECT * FROM ownedBy WHERE podcastID = ? AND podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, podcastHostID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ownedBy = new OwnedBy(resultSet.getString("podcastID"), resultSet.getString("podcastHostID"));
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
        return ownedBy;
    }

    /**
     * Update owned by int.
     *
     * @param ownedBy    the owned by
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateOwnedBy(OwnedBy ownedBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE ownedBy SET podcastID = ?, podcastHostID = ? WHERE podcastID = ? AND podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, ownedBy.getPodcastID());
            statement.setString(2, ownedBy.getPodcastHostID());
            statement.setString(3, ownedBy.getPodcastID());
            statement.setString(4, ownedBy.getPodcastHostID());
            isUpdated = statement.executeUpdate();
            System.out.println("OwnedBy relationship updated.");
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
     * Delete owned by int.
     *
     * @param podcastID     the podcast id
     * @param podcastHostID the podcast host id
     * @param connection    the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteOwnedBy(String podcastID, String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM ownedBy WHERE podcastID = ? AND podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, podcastHostID);
            isDeleted = statement.executeUpdate();
            System.out.println("OwnedBy relationship deleted.");
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