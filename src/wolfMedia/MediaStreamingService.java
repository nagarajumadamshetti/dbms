package wolfMedia;
import java.sql.*;

public class MediaStreamingService {
    private String ID;
    private String name;
    private String email;

    public MediaStreamingService(String ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
