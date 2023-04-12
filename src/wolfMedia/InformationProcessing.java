package wolfMedia;
import java.sql.*;

import java.util.Scanner;

public class InformationProcessing {
    public static Scanner input = new Scanner(System.in);
    public static void processInformation(int subChoice) throws SQLException {
        switch (subChoice) {
            case 1:
                processSong();
                break;
            case 2:
                processArtist();
                break;
            case 3:
                processPodcastHost();
                break;
            case 4:
                processPodcastEpisode();
                break;
            case 5:
                processAlbum();
                break;
            case 6:
                processRecordLabel();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void processSong() throws SQLException {
        // code for processing song information
        SongInformationProcessing.processSong();
    }
    
    private static void processArtist() throws SQLException {
        // code for processing artist information
        ArtistInformationProcessing.processArtist();
    }
    
    private static void processPodcastHost() {
        // code for processing podcast host information
        System.out.println("Enter podcast host information:");
        System.out.print("Host name: ");
        String hostName = input.nextLine();
        // additional fields as required
        // add code to update or delete podcast host information
    }
    
    private static void processPodcastEpisode() {
        // code for processing podcast episode information
        System.out.println("Enter podcast episode information:");
        System.out.print("Episode title: ");
        String episodeTitle = input.nextLine();
        System.out.print("Podcast name: ");
        String podcastName = input.nextLine();
        System.out.print("Host name: ");
        String hostName = input.nextLine();
        // additional fields as required
        // add code to update or delete podcast episode information
    }
    
    private static void processAlbum() throws SQLException {
        // code for processing song information
        AlbumInformationProcessing.processAlbum();
    }
    
    private static void processRecordLabel() {
        // code for processing record label information
        System.out.println("Enter record label information:");
        System.out.print("Label name: ");
        String labelName = input.nextLine();
        // additional fields as required
        // add code to update or delete record label information
    }
}
