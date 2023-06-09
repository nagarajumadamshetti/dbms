package wolfMedia;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Artist information processing.
 */
public class ArtistInformationProcessing {
    /**
     * The constant input.
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * Process artist.
     *
     * @throws SQLException the sql exception
     */
    public static void processArtist() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create artist");
        System.out.println("2. Update artist");
        System.out.println("3. Delete artist");
        System.out.println("4. Read artist information");
        System.out.println("5. Assign Artist To Album");
        System.out.println("6. Assign Artist To RecordLabel");
        System.out.print("Choice: ");

        int subChoice3 = input.nextInt();

        input.nextLine(); // consume newline character

        switch (subChoice3) {
            case 1:
                createArtist();
                break;
            case 2:
                updateArtist();
                break;
            case 3:
                deleteArtist();
                break;
            case 4:
                readArtist();
                break;
            case 5:
                assignArtistToAlbum();
                break;
            case 6:
                assignArtistToRecordLabel();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    /**
     * Create Artist
     */
    private static void createArtist() throws SQLException {
        System.out.println("Enter artist information:");
        System.out.print("Artist ID: ");
        String artistID = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Status active/inactive: ");
        String status = input.nextLine();
        System.out.print("Type solo/band: ");
        String type = input.nextLine();
        Connection conn = null;

        Artist a = new Artist(artistID, name, status, type);
        int isCreated = 0;
        int isBasedInCreated = 0;
        int isSongCreated = 0;
        int isSungByCreated = 0;
        int areCollaboratorsCreated = 0;
        int areAlbumsLinked = 0;
        int isPrimaryGenreCreated = 0;
        int isArtistMonthlyListenersCreated = 0;
        int isArtistContractsAdded = 0;
        try {
            conn = Connections.open();
            conn.setAutoCommit(false);
    
            isCreated = Artist.createArtist(a, conn);
            if (isCreated == 0) {
                System.out.println("Artist not created");
                conn.rollback();
            } else {
                isBasedInCreated = createBasedIn(artistID, conn);
    
                System.out.println("Want to add a song? Enter yes/no");
                String response = input.nextLine();
                if (response.equals("yes")) {
                    isSongCreated = SongInformationProcessing.createSong("artist");
                } else {
                    System.out.println("Insted just Want to add song IDs? Enter yes/no");
                    response = input.nextLine();
                    if (response.equals("yes")) {
                        isSungByCreated = createSungBy(artistID, conn);
                    }
                }
    
                System.out.println("Want to add any collaborations with other artist's songs? Enter yes/no");
                response = input.nextLine();
                if (response.equals("yes")) {
                    areCollaboratorsCreated = createCollaborations(artistID, conn);
                }
    
                System.out.println("Want to add artist to any existing albums? Enter yes/no");
                response = input.nextLine();
                if (response.equals("yes")) {
                    areAlbumsLinked = createHasAlbums(artistID, conn);
                }
    
                isPrimaryGenreCreated = createArtistPrimaryGeneredIn(artistID, conn);
    
                isArtistMonthlyListenersCreated = createArtistMonthlyListeners(artistID, conn);
    
                isArtistContractsAdded = addArtistRecordLabelcontracts(artistID, conn);
    
                if (isBasedInCreated == 0 || isSongCreated == 0 || isSungByCreated == 0 ||
                    areCollaboratorsCreated == 0 || areAlbumsLinked == 0 ||
                    isPrimaryGenreCreated == 0 || isArtistMonthlyListenersCreated == 0 ||
                    isArtistContractsAdded == 0) {
                    conn.rollback();
                    System.out.println("Changes rolled back.");
                } else {
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            Connections.close(conn);
        }
    }
    
    /**
     * Assign Artist To Album.
     *
     * @return nothing
     * @throws SQLException the sql exception
     */
    public static void assignArtistToAlbum() throws SQLException {
        Connection conn = Connections.open();
        System.out.println("EnterArtist ID\n");
        String artistID = input.nextLine();
        int isCreated = 0;
        if (!artistID.isEmpty()) {
            isCreated = createHasAlbums(artistID, conn);
        }
        if (isCreated == 0) {
            System.out.println("Artist not assigned to Albums mentioned");
        } else {
            System.out.println("Artist with ID: " + artistID + "has been assigned to respective Albums");
        }
        Connections.close(conn);
    }

    /**
     * Assign Artist To RecordLabel.
     *
     * @return nothing
     * @throws SQLException the sql exception
     */
    public static void assignArtistToRecordLabel() throws SQLException {
        Connection conn = Connections.open();
        System.out.println("EnterArtist ID\n");
        String artistID = input.nextLine();
        int isCreated = 0;
        if (!artistID.isEmpty()) {
            isCreated = addArtistRecordLabelcontracts(artistID, conn);
        }
        if (isCreated == 0) {
            System.out.println("Artist not assigned to Record Label");
        } else {
            System.out.println("Artist with ID: " + artistID + "has been assigned to respective Record Label");
        }
        Connections.close(conn);
    }

    /**
     * Add artist record labelcontracts int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int addArtistRecordLabelcontracts(String artistID, Connection conn) throws SQLException {
        System.out.println("Enter  Record Label ID\n");
        String recordLabelID = input.nextLine();
        int isCreated = 0;
        if (!recordLabelID.isEmpty()) {
            ContractedWith cW = new ContractedWith(artistID, recordLabelID);
            isCreated = ContractedWith.createContractedWith(cW, conn);
        }
        return isCreated;
    }

    /**
     * Create artist primary genered in int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createArtistPrimaryGeneredIn(String artistID, Connection conn) throws SQLException {
        System.out.println(
                "Enter Artist Primary Genre: \n 1: Pop\n 2: Rock\n 3: Hip hop\n 4: Electronic\n 5: Classical\n 6: Country\n 7: Jazz\n 8: Blues\n");
        String genreID = input.nextLine();
        int isCreated = 0;
        if (!genreID.isEmpty()) {
            PrimaryGenre pG = new PrimaryGenre(artistID, genreID);
            isCreated = PrimaryGenre.createPrimaryGenre(pG, conn);
        }
        return isCreated;
    }

    /**
     * Delete artist primary genered in int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteArtistPrimaryGeneredIn(String artistID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = PrimaryGenre.deletePrimaryGenre(artistID, conn);
        return isDeleted;
    }

    /**
     * Create has albums int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createHasAlbums(String artistID, Connection conn) throws SQLException {
        System.out.println("Enter album IDs by space: ");
        String[] artistAlbumIDs = input.nextLine().split(" ");
        int isCreated = 0;
        for (int i = 0; i < artistAlbumIDs.length; i++) {
            Has h = new Has(artistID, artistAlbumIDs[i]);
            isCreated = Has.createHas(h, conn);
        }
        return isCreated;
    }

    /**
     * Create collaborations int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createCollaborations(String artistID, Connection conn) throws SQLException {
        System.out.println("Collaborated song IDs by space: ");
        String[] collaboratedSongIDs = input.nextLine().split(" ");
        int isCreated = 0;
        for (int i = 0; i < collaboratedSongIDs.length; i++) {
            CollaboratedBy cB = new CollaboratedBy(artistID, collaboratedSongIDs[i]);
            isCreated = CollaboratedBy.createCollaboration(cB, conn);
        }
        return isCreated;
    }

    /**
     * Delete collaboration int.
     *
     * @param artistID the artist id
     * @param songID   the song id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteCollaboration(String artistID, String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = CollaboratedBy.deleteCollaboration(artistID, songID, conn);
        return isDeleted;
    }

    /**
     * Add payment received int.
     *
     * @param artistID  the artist id
     * @param paymentID the payment id
     * @param conn      the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int addPaymentReceived(String artistID, String paymentID, Connection conn) throws SQLException {
        Received r = new Received(paymentID, artistID);
        int isCreated = Received.createReceived(r, conn);
        return isCreated;
    }

    /**
     * Create based in int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createBasedIn(String artistID, Connection conn) throws SQLException {
        System.out.println(
                "Artist is Based In: \n 1: United States\n 2: United Kingdom\n 3: Canada\n 4: Australia\n 5: Japan\n 6: Germany\n 7: France\n 8: Brazil");
        String countryID = input.nextLine();
        int isCreated = 0;
        if (!countryID.isEmpty()) {
            BasedIn bI = new BasedIn(artistID, countryID);
            isCreated = BasedIn.createBasedIn(bI, conn);
        }
        return isCreated;
    }

    /**
     * Delete based in int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteBasedIn(String artistID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = BasedIn.deleteBasedIn(artistID, conn);
        return isDeleted;
    }

    /**
     * Increase artist monthly listeners int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int increaseArtistMonthlyListeners(String artistID, Connection conn) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String date = currentDate.format(formatter);

        MonthlyListeners mL = MonthlyListeners.readMonthlyListeners(artistID, date, conn);
        if (mL == null) {
            mL = new MonthlyListeners(artistID, date, 1);
            MonthlyListeners.createMonthlyListeners(mL, conn);
            return 1;
        } else {
            int count = mL.getCount();
            mL.setCount(count + 1);
            MonthlyListeners.updateMonthlyListeners(mL, conn);
            return count + 1;
        }
    }

    /**
     * Create artist monthly listeners int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createArtistMonthlyListeners(String artistID, Connection conn) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String date = currentDate.format(formatter);

        MonthlyListeners mLRead= MonthlyListeners.readMonthlyListeners(artistID, date, conn);
        if(mLRead!=null){
            return 1;
        }

        MonthlyListeners mL = new MonthlyListeners(artistID, date, 0);
        int isCreated = MonthlyListeners.createMonthlyListeners(mL, conn);
        return isCreated;
    }

    /**
     * Create sung by int.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createSungBy(String artistID, Connection conn) throws SQLException {
        int isCreated = 0;
        while (true) {
            System.out.println("Want to add a songID? Enter yes/no: ");
            String res = input.nextLine();
            if (res == "no") {
                break;
            }
            System.out.println("Enter songID: ");
            String songID = input.nextLine();
            if (!songID.isEmpty()) {
                SungBy sB = new SungBy(artistID, songID);
                isCreated = SungBy.createSungBy(sB, conn);
            }
        }
        return isCreated;
    }

    /**
     * Delete sung by int.
     *
     * @param artistID the artist id
     * @param songID   the song id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteSungBy(String artistID, String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = SungBy.deleteSungBy(artistID, songID, conn);
        return isDeleted;
    }

    /**
     * Update Artist
     */
    private static void updateArtist() throws SQLException {
        System.out.println("Enter artist ID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();
        Artist aToUpdate = Artist.readArtist(updateID, conn);
    
        if (aToUpdate == null) {
            System.out.println("Artist with ID " + updateID + " does not exist");
            Connections.close(conn);
            return;
        }
    
        conn.setAutoCommit(false); // Begin transaction
    
        try {
            System.out.println("Enter new artist information:");
            System.out.print("Name: ");
            aToUpdate.setName(input.nextLine());
            System.out.print("Status active/inactive: ");
            aToUpdate.setStatus(input.nextLine());
            System.out.print("Type solo/band: ");
            aToUpdate.setType(input.nextLine());
            int isUpdated = Artist.updateArtist(aToUpdate, conn);
    
            if (isUpdated == 0) {
                System.out.println("Failed to update artist");
                conn.rollback(); // Rollback transaction
            } else {
                System.out.println("Artist updated successfully");
                conn.commit(); // Commit transaction
            }
    
        } catch (SQLException ex) {
            System.out.println("Error updating artist: " + ex.getMessage());
            conn.rollback(); // Rollback transaction
        } finally {
            Connections.close(conn);
        }
    }
    
    

    private static void deleteArtist() throws SQLException {
        System.out.println("Enter artist ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
        Artist artistToDelete = Artist.readArtist(deleteID, conn);
        if (artistToDelete == null) {
            System.out.println("Artist with ID " + deleteID + " does not exist");
            Connections.close(conn);
            return;
        }
        conn.setAutoCommit(false); // Begin transaction
        try {
            int isDeleted = Artist.deleteArtist(deleteID, conn);
            if (isDeleted == 0) {
                System.out.println("Failed to delete artist");
                conn.rollback(); // Rollback transaction
            } else {
                System.out.println("Artist with ID " + deleteID + " is deleted");
                // TODO: delete artist relationship tables - done with cascase delete
                conn.commit(); // Commit transaction
            }
        } catch (SQLException ex) {
            System.out.println("Error deleting artist: " + ex.getMessage());
            conn.rollback(); // Rollback transaction
        } finally {
            Connections.close(conn);
        }
    }
    

    /**
     * Gets artist payments.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the artist payments
     * @throws SQLException the sql exception
     */
    public static List<ArtistPayments> getArtistPayments(String artistID, Connection conn) throws SQLException {
        List<ArtistPayments> artistPayments = Received.getArtistPayments(artistID, conn);
        if (artistPayments.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (ArtistPayments artistPayment : artistPayments) {
                System.out.println("  " + artistPayment.getPaymentID() + " - " + artistPayment.getPaymentAmount());
            }
        }
        return artistPayments;
    }

    /**
     * Artist based in string.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the string
     * @throws SQLException the sql exception
     */
    public static String artistBasedIn(String artistID, Connection conn) throws SQLException {
        BasedIn rI = BasedIn.readBasedIn(artistID, conn);
        if (rI == null) {
            return "(none)";
        }
        Country c = Country.readCountry(rI.getCountryID(), conn);
        return c.getName();
    }

    /**
     * Artist monthly viewed int.
     *
     * @param artistID the artist id
     * @param date     the date
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int artistMonthlyViewed(String artistID, String date, Connection conn) throws SQLException {
        MonthlyListeners mL = MonthlyListeners.readMonthlyListeners(artistID, date, conn);
        if (mL == null) {
            return 0;
        }
        return mL.getCount();
    }

    /**
     * Gets artist songs.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the artist songs
     * @throws SQLException the sql exception
     */
    public static List<Song> getArtistSongs(String artistID, Connection conn) throws SQLException {
        List<Song> artistSongs = SungBy.getSongsByArtistID(artistID, conn);
        if (artistSongs.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Song artistSong : artistSongs) {
                System.out.println("  " + artistSong.getSongID() + " - " + artistSong.getTitle());
            }
        }
        return artistSongs;
    }

    /**
     * Gets artist collaborated songs.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the artist collaborated songs
     * @throws SQLException the sql exception
     */
    public static List<Song> getArtistCollaboratedSongs(String artistID, Connection conn) throws SQLException {
        List<Song> collabSongs = CollaboratedBy.getSongsByArtistID(artistID, conn);
        if (collabSongs.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Song artistSong : collabSongs) {
                System.out.println("  " + artistSong.getSongID() + " - " + artistSong.getTitle());
            }
        }
        return collabSongs;
    }

    /**
     * Gets artist albums.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the artist albums
     * @throws SQLException the sql exception
     */
    public static List<Album> getArtistAlbums(String artistID, Connection conn) throws SQLException {
        List<Album> albums = Has.getAlbumsByArtistID(artistID, conn);
        if (albums.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Album album : albums) {
                System.out.println("  " + album.getAlbumID() + " - " + album.getName());
            }
        }
        return albums;
    }

    /**
     * Gets artist record label contracts.
     *
     * @param artistID the artist id
     * @param conn     the conn
     * @return the artist record label contracts
     * @throws SQLException the sql exception
     */
    public static List<RecordLabel> getArtistRecordLabelContracts(String artistID, Connection conn)
            throws SQLException {
        List<RecordLabel> recordLabels = ContractedWith.getRecordLabelContractsByArtistID(artistID, conn);
        if (recordLabels.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (RecordLabel recordLabel : recordLabels) {
                System.out.println("  " + recordLabel.getRecordLabelID() + " - " + recordLabel.getName());
            }
        }
        return recordLabels;
    }

    private static void readArtist() throws SQLException {
        // add code to prompt for artist ID and display artist information from data
        // store
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String date = currentDate.format(formatter);

        System.out.println("Enter artist ID to read:");
        String readID = input.nextLine();
        Connection conn = Connections.open();
        Artist a = Artist.readArtist(readID, conn);
        if (a == null) {
            System.out.println("Artist with ID " + readID + " does not exist");
        } else {
            System.out.println("Artist ID: " + a.artistID);
            System.out.println("Name: " + a.name);
            System.out.println("Status: " + a.status);
            System.out.println("Type: " + a.type);

            System.out.println("Payments Received: ");

            getArtistPayments(readID, conn);

            System.out.println("Artist is based in: " + artistBasedIn(readID, conn));

            System.out.println("Artist Monthy Views: " + artistMonthlyViewed(readID, date, conn));

            System.out.println("Artist Songs: ");

            getArtistSongs(readID, conn);

            System.out.println("Artist collaborations: ");

            getArtistCollaboratedSongs(readID, conn);

            System.out.println("Artist Albums: ");

            getArtistAlbums(readID, conn);

            System.out.println("Artist Contracted Record Labels: ");

            getArtistRecordLabelContracts(readID, conn);

        }
        Connections.close(conn);
    }
}
