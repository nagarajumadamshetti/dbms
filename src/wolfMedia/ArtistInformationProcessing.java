package wolfMedia;

import java.sql.*;

import java.util.*;

public class ArtistInformationProcessing {
    public static Scanner input = new Scanner(System.in);

    public static void processArtist() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create artist");
        System.out.println("2. Update artist");
        System.out.println("3. Delete artist");
        System.out.println("4. Read artist information");
        System.out.print("Choice: ");

        int subChoice2 = input.nextInt();

        input.nextLine(); // consume newline character

        switch (subChoice2) {
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
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    /**
     * Create Song
     */
    private static void createArtist() throws SQLException {
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
        // input.nextLine(); // consume newline character
        // String songID, String title, String duration, String releaseDate, float
        // royaltyPaid, float royaltyRate
        // Song s= new Song("1", "Hello", "4:01", "2000-01-04", 12.12345, 12.12345);
        Song s = new Song(songID, title, duration, releaseDate, royaltyPaid, royaltyRate);

        Connection conn = Connections.open();

        int isCreated = Song.createSong(s, conn);

        if (isCreated == 0) {
            System.out.println("Song not created");
        }
        Connections.close(conn);
        // Song s= new Song(songID,title,duration,releaseDate,royaltyPaid,royaltyRate);
        // add code to save new song information to a data store

    }

    private static void updateArtist() throws SQLException {
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
        }
        Connections.close(conn);
    }

    private static void deleteArtist() throws SQLException {
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

    private static void readArtist() throws SQLException {
        // add code to prompt for song ID and display song information from data store
        System.out.println("Enter song ID to read:");
        String readID = input.nextLine();
        Connection conn = Connections.open();
        Song s = Song.readSong(readID, conn);
        if (s == null) {
            System.out.println("Song with ID " + readID + " does not exist");
        }
        else{
        System.out.println("Song ID: " + s.songID);
        System.out.println("Title: " + s.title);
        System.out.println("Duration: " + s.duration);
        System.out.println("Release Date: " + s.releaseDate);
        System.out.println("Royalty Paid: " + s.royaltyPaid);
        System.out.println("Royalty Rate: " + s.royaltyRate);
        }
        Connections.close(conn);
    }
}
