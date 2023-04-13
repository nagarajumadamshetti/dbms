package wolfMedia;
import java.sql.*;

/**
 * The type Podcast genered in.
 */
public class PodcastGeneredIn {

    private String podcastID;
    private String genreID;

    /**
     * Instantiates a new Podcast genered in.
     *
     * @param podcastID the podcast id
     * @param genreID   the genre id
     */
    public PodcastGeneredIn(String podcastID, String genreID) {
        this.podcastID = podcastID;
        this.genreID = genreID;
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
     * Gets genre id.
     *
     * @return the genre id
     */
    public String getGenreID() {
        return genreID;
    }

    /**
     * Sets genre id.
     *
     * @param genreID the genre id
     */
    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    /**
     * Create podcast genered in int.
     *
     * @param podcastGeneredIn the podcast genered in
     * @param connection       the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createPodcastGeneredIn(PodcastGeneredIn podcastGeneredIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastGeneredIn (podcastID, genreID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastGeneredIn.getPodcastID());
            statement.setString(2, podcastGeneredIn.getGenreID());
            isInserted = statement.executeUpdate();
            System.out.println("PodcastGeneredIn created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating PodcastGeneredIn: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read podcast genered in podcast genered in.
     *
     * @param podcastID  the podcast id
     * @param genreID    the genre id
     * @param connection the connection
     * @return the podcast genered in
     * @throws SQLException the sql exception
     */
    public static PodcastGeneredIn readPodcastGeneredIn(String podcastID, String genreID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastGeneredIn podcastGeneredIn = null;
        try {
            String query = "SELECT * FROM podcastGeneredIn WHERE podcastID = ? AND genreID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, genreID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                podcastGeneredIn = new PodcastGeneredIn(resultSet.getString("podcastID"),
                        resultSet.getString("genreID"));
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
        return podcastGeneredIn;
    }

    /**
     * Update podcast genered in int.
     *
     * @param podcastGeneredIn the podcast genered in
     * @param connection       the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updatePodcastGeneredIn(PodcastGeneredIn podcastGeneredIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastGeneredIn SET genreID = ? WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastGeneredIn.getGenreID());
            statement.setString(2, podcastGeneredIn.getPodcastID());
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
     * Delete podcast genered in int.
     *
     * @param podcastID  the podcast id
     * @param genreID    the genre id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deletePodcastGeneredIn(String podcastID, String genreID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcastGeneredIn WHERE podcastID = ? AND genreID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, genreID);
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
