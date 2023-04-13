package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Collaborated by.
 */
public class CollaboratedBy {

    private String artistID;
    private String songID;

    /**
     * Instantiates a new Collaborated by.
     *
     * @param artistID the artist id
     * @param songID   the song id
     */
    public CollaboratedBy(String artistID, String songID) {
        this.artistID = artistID;
        this.songID = songID;
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
     * Create collaboration int.
     *
     * @param collaboration the collaboration
     * @param connection    the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createCollaboration(CollaboratedBy collaboration, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO collaboratedBy (artistID, songID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, collaboration.getArtistID());
            statement.setString(2, collaboration.getSongID());
            isInserted = statement.executeUpdate();
            System.out.println("Collaboration created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating collaboration: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Gets artists.
     *
     * @param songID the song id
     * @param conn   the conn
     * @return the artists
     * @throws SQLException the sql exception
     */
    public static List<Artist> getArtists(String songID, Connection conn) throws SQLException {

        List<Artist> artists = new ArrayList<>();

        String sql = "SELECT a.* FROM collaboratedBy cb JOIN Artists a ON cb.artistID = a.artistID WHERE cb.songID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, songID);
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
     * Gets songs by artist id.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the songs by artist id
     * @throws SQLException the sql exception
     */
    public static List<Song> getSongsByArtistID(String artistID, Connection conn) throws SQLException {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT s.* FROM collaboratedBy cb JOIN Songs s ON cb.songID = s.songID WHERE cb.artistID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, artistID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // String songID, String title, String duration, String releaseDate, float
            // royaltyPaid, float royaltyRate
            Song song = new Song(rs.getString("songID"), rs.getString("title"), rs.getString("duration"),
                    rs.getString("releaseDate"), rs.getFloat("royaltyPaid"), rs.getFloat("royaltyRate"));
            songs.add(song);
        }

        rs.close();
        pstmt.close();

        return songs;
    }

    /**
     * Read collaboration collaborated by.
     *
     * @param artistID   the artist id
     * @param songID     the song id
     * @param connection the connection
     * @return the collaborated by
     * @throws SQLException the sql exception
     */
    public static CollaboratedBy readCollaboration(String artistID, String songID, Connection connection)
            throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CollaboratedBy collaboration = null;
        try {
            String query = "SELECT * FROM collaboratedBy WHERE artistID = ? AND songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                collaboration = new CollaboratedBy(resultSet.getString("artistID"), resultSet.getString("songID"));
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
        return collaboration;
    }

    /**
     * Update collaboration int.
     *
     * @param collaboration the collaboration
     * @param connection    the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateCollaboration(CollaboratedBy collaboration, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE collaboratedBy SET artistID = ? WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, collaboration.getArtistID());
            statement.setString(2, collaboration.getSongID());
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
     * Delete collaboration int.
     *
     * @param artistID   the artist id
     * @param songID     the song id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteCollaboration(String artistID, String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM collaboratedBy WHERE artistID = ? AND songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, songID);
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
