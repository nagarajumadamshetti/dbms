import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrimaryGenre {
    private String artistID;
    private String genreID;

    public PrimaryGenre(String artistID, String genreID) {
        this.artistID = artistID;
        this.genreID = genreID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public int createPrimaryGenre(PrimaryGenre primaryGenre, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO primarygenre (artistID, genreID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, primaryGenre.getArtistID());
            statement.setString(2, primaryGenre.getGenreID());
            isInserted = statement.executeUpdate();
            System.out.println("Primary genre created.");
        } catch (SQLException ex) {
            System.out.println("Error creating primary genre: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    public PrimaryGenre readPrimaryGenre(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PrimaryGenre primaryGenre = null;
        try {
            String query = "SELECT * FROM primarygenre WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                primaryGenre = new PrimaryGenre(resultSet.getString("artistID"), resultSet.getString("genreID"));
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
        return primaryGenre;
    }

    public int updatePrimaryGenre(PrimaryGenre primaryGenre, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE primarygenre SET genreID = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, primaryGenre.getGenreID());
            statement.setString(2, primaryGenre.getArtistID());
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

    public int deletePrimaryGenre(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        
        try {
            String query = "DELETE FROM primarygenre WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
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
