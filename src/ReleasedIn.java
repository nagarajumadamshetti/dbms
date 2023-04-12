import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public int createReleasedIn(ReleasedIn releasedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO released_in (songID, countryID) VALUES (?, ?)";
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

    public ReleasedIn readReleasedIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ReleasedIn releasedIn = null;
        try {
            String query = "SELECT * FROM released_in WHERE songID = ?";
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

    public int updateReleasedIn(ReleasedIn releasedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE released_in SET countryID = ? WHERE songID = ?";
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

    public int deleteReleasedIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM released_in WHERE songID = ?";
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
