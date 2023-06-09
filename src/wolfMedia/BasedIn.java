package wolfMedia;

import java.sql.*;

/**
 * The type Based in.
 */
public class BasedIn {
    private String artistID;
    private String countryID;

    /**
     * Instantiates a new Based in.
     *
     * @param artistID  the artist id
     * @param countryID the country id
     */
    public BasedIn(String artistID, String countryID) {
        this.artistID = artistID;
        this.countryID = countryID;
    }

    /**
     * Gets artist id.
     *
     * @return the artist id
     */
    public String getArtistID() {
        return artistID;
    }

    /**
     * Sets artist id.
     *
     * @param artistID the artist id
     */
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    /**
     * Gets country id.
     *
     * @return the country id
     */
    public String getCountryID() {
        return countryID;
    }

    /**
     * Sets country id.
     *
     * @param countryID the country id
     */
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    /**
     * Create based in int.
     *
     * @param basedIn    the based in
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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

    /**
     * Read based in based in.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return the based in
     * @throws SQLException the sql exception
     */
    public static BasedIn readBasedIn(String artistID, Connection connection) throws SQLException {
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

    /**
     * Update based in int.
     *
     * @param basedIn    the based in
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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

    /**
     * Delete based in int.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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
