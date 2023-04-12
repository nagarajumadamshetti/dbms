package wolfMedia;
import java.sql.*;

public class SungBy {
    private String artistID;
    private String songID;

    public SungBy(String artistID, String songID) {
        this.artistID = artistID;
        this.songID = songID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public static int createSungBy(SungBy sungBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO SungBy (artistID, songID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, sungBy.getArtistID());
            statement.setString(2, sungBy.getSongID());
            isInserted = statement.executeUpdate();
            System.out.println("SungBy created.");
        } catch (SQLException ex) {
            System.out.println("Error creating SungBy: " + ex.getMessage());
    			if (statement != null) {
    				statement.close();
    			}
            return 0;
        }
        finally {
			if (statement != null) {
				statement.close();
			}
		}
        return isInserted;
    }

    public static SungBy readSungBy(String artistID, String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SungBy sungBy = null;
        try {
            String query = "SELECT * FROM SungBy WHERE artistID = ? AND songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sungBy = new SungBy(resultSet.getString("artistID"),
                        resultSet.getString("songID"));
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
        return sungBy;
    }

    public static int updateSungBy(SungBy sungBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE SungBy SET songID = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, sungBy.getSongID());
            statement.setString(2, sungBy.getArtistID());
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

    public static int deleteSungBy(String artistID, String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM SungBy WHERE artistID = ? AND songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, songID);
            isDeleted = statement.executeUpdate();
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
