package wolfMedia;
import java.sql.*;
public class PodcastGeneredIn {

    private String podcastID;
    private String genreID;

    public PodcastGeneredIn(String podcastID, String genreID) {
        this.podcastID = podcastID;
        this.genreID = genreID;
    }

    public String getPodcastID() {
        return podcastID;
    }

    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public static int createPodcastGeneredIn(PodcastGeneredIn podcastGeneredIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastGeneredIn (podcastID, genreID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastGeneredIn.getPodcastID());
            statement.setString(2, podcastGeneredIn.getGenreID());
            isInserted = statement.executeUpdate();
            System.out.println("PodcastGeneredIn created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating PodcastGeneredIn: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public PodcastGeneredIn readPodcastGeneredIn(String podcastID, String genreID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastGeneredIn podcastGeneredIn = null;
        try {
            String query = "SELECT * FROM podcastGeneredIn WHERE podcastID = ? AND genreID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, genreID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                podcastGeneredIn = new PodcastGeneredIn(resultSet.getString("podcastID"),
                        resultSet.getString("genreID"));
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
        return podcastGeneredIn;
    }
    
    public static int updatePodcastGeneredIn(PodcastGeneredIn podcastGeneredIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastGeneredIn SET genreID = ? WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastGeneredIn.getGenreID());
            statement.setString(2, podcastGeneredIn.getPodcastID());
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
    
    public static int deletePodcastGeneredIn(String podcastID, String genreID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcastGeneredIn WHERE podcastID = ? AND genreID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, genreID);
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
