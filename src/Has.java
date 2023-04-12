package wolfMedia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Has {

    private String artistID;
    private String albumID;
    
    public Has(String artistID, String albumID) {
        this.artistID = artistID;
        this.albumID = albumID;
    }
    
    public String getArtistID() {
        return artistID;
    }
    
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
    
    public String getAlbumID() {
        return albumID;
    }
    
    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }
    
    public int createHas(Has has, Connection connection) throws SQLException {
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
    
    public Has readHas(String artistID, String albumID, Connection connection) throws SQLException {
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
    
    public int updateHas(Has has, Connection connection) throws SQLException {
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
    
    public int deleteHas(String artistID, String albumID, Connection connection) throws SQLException {
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
