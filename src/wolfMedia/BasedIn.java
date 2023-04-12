package wolfMedia;

import java.sql.*;

public class BasedIn {
    private String artistID;
    private String countryID;

    public BasedIn(String artistID, String countryID) {
        this.artistID = artistID;
        this.countryID = countryID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public static int createBasedIn(BasedIn basedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO basedIn (artistID, countryID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, basedIn.getArtistID());
            statement.setString(2, basedIn.getCountryID());
            isInserted = statement.executeUpdate();
            System.out.println("BasedIn created.");
        } catch (SQLException ex) {
            System.out.println("Error creating based in: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    public BasedIn readBasedIn(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BasedIn basedIn = null;
        try {
            String query = "SELECT * FROM basedIn WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                basedIn = new BasedIn(resultSet.getString("artistID"),
                        resultSet.getString("countryID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null ;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return basedIn;
    }

    public static int updateBasedIn(BasedIn basedIn, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE basedIn SET countryID = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, basedIn.getCountryID());
            statement.setString(2, basedIn.getArtistID());
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

    public static int deleteBasedIn(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM basedIn WHERE artistID = ?";
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
