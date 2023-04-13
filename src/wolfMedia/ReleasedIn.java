package wolfMedia;
import java.sql.*;

/**
 * The type Released in.
 */
public class ReleasedIn {
    private String songID;
    private String countryID;

    /**
     * Instantiates a new Released in.
     *
     * @param songID    the song id
     * @param countryID the country id
     */
    public ReleasedIn(String songID, String countryID) {
        this.songID = songID;
        this.countryID = countryID;
    }

    /**
     * Gets song id.
     *
     * @return the song id
     */
    public String getSongID() {
        return songID;
    }

    /**
     * Sets song id.
     *
     * @param songID the song id
     */
    public void setSongID(String songID) {
        this.songID = songID;
    }

    /**
     * Gets country id.
     *
     * @return the country id
     */
    public String getCountryID() {
        return countryID;
    }

    /**
     * Sets country id.
     *
     * @param countryID the country id
     */
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    /**
     * Create released in int.
     *
     * @param releasedIn the released in
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createReleasedIn(ReleasedIn releasedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO releasedIn (songID, countryID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, releasedIn.getSongID());
            statement.setString(2, releasedIn.getCountryID());
            isInserted = statement.executeUpdate();
            System.out.println("ReleasedIn created.");
        } catch (SQLException ex) {
            System.out.println("Error creating ReleasedIn: " + ex.getMessage());
            if (statement != null) {
                statement.close();
            }
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    /**
     * Read released in released in.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return the released in
     * @throws SQLException the sql exception
     */
    public static ReleasedIn readReleasedIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ReleasedIn releasedIn = null;
        try {
            String query = "SELECT * FROM releasedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                releasedIn = new ReleasedIn(resultSet.getString("songID"),
                        resultSet.getString("countryID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return releasedIn;
    }

    /**
     * Update released in int.
     *
     * @param releasedIn the released in
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateReleasedIn(ReleasedIn releasedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE releasedIn SET countryID = ? WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, releasedIn.getCountryID());
            statement.setString(2, releasedIn.getSongID());
            isUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isUpdated;
    }

    /**
     * Delete released in int.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteReleasedIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM releasedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isDeleted;
    }
}
