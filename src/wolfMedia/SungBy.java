package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sung by.
 */
public class SungBy {
    private String artistID;
    private String songID;

    /**
     * Instantiates a new Sung by.
     *
     * @param artistID the artist id
     * @param songID   the song id
     */
    public SungBy(String artistID, String songID) {
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
     * Create sung by int.
     *
     * @param sungBy     the sung by
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createSungBy(SungBy sungBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO SungBy (artistID, songID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, sungBy.getArtistID());
            statement.setString(2, sungBy.getSongID());
            isInserted = statement.executeUpdate();
            System.out.println("SungBy created.");
        } catch (SQLException ex) {
            System.out.println("Error creating SungBy: " + ex.getMessage());
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
     * Gets songs by artist id.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the songs by artist id
     * @throws SQLException the sql exception
     */
    public static List<Song> getSongsByArtistID(String artistID, Connection conn) throws SQLException {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT s.* FROM sungBy sb JOIN Songs s ON sb.songID = s.songID WHERE sb.artistID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, artistID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // String songID, String title, String duration, String releaseDate, float
            // royaltyPaid, float royaltyRate
            Song song = new Song(rs.getString("songID"), rs.getString("title"), rs.getString("duration"),
                    rs.getString("releaseDate"), rs.getBoolean("royaltyPaid"), rs.getFloat("royaltyRate"));
            songs.add(song);
        }

        rs.close();
        pstmt.close();

        return songs;
    }

    /**
     * Read sung by song id sung by.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return the sung by
     * @throws SQLException the sql exception
     */
    public static SungBy readSungBySongID(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SungBy sungBy = null;
        try {
            String query = "SELECT * FROM SungBy WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sungBy = new SungBy(resultSet.getString("artistID"),
                        resultSet.getString("songID"));
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
        return sungBy;
    }

    /**
     * Read sung by sung by.
     *
     * @param artistID   the artist id
     * @param songID     the song id
     * @param connection the connection
     * @return the sung by
     * @throws SQLException the sql exception
     */
    public static SungBy readSungBy(String artistID, String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SungBy sungBy = null;
        try {
            String query = "SELECT * FROM SungBy WHERE artistID = ? AND songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sungBy = new SungBy(resultSet.getString("artistID"),
                        resultSet.getString("songID"));
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
        return sungBy;
    }

    /**
     * Update sung by int.
     *
     * @param sungBy     the sung by
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateSungBy(SungBy sungBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE SungBy SET songID = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, sungBy.getSongID());
            statement.setString(2, sungBy.getArtistID());
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
     * Delete sung by int.
     *
     * @param artistID   the artist id
     * @param songID     the song id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteSungBy(String artistID, String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM SungBy WHERE artistID = ? AND songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, songID);
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
