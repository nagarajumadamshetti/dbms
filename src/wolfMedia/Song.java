package wolfMedia;
import java.sql.*;
// import java.util.Date;

public class Song {
    public String songID;
    public String title;
    public String duration;
    public String releaseDate;
    public float royaltyPaid;
    public float royaltyRate;
    
    public Song(String songID, String title, String duration, String releaseDate, float royaltyPaid, float royaltyRate) {
        this.songID = songID;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.royaltyPaid = royaltyPaid;
        this.royaltyRate = royaltyRate;
    }
    
    public String getSongID() {
        return songID;
    }
    
    public void setSongID(String songID) {
        this.songID = songID;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public float getRoyaltyPaid() {
        return royaltyPaid;
    }
    
    public void setRoyaltyPaid(float royaltyPaid) {
        this.royaltyPaid = royaltyPaid;
    }
    
    public float getRoyaltyRate() {
        return royaltyRate;
    }
    
    public void setRoyaltyRate(float royaltyRate) {
        this.royaltyRate = royaltyRate;
    }
    
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
