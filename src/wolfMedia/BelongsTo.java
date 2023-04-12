package wolfMedia;

import java.sql.*;

public class BelongsTo {

    private String albumID;
    private String songID;
    private int trackNumber;
    
    public BelongsTo(String albumID, String songID, int trackNumber) {
        this.albumID = albumID;
        this.songID = songID;
        this.trackNumber = trackNumber;
    }
    
    public String getAlbumID() {
        return albumID;
    }
    
    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }
    
    public String getSongID() {
        return songID;
    }
    
    public void setSongID(String songID) {
        this.songID = songID;
    }
    
    public int getTrackNumber() {
        return trackNumber;
    }
    
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
    
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
    
    public BelongsTo readBelongsTo(String songID, String albumID, Connection connection) throws SQLException {
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
