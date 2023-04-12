package wolfMedia;
import java.sql.*;
public class RecordLabel {
    private String recordLabelID;
    private String name;

    public RecordLabel(String recordLabelID, String name) {
        this.recordLabelID = recordLabelID;
        this.name = name;
    }

    public String getRecordLabelID() {
        return recordLabelID;
    }

    public void setRecordLabelID(String recordLabelID) {
        this.recordLabelID = recordLabelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int createRecordLabel(RecordLabel recordLabel, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        
        try {
            String query = "INSERT INTO recordLabel (recordLabelID, name) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, recordLabel.getRecordLabelID());
            statement.setString(2, recordLabel.getName());
            isInserted = statement.executeUpdate();
            System.out.println("Record label created.");
        } catch (SQLException ex) {
            System.out.println("Error creating record label: " + ex.getMessage());
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

    public static RecordLabel readRecordLabel(String recordLabelID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RecordLabel recordLabel = null;
        try {
            String query = "SELECT * FROM recordLabel WHERE recordLabelID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, recordLabelID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                recordLabel = new RecordLabel(resultSet.getString("recordLabelID"),
                        resultSet.getString("name"));
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
        return recordLabel;
    }

    public static int updateRecordLabel(RecordLabel recordLabel, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE recordLabel SET name = ? WHERE recordLabelID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, recordLabel.getName());
            statement.setString(2, recordLabel.getRecordLabelID());
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

    public static int deleteRecordLabel(String recordLabelID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM recordLabel WHERE recordLabelID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, recordLabelID);
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