package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Album.
 */
public class Album {

    /**
     * The Album id.
     */
    public String albumID;
    /**
     * The Name.
     */
    public String name;
    /**
     * The Edition.
     */
    public String edition;
    /**
     * The Release year.
     */
    public int releaseYear;

    /**
     * Instantiates a new Album.
     *
     * @param albumID     the album id
     * @param name        the name
     * @param edition     the edition
     * @param releaseYear the release year
     */
    public Album(String albumID, String name, String edition, int releaseYear) {
        this.albumID = albumID;
        this.name = name;
        this.edition = edition;
        this.releaseYear = releaseYear;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets edition.
     *
     * @return the edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Sets edition.
     *
     * @param edition the edition
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * Gets release year.
     *
     * @return the release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear the release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Create album int.
     *
     * @param album      the album
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createAlbum(Album album, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO albums (albumID, name, edition, releaseYear) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, album.getAlbumID());
            statement.setString(2, album.getName());
            statement.setString(3, album.getEdition());
            statement.setInt(4, album.getReleaseYear());
            isInserted = statement.executeUpdate();
            System.out.println("Album created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating album: " + ex.getMessage());
            return 0;
        }
    }

    /**
     * Gets artists.
     *
     * @param albumID the album id
     * @param conn    the conn
     * @return the artists
     * @throws SQLException the sql exception
     */
    public static List<Artist> getArtists(String albumID, Connection conn) throws SQLException {

        List<Artist> artists = new ArrayList<>();

        String sql = "SELECT a.* FROM Artists a JOIN Has ha ON a.artistID = ha.artistID WHERE ha.albumID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, albumID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Artist artist = new Artist(rs.getString("artistID"), rs.getString("name"), rs.getString("status"),
                    rs.getString("type"));
            artists.add(artist);
        }

        rs.close();
        pstmt.close();

        return artists;
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
     * Read album album.
     *
     * @param albumID    the album id
     * @param connection the connection
     * @return the album
     * @throws SQLException the sql exception
     */
    public static Album readAlbum(String albumID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Album album = null;
        try {
            String query = "SELECT * FROM albums WHERE albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, albumID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                album = new Album(resultSet.getString("albumID"),
                        resultSet.getString("name"),
                        resultSet.getString("edition"),
                        resultSet.getInt("releaseYear"));
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
        return album;
    }

    /**
     * Update album int.
     *
     * @param album      the album
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateAlbum(Album album, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE albums SET name = ?, edition = ?, releaseYear = ? WHERE albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, album.getName());
            statement.setString(2, album.getEdition());
            statement.setInt(3, album.getReleaseYear());
            statement.setString(4, album.getAlbumID());
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
     * Delete album int.
     *
     * @param albumID    the album id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteAlbum(String albumID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM albums WHERE albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, albumID);
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
