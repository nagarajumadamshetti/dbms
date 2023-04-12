package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractedWith {
    private String artistID;
    private String recordLabelID;

    public ContractedWith(String artistID, String recordLabelID) {
        this.artistID = artistID;
        this.recordLabelID = recordLabelID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getRecordLabelID() {
        return recordLabelID;
    }

    public void setRecordLabelID(String recordLabelID) {
        this.recordLabelID = recordLabelID;
    }

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

    public static List<RecordLabel> getRecordLabelContractsByArtistID(String artistID, Connection conn) throws SQLException {

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
