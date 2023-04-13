package wolfMedia;
import java.sql.*;

/**
 * The type Sponsor.
 */
public class Sponsor {
    private String sponsorID;
    private String sponsorName;

    /**
     * Instantiates a new Sponsor.
     *
     * @param sponsorID   the sponsor id
     * @param sponsorName the sponsor name
     */
    public Sponsor(String sponsorID, String sponsorName) {
        this.sponsorID = sponsorID;
        this.sponsorName = sponsorName;
    }

    /**
     * Gets sponsor id.
     *
     * @return the sponsor id
     */
    public String getSponsorID() {
        return sponsorID;
    }

    /**
     * Sets sponsor id.
     *
     * @param sponsorID the sponsor id
     */
    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    /**
     * Gets sponsor name.
     *
     * @return the sponsor name
     */
    public String getSponsorName() {
        return sponsorName;
    }

    /**
     * Sets sponsor name.
     *
     * @param sponsorName the sponsor name
     */
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    /**
     * Create sponsor int.
     *
     * @param sponsor    the sponsor
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createSponsor(Sponsor sponsor, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO sponsors (sponsorID, sponsorName) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, sponsor.getSponsorID());
            statement.setString(2, sponsor.getSponsorName());
            isInserted = statement.executeUpdate();
            System.out.println("Sponsor created.");
        } catch (SQLException ex) {
            System.out.println("Error creating sponsor: " + ex.getMessage());
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

    /**
     * Read sponsor sponsor.
     *
     * @param sponsorID  the sponsor id
     * @param connection the connection
     * @return the sponsor
     * @throws SQLException the sql exception
     */
    public static Sponsor readSponsor(String sponsorID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Sponsor sponsor = null;
        try {
            String query = "SELECT * FROM sponsors WHERE sponsorID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, sponsorID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sponsor = new Sponsor(resultSet.getString("sponsorID"),
                        resultSet.getString("sponsorName"));
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
        return sponsor;
    }

    /**
     * Update sponsor int.
     *
     * @param sponsor    the sponsor
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateSponsor(Sponsor sponsor, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE sponsors SET sponsorName = ? WHERE sponsorID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, sponsor.getSponsorName());
            statement.setString(2, sponsor.getSponsorID());
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

    /**
     * Delete sponsor int.
     *
     * @param sponsorID  the sponsor id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteSponsor(String sponsorID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM sponsors WHERE sponsorID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, sponsorID);
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
