import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongsViewed {

    private String songID;
    private Date date;
    private int count;

    public SongsViewed(String songID, Date date, int count) {
        this.songID = songID;
        this.date = date;
        this.count = count;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int createSongsViewed(SongsViewed songsViewed, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO songsViewed (songID, date, count) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, songsViewed.getSongID());
            statement.setDate(2, songsViewed.getDate());
            statement.setInt(3, songsViewed.getCount());
            isInserted = statement.executeUpdate();
            System.out.println("SongsViewed created.");
        } catch (SQLException ex) {
            System.out.println("Error creating SongsViewed: " + ex.getMessage());
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

    public SongsViewed readSongsViewed(String songID, Date date, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SongsViewed songsViewed = null;
        try {
            String query = "SELECT * FROM songsViewed WHERE songID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            statement.setDate(2, date);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                songsViewed = new SongsViewed(resultSet.getString("songID"),
                        resultSet.getDate("date"),
                        resultSet.getInt("count"));
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
        return songsViewed;
    }

    public int updateSongsViewed(SongsViewed songsViewed, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE songsViewed SET count = ? WHERE songID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, songsViewed.getCount());
            statement.setString(2, songsViewed.getSongID());
            statement.setDate(3, songsViewed.getDate());
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

    public int deleteSongsViewed(String songID, Date date, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM songsViewed WHERE songID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            statement.setDate(2, date);
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
