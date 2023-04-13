package wolfMedia;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class contains methods for maintaining payments.
 */
public class MaintainPayments {
    public static Scanner input = new Scanner(System.in);

    public static void processPayments(int subChoice) throws SQLException {
        switch (subChoice) {
            case 1:
                makeRoyaltyPaymentsForSong();
                break;
            case 2:
                makePaymentsToPodcastHost();
                break;
            case 3:
                receivePaymentFromSubscribers();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Creates all payment records when a user subscribes.
     *
     */

    private static void receivePaymentFromSubscribers() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("Enter Date:");
        String date = input.nextLine();
        String formattedDate = LocalDate.parse(date).withDayOfMonth(1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("User ID: ");
        String userID = input.nextLine();
        String masterPaymentID = makeMasterPayment(formattedDate, conn);
        if (masterPaymentID.equals("ERROR")) {
            System.out.println("Retry");
        } else {
            makeUserPayments(userID, masterPaymentID, conn); //
            makePaymentsMade(userID, masterPaymentID, conn);
        }
        Connections.close(conn);

    }

    /**
     * Creates userPayments records when a user subscribes.
     *
     * @param usesrID user id of the person making subscription
     * @param masterPaymentID main payment table ID
     * @param connection Connection object to communicate with Database
     * @return nothing (void)
     */

    public static void makeUserPayments(String userID, String masterPaymentID, Connection conn) throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO userPayments (paymentID, paymentAmount) " +
                "select ? as paymentID, u.monthlySubscriptionFee as paymentAmount " +
                "FROM users u " +
                "WHERE u.userID = ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, userID);

        // Execute SQL statement and get number of rows affected

        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        // //System.out.println(rowsAffected + " row(s) affected");
    }

    public static void makePaymentsMade(String userID, String masterPaymentID, Connection conn) throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO paymentsMade (paymentID, userID) " +
                "SELECT ? as paymentID, u.userID as userID " +
                "FROM users u " +
                "WHERE u.userID = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, userID);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        // //System.out.println(rowsAffected + " row(s) affected");
    }

    private static void makePaymentsToPodcastHost() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("Enter Date:");
        String date = input.nextLine();
        String formattedDate = LocalDate.parse(date).withDayOfMonth(1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Podcast Episode ID: ");
        String podcastEpisodeID = input.nextLine();
        String masterPaymentID = makeMasterPayment(formattedDate, conn);
        if (masterPaymentID.equals("ERROR")) {
            System.out.println("Retry");
        } else {
            makePodcastHostPayments(podcastEpisodeID, masterPaymentID, conn); //
            createPodcastPayments(podcastEpisodeID, masterPaymentID, conn);
        }
        Connections.close(conn);

    }

    public static void makePodcastHostPayments(String podcastEpisodeID, String masterPaymentID, Connection conn)
            throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO podcastHostPayments (paymentID, flatFee, bonus) " +
                "select ? as paymentID, 10 as flatFee,( p.rating * ps.advertisementCount) as bonus " +
                "FROM podcasts p " +
                "JOIN partOf po ON po.podcastID=p.podcastID " +
                "JOIN podcastEpisodes ps ON ps.podcastEpisodeID = po.podcastEpisodeID " +
                "WHERE ps.podcastEpisodeID = ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, podcastEpisodeID);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        //System.out.println(rowsAffected + " row(s) affected");
    }

    public static void createPodcastPayments(String podcastEpisodeID, String masterPaymentID, Connection conn)
            throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO podcastPayments (paymentID, podcastHostID) " +
                "SELECT ? as paymentID, ph.podcastHostID as podcastHostID " +
                "FROM podcasts p " +
                "JOIN ownedBy o ON o.podcastID = p.podcastID " +
                "JOIN  podcastHosts ph ON ph.podcastHostID = o.podcastHostID " +
                "JOIN partOf po ON po.podcastID=p.podcastID " +
                "JOIN podcastEpisodes ps ON ps.podcastEpisodeID = po.podcastEpisodeID " +
                "WHERE ps.podcastEpisodeID = ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, podcastEpisodeID);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        //System.out.println(rowsAffected + " row(s) affected");
    }

    private static void makeRoyaltyPaymentsForSong() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month:
        // ");

        System.out.println("Enter Date:");
        String date = input.nextLine();
        String formattedDate = LocalDate.parse(date).withDayOfMonth(1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("SongID: ");
        String songID = input.nextLine();
        String masterPaymentID = makeMasterPayment(formattedDate, conn);
        if (masterPaymentID.equals("ERROR")) {
            System.out.println("Retry");
        } else {
            makeRecordLabelPayment(songID, masterPaymentID, formattedDate, conn); //
            createPaymentsReceivedRecord(songID, masterPaymentID, conn);//
            makeArtistPayment(songID, masterPaymentID, conn);
            makeCollaboratorPayments(songID, masterPaymentID, conn);
            makeMainArtistPayment(songID, masterPaymentID, conn);
        }
        Connections.close(conn);
    }

    public static void makeMainArtistPayment(String songID, String masterPaymentID, Connection conn)
            throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO received (paymentID, artistID) " +
                "SELECT ? as paymentID, sb.artistID " +
                "FROM songs s " +
                "JOIN sungBy sb ON s.songID = sb.songID " +
                "WHERE s.songID = ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, songID);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        //System.out.println(rowsAffected + " row(s) affected");
    }

    public static void makeCollaboratorPayments(String songID, String masterPaymentID, Connection conn)
            throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO received (paymentID, artistID) SELECT ? as paymentID, c.artistID FROM songs s JOIN collaboratedBy c ON c.songID = s.songID WHERE s.songID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, songID);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        //System.out.println(rowsAffected + " row(s) affected");
    }

    public static void makeArtistPayment(String songID, String masterPaymentID, Connection conn) throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO artistPayments (paymentID, paymentAmount) select ? as paymentID, (songsViewed.count * s.royaltyRate) * 0.7/ count(*) FROM songs s JOIN belongsTo b ON s.songID = b.songID JOIN albums a ON b.albumID = a.albumID JOIN has h ON a.albumID = h.albumID JOIN contractedWith c ON h.artistID = c.artistID JOIN recordLabel r ON c.recordLabelID = r.recordLabelID join songsViewed ON s.songID= songsViewed.songID WHERE s.songID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, songID);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        //System.out.println(rowsAffected + " row(s) affected");
    }

    public static void createPaymentsReceivedRecord(String songID, String masterPaymentID, Connection conn)
            throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO paymentsReceived (paymentID, recordLabelID) " +
                "select ? as paymentID, r.recordLabelID as recordLabelID " +
                "FROM songs s " +
                "JOIN sungBy sb ON s.songID = sb.songID " +
                "JOIN artists a ON a.artistID = sb.artistID " +
                "JOIN contractedWith c ON c.artistID = a.artistID " +
                "JOIN recordLabel r ON c.recordLabelID = r.recordLabelID " +
                "WHERE s.songID = ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, songID);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        //System.out.println(rowsAffected + " row(s) affected");
    }

    public static void makeRecordLabelPayment(String songID, String masterPaymentID, String formattedDate,
            Connection conn) throws SQLException {
        // Prepare SQL statement
        String sql = "INSERT INTO recordLabelPayments (paymentID, paymentAmount) " +
                "SELECT ?, songsViewed.count * songs.royaltyRate * 0.3 " +
                "FROM songs " +
                "JOIN songsViewed ON songs.songID = songsViewed.songID " +
                "WHERE songs.songID = ? AND songsViewed.date = ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterPaymentID);
        pstmt.setString(2, songID);
        pstmt.setString(3, formattedDate);

        // Execute SQL statement and get number of rows affected
        ResultSet resultSet = pstmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

        // Close resources
        pstmt.close();

        // Print number of rows affected
        //System.out.println(rowsAffected + " row(s) affected");
    }

    public static String makeMasterPayment(String formattedDate, Connection conn) throws SQLException {
        System.out.println("Enter Master Payment ID: ");
        String paymentID = input.nextLine();
        Payment p = new Payment(paymentID, formattedDate);
        int isCreated = Payment.createPayment(p, conn);
        if (isCreated == 1) {
            return paymentID;
        }
        return "ERROR";
    }

}
