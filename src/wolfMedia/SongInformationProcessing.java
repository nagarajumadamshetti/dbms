package wolfMedia;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SongInformationProcessing {
    public static Scanner input = new Scanner(System.in);

    public static void processSong() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create song");
        System.out.println("2. Update song");
        System.out.println("3. Delete song");
        System.out.println("4. Read song information");
        System.out.print("Choice: ");

        int subChoice2 = input.nextInt();

        input.nextLine(); // consume newline character

        switch (subChoice2) {
            case 1:
                createSong("song");
                break;
            case 2:
                updateSong();
                break;
            case 3:
                deleteSong();
                break;
            case 4:
                readSong();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    /**
     * Create Song
     */
    public static void createSong(String from) throws SQLException {
        System.out.println("Enter song information:");
        System.out.print("Song ID: ");
        String songID = input.nextLine();
        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.print("Duration: ");
        String duration = input.nextLine();
        System.out.print("Release date in yyyy-MM-dd: ");
        String releaseDate = input.nextLine();
        System.out.print("Royalty paid: ");
        float royaltyPaid = Float.parseFloat(input.nextLine());
        System.out.print("Royalty rate: ");
        float royaltyRate = Float.parseFloat(input.nextLine());
        Connection conn = Connections.open();

        Song s = new Song(songID, title, duration, releaseDate, royaltyPaid, royaltyRate);
        int isCreated = Song.createSong(s, conn);

        if (isCreated == 0) {
            System.out.println("Song not created");
        } else {
            createReleasedIn(songID,conn);

            createSungIn(songID,conn);

            createGeneredIn(songID,conn);

            if(true){// from =="song"
            createBelongsTo(songID,conn);}

            if(true){// from =="song"
            createSungBy(songID,conn);}

            if(true){ // from =="song"
            createCollaborators(songID, conn);}

            viewSong(songID, conn);
        }
        // input.nextLine(); // consume newline character
        // String songID, String title, String duration, String releaseDate, float
        // royaltyPaid, float royaltyRate
        // Song s= new Song("1", "Hello", "4:01", "2000-01-04", 12.12345, 12.12345);

        Connections.close(conn);
        // Song s= new Song(songID,title,duration,releaseDate,royaltyPaid,royaltyRate);
        // add code to save new song information to a data store

    }

    public static int createReleasedIn(String songID, Connection conn) throws SQLException {
        System.out.println("Released In: \n 1: United States\n 2: United Kingdom\n 3: Canada\n 4: Australia\n 5: Japan\n 6: Germany\n 7: France\n 8: Brazil");
        String countryID = input.nextLine();
        int isCreated = 0;
        if (!countryID.isEmpty()) {
            ReleasedIn r = new ReleasedIn(songID, countryID);
            isCreated = ReleasedIn.createReleasedIn(r, conn);
        }
        return isCreated;
    }
    
    public static int deleteReleasedIn(String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = ReleasedIn.deleteReleasedIn(songID, conn);
        return isDeleted;
    }

    public static int createSungIn(String songID, Connection conn) throws SQLException {
        System.out.println("Sung Language: \n 1: English\n 2:  Japanese\n 3: French\n 4: German\n 5: Portuguese\n 6: Spanish\n 7: Italian \n 8: Mandarin\n");
        String languageID = input.nextLine();
        int isCreated = 0;
        if (!languageID.isEmpty()) {
            SungIn sI = new SungIn(songID, languageID);
            isCreated = SungIn.createSungIn(sI, conn);
        }
        return isCreated;
    }
    
    public static int deleteSungIn(String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = SungIn.deleteSungIn(songID, conn);
        return isDeleted;
        }

    public static int createGeneredIn(String songID, Connection conn) throws SQLException {
        System.out.println("Song Genre: \n 1: Pop\n 2: Rock\n 3: Hip hop\n 4: Electronic\n 5: Classical\n 6: Country\n 7: Jazz\n 8: Blues\n");
        String genreID = input.nextLine();
        int isCreated = 0;
        if (!genreID.isEmpty()) {
            GeneredIn gI = new GeneredIn(songID, genreID);
            isCreated = GeneredIn.createGeneredIn(gI, conn);
        }
        return isCreated;
    }
    
    public static int deleteGeneredIn(String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = GeneredIn.deleteGeneredIn(songID, conn);
        return isDeleted;
        }

    public static int createBelongsTo(String songID, Connection conn) throws SQLException {
        System.out.println("Album ID: ");
        String albumID = input.nextLine();
        System.out.println("Track Number: ");
        int trackNumber = Integer.parseInt(input.nextLine());
        int isCreated = 0;
        if (!albumID.isEmpty()) {
            BelongsTo bT = new BelongsTo(albumID, songID, trackNumber);
            isCreated = BelongsTo.createBelongsTo(bT, conn);
        }
        return isCreated;
    }
    
    public static int deleteBelongsTo(String songID,String albumID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = BelongsTo.deleteBelongsTo(songID,albumID, conn);
        return isDeleted;
        }

    public static int createSungBy(String songID, Connection conn) throws SQLException {
        System.out.println("SungBy ArtistID: ");
        String artistID = input.nextLine();
        int isCreated = 0;
        if (!artistID.isEmpty()) {
            SungBy sB = new SungBy(artistID, songID);
            isCreated = SungBy.createSungBy(sB, conn);
        }
        return isCreated;
    }

    public static int deleteSungBy(String artistID,String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = SungBy.deleteSungBy(artistID, songID, conn);
        return isDeleted;
        }

    public static int createCollaborators(String songID, Connection conn) throws SQLException {
        System.out.println("Collaborated Artist IDs by space: ");
        String[] collaboratedArtistIDs = input.nextLine().split(" ");
        int isCreated = 0;
        if(collaboratedArtistIDs.length==0){
            return isCreated;
        }
        for (int i = 0; i < collaboratedArtistIDs.length; i++) {
            CollaboratedBy cB = new CollaboratedBy(collaboratedArtistIDs[i], songID);
            isCreated = CollaboratedBy.createCollaboration(cB, conn);
        }
        return isCreated;
    }

    public static int deleteCollaboration(String artistID, String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = CollaboratedBy.deleteCollaboration(artistID,songID, conn);
        return isDeleted;
        }

    public static int viewSong(String songID, Connection conn) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String date = currentDate.format(formatter);

        SongsViewed sV = SongsViewed.readSongsViewed(songID, date, conn);
        
        SungBy sB= SungBy.readSungBySongID(songID, conn);
        ArtistInformationProcessing.increaseArtistMonthlyListeners(sB.getArtistID(), conn);

        if (sV == null) {
            sV = new SongsViewed(songID, date, 1);
            SongsViewed.createSongsViewed(sV, conn);
            return 1;
        } else {
            int count = sV.getCount();
            sV.setCount(count + 1);
            SongsViewed.updateSongsViewed(sV, conn);
            return count + 1;
        }
    }

    public static void updateSong() throws SQLException {
        // add code to prompt for song ID and new song information, then update song in
        // data store
        // Update an existing Song record in the database
        System.out.println("Enter song ID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();
        
        Song sToUpdate = Song.readSong(updateID, conn);
        if (sToUpdate == null) {
            System.out.println("Song with ID " + updateID + " does not exist");
        } else {
            System.out.println("Enter new song information:");
            System.out.print("Title: ");
            sToUpdate.setTitle(input.nextLine());
            System.out.print("Duration: ");
            sToUpdate.setDuration(input.nextLine());
            System.out.print("Release date in yyyy-MM-dd: ");
            sToUpdate.setReleaseDate(input.nextLine());
            System.out.print("Royalty paid: ");
            sToUpdate.setRoyaltyPaid(Float.parseFloat(input.nextLine()));
            System.out.print("Royalty rate: ");
            sToUpdate.setRoyaltyRate(Float.parseFloat(input.nextLine()));
            int isUpdated;
            isUpdated = Song.updateSong(sToUpdate, conn);
            if (isUpdated == 0) {
                System.out.println("Failed to update song");
            }
            // TODO update songs relationship tables
        }
        Connections.close(conn);
    }

    private static void deleteSong() throws SQLException {
        // add code to prompt for song ID and delete song from data store
        System.out.println("Enter song ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
        int s = Song.deleteSong(deleteID, conn);
        if (s == 0) {
            System.out.println("Song with ID " + deleteID + " does not exist");
        } else {
            System.out.println("Song with Song ID: " + deleteID + " is deleted");
        }
        Connections.close(conn);
    }
    public static int songViewed(String songID, String date, Connection conn) throws SQLException{
        SongsViewed sV = SongsViewed.readSongsViewed(songID, date, conn );
        return sV.getCount();
    }

    public static String songReleasedIn(String songID, Connection conn) throws SQLException{
        ReleasedIn rI = ReleasedIn.readReleasedIn(songID, conn );
        Country c = Country.readCountry(rI.getCountryID(), conn);
        return c.getName();
    }

    public static String songSungIn(String songID, Connection conn) throws SQLException{
        SungIn sI = SungIn.readSungIn(songID, conn );
        Language l = Language.readLanguage(sI.getLanguageID(), conn);
        return l.getName();
    }

    public static String songGeneredIn(String songID, Connection conn) throws SQLException{
        GeneredIn gI = GeneredIn.readGeneredIn(songID, conn );
        Genre g = Genre.readGenre(gI.getGenreID(), conn);
        return g.getName();
    }
    
    public static String songArtist(String songID, Connection conn) throws SQLException{
        SungBy sB= SungBy.readSungBySongID(songID, conn);
        Artist a= Artist.readArtist(sB.getArtistID(), conn);
        return a.getName();
    }

    public static List<Album> songBelongsToAlbums(String songID, Connection conn) throws SQLException{
        List<Album> albums = BelongsTo.getAlbums(songID, conn);
        return albums;
    }

    public static List<Artist> songCollaborators(String songID, Connection conn) throws SQLException{
        List<Artist> artists = CollaboratedBy.getArtists(songID, conn);
        return artists;
    }

    private static void readSong() throws SQLException {
        // add code to prompt for song ID and display song information from data store
        System.out.println("Enter song ID to read:");
        String readID = input.nextLine();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String date = currentDate.format(formatter);

        Connection conn = Connections.open();
        Song s = Song.readSong(readID, conn);
        if (s == null) {
            System.out.println("Song with ID " + readID + " does not exist");
        } else {
            System.out.println("Song ID: " + s.songID);
            System.out.println("Title: " + s.title);
            System.out.println("Duration: " + s.duration);
            System.out.println("Release Date: " + s.releaseDate);
            System.out.println("Royalty Paid: " + s.royaltyPaid);
            System.out.println("Royalty Rate: " + s.royaltyRate);

            System.out.println("Song sung By: "+ songArtist(readID,conn));

            System.out.println("Song Viewed: "+ songViewed(readID, date, conn));

            System.out.println("Song Released In: " + songReleasedIn(readID,conn));

            System.out.println("Song Sung In: " + songSungIn(readID,conn));

            System.out.println("Song Genered In: " + songGeneredIn(readID,conn));

            System.out.println("Song Collaborators: ");

            List<Artist> artists= songCollaborators(readID,conn);
            if (artists.isEmpty()) {
                System.out.println("  (none)");
            } else {
                for (Artist artist : artists) {
                    System.out.println("  " + artist.getArtistID() + " - " + artist.getName());
                }
            }

            System.out.println("Song Belongs to albums: ");
            List<Album> albums = songBelongsToAlbums(readID,conn);
            if (albums.isEmpty()) {
                System.out.println("  (none)");
            } else {
                for (Album album : albums) {
                    System.out.println("  " + album.getAlbumID() + " - " + album.getName());
                }
            }
        }
        Connections.close(conn);
    }
}
