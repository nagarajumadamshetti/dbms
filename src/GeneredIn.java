import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneredIn {
    private String songID;
    private String genreID;

    public GeneredIn(String songID, String genreID) {
        this.songID = songID;
        this.genreID = genreID;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public int createGeneredIn(GeneredIn generedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO generedIn (songID, genreID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, generedIn.getSongID());
            statement.setString(2, generedIn.getGenreID());
            isInserted = statement.executeUpdate();
            System.out.println("GeneredIn created.");
        } catch (SQLException ex) {
            System.out.println("Error creating genered in: " + ex.getMessage());
            return 0;
            
        }
        return isInserted;
    }

    public GeneredIn readGeneredIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        GeneredIn generedIn = null;
        try {
            String query = "SELECT * FROM generedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                generedIn = new GeneredIn(resultSet.getString("songID"),
                        resultSet.getString("genreID"));
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
        return generedIn;
    }

    public int updateGeneredIn(GeneredIn generedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE generedIn SET genreID = ? WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, generedIn.getGenreID());
            statement.setString(2, generedIn.getSongID());
            isUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isUpdated;
    }

    public int deleteGeneredIn(String songID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM generedIn WHERE songID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, songID);
            isDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isDeleted;
    }
}
