package wolfMedia;

import java.sql.*;

/**
 * The type Country.
 */
public class Country {
    private String countryID;
    private String name;

    /**
     * Instantiates a new Country.
     *
     * @param countryID the country id
     * @param name      the name
     */
    public Country(String countryID, String name) {
        this.countryID = countryID;
        this.name = name;
    }

    /**
     * Gets country id.
     *
     * @return the country id
     */
    public String getCountryID() {
        return countryID;
    }

    /**
     * Sets country id.
     *
     * @param countryID the country id
     */
    public void setCountryID(String countryID) {
        this.countryID = countryID;
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
     * Create country int.
     *
     * @param country    the country
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createCountry(Country country, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO countries (countryID, name) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, country.getCountryID());
            statement.setString(2, country.getName());
            isInserted = statement.executeUpdate();
            System.out.println("Country created.");
        } catch (SQLException ex) {
            System.out.println("Error creating country: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Read country country.
     *
     * @param countryID  the country id
     * @param connection the connection
     * @return the country
     * @throws SQLException the sql exception
     */
    public static Country readCountry(String countryID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Country country = null;
        try {
            String query = "SELECT * FROM countries WHERE countryID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, countryID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                country = new Country(resultSet.getString("countryID"),
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

    /**
     * Update country int.
     *
     * @param country    the country
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateCountry(Country country, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE countries SET name = ? WHERE countryID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, country.getName());
            statement.setString(2, country.getCountryID());
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
     * Delete country int.
     *
     * @param countryID  the country id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteCountry(String countryID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM countries WHERE countryID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, countryID);
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
