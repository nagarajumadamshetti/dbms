package wolfMedia;
import java.sql.*;
public class Sponsor {
    private String sponsorID;
    private String sponsorName;

    public Sponsor(String sponsorID, String sponsorName) {
        this.sponsorID = sponsorID;
        this.sponsorName = sponsorName;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

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
