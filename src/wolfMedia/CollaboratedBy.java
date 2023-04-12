package wolfMedia;
import java.sql.*;

public class CollaboratedBy {

    private String artistID;
    private String songID;

    public CollaboratedBy(String artistID, String songID) {
        this.artistID = artistID;
        this.songID = songID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

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
    
    public CollaboratedBy readCollaboration(String artistID, String songID, Connection connection) throws SQLException {
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
