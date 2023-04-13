package wolfMedia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Part of.
 */
public class PartOf {
    private String podcastID;
    private String podcastEpisodeID;

    /**
     * Instantiates a new Part of.
     *
     * @param podcastID        the podcast id
     * @param podcastEpisodeID the podcast episode id
     */
    public PartOf(String podcastID, String podcastEpisodeID) {
        this.podcastID = podcastID;
        this.podcastEpisodeID = podcastEpisodeID;
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
     * Gets podcast episode id.
     *
     * @return the podcast episode id
     */
    public String getPodcastEpisodeID() {
        return podcastEpisodeID;
    }

    /**
     * Sets podcast episode id.
     *
     * @param podcastEpisodeID the podcast episode id
     */
    public void setPodcastEpisodeID(String podcastEpisodeID) {
        this.podcastEpisodeID = podcastEpisodeID;
    }

    /**
     * Create part of int.
     *
     * @param partOf     the part of
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createPartOf(PartOf partOf, Connection connection) throws SQLException {
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

    /**
     * Gets podcast by podcast episode id.
     *
     * @param podcastEpisodeID the podcast episode id
     * @param conn             the conn
     * @return the podcast by podcast episode id
     * @throws SQLException the sql exception
     */
    public static PodCast getPodcastByPodcastEpisodeID(String podcastEpisodeID,Connection conn) throws SQLException{
        PodCast podcast = null;

        String sql = "SELECT p.* FROM partOf pO JOIN  podcasts p ON p.podcastID = pO.podcastID WHERE pO.podcastEpisodeID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, podcastEpisodeID);
        ResultSet resultSet = pstmt.executeQuery();

        if (resultSet.next()) {
            podcast = new PodCast(resultSet.getString("podcastID"),
                        resultSet.getString("podcastName"),
                        resultSet.getString("language"),
                        resultSet.getInt("episodeCount"),
                        resultSet.getInt("totalSubscribers"),
                        resultSet.getInt("rating"));
        }

        resultSet.close();
        pstmt.close();

        return podcast;
    }

    /**
     * Gets podcast episodes by podcast id.
     *
     * @param podcastID the podcast id
     * @param conn      the conn
     * @return the podcast episodes by podcast id
     * @throws SQLException the sql exception
     */
    public static List<PodcastEpisode> getPodcastEpisodesByPodcastID(String podcastID,Connection conn) throws SQLException{
        List<PodcastEpisode> podcastEpisodes = new ArrayList<>();

        String sql = "SELECT pE.* FROM partOf pO JOIN  podcastEpisodes pE ON pE.podcastEpisodeID = pO.podcastEpisodeID WHERE pO.podcastID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, podcastID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // String podcastEpisodeID, String episodeTitle, Time duration, Date releaseDate,
            // int listeningCount, int advertisementCount
            PodcastEpisode podcastEpisode = new PodcastEpisode(rs.getString("podcastEpisodeID"), rs.getString("episodeTitle"), rs.getString("duration"),
                    rs.getString("releaseDate"), rs.getInt("listeningCount"), rs.getInt("advertisementCount"));
            podcastEpisodes.add(podcastEpisode);
        }

        rs.close();
        pstmt.close();

        return podcastEpisodes;
    }

    /**
     * Read part of part of.
     *
     * @param podcastID        the podcast id
     * @param podcastEpisodeID the podcast episode id
     * @param connection       the connection
     * @return the part of
     * @throws SQLException the sql exception
     */
    public static PartOf readPartOf(String podcastID, String podcastEpisodeID, Connection connection) throws SQLException {
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

    /**
     * Update part of int.
     *
     * @param partOf     the part of
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updatePartOf(PartOf partOf, Connection connection) throws SQLException {
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

    /**
     * Delete part of int.
     *
     * @param podcastID        the podcast id
     * @param podcastEpisodeID the podcast episode id
     * @param connection       the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deletePartOf(String podcastID, String podcastEpisodeID, Connection connection) throws SQLException {
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
