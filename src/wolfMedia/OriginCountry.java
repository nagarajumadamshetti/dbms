package wolfMedia;
import java.sql.*;

/**
 * The type Origin country.
 */
public class OriginCountry {

    private String podcastID;
    private String countryID;

    /**
     * Instantiates a new Origin country.
     *
     * @param podcastID the podcast id
     * @param countryID the country id
     */
    public OriginCountry(String podcastID, String countryID) {
        this.podcastID = podcastID;
        this.countryID = countryID;
    }

    /**
     * Gets podcast id.
     *
     * @return the podcast id
     */
    public String getPodcastID() {
        return podcastID;
    }

    /**
     * Sets podcast id.
     *
     * @param podcastID the podcast id
     */
    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    /**
     * Gets country id.
     *
     * @return the country id
     */
    public String getCountryID() {
        return countryID;
    }

    /**
     * Sets country id.
     *
     * @param countryID the country id
     */
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    /**
     * Create origin country int.
     *
     * @param originCountry the origin country
     * @param connection    the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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

    /**
     * Read origin country origin country.
     *
     * @param podcastID  the podcast id
     * @param connection the connection
     * @return the origin country
     * @throws SQLException the sql exception
     */
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

    /**
     * Update origin country int.
     *
     * @param originCountry the origin country
     * @param connection    the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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

    /**
     * Delete origin country int.
     *
     * @param podcastID  the podcast id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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
