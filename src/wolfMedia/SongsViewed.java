package wolfMedia;

import java.sql.*;

/**
 * The type Songs viewed.
 */
public class SongsViewed {

    private String songID;
    private String date;
    private int count;

    /**
     * Instantiates a new Songs viewed.
     *
     * @param songID the song id
     * @param date   the date
     * @param count  the count
     */
    public SongsViewed(String songID, String date, int count) {
        this.songID = songID;
        this.date = date;
        this.count = count;
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
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        return date;
    }

    /**
     * Sets string.
     *
     * @param date the date
     */
    public void setString(String date) {
        this.date = date;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Create songs viewed int.
     *
     * @param songsViewed the songs viewed
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createSongsViewed(SongsViewed songsViewed, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO songsViewed (songID, date, count) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, songsViewed.getSongID());
            statement.setString(2, songsViewed.getString());
            statement.setInt(3, songsViewed.getCount());
            isInserted = statement.executeUpdate();
            System.out.println("SongsViewed created.");
        } catch (SQLException ex) {
            System.out.println("Error creating SongsViewed: " + ex.getMessage());
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
     * Read songs viewed songs viewed.
     *
     * @param songID     the song id
     * @param date       the date
     * @param connection the connection
     * @return the songs viewed
     * @throws SQLException the sql exception
     */
    public static SongsViewed readSongsViewed(String songID, String date, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SongsViewed songsViewed = null;
        try {
            String query = "SELECT * FROM songsViewed WHERE songID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            statement.setString(2, date);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                songsViewed = new SongsViewed(resultSet.getString("songID"),
                        resultSet.getString("date"),
                        resultSet.getInt("count"));
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
        return songsViewed;
    }

    /**
     * Update songs viewed int.
     *
     * @param songsViewed the songs viewed
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateSongsViewed(SongsViewed songsViewed, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE songsViewed SET count = ? WHERE songID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, songsViewed.getCount());
            statement.setString(2, songsViewed.getSongID());
            statement.setString(3, songsViewed.getString());
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
     * Delete songs viewed int.
     *
     * @param songID     the song id
     * @param date       the date
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteSongsViewed(String songID, String date, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM songsViewed WHERE songID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            statement.setString(2, date);
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
