package wolfMedia;
import java.sql.*;

/**
 * The type Podcast host.
 */
public class PodcastHost {

    private String podcastHostID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;

    /**
     * Instantiates a new Podcast host.
     *
     * @param podcastHostID the podcast host id
     * @param firstName     the first name
     * @param lastName      the last name
     * @param phone         the phone
     * @param email         the email
     * @param city          the city
     */
    public PodcastHost(String podcastHostID, String firstName, String lastName, String phone, String email, String city) {
        this.podcastHostID = podcastHostID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    /**
     * Gets podcast host id.
     *
     * @return the podcast host id
     */
    public String getPodcastHostID() {
        return podcastHostID;
    }

    /**
     * Sets podcast host id.
     *
     * @param podcastHostID the podcast host id
     */
    public void setPodcastHostID(String podcastHostID) {
        this.podcastHostID = podcastHostID;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Create podcast host int.
     *
     * @param podcastHost the podcast host
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createPodcastHost(PodcastHost podcastHost, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastHosts (podcastHostID, firstName, lastName, phone, email, city) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastHost.getPodcastHostID());
            statement.setString(2, podcastHost.getFirstName());
            statement.setString(3, podcastHost.getLastName());
            statement.setString(4, podcastHost.getPhone());
            statement.setString(5, podcastHost.getEmail());
            statement.setString(6, podcastHost.getCity());
            isInserted = statement.executeUpdate();
            System.out.println("Podcast host created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating podcast host: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read podcast host podcast host.
     *
     * @param podcastHostID the podcast host id
     * @param connection    the connection
     * @return the podcast host
     * @throws SQLException the sql exception
     */
    public static PodcastHost readPodcastHost(String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastHost podcastHost = null;
        try {
            String query = "SELECT * FROM podcastHosts WHERE podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastHostID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                podcastHost = new PodcastHost(resultSet.getString("podcastHostID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("city"));
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
        return podcastHost;
    }

    /**
     * Update podcast host int.
     *
     * @param podcastHost the podcast host
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updatePodcastHost(PodcastHost podcastHost, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastHosts SET firstName=?, lastName=?, phone=?, email=?, city=? WHERE podcastHostID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastHost.getFirstName());
            statement.setString(2, podcastHost.getLastName());
            statement.setString(3, podcastHost.getPhone());
            statement.setString(4, podcastHost.getEmail());
            statement.setString(5, podcastHost.getCity());
            statement.setString(6, podcastHost.getPodcastHostID());
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
     * Delete podcast host int.
     *
     * @param podcastHostID the podcast host id
     * @param connection    the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deletePodcastHost(String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcastHosts WHERE podcastHostID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastHostID);
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