package wolfMedia;
import java.sql.*;

public class OriginCountry {

    private String podcastID;
    private String countryID;

    public OriginCountry(String podcastID, String countryID) {
        this.podcastID = podcastID;
        this.countryID = countryID;
    }

    public String getPodcastID() {
        return podcastID;
    }

    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public static int createOriginCountry(OriginCountry originCountry, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO originCountry (podcastID, countryID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, originCountry.getPodcastID());
            statement.setString(2, originCountry.getCountryID());
            isInserted = statement.executeUpdate();
            System.out.println("Origin country created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating origin country: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static OriginCountry readOriginCountry(String podcastID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OriginCountry originCountry = null;
        try {
            String query = "SELECT * FROM originCountry WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                originCountry = new OriginCountry(resultSet.getString("podcastID"),
                        resultSet.getString("countryID"));
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
        return originCountry;
    }
    
    public static int updateOriginCountry(OriginCountry originCountry, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE originCountry SET countryID = ? WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, originCountry.getCountryID());
            statement.setString(2, originCountry.getPodcastID());
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
    
    public static int deleteOriginCountry(String podcastID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM originCountry WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
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
