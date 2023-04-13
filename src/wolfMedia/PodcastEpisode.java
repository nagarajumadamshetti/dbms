package wolfMedia;

import java.sql.*;

/**
 * The type Podcast episode.
 */
public class PodcastEpisode {

    private String podcastEpisodeID;
    private String episodeTitle;
    private String duration;
    private String releaseDate;
    private int listeningCount;
    private int advertisementCount;

    /**
     * Instantiates a new Podcast episode.
     *
     * @param podcastEpisodeID   the podcast episode id
     * @param episodeTitle       the episode title
     * @param duration           the duration
     * @param releaseDate        the release date
     * @param listeningCount     the listening count
     * @param advertisementCount the advertisement count
     */
    public PodcastEpisode(String podcastEpisodeID, String episodeTitle, String duration, String releaseDate,
            int listeningCount, int advertisementCount) {
        this.podcastEpisodeID = podcastEpisodeID;
        this.episodeTitle = episodeTitle;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.listeningCount = listeningCount;
        this.advertisementCount = advertisementCount;
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
     * Gets episode title.
     *
     * @return the episode title
     */
    public String getEpisodeTitle() {
        return episodeTitle;
    }

    /**
     * Sets episode title.
     *
     * @param episodeTitle the episode title
     */
    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets release date.
     *
     * @return the release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets release date.
     *
     * @param releaseDate the release date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets listening count.
     *
     * @return the listening count
     */
    public int getListeningCount() {
        return listeningCount;
    }

    /**
     * Sets listening count.
     *
     * @param listeningCount the listening count
     */
    public void setListeningCount(int listeningCount) {
        this.listeningCount = listeningCount;
    }

    /**
     * Gets advertisement count.
     *
     * @return the advertisement count
     */
    public int getAdvertisementCount() {
        return advertisementCount;
    }

    /**
     * Sets advertisement count.
     *
     * @param advertisementCount the advertisement count
     */
    public void setAdvertisementCount(int advertisementCount) {
        this.advertisementCount = advertisementCount;
    }

    /**
     * Create podcast episode int.
     *
     * @param episode    the episode
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createPodcastEpisode(PodcastEpisode episode, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, episode.getPodcastEpisodeID());
            statement.setString(2, episode.getEpisodeTitle());
            statement.setString(3, episode.getDuration());
            statement.setString(4, episode.getReleaseDate());
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

    /**
     * Read podcast episode podcast episode.
     *
     * @param podcastEpisodeID the podcast episode id
     * @param connection       the connection
     * @return the podcast episode
     * @throws SQLException the sql exception
     */
    public static PodcastEpisode readPodcastEpisode(String podcastEpisodeID, Connection connection)
            throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastEpisode episode = null;
        try {
            String query = "SELECT * FROM podcastEpisodes WHERE podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastEpisodeID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                episode = new PodcastEpisode(resultSet.getString("podcastEpisodeID"),
                        resultSet.getString("episodeTitle"),
                        resultSet.getString("duration"), resultSet.getString("releaseDate"),
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

    /**
     * Update podcast episode int.
     *
     * @param episode    the episode
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updatePodcastEpisode(PodcastEpisode episode, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastEpisodes SET episodeTitle = ?, duration = ?, releaseDate = ?, listeningCount = ?, advertisementCount = ? WHERE podcastEpisodeID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, episode.getEpisodeTitle());
            statement.setString(2, episode.getDuration());
            statement.setString(3, episode.getReleaseDate());
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

    /**
     * Delete podcast episode int.
     *
     * @param podcastEpisodeID the podcast episode id
     * @param connection       the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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