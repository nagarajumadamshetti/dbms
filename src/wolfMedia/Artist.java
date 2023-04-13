package wolfMedia;
import java.sql.*;

/**
 * The type Artist.
 */
public class Artist {
    /**
     * The Artist id.
     */
    public String artistID;
    /**
     * The Name.
     */
    public String name;
    /**
     * The Status.
     */
    public String status;
    /**
     * The Type.
     */
    public String type;

    /**
     * Instantiates a new Artist.
     *
     * @param artistID the artist id
     * @param name     the name
     * @param status   the status
     * @param type     the type
     */
    public Artist(String artistID, String name, String status, String type) {
        this.artistID = artistID;
        this.name = name;
        this.status = status;
        this.type = type;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Create artist int.
     *
     * @param artist     the artist
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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

    /**
     * Read artist artist.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return the artist
     * @throws SQLException the sql exception
     */
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

    /**
     * Update artist int.
     *
     * @param artist     the artist
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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

    /**
     * Delete artist int.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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
