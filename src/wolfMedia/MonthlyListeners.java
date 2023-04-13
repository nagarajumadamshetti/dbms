package wolfMedia;
import java.sql.*;

/**
 * The type Monthly listeners.
 */
public class MonthlyListeners {
    private String artistID;
    private String date;
    private int count;

    /**
     * Instantiates a new Monthly listeners.
     *
     * @param artistID the artist id
     * @param date     the date
     * @param count    the count
     */
    public MonthlyListeners(String artistID, String date, int count) {
        this.artistID = artistID;
        this.date = date;
        this.count = count;
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
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Create monthly listeners int.
     *
     * @param monthlyListeners the monthly listeners
     * @param connection       the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createMonthlyListeners(MonthlyListeners monthlyListeners, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO monthlyListeners (artistID, date, count) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, monthlyListeners.getArtistID());
            statement.setString(2, monthlyListeners.getDate());
            statement.setInt(3, monthlyListeners.getCount());
            isInserted = statement.executeUpdate();
            System.out.println("Monthly listeners created.");
        } catch (SQLException ex) {
            System.out.println("Error creating monthly listeners: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Read monthly listeners monthly listeners.
     *
     * @param artistID   the artist id
     * @param date       the date
     * @param connection the connection
     * @return the monthly listeners
     * @throws SQLException the sql exception
     */
    public static MonthlyListeners readMonthlyListeners(String artistID, String date, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        MonthlyListeners monthlyListeners = null;
        try {
            String query = "SELECT * FROM monthlyListeners WHERE artistID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, date);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                monthlyListeners = new MonthlyListeners(resultSet.getString("artistID"),
                        resultSet.getString("date"),
                        resultSet.getInt("count"));
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
        return monthlyListeners;
    }

    /**
     * Update monthly listeners int.
     *
     * @param monthlyListeners the monthly listeners
     * @param connection       the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateMonthlyListeners(MonthlyListeners monthlyListeners, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated =0;
        try {
            String query = "UPDATE monthlyListeners SET count = ? WHERE artistID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, monthlyListeners.getCount());
            statement.setString(2, monthlyListeners.getArtistID());
            statement.setString(3, monthlyListeners.getDate());
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
     * Delete monthly listeners int.
     *
     * @param artistID   the artist id
     * @param date       the date
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteMonthlyListeners(String artistID, String date, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM monthlyListeners WHERE artistID = ? AND date = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, artistID);
            statement.setString(2, date);
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
