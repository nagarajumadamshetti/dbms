package wolfMedia;
import java.sql.*;
// import java.util.Date;

/**
 * The type Song.
 */
public class Song {
    /**
     * The Song id.
     */
    public String songID;
    /**
     * The Title.
     */
    public String title;
    /**
     * The Duration.
     */
    public String duration;
    /**
     * The Release date.
     */
    public String releaseDate;
    /**
     * The Royalty paid.
     */
    public float royaltyPaid;
    /**
     * The Royalty rate.
     */
    public float royaltyRate;

    /**
     * Instantiates a new Song.
     *
     * @param songID      the song id
     * @param title       the title
     * @param duration    the duration
     * @param releaseDate the release date
     * @param royaltyPaid the royalty paid
     * @param royaltyRate the royalty rate
     */
    public Song(String songID, String title, String duration, String releaseDate, float royaltyPaid, float royaltyRate) {
        this.songID = songID;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.royaltyPaid = royaltyPaid;
        this.royaltyRate = royaltyRate;
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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets release date.
     *
     * @return the release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets release date.
     *
     * @param releaseDate the release date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets royalty paid.
     *
     * @return the royalty paid
     */
    public float getRoyaltyPaid() {
        return royaltyPaid;
    }

    /**
     * Sets royalty paid.
     *
     * @param royaltyPaid the royalty paid
     */
    public void setRoyaltyPaid(float royaltyPaid) {
        this.royaltyPaid = royaltyPaid;
    }

    /**
     * Gets royalty rate.
     *
     * @return the royalty rate
     */
    public float getRoyaltyRate() {
        return royaltyRate;
    }

    /**
     * Sets royalty rate.
     *
     * @param royaltyRate the royalty rate
     */
    public void setRoyaltyRate(float royaltyRate) {
        this.royaltyRate = royaltyRate;
    }

    /**
     * Create song int.
     *
     * @param song       the song
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createSong(Song song, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, song.getSongID());
            statement.setString(2, song.getTitle());
            statement.setString(3, song.getDuration());
            statement.setString(4, song.getReleaseDate());
            statement.setFloat(5, song.getRoyaltyPaid());
            statement.setFloat(6, song.getRoyaltyRate());
            isInserted = statement.executeUpdate();
            System.out.println("Song created.");
        } catch (SQLException ex) {
            System.out.println("Error creating song: " + ex.getMessage());
            if (statement != null) {
                statement.close();
            }
            return 0;
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    /**
     * Read song song.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return the song
     * @throws SQLException the sql exception
     */
    public static Song readSong(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Song song = null;
        try {
            String query = "SELECT * FROM songs WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                song = new Song(resultSet.getString("songID"),
                                 resultSet.getString("title"),
                                 resultSet.getString("duration"),
                                 resultSet.getString("releaseDate"),
                                 resultSet.getFloat("royaltyPaid"),
                                 resultSet.getFloat("royaltyRate"));
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
        return song;
    }

    /**
     * Update song int.
     *
     * @param song       the song
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateSong(Song song, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE songs SET title = ?, duration = ?, releaseDate = ?, royaltyPaid = ?, royaltyRate = ? WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getDuration());
            statement.setString(3, song.getReleaseDate());
            statement.setFloat(4, song.getRoyaltyPaid());
            statement.setFloat(5, song.getRoyaltyRate());
            statement.setString(6, song.getSongID());
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
     * Delete song int.
     *
     * @param songID     the song id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteSong(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM songs WHERE songID = ?";
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
