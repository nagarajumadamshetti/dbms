package wolfMedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartOf {
    private String podcastID;
    private String podcastEpisodeID;

    public PartOf(String podcastID, String podcastEpisodeID) {
        this.podcastID = podcastID;
        this.podcastEpisodeID = podcastEpisodeID;
    }

    public String getPodcastID() {
        return podcastID;
    }

    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    public String getPodcastEpisodeID() {
        return podcastEpisodeID;
    }

    public void setPodcastEpisodeID(String podcastEpisodeID) {
        this.podcastEpisodeID = podcastEpisodeID;
    }

    public int createPartOf(PartOf partOf, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO partOf (podcastID, podcastEpisodeID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, partOf.getPodcastID());
            statement.setString(2, partOf.getPodcastEpisodeID());
            isInserted = statement.executeUpdate();
            System.out.println("Part of created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating part of: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public PartOf readPartOf(String podcastID, String podcastEpisodeID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PartOf partOf = null;
        try {
            String query = "SELECT * FROM partOf WHERE podcastID = ? AND podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, podcastEpisodeID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                partOf = new PartOf(resultSet.getString("podcastID"),
                        resultSet.getString("podcastEpisodeID"));
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
        return partOf;
    }
    
    public int updatePartOf(PartOf partOf, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE partOf SET podcastEpisodeID = ? WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, partOf.getPodcastEpisodeID());
            statement.setString(2, partOf.getPodcastID());
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
    
    public int deletePartOf(String podcastID, String podcastEpisodeID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM partOf WHERE podcastID = ? AND podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            statement.setString(2, podcastEpisodeID);
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
