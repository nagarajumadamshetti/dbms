package wolfMedia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sponsored by.
 */
public class SponsoredBy {
    private String podcastID;
    private String sponsorID;

    /**
     * Instantiates a new Sponsored by.
     *
     * @param podcastID the podcast id
     * @param sponsorID the sponsor id
     */
    public SponsoredBy(String podcastID, String sponsorID) {
        this.podcastID = podcastID;
        this.sponsorID = sponsorID;
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
     * Gets sponsor id.
     *
     * @return the sponsor id
     */
    public String getSponsorID() {
        return sponsorID;
    }

    /**
     * Sets sponsor id.
     *
     * @param sponsorID the sponsor id
     */
    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    /**
     * Create sponsored by int.
     *
     * @param sponsoredBy the sponsored by
     * @param connection  the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createSponsoredBy(SponsoredBy sponsoredBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO sponsoredBy (podcastID, sponsorID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, sponsoredBy.getPodcastID());
            statement.setString(2, sponsoredBy.getSponsorID());
            isInserted = statement.executeUpdate();
            System.out.println("SponsoredBy created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating SponsoredBy: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read sponsors by podcast list.
     *
     * @param podcastID  the podcast id
     * @param connection the connection
     * @return the list
     * @throws SQLException the sql exception
     */
    public static List<String> readSponsorsByPodcast(String podcastID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> sponsorIDs = new ArrayList<>();
        try {
            String query = "SELECT sponsorID FROM sponsoredBy WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sponsorIDs.add(resultSet.getString("sponsorID"));
            }
        } catch (SQLException ex) {
            System.out.println("Error reading sponsors by podcast: " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return sponsorIDs;
    }

    /**
     * Read podcasts by sponsor list.
     *
     * @param sponsorID  the sponsor id
     * @param connection the connection
     * @return the list
     * @throws SQLException the sql exception
     */
    public static List<String> readPodcastsBySponsor(String sponsorID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> podcastIDs = new ArrayList<>();
        try {
            String query = "SELECT podcastID FROM sponsoredBy WHERE sponsorID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, sponsorID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                podcastIDs.add(resultSet.getString("podcastID"));
            }
        } catch (SQLException ex) {
            System.out.println("Error reading podcasts by sponsor: " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return podcastIDs;
    }

    /**
     * Delete sponsored by int.
     *
     * @param podcastID  the podcast id
     * @param sponsorID  the sponsor id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteSponsoredBy(String podcastID, String sponsorID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM sponsoredBy WHERE podcastID = ? AND sponsorID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, sponsorID);
            isDeleted = statement.executeUpdate();
            System.out.println("SponsoredBy deleted.");
            return isDeleted;
        } catch (SQLException ex) {
            System.out.println("Error deleting SponsoredBy: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    

}