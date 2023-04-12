package wolfMedia;

import java.sql.*;

public class Country {
    private int countryID;
    private String name;

    public Country(int countryID, String name) {
        this.countryID = countryID;
        this.name = name;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int createCountry(Country country, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO country (countryID, name) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, country.getCountryID());
            statement.setString(2, country.getName());
            isInserted = statement.executeUpdate();
            System.out.println("Country created.");
        } catch (SQLException ex) {
            System.out.println("Error creating country: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    public static Country readCountry(int countryID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Country country = null;
        try {
            String query = "SELECT * FROM country WHERE countryID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, countryID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                country = new Country(resultSet.getInt("countryID"),
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
        return country;
    }

    public static int updateCountry(Country country, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE country SET name = ? WHERE countryID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, country.getName());
            statement.setInt(2, country.getCountryID());
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

    public static int deleteCountry(int countryID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM country WHERE countryID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, countryID);
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
