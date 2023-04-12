package wolfMedia;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlbumInformationProcessing {
    public static Scanner input = new Scanner(System.in);

    public static void processAlbum() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create album");
        System.out.println("2. Update album");
        System.out.println("3. Delete album");
        System.out.println("4. Read album information");
        System.out.print("Choice: ");

        int subChoice3 = input.nextInt();

        input.nextLine(); // consume newline character

        switch (subChoice3) {
            case 1:
                createAlbum();
                break;
            case 2:
                updateAlbum();
                break;
            case 3:
                deleteAlbum();
                break;
            case 4:
                readAlbum();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    /**
     * Create Album
     */
    private static void createAlbum() throws SQLException {
        System.out.println("Enter artist information:");
        System.out.print("Album ID: ");
        String albumID = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Edition: ");
        String edition = input.nextLine();
        System.out.print("Release year: ");
        int releaseYear = Integer.parseInt(input.nextLine());

        Connection conn = Connections.open();

        Album a = new Album(albumID, name, edition, releaseYear);
        int isCreated = Album.createAlbum(a, conn);

        if (isCreated == 0) {
            System.out.println("Album not created");
        } else {
            System.out.println("Add a artists to album? Enter yes/no");
            String response = input.nextLine();
            if (response.equals("yes")) {
                createHasArtists(albumID, conn);
            }

            createBelongsTo(albumID, conn);

            System.out.println("Add a song ID's  to album? Enter yes/no");
            response = input.nextLine();
            if (response.equals("yes")) {
                createBelongsTo(albumID, conn);
            }

            // System.out.println("Add a songs? Enter yes/no");
            // response = input.nextLine();
            // if (response.equals("yes")) {
            // createHasArtists(albumID, conn);
            // }
        }

        Connections.close(conn);
    }

    public static int createBelongsTo(String albumID, Connection conn) throws SQLException {
        int isCreated = 0;
        while (true) {
            System.out.println("Add a song to album? Enter yes/no");
            String response = input.nextLine();
            if (response == "no") {
                break;
            }
            System.out.println("Song ID: ");
            String songID = input.nextLine();
            System.out.println("Track Number: ");
            int trackNumber = Integer.parseInt(input.nextLine());
            if (!songID.isEmpty()) {
                BelongsTo bT = new BelongsTo(albumID, songID, trackNumber);
                isCreated = BelongsTo.createBelongsTo(bT, conn);
            }

        }
        return isCreated;
    }

    public static int createHasArtists(String albumID, Connection conn) throws SQLException {
        System.out.println("Enter album artist IDs by space: ");
        String[] albumArtistIDs = input.nextLine().split(" ");
        int isCreated = 0;
        for (int i = 0; i < albumArtistIDs.length; i++) {
            Has h = new Has(albumArtistIDs[i], albumID);
            isCreated = Has.createHas(h, conn);
        }
        return isCreated;
    }

    public static int deleteHasArtists(String artistID, String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = CollaboratedBy.deleteCollaboration(artistID, songID, conn);
        return isDeleted;
    }

    /**
     * Update Album
     */
    public static void updateAlbum() throws SQLException {
        System.out.println("Enter album ID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();

        Album aToUpdate = Album.readAlbum(updateID, conn);
        if (aToUpdate == null) {
            System.out.println("Album with ID " + updateID + " does not exist");
        } else {
            System.out.println("Enter new album information:");
            System.out.print("Name: ");
            aToUpdate.setName(input.nextLine());
            System.out.print("Edition: ");
            aToUpdate.setEdition(input.nextLine());
            System.out.print("Release year: ");
            aToUpdate.setReleaseYear(Integer.parseInt(input.nextLine()));
            int isUpdated;
            isUpdated = Album.updateAlbum(aToUpdate, conn);
            if (isUpdated == 0) {
                System.out.println("Failed to update album");
            }
        }
        Connections.close(conn);
    }

    public static void deleteAlbum() throws SQLException {
        System.out.println("Enter album ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
        int s = Album.deleteAlbum(deleteID, conn);
        if (s == 0) {
            System.out.println("Album with ID " + deleteID + " does not exist");
        } else {
            System.out.println("Album with ID " + deleteID + " is deleted");
        }
        Connections.close(conn);
    }

    private static void readAlbum() throws SQLException {
        // add code to prompt for album ID and display album information from data store
        System.out.println("Enter album ID to read:");
        String readID = input.nextLine();
        Connection conn = Connections.open();
        Album a = Album.readAlbum(readID, conn);
        if (a == null) {
            System.out.println("Album with ID " + readID + " does not exist");
        } else {
            System.out.println("Album ID: " + a.albumID);
            System.out.println("Name: " + a.name);
            System.out.println("Edition: " + a.edition);
            System.out.println("Release Year: " + a.releaseYear);
            System.out.println("Artists:");
            List<Artist> artists = Album.getArtists(a.albumID, conn);
            if (artists.isEmpty()) {
                System.out.println("  (none)");
            } else {
                for (Artist artist : artists) {
                    System.out.println("  " + artist.getArtistID() + " - " + artist.getName());
                }
            }
            System.out.println("Songs:");
            List<Song> songs = Album.getSongs(a.albumID, conn);
            if (songs.isEmpty()) {
                System.out.println("  (none)");
            } else {
                for (Song song : songs) {
                    System.out.println("  " + song.getSongID() + " - " + song.getTitle());
                }
            }
        }
        Connections.close(conn);
    }

}
