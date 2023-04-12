package wolfMedia;
import java.sql.*;

public class Artist {
    private String artistID;
    private String name;
    private String status;
    private String type;

    public Artist(String artistID, String name, String status, String type) {
        this.artistID = artistID;
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public static int createArtist(Artist artist, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO artists (artistID, name, status, type) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, artist.getArtistID());
            statement.setString(2, artist.getName());
            statement.setString(3, artist.getStatus());
            statement.setString(4, artist.getType());
            isInserted = statement.executeUpdate();
            System.out.println("Artist created.");
        } catch (SQLException ex) {
            System.out.println("Error creating artist: " + ex.getMessage());
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

    public static Artist readArtist(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Artist artist = null;
        try {
            String query = "SELECT * FROM artists WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                artist = new Artist(resultSet.getString("artistID"),
                        resultSet.getString("name"),
                        resultSet.getString("status"),
                        resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
            return null ;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return artist;
    }

    public static int updateArtist(Artist artist, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE artists SET name = ?, status = ?, type = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artist.getName());
            statement.setString(2, artist.getStatus());
            statement.setString(3, artist.getType());
            statement.setString(4, artist.getArtistID());
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

    public static int deleteArtist(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted=0;
        try {
            String query = "DELETE FROM artists WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            isDeleted=statement.executeUpdate();
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
