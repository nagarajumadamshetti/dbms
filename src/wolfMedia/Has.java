package wolfMedia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Has.
 */
public class Has {

    private String artistID;
    private String albumID;

    /**
     * Instantiates a new Has.
     *
     * @param artistID the artist id
     * @param albumID  the album id
     */
    public Has(String artistID, String albumID) {
        this.artistID = artistID;
        this.albumID = albumID;
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
     * Gets album id.
     *
     * @return the album id
     */
    public String getAlbumID() {
        return albumID;
    }

    /**
     * Sets album id.
     *
     * @param albumID the album id
     */
    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    /**
     * Create has int.
     *
     * @param has        the has
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createHas(Has has, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO has (artistID, albumID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, has.getArtistID());
            statement.setString(2, has.getAlbumID());
            isInserted = statement.executeUpdate();
            System.out.println("Has record created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating has record: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Gets albums by artist id.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the albums by artist id
     * @throws SQLException the sql exception
     */
    public static List<Album> getAlbumsByArtistID(String artistID, Connection conn) throws SQLException {

        List<Album> albums = new ArrayList<>();

        String sql = "SELECT a.* FROM has ha JOIN Albums a ON ha.albumID = a.albumID WHERE ha.artistID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, artistID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // String albumID, String name, String edition, int releaseYea
            Album album = new Album(rs.getString("albumID"), rs.getString("name"), rs.getString("edition"),
                    rs.getInt("releaseYear"));
            albums.add(album);
        }

        rs.close();
        pstmt.close();

        return albums;
    }

    /**
     * Read has has.
     *
     * @param artistID   the artist id
     * @param albumID    the album id
     * @param connection the connection
     * @return the has
     * @throws SQLException the sql exception
     */
    public static Has readHas(String artistID, String albumID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Has has = null;
        try {
            String query = "SELECT * FROM has WHERE artistID = ? AND albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, albumID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                has = new Has(resultSet.getString("artistID"), resultSet.getString("albumID"));
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
        return has;
    }

    /**
     * Update has int.
     *
     * @param has        the has
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateHas(Has has, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE has SET albumID = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, has.getAlbumID());
            statement.setString(2, has.getArtistID());
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
     * Delete has int.
     *
     * @param artistID   the artist id
     * @param albumID    the album id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteHas(String artistID, String albumID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM has WHERE artistID = ? AND albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, albumID);
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
