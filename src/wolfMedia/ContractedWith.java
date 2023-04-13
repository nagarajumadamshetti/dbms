package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Contracted with.
 */
public class ContractedWith {
    private String artistID;
    private String recordLabelID;

    /**
     * Instantiates a new Contracted with.
     *
     * @param artistID      the artist id
     * @param recordLabelID the record label id
     */
    public ContractedWith(String artistID, String recordLabelID) {
        this.artistID = artistID;
        this.recordLabelID = recordLabelID;
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
     * Gets record label id.
     *
     * @return the record label id
     */
    public String getRecordLabelID() {
        return recordLabelID;
    }

    /**
     * Sets record label id.
     *
     * @param recordLabelID the record label id
     */
    public void setRecordLabelID(String recordLabelID) {
        this.recordLabelID = recordLabelID;
    }

    /**
     * Create contracted with int.
     *
     * @param contractedWith the contracted with
     * @param connection     the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createContractedWith(ContractedWith contractedWith, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO contractedWith (artistID, recordLabelID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, contractedWith.getArtistID());
            statement.setString(2, contractedWith.getRecordLabelID());
            isInserted = statement.executeUpdate();
            System.out.println("ContractedWith created.");
        } catch (SQLException ex) {
            System.out.println("Error creating ContractedWith: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Gets record label contracts by artist id.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the record label contracts by artist id
     * @throws SQLException the sql exception
     */
    public static List<RecordLabel> getRecordLabelContractsByArtistID(String artistID, Connection conn)
            throws SQLException {

        List<RecordLabel> recordLabels = new ArrayList<>();

        String sql = "SELECT rl.* FROM contractedWith cW JOIN recordLabel rl ON cW.recordLabelID = rl.recordLabelID WHERE cW.artistID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, artistID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // String recordLabelID, String name
            RecordLabel recordLabel = new RecordLabel(rs.getString("recordLabelID"), rs.getString("name"));
            recordLabels.add(recordLabel);
        }

        rs.close();
        pstmt.close();

        return recordLabels;
    }

    /**
     * Gets artists by record label id.
     *
     * @param recordLabelID the record label id
     * @param conn          the conn
     * @return the artists by record label id
     * @throws SQLException the sql exception
     */
    public static List<Artist> getArtistsByRecordLabelID(String recordLabelID, Connection conn) throws SQLException {

        List<Artist> artists = new ArrayList<>();

        String sql = "SELECT a.* FROM contractedWith cW JOIN artists a ON cW.artistID = a.artistID WHERE cW.recordLabelID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, recordLabelID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Artist artist = new Artist(rs.getString("artistID"), rs.getString("name"), rs.getString("status"),
                    rs.getString("type"));
            artists.add(artist);
        }

        rs.close();
        pstmt.close();

        return artists;
    }

    /**
     * Read contracted with contracted with.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return the contracted with
     * @throws SQLException the sql exception
     */
    public static ContractedWith readContractedWith(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ContractedWith contractedWith = null;
        try {
            String query = "SELECT * FROM contractedWith WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contractedWith = new ContractedWith(resultSet.getString("artistID"),
                        resultSet.getString("recordLabelID"));
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
        return contractedWith;
    }

    /**
     * Update contracted with int.
     *
     * @param contractedWith the contracted with
     * @param connection     the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateContractedWith(ContractedWith contractedWith, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE contractedWith SET recordLabelID = ? WHERE artistID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, contractedWith.getRecordLabelID());
            statement.setString(2, contractedWith.getArtistID());
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
     * Delete contracted with int.
     *
     * @param artistID   the artist id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteContractedWith(String artistID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM contractedWith WHERE artistID = ?";
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
