package wolfMedia;

import java.sql.*;
import java.util.*;

/**
 * The type Album information processing.
 */
public class AlbumInformationProcessing {
    /**
     * The constant input.
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * Process album.
     *
     * @throws SQLException the sql exception
     */
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
        System.out.println("Enter album information:");
        System.out.print("Album ID: ");
        String albumID = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Edition: ");
        String edition = input.nextLine();
        System.out.print("Release year: ");
        int releaseYear = Integer.parseInt(input.nextLine());
    
        Connection conn = Connections.open();
        conn.setAutoCommit(false); // start transaction
    
        Album a = new Album(albumID, name, edition, releaseYear);
        int isAlbumCreated = Album.createAlbum(a, conn);
        int isHasArtistCreated = 0;
        int isBelongsToCreated = 0;
    
        if (isAlbumCreated == 0) {
            System.out.println("Album not created");
        } else {
            try {
                System.out.println("Add artists to album? Enter yes/no");
                String response = input.nextLine();
                if (response.equals("yes")) {
                    isHasArtistCreated = createHasArtists(albumID, conn);
                }
    
                System.out.println("Add song IDs to album? Enter yes/no");
                response = input.nextLine();
                if (response.equals("yes")) {
                    isBelongsToCreated = createBelongsTo(albumID, conn);
                }
    
                if (isHasArtistCreated == 0 || isBelongsToCreated == 0) {
                    conn.rollback(); // rollback if any failures
                    System.out.println("Transaction rolled back.");
                } else {
                    conn.commit(); // commit if everything is successful
                    System.out.println("Transaction committed.");
                }
            } catch (Exception e) {
                conn.rollback(); // rollback on exception
                System.out.println("Transaction rolled back due to exception: " + e.getMessage());
            }
        }
    
        Connections.close(conn);
    }
    
    /**
     * Create belongs to int.
     *
     * @param albumID the album id
     * @param conn    the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createBelongsTo(String albumID, Connection conn) throws SQLException {
        int isCreated = 0;
        while (true) {
            System.out.println("Add a song to album? Enter yes/no");
            String response = input.nextLine();
            if (response.equals("no")) {
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

    /**
     * Create has artists int.
     *
     * @param albumID the album id
     * @param conn    the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
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

    /**
     * Delete has artists int.
     *
     * @param artistID the artist id
     * @param songID   the song id
     * @param conn     the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteHasArtists(String artistID, String songID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = CollaboratedBy.deleteCollaboration(artistID, songID, conn);
        return isDeleted;
    }

    /**
     * Update Album
     *
     * @throws SQLException the sql exception
     */
    public static void updateAlbum() throws SQLException {
        System.out.println("Enter album ID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();
        Album aToUpdate = Album.readAlbum(updateID, conn);
    
        if (aToUpdate == null) {
            System.out.println("Album with ID " + updateID + " does not exist");
            Connections.close(conn);
            return;
        }
    
        conn.setAutoCommit(false); // Begin transaction
    
        try {
            System.out.println("Enter new album information:");
            System.out.print("Name: ");
            aToUpdate.setName(input.nextLine());
            System.out.print("Edition: ");
            aToUpdate.setEdition(input.nextLine());
            System.out.print("Release year: ");
            aToUpdate.setReleaseYear(Integer.parseInt(input.nextLine()));
            int isUpdated = Album.updateAlbum(aToUpdate, conn);
    
            if (isUpdated == 0) {
                System.out.println("Failed to update album");
                conn.rollback(); // Rollback transaction
            } else {
                System.out.println("Album updated successfully");
                conn.commit(); // Commit transaction
            }
    
        } catch (SQLException ex) {
            System.out.println("Error updating album: " + ex.getMessage());
            conn.rollback(); // Rollback transaction
        } finally {
            Connections.close(conn);
        }
    }
    

    /**
     * Delete album.
     *
     * @throws SQLException the sql exception
     */
    public static void deleteAlbum() throws SQLException {
        System.out.println("Enter album ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
    
        conn.setAutoCommit(false); // Begin transaction
    
        try {
            int s = Album.deleteAlbum(deleteID, conn);
            if (s == 0) {
                System.out.println("Album with ID " + deleteID + " does not exist");
                conn.rollback(); // Rollback transaction
            } else {
                System.out.println("Album with ID " + deleteID + " is deleted");
                conn.commit(); // Commit transaction
            }
    
        } catch (SQLException ex) {
            System.out.println("Error deleting album: " + ex.getMessage());
            conn.rollback(); // Rollback transaction
        } finally {
            Connections.close(conn);
        }
    }
    
    private static void readAlbum() throws SQLException {
        System.out.println("Enter album ID to read:");
        String readID = input.nextLine();
        Connection conn = Connections.open();
        try {
            conn.setAutoCommit(false);
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
                List<Song> songs = Album.getSongsByAlbumID(a.albumID, conn);
                if (songs.isEmpty()) {
                    System.out.println("  (none)");
                } else {
                    for (Song song : songs) {
                        System.out.println("  " + song.getSongID() + " - " + song.getTitle());
                    }
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            System.out.println("Failed to read album with ID " + readID);
            conn.rollback();
        } finally {
            Connections.close(conn);
        }
    }
}
