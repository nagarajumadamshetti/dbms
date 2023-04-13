package wolfMedia;

import java.sql.*;

/**
 * The type Language.
 */
public class Language {
    private String languageID;
    private String name;

    /**
     * Instantiates a new Language.
     *
     * @param languageID the language id
     * @param name       the name
     */
    public Language(String languageID, String name) {
        this.languageID = languageID;
        this.name = name;
    }

    /**
     * Gets language id.
     *
     * @return the language id
     */
    public String getLanguageID() {
        return languageID;
    }

    /**
     * Sets language id.
     *
     * @param languageID the language id
     */
    public void setLanguageID(String languageID) {
        this.languageID = languageID;
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
     * Create language int.
     *
     * @param language   the language
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createLanguage(Language language, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        
        try {
            String query = "INSERT INTO languages (languageID, name) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, language.getLanguageID());
            statement.setString(2, language.getName());
            isInserted = statement.executeUpdate();
            System.out.println("Language created.");
        } catch (SQLException ex) {
            System.out.println("Error creating language: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Read language language.
     *
     * @param languageID the language id
     * @param connection the connection
     * @return the language
     * @throws SQLException the sql exception
     */
    public static Language readLanguage(String languageID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Language language = null;
        try {
            String query = "SELECT * FROM languages WHERE languageID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, languageID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                language = new Language(resultSet.getString("languageID"),
                        resultSet.getString("name"));
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
        return language;
    }

    /**
     * Update language int.
     *
     * @param language   the language
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateLanguage(Language language, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        
        try {
            String query = "UPDATE languages SET name = ? WHERE languageID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, language.getName());
            statement.setString(2, language.getLanguageID());
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
     * Delete language int.
     *
     * @param languageID the language id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteLanguage(String languageID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM languages WHERE languageID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, languageID);
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
