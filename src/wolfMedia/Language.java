package wolfMedia;

import java.sql.*;

public class Language {
    private String languageID;
    private String name;

    public Language(String languageID, String name) {
        this.languageID = languageID;
        this.name = name;
    }

    public String getLanguageID() {
        return languageID;
    }

    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int createLanguage(Language language, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        
        try {
            String query = "INSERT INTO language (languageID, name) VALUES (?, ?)";
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

    public static Language readLanguage(String languageID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Language language = null;
        try {
            String query = "SELECT * FROM language WHERE languageID = ?";
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

    public static int updateLanguage(Language language, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        
        try {
            String query = "UPDATE language SET name = ? WHERE languageID = ?";
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

    public static int deleteLanguage(String languageID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM language WHERE languageID = ?";
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
