package wolfMedia;
import java.sql.*;

public class SungIn {
	private String songID;
	private String languageID;

	public SungIn(String songID, String languageID) {
		this.songID = songID;
		this.languageID = languageID;
	}

	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID) {
		this.songID = songID;
	}

	public String getLanguageID() {
		return languageID;
	}

	public void setLanguageID(String languageID) {
		this.languageID = languageID;
	}

	public static int createSungIn(SungIn sungIn, Connection connection) throws SQLException {
		PreparedStatement statement = null;
		int isInserted = 0;
		try {
			String query = "INSERT INTO sungIn (songID, languageID) VALUES (?, ?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, sungIn.getSongID());
			statement.setString(2, sungIn.getLanguageID());
			isInserted = statement.executeUpdate();
			System.out.println("SungIn created.");
		} catch (SQLException ex) {
			System.out.println("Error creating SungIn: " + ex.getMessage());
			if (statement != null) {
				statement.close();
			}
			return 0;
		}
		finally {
			if (statement != null) {
				statement.close();
			}
		}

		return isInserted;
	}

	public SungIn readSungIn(String songID, Connection connection) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		SungIn sungIn = null;
		try {
			String query = "SELECT * FROM sungIn WHERE songID = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, songID);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				sungIn = new SungIn(resultSet.getString("songID"), resultSet.getString("languageID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (statement != null) {
				statement.close();
			}
			return null;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return sungIn;
	}

	public static int updateSungIn(SungIn sungIn, Connection connection) throws SQLException {
		PreparedStatement statement = null;
		int isUpdated = 0;
		try {
			String query = "UPDATE sungIn SET languageID = ? WHERE songID = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, sungIn.getLanguageID());
			statement.setString(2, sungIn.getSongID());
			isUpdated = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			if (statement != null) {
				statement.close();
			}
			return 0;
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return isUpdated;
	}

	public static int deleteSungIn(String songID, Connection connection) throws SQLException {
		PreparedStatement statement = null;
		int isDeleted = 0;
		try {
			String query = "DELETE FROM sungIn WHERE songID = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, songID);
			isDeleted = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			if (statement != null) {
				statement.close();
			}
			return 0;
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return isDeleted;
	}
}
