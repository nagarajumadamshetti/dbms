package wolfMedia;
import java.sql.*;

public class PodcastHostPayment {

    private String paymentID;
    private float flatFee;
    private float bonus;

    public PodcastHostPayment(String paymentID, float flatFee, float bonus) {
        this.paymentID = paymentID;
        this.flatFee = flatFee;
        this.bonus = bonus;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public float getFlatFee() {
        return flatFee;
    }

    public void setFlatFee(float flatFee) {
        this.flatFee = flatFee;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

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