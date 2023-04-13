package wolfMedia;
import java.sql.*;

/**
 * The type Primary genre.
 */
public class PrimaryGenre {
    private String artistID;
    private String genreID;

    /**
     * Instantiates a new Primary genre.
     *
     * @param artistID the artist id
     * @param genreID  the genre id
     */
    public PrimaryGenre(String artistID, String genreID) {
        this.artistID = artistID;
        this.genreID = genreID;
    }

    /**
     * Gets artist id.
     *
     * @return the artist id
     */
    public String getArtistID() {
        return artistID;
    }

    /**
     * Sets artist id.
     *
     * @param artistID the artist id
     */
    public void setArtistID(String artistID) {
        this.artistID = artistID;
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
     * Create primary genre int.
     *
     * @param primaryGenre the primary genre
     * @param connection   the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createPrimaryGenre(PrimaryGenre primaryGenre, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO primarygenre (artistID, genreID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, primaryGenre.getArtistID());
            statement.setString(2, primaryGenre.getGenreID());
            isInserted = statement.executeUpdate();
            System.out.println("Primary genre created.");
        } catch (SQLException ex) {
            System.out.println("Error creating primary genre: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Read primary genre primary genre.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return the primary genre
     * @throws SQLException the sql exception
     */
    public static PrimaryGenre readPrimaryGenre(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PrimaryGenre primaryGenre = null;
        try {
            String query = "SELECT * FROM primarygenre WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                primaryGenre = new PrimaryGenre(resultSet.getString("artistID"), resultSet.getString("genreID"));
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
        return primaryGenre;
    }

    /**
     * Update primary genre int.
     *
     * @param primaryGenre the primary genre
     * @param connection   the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updatePrimaryGenre(PrimaryGenre primaryGenre, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE primarygenre SET genreID = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, primaryGenre.getGenreID());
            statement.setString(2, primaryGenre.getArtistID());
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
     * Delete primary genre int.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deletePrimaryGenre(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        
        try {
            String query = "DELETE FROM primarygenre WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
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
