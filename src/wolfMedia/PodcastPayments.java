package wolfMedia;
import java.sql.*;

/**
 * The type Podcast payments.
 */
public class PodcastPayments {
    private String paymentID;
    private String podcastHostID;

    /**
     * Instantiates a new Podcast payments.
     *
     * @param paymentID     the payment id
     * @param podcastHostID the podcast host id
     */
    public PodcastPayments(String paymentID, String podcastHostID) {
        this.paymentID = paymentID;
        this.podcastHostID = podcastHostID;
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
     * Gets podcast host id.
     *
     * @return the podcast host id
     */
    public String getPodcastHostID() {
        return podcastHostID;
    }

    /**
     * Sets podcast host id.
     *
     * @param podcastHostID the podcast host id
     */
    public void setPodcastHostID(String podcastHostID) {
        this.podcastHostID = podcastHostID;
    }

    /**
     * Create podcast payment int.
     *
     * @param podcastPayment the podcast payment
     * @param connection     the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int createPodcastPayment(PodcastPayments podcastPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastPayments (paymentID, podcastHostID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastPayment.getPaymentID());
            statement.setString(2, podcastPayment.getPodcastHostID());
            isInserted = statement.executeUpdate();
            System.out.println("Podcast payment created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating podcast payment: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read podcast payment podcast payments.
     *
     * @param paymentID     the payment id
     * @param podcastHostID the podcast host id
     * @param connection    the connection
     * @return the podcast payments
     * @throws SQLException the sql exception
     */
    public static PodcastPayments readPodcastPayment(String paymentID, String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastPayments podcastPayment = null;
        try {
            String query = "SELECT * FROM podcastPayments WHERE paymentID = ? AND podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            statement.setString(2, podcastHostID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                podcastPayment = new PodcastPayments(resultSet.getString("paymentID"),
                        resultSet.getString("podcastHostID"));
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
        return podcastPayment;
    }

    /**
     * Update podcast payment int.
     *
     * @param podcastPayment the podcast payment
     * @param connection     the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int updatePodcastPayment(PodcastPayments podcastPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastPayments SET paymentID=? WHERE podcastHostID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastPayment.getPaymentID());
            statement.setString(2, podcastPayment.getPodcastHostID());
            isUpdated = statement.executeUpdate();
            return isUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Delete podcast payment int.
     *
     * @param paymentID     the payment id
     * @param podcastHostID the podcast host id
     * @param connection    the connection
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int deletePodcastPayment(String paymentID, String podcastHostID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcastPayments WHERE paymentID = ? AND podcastHostID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            statement.setString(2, podcastHostID);
            isDeleted = statement.executeUpdate();
            return isDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    } 
    
}
