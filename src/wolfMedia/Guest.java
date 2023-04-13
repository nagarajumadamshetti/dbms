package wolfMedia;
import java.sql.*;

/**
 * The type Guest.
 */
public class Guest {
    private String guestID;
    private String name;

    /**
     * Instantiates a new Guest.
     *
     * @param guestID the guest id
     * @param name    the name
     */
    public Guest(String guestID, String name) {
        this.guestID = guestID;
        this.name = name;
    }

    /**
     * Gets guest id.
     *
     * @return the guest id
     */
    public String getGuestID() {
        return guestID;
    }

    /**
     * Sets guest id.
     *
     * @param guestID the guest id
     */
    public void setGuestID(String guestID) {
        this.guestID = guestID;
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
     * Create guest int.
     *
     * @param guest      the guest
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createGuest(Guest guest, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO guest (guestID, name) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, guest.getGuestID());
            statement.setString(2, guest.getName());
            isInserted = statement.executeUpdate();
            System.out.println("Guest created.");
        } catch (SQLException ex) {
            System.out.println("Error creating guest: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Read guest guest.
     *
     * @param guestID    the guest id
     * @param connection the connection
     * @return the guest
     * @throws SQLException the sql exception
     */
    public static Guest readGuest(String guestID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Guest guest = null;
        try {
            String query = "SELECT * FROM guest WHERE guestID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, guestID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                guest = new Guest(resultSet.getString("guestID"),
                        resultSet.getString("name"));
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
        return guest;
    }

    /**
     * Update guest int.
     *
     * @param guest      the guest
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updateGuest(Guest guest, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE guest SET name = ? WHERE guestID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getGuestID());
            statement.executeUpdate();
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
     * Delete guest int.
     *
     * @param guestID    the guest id
     * @param connection the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deleteGuest(String guestID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM guest WHERE guestID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, guestID);
            statement.executeUpdate();
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
