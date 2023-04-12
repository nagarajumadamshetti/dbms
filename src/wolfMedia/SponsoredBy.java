package wolfMedia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SponsoredBy {
    private String podcastID;
    private String sponsorID;

    public SponsoredBy(String podcastID, String sponsorID) {
        this.podcastID = podcastID;
        this.sponsorID = sponsorID;
    }

    public String getPodcastID() {
        return podcastID;
    }

    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

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
    
    public List<String> readSponsorsByPodcast(String podcastID, Connection connection) throws SQLException {
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
    
    public List<String> readPodcastsBySponsor(String sponsorID, Connection connection) throws SQLException {
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