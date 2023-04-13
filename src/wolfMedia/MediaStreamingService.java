package wolfMedia;
import java.sql.*;

/**
 * The type Media streaming service.
 */
public class MediaStreamingService {
    private String ID;
    private String name;
    private String email;

    /**
     * Instantiates a new Media streaming service.
     *
     * @param ID    the id
     * @param name  the name
     * @param email the email
     */
    public MediaStreamingService(String ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
        return ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID(String ID) {
        this.ID = ID;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Create media streaming service int.
     *
     * @param mediaStreamingService the media streaming service
     * @param connection            the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createMediaStreamingService(MediaStreamingService mediaStreamingService, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO mediaStreamingService (ID, name, email) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, mediaStreamingService.getID());
            statement.setString(2, mediaStreamingService.getName());
            statement.setString(3, mediaStreamingService.getEmail());
            isInserted = statement.executeUpdate();
            System.out.println("MediaStreamingService created.");
        } catch (SQLException ex) {
            System.out.println("Error creating media streaming service: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Read media streaming service media streaming service.
     *
     * @param ID         the id
     * @param connection the connection
     * @return the media streaming service
     * @throws SQLException the sql exception
     */
    public static MediaStreamingService readMediaStreamingService(String ID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        MediaStreamingService mediaStreamingService = null;
        try {
            String query = "SELECT * FROM mediaStreamingService WHERE ID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, ID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                mediaStreamingService = new MediaStreamingService(resultSet.getString("ID"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null ;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return mediaStreamingService;
    }

    /**
     * Update media streaming service int.
     *
     * @param mediaStreamingService the media streaming service
     * @param connection            the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateMediaStreamingService(MediaStreamingService mediaStreamingService, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE mediaStreamingService SET name = ?, email = ? WHERE ID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, mediaStreamingService.getName());
            statement.setString(2, mediaStreamingService.getEmail());
            statement.setString(3, mediaStreamingService.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isUpdated;
    }

    /**
     * Delete media streaming service int.
     *
     * @param ID         the id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteMediaStreamingService(String ID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM mediaStreamingService WHERE ID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, ID);
            isDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isDeleted;
    }
}
