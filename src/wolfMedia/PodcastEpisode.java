package wolfMedia;
import java.sql.*;

public class PodcastEpisode {

    private String podcastEpisodeID;
    private String episodeTitle;
    private Time duration;
    private Date releaseDate;
    private int listeningCount;
    private int advertisementCount;

    public PodcastEpisode(String podcastEpisodeID, String episodeTitle, Time duration, Date releaseDate,
            int listeningCount, int advertisementCount) {
        this.podcastEpisodeID = podcastEpisodeID;
        this.episodeTitle = episodeTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.listeningCount = listeningCount;
        this.advertisementCount = advertisementCount;
    }

    public String getPodcastEpisodeID() {
        return podcastEpisodeID;
    }

    public void setPodcastEpisodeID(String podcastEpisodeID) {
        this.podcastEpisodeID = podcastEpisodeID;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getListeningCount() {
        return listeningCount;
    }

    public void setListeningCount(int listeningCount) {
        this.listeningCount = listeningCount;
    }

    public int getAdvertisementCount() {
        return advertisementCount;
    }

    public void setAdvertisementCount(int advertisementCount) {
        this.advertisementCount = advertisementCount;
    }

    public static int createPodcastEpisode(PodcastEpisode episode, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, episode.getPodcastEpisodeID());
            statement.setString(2, episode.getEpisodeTitle());
            statement.setTime(3, episode.getDuration());
            statement.setDate(4, episode.getReleaseDate());
            statement.setInt(5, episode.getListeningCount());
            statement.setInt(6, episode.getAdvertisementCount());
            isInserted = statement.executeUpdate();
            System.out.println("Podcast episode created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating podcast episode: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public PodcastEpisode readPodcastEpisode(String podcastEpisodeID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastEpisode episode = null;
        try {
            String query = "SELECT * FROM podcastEpisodes WHERE podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastEpisodeID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                episode = new PodcastEpisode(resultSet.getString("podcastEpisodeID"), resultSet.getString("episodeTitle"),
                        resultSet.getTime("duration"), resultSet.getDate("releaseDate"),
                        resultSet.getInt("listeningCount"), resultSet.getInt("advertisementCount"));
            }
        } catch (SQLException ex) {
            System.out.println("Error reading podcast episode: " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return episode;
    }
    
    public static int updatePodcastEpisode(PodcastEpisode episode, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastEpisodes SET episodeTitle = ?, duration = ?, releaseDate = ?, listeningCount = ?, advertisementCount = ? WHERE podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, episode.getEpisodeTitle());
            statement.setTime(2, episode.getDuration());
            statement.setDate(3, episode.getReleaseDate());
            statement.setInt(4, episode.getListeningCount());
            statement.setInt(5, episode.getAdvertisementCount());
            statement.setString(6, episode.getPodcastEpisodeID());
            isUpdated = statement.executeUpdate();
            return isUpdated;
        } catch (SQLException ex) {
            System.out.println("Error updating podcast episode: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static int deletePodcastEpisode(String podcastEpisodeID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcastEpisodes WHERE podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastEpisodeID);
            isDeleted = statement.executeUpdate();
            return isDeleted;
        } catch (SQLException ex) {
            System.out.println("Error deleting podcast episode: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
}    