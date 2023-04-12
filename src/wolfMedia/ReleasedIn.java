package wolfMedia;
import java.sql.*;

public class ReleasedIn {
    private String songID;
    private String countryID;

    public ReleasedIn(String songID, String countryID) {
        this.songID = songID;
        this.countryID = countryID;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public static int createReleasedIn(ReleasedIn releasedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO releasedIn (songID, countryID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, releasedIn.getSongID());
            statement.setString(2, releasedIn.getCountryID());
            isInserted = statement.executeUpdate();
            System.out.println("ReleasedIn created.");
        } catch (SQLException ex) {
            System.out.println("Error creating ReleasedIn: " + ex.getMessage());
            if (statement != null) {
                statement.close();
            }
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    public static ReleasedIn readReleasedIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ReleasedIn releasedIn = null;
        try {
            String query = "SELECT * FROM releasedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                releasedIn = new ReleasedIn(resultSet.getString("songID"),
                        resultSet.getString("countryID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return releasedIn;
    }

    public static int updateReleasedIn(ReleasedIn releasedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE releasedIn SET countryID = ? WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, releasedIn.getCountryID());
            statement.setString(2, releasedIn.getSongID());
            isUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isUpdated;
    }

    public static int deleteReleasedIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM releasedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null) {
                statement.close();
            }
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isDeleted;
    }
}
