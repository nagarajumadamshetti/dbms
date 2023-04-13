package wolfMedia;
import java.sql.*;

/**
 * The type Genered in.
 */
public class GeneredIn {
    private String songID;
    private String genreID;

    /**
     * Instantiates a new Genered in.
     *
     * @param songID  the song id
     * @param genreID the genre id
     */
    public GeneredIn(String songID, String genreID) {
        this.songID = songID;
        this.genreID = genreID;
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
     * Create genered in int.
     *
     * @param generedIn  the genered in
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createGeneredIn(GeneredIn generedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO generedIn (songID, genreID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, generedIn.getSongID());
            statement.setString(2, generedIn.getGenreID());
            isInserted = statement.executeUpdate();
            System.out.println("GeneredIn created.");
        } catch (SQLException ex) {
            System.out.println("Error creating genered in: " + ex.getMessage());
            return 0;
            
        }
        return isInserted;
    }

    /**
     * Read genered in genered in.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return the genered in
     * @throws SQLException the sql exception
     */
    public static GeneredIn readGeneredIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        GeneredIn generedIn = null;
        try {
            String query = "SELECT * FROM generedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                generedIn = new GeneredIn(resultSet.getString("songID"),
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
        return generedIn;
    }

    /**
     * Update genered in int.
     *
     * @param generedIn  the genered in
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateGeneredIn(GeneredIn generedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE generedIn SET genreID = ? WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, generedIn.getGenreID());
            statement.setString(2, generedIn.getSongID());
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

    /**
     * Delete genered in int.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteGeneredIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM generedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
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
