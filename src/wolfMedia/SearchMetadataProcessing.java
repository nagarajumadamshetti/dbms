package wolfMedia;

import java.sql.*;
import java.util.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;

/**
 * The type Search metadata processing.
 */
public class SearchMetadataProcessing {

    /**
     * The constant input.
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * Process search.
     *
     * @throws SQLException the sql exception
     */
// code for finding songs and podcast episodes given artist, album, and/or podcast
    public static void processSearch()throws SQLException {
        System.out.println("Enter search criteria:");
        System.out.println("1. Search Songs by artist");
        System.out.println("2. Search Songs by album");
        System.out.println("3. Search Podcast Episodes by podcast");
        System.out.println("4. Search Songs by artist and album");
        System.out.print("Choice: ");
    
        int searchType = input.nextInt();
    
        input.nextLine(); // consume newline character
    
        switch (searchType) {
            case 1:
                // Code to find songs and podcast episodes by artist
                searchSongByArtist();
                break;
            case 2:
                // Code to find songs and podcast episodes by album
                searchSongByAlbum();
                break;
            case 3:
                // Code to find podcast episodes by podcast
                searchPodcastEpisodesByPodcast();
                break;
            case 4:
                // Code to find songs by artist and album
                searchSongsByArtistAndAlbum();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }
    private static void searchSongByArtist() throws SQLException {
        System.out.println("Enter Artist ID to search for songs:");
        String artistID = input.nextLine();
    
        Connection conn = Connections.open();
        ArtistInformationProcessing.getArtistSongs(artistID, conn);
    
        Connections.close(conn);
    }

    private static void searchSongByAlbum() throws SQLException {
        System.out.println("Enter Album ID to search for songs:");
        String albumID = input.nextLine();
    
        Connection conn = Connections.open();

        System.out.println("Songs:");

        List<Song> songs = Album.getSongsByAlbumID(albumID, conn);
        if (songs.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Song song : songs) {
                System.out.println("  " + song.getSongID() + " - " + song.getTitle());
            }
        }
        Connections.close(conn);
    }

    private static void searchPodcastEpisodesByPodcast() throws SQLException {
        System.out.println("Enter Podcast ID to search for podcast episodes:");
        String podcastID = input.nextLine();
    
        Connection conn = Connections.open();

        System.out.println("Podcast Episodes:");

        List<PodcastEpisode> podcastEpisodes = PartOf.getPodcastEpisodesByPodcastID(podcastID, conn);
        if (podcastEpisodes.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (PodcastEpisode podcastEpisode : podcastEpisodes) {
                System.out.println("  " + podcastEpisode.getPodcastEpisodeID() + " - " + podcastEpisode.getEpisodeTitle());
            }
        }
        Connections.close(conn);
    }
    

    private static void searchSongsByArtistAndAlbum() throws SQLException {
        // System.out.println("Enter Record Label information:");
        System.out.print("Album ID: ");
        String albumID = input.nextLine();
        System.out.print("songID: ");
        String songID = input.nextLine();
        Connection conn = Connections.open();

        List<Song> songs= BelongsTo.getSongsByAlbumIDSongID(albumID,songID,conn);

        if (songs.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Song song : songs) {
                System.out.println("  " + song.getSongID() + " - " + song.getTitle());
            }
        }

        Connections.close(conn);
    }
}
