package wolfMedia;
import java.sql.*;

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

    public static int createManagedBy(ManagedBy managedBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        
        try {
            String query = "INSERT INTO managedBy (paymentID, ID) VALUES (?, ?)";
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

    public static ManagedBy readManagedBy(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ManagedBy managedBy = null;
        try {
            String query = "SELECT * FROM managedBy WHERE paymentID = ?";
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

    public static int updateManagedBy(ManagedBy managedBy, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE managedBy SET ID = ? WHERE paymentID = ?";
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

    public static int deleteManagedBy(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM managedBy WHERE paymentID = ?";
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
