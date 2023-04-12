package wolfMedia;
import java.sql.*;

public class PodCast {

    private String podcastID;
    private String podcastName;
    private String language;
    private int episodeCount;
    private int totalSubscribers;
    private int rating;

    public PodCast(String podcastID, String podcastName, String language, int episodeCount, int totalSubscribers, int rating) {
        this.podcastID = podcastID;
        this.podcastName = podcastName;
        this.language = language;
        this.episodeCount = episodeCount;
        this.totalSubscribers = totalSubscribers;
        this.rating = rating;
    }

    public String getPodcastID() {
        return podcastID;
    }

    public void setPodcastID(String podcastID) {
        this.podcastID = podcastID;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getTotalSubscribers() {
        return totalSubscribers;
    }

    public void setTotalSubscribers(int totalSubscribers) {
        this.totalSubscribers = totalSubscribers;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public static int createPodcast(PodCast podcast, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcasts (podcastID, podcastName, language, episodeCount, totalSubscribers, rating) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcast.getPodcastID());
            statement.setString(2, podcast.getPodcastName());
            statement.setString(3, podcast.getLanguage());
            statement.setInt(4, podcast.getEpisodeCount());
            statement.setInt(5, podcast.getTotalSubscribers());
            statement.setInt(6, podcast.getRating());
            isInserted = statement.executeUpdate();
            System.out.println("PodCast created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating podcast: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static PodCast readPodcast(String podcastID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodCast podcast = null;
        try {
            String query = "SELECT * FROM podcasts WHERE podcastID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                podcast = new PodCast(resultSet.getString("podcastID"),
                        resultSet.getString("podcastName"),
                        resultSet.getString("language"),
                        resultSet.getInt("episodeCount"),
                        resultSet.getInt("totalSubscribers"),
                        resultSet.getInt("rating"));
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
        return podcast;
    }
    
    public static int updatePodcast(PodCast podcast, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcasts SET podcastName=?, language=?, episodeCount=?, totalSubscribers=?, rating=? WHERE podcastID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcast.getPodcastName());
            statement.setString(2, podcast.getLanguage());
            statement.setInt(3, podcast.getEpisodeCount());
            statement.setInt(4, podcast.getTotalSubscribers());
            statement.setInt(5, podcast.getRating());
            statement.setString(6, podcast.getPodcastID());
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
    
    public static int deletePodcast(String podcastID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcasts WHERE podcastID = ?";
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


    