package wolfMedia;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Generate reports.
 */
public class GenerateReports {
    /**
     * The constant input.
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * Process choice.
     *
     * @param subChoice4 the sub choice 4
     * @throws SQLException the sql exception
     */
    public static void processChoice(int subChoice4) throws SQLException {
        switch (subChoice4) {
            case 1:
                calculateMonthlyPlayCount();
                break;
            case 2:
                calculateTotalPayments();
                break;
            case 3:
                calculateTotalRevenue();
                break;
            case 4:
                reportSongsAndPodcastEpisodes();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void reportSongsAndPodcastEpisodes() throws SQLException {
    System.out.println("Choose an option to report all songs/podcast episodes:");
    System.out.println("1. Report Songs by Artist");
    System.out.println("2. Report Songs by Album");
    System.out.println("3. Report Podcast Episodes by Podcast");
    System.out.print("Choice: ");
    int choice = Integer.parseInt(input.nextLine());

    switch (choice) {
        case 1:
            // Implement logic to report all songs by artist
            reportSongsByArtist();
            break;
        case 2:
            // Implement logic to report all songs by album
            reportSongsByAlbum();
            break;
        case 3:
            // Implement logic to report all podcast episodes by podcast
            reportPodcastEpisodesByPodcast();
            break;
        default:
            System.out.println("Invalid choice.");
            break;
    }
}

private static void reportPodcastEpisodesByPodcast() throws SQLException {
    // code for updating play count for songs
    Connection conn = Connections.open();

    System.out.println("PodcastID: ");
    String podcastID = input.nextLine();

    podcastEpisodesByPodcastID(podcastID, conn);

    Connections.close(conn);
}

private static void podcastEpisodesByPodcastID(String podcastID, Connection conn) throws SQLException {
    // Prepare SQL statement
    String sql = " SELECT podcastEpisodes.* "+
    "FROM podcastEpisodes "+
    "INNER JOIN partOf ON podcastEpisodes.podcastEpisodeID = partOf.podcastEpisodeID "+
    "INNER JOIN podcasts ON partOf.podcastID = podcasts.podcastID  "+
    "WHERE podcasts.podcastID= ? ";

    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, podcastID);
    // Execute SQL statement and get number of rows affected

    ResultSet resultSet = pstmt.executeQuery();
    printResultSet(resultSet);
    // Close resources
    pstmt.close();
}

private static void reportSongsByAlbum() throws SQLException {
    // code for updating play count for songs
    Connection conn = Connections.open();

    System.out.println("AlbumID: ");
    String albumID = input.nextLine();

    songsByAlbumID(albumID, conn);

    Connections.close(conn);
}

private static void songsByAlbumID(String albumID, Connection conn) throws SQLException {
    // Prepare SQL statement
    String sql = " SELECT songs.* "+
    "FROM songs "+
    "INNER JOIN belongsTo ON songs.songID = belongsTo.songID  "+
    "INNER JOIN albums ON belongsTo.albumID = albums.albumID  "+
    "WHERE albums.albumID =?";

    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, albumID);
    // Execute SQL statement and get number of rows affected

    ResultSet resultSet = pstmt.executeQuery();
    printResultSet(resultSet);
    // Close resources
    pstmt.close();
}

private static void reportSongsByArtist() throws SQLException {
    // code for updating play count for songs
    Connection conn = Connections.open();

    System.out.println("ArtistID: ");
    String artistID = input.nextLine();

    songsByArtistID(artistID, conn);

    Connections.close(conn);

}

private static void songsByArtistID(String artistID, Connection conn) throws SQLException {
    // code for displaying play count for songID
    // Prepare SQL statement
    String sql = " SELECT songs.* "+ 
    "FROM songs  "+
    "INNER JOIN sungBy ON songs.songID = sungBy.songID  "+
    "INNER JOIN artists ON sungBy.artistID = artists.artistID  "+
    "WHERE artists.artistID = ?";

    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, artistID);
    // Execute SQL statement and get number of rows affected

    ResultSet resultSet = pstmt.executeQuery();
    printResultSet(resultSet);
    // Close resources
    pstmt.close();
}

    private static void calculateTotalRevenue() throws SQLException {

        System.out.println("Choose Total revenue of the streaming service:");
        System.out.println("1. Per Month");
        System.out.println("2. Per Year");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                // Implement logic to calculate monthly play count for a song
                calculateTotalRevenuePerMonth();
                break;
            case 2:
                // Implement logic to calculate monthly play count for an artist
                calculateTotalRevenuePerYear();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void calculateTotalRevenuePerMonth() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        // System.out.println("PodcastHostID: ");
        // String podcastHostID = input.nextLine();

        // System.out.println("Enter From Date yyyy-mm-dd: ");
        // String fromDate= input.nextLine();

        // System.out.println("Enter To Date yyyy-mm-dd: ");
        // String toDate= input.nextLine();

        totalRevenuePerMonth(conn);

        Connections.close(conn);

    }

    private static void totalRevenuePerMonth(Connection conn) throws SQLException {
        // code for displaying play count for songID
        // Prepare SQL statement
        String sql = "SELECT DATE_FORMAT(payments.date, '%Y-%m') AS month, "
        + "COALESCE(SUM(recordLabelPayments.paymentAmount),0) + "
        + "COALESCE(SUM(artistPayments.paymentAmount),0) + "
        + "COALESCE(SUM(userPayments.paymentAmount),0) AS total_revenue "
        + "FROM payments "
        + "LEFT JOIN recordLabelPayments ON payments.paymentID = recordLabelPayments.paymentID "
        + "LEFT JOIN artistPayments ON payments.paymentID = artistPayments.paymentID "
        + "LEFT JOIN userPayments ON payments.paymentID = userPayments.paymentID "
        + "GROUP BY DATE_FORMAT(payments.date, '%Y-%m')";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);
        // Close resources
        pstmt.close();
    }

    private static void calculateTotalRevenuePerYear() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        // System.out.println("PodcastHostID: ");
        // String podcastHostID = input.nextLine();

        // System.out.println("Enter From Date yyyy-mm-dd: ");
        // String fromDate= input.nextLine();

        // System.out.println("Enter To Date yyyy-mm-dd: ");
        // String toDate= input.nextLine();

        totalRevenuePerYear(conn);

        Connections.close(conn);

    }

    private static void totalRevenuePerYear(Connection conn) throws SQLException {
        // code for displaying play count for songID
        // Prepare SQL statement
        String sql = "SELECT YEAR(payments.date) AS year, "
             + "COALESCE(SUM(recordLabelPayments.paymentAmount),0) + "
             + "COALESCE(SUM(artistPayments.paymentAmount),0) + "
             + "COALESCE(SUM(userPayments.paymentAmount),0) AS total_revenue "
             + "FROM payments "
             + "LEFT JOIN recordLabelPayments ON payments.paymentID = recordLabelPayments.paymentID "
             + "LEFT JOIN artistPayments ON payments.paymentID = artistPayments.paymentID "
             + "LEFT JOIN userPayments ON payments.paymentID = userPayments.paymentID "
             + "GROUP BY YEAR(payments.date)";


        PreparedStatement pstmt = conn.prepareStatement(sql);
        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);
        // Close resources
        pstmt.close();
    }

    private static void calculateTotalPayments() throws SQLException {

        System.out.println("Choose the type of item to calculate Total Payments:");
        System.out.println("1. Podcast Host");
        System.out.println("2. Artist");
        System.out.println("3. Record Label");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                // Implement logic to calculate monthly play count for a song
                calculateTotalPaymentsForPodcastHost();
                break;
            case 2:
                // Implement logic to calculate monthly play count for an artist
                calculateTotalPaymentsForArtist();
                break;
            case 3:
                // Implement logic to calculate monthly play count for an album
                calculateTotalPaymentsForRecordLabel();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    private static void calculateTotalPaymentsForPodcastHost() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("PodcastHostID: ");
        String podcastHostID = input.nextLine();

        System.out.println("Enter From Date yyyy-mm-dd: ");
        String fromDate= input.nextLine();

        System.out.println("Enter To Date yyyy-mm-dd: ");
        String toDate= input.nextLine();

        totalPaymmentsForPodcastHostID(podcastHostID, fromDate,toDate, conn);

        Connections.close(conn);

    }

    private static void totalPaymmentsForPodcastHostID(String podcastHostID,String fromDate,String toDate, Connection conn) throws SQLException {
        // code for displaying play count for songID
        // Prepare SQL statement
        String sql = " SELECT podcastHosts.firstName, SUM(podcastHostPayments.flatFee + podcastHostPayments.bonus) AS totalPayments "+
        "FROM podcastHostPayments "+
        "JOIN podcastPayments ON podcastHostPayments.paymentID = podcastPayments.paymentID "+
        "JOIN payments ON podcastHostPayments.paymentID = payments.paymentID "+
        "JOIN podcastHosts ON podcastHosts.podcastHostID = podcastPayments.podcastHostID "+
        "WHERE "+
        "podcastHosts.podcastHostID= ? AND  "+
        "payments.date BETWEEN ? AND ? "+
        "GROUP BY podcastHosts.podcastHostID";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, podcastHostID);
        pstmt.setString(2, fromDate);
        pstmt.setString(3, toDate);
        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);
        // Close resources
        pstmt.close();
    }

    private static void calculateTotalPaymentsForArtist() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("ArtistID: ");
        String artistID = input.nextLine();

        System.out.println("Enter From Date yyyy-mm-dd: ");
        String fromDate= input.nextLine();

        System.out.println("Enter To Date yyyy-mm-dd: ");
        String toDate= input.nextLine();

        totalPaymmentsForArtistID(artistID, fromDate,toDate, conn);

        Connections.close(conn);

    }

    private static void totalPaymmentsForArtistID(String artistID,String fromDate,String toDate, Connection conn) throws SQLException {
        // code for displaying play count for songID
        // Prepare SQL statement
        String sql = " SELECT artists.name, SUM(artistPayments.paymentAmount) AS total_payments "+
        "FROM artistPayments "+
        "JOIN received ON artistPayments.paymentID = received.paymentID "+
        "JOIN artists ON received.artistID = artists.artistID "+
        "JOIN payments ON artistPayments.paymentID = payments.paymentID "+
        "WHERE artists.artistID= ? AND "+
        "payments.date BETWEEN ? AND ? "+
        "GROUP BY artists.artistID";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, artistID);
        pstmt.setString(2, fromDate);
        pstmt.setString(3, toDate);
        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);
        // Close resources
        pstmt.close();
    }

    private static void calculateTotalPaymentsForRecordLabel() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("RecordLabelID: ");
        String recordLabelID = input.nextLine();

        System.out.println("Enter From Date yyyy-mm-dd: ");
        String fromDate= input.nextLine();

        System.out.println("Enter To Date yyyy-mm-dd: ");
        String toDate= input.nextLine();

        totalPaymmentsForRecoredLabelID(recordLabelID, fromDate,toDate, conn);

        Connections.close(conn);

    }

    private static void totalPaymmentsForRecoredLabelID(String recordlabelID,String fromDate,String toDate, Connection conn) throws SQLException {
        // code for displaying play count for songID
        // Prepare SQL statement
        String sql = " SELECT recordLabel.name, SUM(paymentAmount) AS total_payments "+
        "FROM recordLabelPayments "+
        "JOIN paymentsReceived ON paymentsReceived.paymentID = recordLabelPayments.paymentID "+
        "JOIN payments ON recordLabelPayments.paymentID = payments.paymentID "+
        "JOIN recordLabel ON recordLabel.recordLabelID = paymentsReceived.recordLabelID "+
        "WHERE recordLabel.recordLabelID= ?  AND "+
        "payments.date BETWEEN ? AND ? "+
        "GROUP BY recordLabel.name";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, recordlabelID);
        pstmt.setString(2, fromDate);
        pstmt.setString(3, toDate);
        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);
        // Close resources
        pstmt.close();
    }

    private static void calculateMonthlyPlayCount() throws SQLException {

        System.out.println("Choose the type of item to calculate monthly play count:");
        System.out.println("1. Song");
        System.out.println("2. Artist");
        System.out.println("3. Album");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                // Implement logic to calculate monthly play count for a song
                calculatePlayCountForSong();
                break;
            case 2:
                // Implement logic to calculate monthly play count for an artist
                calculatePlayCountForArtist();
                break;
            case 3:
                // Implement logic to calculate monthly play count for an album
                calculatePlayCountForAlbum();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void calculatePlayCountForArtist() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("artistID: ");
        String artistID = input.nextLine();

        playCountForArtistID(artistID, conn);

        Connections.close(conn);

    }

    private static void playCountForArtistID(String artistID, Connection conn) throws SQLException {
        // code for displaying play count for songID
        // Prepare SQL statement
        String sql = " SELECT ar.name, YEAR(ml.date) AS year, DATE_FORMAT(CONCAT(YEAR(ml.date),'-',MONTH(ml.date),'-01'), '%M') AS month, SUM(ml.count) AS play_count " +
                "FROM artists ar " +
                "INNER JOIN monthlyListeners ml ON ar.artistID = ml.artistID " +
                "where ar.artistID= ? " +
                "GROUP BY ar.artistID, YEAR(ml.date), MONTH(ml.date)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, artistID);

        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);
        // Close resources
        pstmt.close();
    }

    private static void calculatePlayCountForAlbum() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("albumID: ");
        String albumID = input.nextLine();

        playCountForAlbumID(albumID, conn);

        Connections.close(conn);

    }

    private static void playCountForAlbumID(String albumID, Connection conn) throws SQLException {
        String sql ="SELECT a.albumID, a.name, YEAR(temp.date) AS year, DATE_FORMAT(CONCAT(YEAR(temp.date),'-',MONTH(temp.date),'-01'), '%M') AS month, SUM(temp.play_count) AS album_views "+
        "FROM albums a "+
        "INNER JOIN belongsTo bt ON a.albumID = bt.albumID "+
        "INNER JOIN ( "+
        "    SELECT sv.songID, sv.date, SUM(sv.count) AS play_count "+
        "    FROM songsViewed sv "+
        "    GROUP BY sv.songID, YEAR(sv.date), MONTH(sv.date) "+
        ") temp ON bt.songID = temp.songID  "+
        "WHERE a.albumID = ? "+
        "GROUP BY a.albumID, YEAR(temp.date), MONTH(temp.date)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, albumID);

        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);

        // Close resources
        pstmt.close();
    }

    private static void calculatePlayCountForSong() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("songID: ");
        String songID = input.nextLine();

        playCountForSongID(songID, conn);

        Connections.close(conn);

    }

    private static void playCountForSongID(String songID, Connection conn) throws SQLException {
        // code for displaying play count for songID
        // Prepare SQL statement
        String sql = "SELECT s.songID, s.title, YEAR(sv.date) AS year,DATE_FORMAT(CONCAT(YEAR(sv.date),'-',MONTH(sv.date),'-01'), '%M') AS month, SUM(sv.count) AS play_count "+
                "FROM songs s " +
                "INNER JOIN songsViewed sv ON s.songID = sv.songID " +
                "where s.songID= ? " +
                "GROUP BY s.songID, YEAR(sv.date), MONTH(sv.date)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, songID);

        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        printResultSet(resultSet);

        pstmt.close();
    }

    /**
     * Print result set.
     *
     * @param resultSet the result set
     * @throws SQLException the sql exception
     */
    public static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numColumns = metaData.getColumnCount();
        
        // Print column headers
        for (int i = 1; i <= numColumns; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.format("+-----------------------");
            for (int j = 0; j < columnName.length(); j++) {
                System.out.print("-");
            }
            System.out.format("-------");
        }
        System.out.format("+%n");
        
        for (int i = 1; i <= numColumns; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.format("| %-" + columnName.length() + "s ", columnName);
        }
        System.out.format("|%n");
        
        // Print separator row
        for (int i = 1; i <= numColumns; i++) {
            System.out.format("+-----------------------");
            for (int j = 0; j < metaData.getColumnName(i).length(); j++) {
                System.out.print("-");
            }
            System.out.format("-------");
        }
        System.out.format("+%n");
        
        // Print data rows
        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                Object value = resultSet.getObject(i);
                String stringValue = (value == null) ? "null" : value.toString();
                System.out.format("| %-" + metaData.getColumnName(i).length() + "s ", stringValue);
            }
            System.out.format("|%n");
        }
        
        // Print bottom border
        for (int i = 1; i <= numColumns; i++) {
            System.out.format("+-----------------------");
            for (int j = 0; j < metaData.getColumnName(i).length(); j++) {
                System.out.print("-");
            }
            System.out.format("-------");
        }
        System.out.format("+%n");
        

    }
}
