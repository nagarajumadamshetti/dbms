package wolfMedia;
import java.sql.*;

/**
 * The type Podcast host payment.
 */
public class PodcastHostPayment {

    private String paymentID;
    private float flatFee;
    private float bonus;

    /**
     * Instantiates a new Podcast host payment.
     *
     * @param paymentID the payment id
     * @param flatFee   the flat fee
     * @param bonus     the bonus
     */
    public PodcastHostPayment(String paymentID, float flatFee, float bonus) {
        this.paymentID = paymentID;
        this.flatFee = flatFee;
        this.bonus = bonus;
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
     * Gets flat fee.
     *
     * @return the flat fee
     */
    public float getFlatFee() {
        return flatFee;
    }

    /**
     * Sets flat fee.
     *
     * @param flatFee the flat fee
     */
    public void setFlatFee(float flatFee) {
        this.flatFee = flatFee;
    }

    /**
     * Gets bonus.
     *
     * @return the bonus
     */
    public float getBonus() {
        return bonus;
    }

    /**
     * Sets bonus.
     *
     * @param bonus the bonus
     */
    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    /**
     * Create podcast host payment int.
     *
     * @param podcastHostPayment the podcast host payment
     * @param connection         the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createPodcastHostPayment(PodcastHostPayment podcastHostPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO podcastHostPayments (paymentID, flatFee, bonus) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, podcastHostPayment.getPaymentID());
            statement.setFloat(2, podcastHostPayment.getFlatFee());
            statement.setFloat(3, podcastHostPayment.getBonus());
            isInserted = statement.executeUpdate();
            System.out.println("Podcast host payment created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating podcast host payment: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Read podcast host payment podcast host payment.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return the podcast host payment
     * @throws SQLException the sql exception
     */
    public static PodcastHostPayment readPodcastHostPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PodcastHostPayment podcastHostPayment = null;
        try {
            String query = "SELECT * FROM podcastHostPayments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                podcastHostPayment = new PodcastHostPayment(resultSet.getString("paymentID"),
                        resultSet.getFloat("flatFee"),
                        resultSet.getFloat("bonus"));
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
        return podcastHostPayment;
    }

    /**
     * Update podcast host payment int.
     *
     * @param podcastHostPayment the podcast host payment
     * @param connection         the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updatePodcastHostPayment(PodcastHostPayment podcastHostPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE podcastHostPayments SET flatFee=?, bonus=? WHERE paymentID=?";
            statement = connection.prepareStatement(query);
            statement.setFloat(1, podcastHostPayment.getFlatFee());
            statement.setFloat(2, podcastHostPayment.getBonus());
            statement.setString(3, podcastHostPayment.getPaymentID());
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
     * Delete podcast host payment int.
     *
     * @param paymentID  the payment id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deletePodcastHostPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM podcastHostPayments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
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