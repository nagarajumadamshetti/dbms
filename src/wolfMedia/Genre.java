package wolfMedia;

import java.sql.*;

/**
 * The type Genre.
 */
public class Genre {
    private String genreID;
    private String name;

    /**
     * Instantiates a new Genre.
     *
     * @param genreID the genre id
     * @param name    the name
     */
    public Genre(String genreID, String name) {
        this.genreID = genreID;
        this.name = name;
    }

    /**
     * Gets genre id.
     *
     * @return the genre id
     */
    public String getGenreID() {
        return genreID;
    }

    /**
     * Sets genre id.
     *
     * @param genreID the genre id
     */
    public void setGenreID(String genreID) {
        this.genreID = genreID;
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
     * Create genre int.
     *
     * @param genre      the genre
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createGenre(Genre genre, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO genres (genreID, name) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, genre.getGenreID());
            statement.setString(2, genre.getName());
            isInserted = statement.executeUpdate();
            System.out.println("Genre created.");
        } catch (SQLException ex) {
            System.out.println("Error creating genre: " + ex.getMessage());
            return 0;
        }
        return isInserted;
    }

    /**
     * Read genre genre.
     *
     * @param genreID    the genre id
     * @param connection the connection
     * @return the genre
     * @throws SQLException the sql exception
     */
    public static Genre readGenre(String genreID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Genre genre = null;
        try {
            String query = "SELECT * FROM genres WHERE genreID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, genreID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genre = new Genre(resultSet.getString("genreID"),
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
        return genre;
    }

    /**
     * Update genre int.
     *
     * @param genre      the genre
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int updateGenre(Genre genre, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE genres SET name = ? WHERE genreID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, genre.getName());
            statement.setString(2, genre.getGenreID());
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
     * Delete genre int.
     *
     * @param genreID    the genre id
     * @param connection the connection
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteGenre(String genreID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM genres WHERE genreID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, genreID);
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
