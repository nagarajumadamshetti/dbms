import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagedBy {
    private String paymentID;
    private String ID;

    public ManagedBy(String paymentID, String ID) {
        this.paymentID = paymentID;
        this.ID = ID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int createManagedBy(ManagedBy managedBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        
        try {
            String query = "INSERT INTO managed_by (paymentID, ID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, managedBy.getPaymentID());
            statement.setString(2, managedBy.getID());
            isInserted = statement.executeUpdate();
            System.out.println("ManagedBy created.");
        } catch (SQLException ex) {
            System.out.println("Error creating managed by: " + ex.getMessage()); 
            return 0;
        }
        return isInserted;
    }

    public ManagedBy readManagedBy(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ManagedBy managedBy = null;
        try {
            String query = "SELECT * FROM managed_by WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                managedBy = new ManagedBy(resultSet.getString("paymentID"),
                        resultSet.getString("ID"));
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
        return managedBy;
    }

    public int updateManagedBy(ManagedBy managedBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE managed_by SET ID = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, managedBy.getID());
            statement.setString(2, managedBy.getPaymentID());
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

    public int deleteManagedBy(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM managed_by WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
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
