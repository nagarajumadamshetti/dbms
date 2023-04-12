package wolfMedia;
import java.sql.*;

public class MonthlyListeners {
    private String artistID;
    private String date;
    private int count;
    
    public MonthlyListeners(String artistID, String date, int count) {
        this.artistID = artistID;
        this.date = date;
        this.count = count;
    }
    
    public String getArtistID() {
        return artistID;
    }
    
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
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
