package wolfMedia;
import java.sql.*;

/**
 * The type Sung in.
 */
public class SungIn {
    private String songID;
    private String languageID;

    /**
     * Instantiates a new Sung in.
     *
     * @param songID     the song id
     * @param languageID the language id
     */
    public SungIn(String songID, String languageID) {
        this.songID = songID;
        this.languageID = languageID;
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
     * Gets language id.
     *
     * @return the language id
     */
    public String getLanguageID() {
        return languageID;
    }

    /**
     * Sets language id.
     *
     * @param languageID the language id
     */
    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }

    /**
     * Create sung in int.
     *
     * @param sungIn     the sung in
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createSungIn(SungIn sungIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO sungIn (songID, languageID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, sungIn.getSongID());
            statement.setString(2, sungIn.getLanguageID());
            isInserted = statement.executeUpdate();
            System.out.println("SungIn created.");
        } catch (SQLException ex) {
            System.out.println("Error creating SungIn: " + ex.getMessage());
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
     * Read sung in sung in.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return the sung in
     * @throws SQLException the sql exception
     */
    public static SungIn readSungIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SungIn sungIn = null;
        try {
            String query = "SELECT * FROM sungIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sungIn = new SungIn(resultSet.getString("songID"), resultSet.getString("languageID"));
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
        return sungIn;
    }

    /**
     * Update sung in int.
     *
     * @param sungIn     the sung in
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateSungIn(SungIn sungIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE sungIn SET languageID = ? WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, sungIn.getLanguageID());
            statement.setString(2, sungIn.getSongID());
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
     * Delete sung in int.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteSungIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM sungIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            isDeleted = statement.executeUpdate();
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
