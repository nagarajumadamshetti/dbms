package wolfMedia;
import java.sql.*;

/**
 * The type Managed by.
 */
public class ManagedBy {
    private String paymentID;
    private String ID;

    /**
     * Instantiates a new Managed by.
     *
     * @param paymentID the payment id
     * @param ID        the id
     */
    public ManagedBy(String paymentID, String ID) {
        this.paymentID = paymentID;
        this.ID = ID;
    }

    /**
     * Gets payment id.
     *
     * @return the payment id
     */
    public String getPaymentID() {
        return paymentID;
    }

    /**
     * Sets payment id.
     *
     * @param paymentID the payment id
     */
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
        return ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Create managed by int.
     *
     * @param managedBy  the managed by
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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

    /**
     * Read managed by managed by.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the managed by
     * @throws SQLException the sql exception
     */
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

    /**
     * Update managed by int.
     *
     * @param managedBy  the managed by
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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

    /**
     * Delete managed by int.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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
