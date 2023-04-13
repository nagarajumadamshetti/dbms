package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Belongs to.
 */
public class BelongsTo {

    private String albumID;
    private String songID;
    private int trackNumber;

    /**
     * Instantiates a new Belongs to.
     *
     * @param albumID     the album id
     * @param songID      the song id
     * @param trackNumber the track number
     */
    public BelongsTo(String albumID, String songID, int trackNumber) {
        this.albumID = albumID;
        this.songID = songID;
        this.trackNumber = trackNumber;
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
     * Gets track number.
     *
     * @return the track number
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     * Sets track number.
     *
     * @param trackNumber the track number
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * Create belongs to int.
     *
     * @param belongsTo  the belongs to
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createBelongsTo(BelongsTo belongsTo, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO belongsTo (albumID, songID, trackNumber) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, belongsTo.getAlbumID());
            statement.setString(2, belongsTo.getSongID());
            statement.setInt(3, belongsTo.getTrackNumber());
            isInserted = statement.executeUpdate();
            System.out.println("BelongsTo created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating BelongsTo: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read belongs to belongs to.
     *
     * @param songID     the song id
     * @param albumID    the album id
     * @param connection the connection
     * @return the belongs to
     * @throws SQLException the sql exception
     */
    public static BelongsTo readBelongsTo(String songID, String albumID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BelongsTo belongsTo = null;
        try {
            String query = "SELECT * FROM belongsTo WHERE songID = ? AND albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            statement.setString(2, albumID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                belongsTo = new BelongsTo(resultSet.getString("albumID"),
                        resultSet.getString("songID"),
                        resultSet.getInt("trackNumber"));
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
        return belongsTo;
    }

    /**
     * Gets songs by album id.
     *
     * @param albumID the album id
     * @param conn    the conn
     * @return the songs by album id
     * @throws SQLException the sql exception
     */
    public static List<Song> getSongsByAlbumID(String albumID, Connection conn) throws SQLException {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT s.* FROM Songs s JOIN BelongsTo bt ON s.songID = bt.songID WHERE bt.albumID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, albumID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // songID, String title, String duration, String releaseDate, float royaltyPaid,
            // float royaltyRate
            Song song = new Song(rs.getString("songID"), rs.getString("title"), rs.getString("duration"),
                    rs.getString("releaseDate"), rs.getFloat("royaltyPaid"), rs.getFloat("royaltyRate"));
            songs.add(song);
        }

        rs.close();
        pstmt.close();

        return songs;
    }

    /**
     * Gets songs by album id song id.
     *
     * @param albumID the album id
     * @param songID  the song id
     * @param conn    the conn
     * @return the songs by album id song id
     * @throws SQLException the sql exception
     */
    public static List<Song> getSongsByAlbumIDSongID(String albumID,String songID, Connection conn) throws SQLException {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT s.* FROM Songs s JOIN BelongsTo bt ON s.songID = bt.songID WHERE bt.albumID = ? AND bt.songID= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, albumID);
        pstmt.setString(2, songID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // songID, String title, String duration, String releaseDate, float royaltyPaid,
            // float royaltyRate
            Song song = new Song(rs.getString("songID"), rs.getString("title"), rs.getString("duration"),
                    rs.getString("releaseDate"), rs.getFloat("royaltyPaid"), rs.getFloat("royaltyRate"));
            songs.add(song);
        }

        rs.close();
        pstmt.close();

        return songs;
    }

    /**
     * Gets albums.
     *
     * @param songID the song id
     * @param conn   the conn
     * @return the albums
     * @throws SQLException the sql exception
     */
    public static List<Album> getAlbums(String songID, Connection conn) throws SQLException {

        List<Album> albums = new ArrayList<>();

        String sql = "SELECT a.* FROM belongsTo bt JOIN Albums a ON bt.albumID = a.albumID WHERE bt.songID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, songID);
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
     * Update belongs to int.
     *
     * @param belongsTo  the belongs to
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateBelongsTo(BelongsTo belongsTo, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE belongsTo SET trackNumber = ? WHERE albumID = ? AND songID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, belongsTo.getTrackNumber());
            statement.setString(2, belongsTo.getAlbumID());
            statement.setString(3, belongsTo.getSongID());
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
     * Delete belongs to int.
     *
     * @param songID     the song id
     * @param albumID    the album id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteBelongsTo(String songID, String albumID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM belongsTo WHERE songID = ? AND albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
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
