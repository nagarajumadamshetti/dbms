package wolfMedia;
import java.sql.*;

/**
 * The type Record label.
 */
public class RecordLabel {
    /**
     * The Record label id.
     */
    public String recordLabelID;
    /**
     * The Name.
     */
    public String name;

    /**
     * Instantiates a new Record label.
     *
     * @param recordLabelID the record label id
     * @param name          the name
     */
    public RecordLabel(String recordLabelID, String name) {
        this.recordLabelID = recordLabelID;
        this.name = name;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Create record label int.
     *
     * @param recordLabel the record label
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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

    /**
     * Read record label record label.
     *
     * @param recordLabelID the record label id
     * @param connection    the connection
     * @return the record label
     * @throws SQLException the sql exception
     */
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

    /**
     * Update record label int.
     *
     * @param recordLabel the record label
     * @param connection  the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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

    /**
     * Delete record label int.
     *
     * @param recordLabelID the record label id
     * @param connection    the connection
     * @return the int
     * @throws SQLException the sql exception
     */
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
