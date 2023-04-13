package wolfMedia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Received.
 */
public class Received {
    private String paymentID;
    private String artistID;

    /**
     * Instantiates a new Received.
     *
     * @param paymentID the payment id
     * @param artistID  the artist id
     */
    public Received(String paymentID, String artistID) {
        this.paymentID = paymentID;
        this.artistID = artistID;
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
     * Create received int.
     *
     * @param received   the received
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createReceived(Received received, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO received (paymentID, artistID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, received.getPaymentID());
            statement.setString(2, received.getArtistID());
            isInserted = statement.executeUpdate();
            System.out.println("Received created.");
        } catch (SQLException ex) {
            System.out.println("Error creating received: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    /**
     * Gets artist payments.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the artist payments
     * @throws SQLException the sql exception
     */
    public static List<ArtistPayments> getArtistPayments(String artistID, Connection conn) throws SQLException {

        List<ArtistPayments> artistPayments = new ArrayList<>();

        String sql = "SELECT aP.* FROM received r JOIN ArtistPayments aP ON ap.paymentID = r.paymentID WHERE r.artistID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, artistID);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            ArtistPayments artistPayment = new ArtistPayments(rs.getString("paymentID"), rs.getInt("paymentAmount"));
            artistPayments.add(artistPayment);
        }

        rs.close();
        pstmt.close();

        return artistPayments;
    }

    /**
     * Read received received.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the received
     * @throws SQLException the sql exception
     */
    public static Received readReceived(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Received received = null;
        try {
            String query = "SELECT * FROM received WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                received = new Received(resultSet.getString("paymentID"),
                        resultSet.getString("artistID"));
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
        return received;
    }

    /**
     * Update received int.
     *
     * @param received   the received
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateReceived(Received received, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE received SET artistID = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, received.getArtistID());
            statement.setString(2, received.getPaymentID());
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
     * Delete received int.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteReceived(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM received WHERE paymentID = ?";
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
