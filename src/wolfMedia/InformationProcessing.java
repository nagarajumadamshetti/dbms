package wolfMedia;
import java.sql.*;

import java.util.Scanner;

/**
 * The type Information processing.
 */
public class InformationProcessing {
    /**
     * The constant input.
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * Process information.
     *
     * @param subChoice the sub choice
     * @throws SQLException the sql exception
     */
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
            case 7:
                processPodCast();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
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
    
        
    private static void processPodCast() throws SQLException {
        // code for processing artist information
        // PodCastInformationProcessing.processPodCast();
        PodCastInformationProcessing.processPodcast();
    }
    
    private static void processPodcastHost()throws SQLException {
        // code for processing podcast host information
        PodcastHostInformationProcessing.processPodcastHost();
        // additional fields as required
        // add code to update or delete podcast host information
    }
    
    private static void processPodcastEpisode() throws SQLException{
        // code for processing podcast episode information
        PodcastEpisodeInformationProcessing.processPodcastEpisode();
        // additional fields as required
        // add code to update or delete podcast episode information
    }
    
    private static void processAlbum() throws SQLException {
        // code for processing song information
        AlbumInformationProcessing.processAlbum();
    }
    
    private static void processRecordLabel()throws SQLException {
        // code for processing record label information
        RecordLabelInformationProcessing.processRecordLabel();
    }
}
