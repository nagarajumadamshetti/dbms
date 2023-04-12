package wolfMedia;
import java.sql.*;

public class Album {

    private String albumID;
    private String name;
    private String edition;
    private int releaseYear;

    public Album(String albumID, String name, String edition, int releaseYear) {
        this.albumID = albumID;
        this.name = name;
        this.edition = edition;
        this.releaseYear = releaseYear;
    }

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

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

    public Album readAlbum(String albumID, Connection connection) throws SQLException {
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
            isUpdated=statement.executeUpdate();
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

    public static int deleteAlbum(String albumID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted=0;
        try {
            String query = "DELETE FROM albums WHERE albumID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, albumID);
            isDeleted=statement.executeUpdate();
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
