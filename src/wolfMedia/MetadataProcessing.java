package wolfMedia;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MetadataProcessing {
    public static Scanner input = new Scanner(System.in);

    public static void processMetadata(int subChoice) throws SQLException {
        switch (subChoice) {
            case 1:
                processPlayCount();
                break;
            case 2:
                processMonthlyListeners();
                break;
            case 3:
                processPodcastSubscribers();
                break;
            case 4:
                processEpisodeListeningCount();
                break;
            case 5:
                processSearch();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void processPlayCount() throws SQLException {
        // code for updating play count for songs
        Connection conn = Connections.open();
        // PlayCountMetadataProcessing.processPlayCount();
        // System.out.println("Enter/update the play count of the song for that month: ");
        System.out.println("SongID: ");
        String songID= input.nextLine();
        Song s = Song.readSong(songID, conn);
        if (s == null) {
            System.out.println("Song with ID " + songID + " does not exist");
            return;
        } 
        System.out.println("Enter date in yyyy-MM-dd format as we store count for every month: ");
        System.out.println("Date yyyy-mm-dd: ");
        String date= input.nextLine();
        // LocalDate currentDate = LocalDate.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        // date = currentDate.format(formatter);
        String formattedDate = LocalDate.parse(date).withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        System.out.println("Enter the new song play count: ");
        int count=Integer.parseInt(input.nextLine());
        
        SongsViewed sV= SongsViewed.readSongsViewed(songID, formattedDate, conn);//new SongsViewed(songID, formattedDate, 0);

        if (sV == null) {
            sV = new SongsViewed(songID, date, count);
            SongsViewed.createSongsViewed(sV, conn);
        } else {
            sV.setCount(count);
            SongsViewed.updateSongsViewed(sV, conn);
        }

        System.out.println("Updated song play count successfully");
        
        Connections.close(conn);

    }

    private static void processMonthlyListeners() throws SQLException {
        // code for updating count of monthly listeners for artists
        // MonthlyListenersMetadataProcessing.processMonthlyListeners();
        Connection conn = Connections.open();
        
        System.out.println("ArtistID: ");
        String artistID= input.nextLine();
        Artist a = Artist.readArtist(artistID, conn);
        if (a == null) {
            System.out.println("Artist with ID " + artistID + " does not exist");
            return;
        }

        System.out.println("Enter date in yyyy-MM-dd format as we store count for every month: ");
        System.out.println("Date yyyy-mm-dd: ");
        String date= input.nextLine();
        
        System.out.println("Enter the new monthly play count: ");
        int count=Integer.parseInt(input.nextLine());


        // LocalDate currentDate = LocalDate.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        // date = currentDate.format(formatter);
        String formattedDate = LocalDate.parse(date).withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        MonthlyListeners mL = MonthlyListeners.readMonthlyListeners(artistID, formattedDate, conn);

        if (mL == null) {
            mL = new MonthlyListeners(artistID, date, count);
            MonthlyListeners.createMonthlyListeners(mL, conn);
        } else {
            mL.setCount(count);
            MonthlyListeners.updateMonthlyListeners(mL, conn);
        }

        System.out.println("Updated monthly listeners count successfully");

        Connections.close(conn);

    }

    private static void processPodcastSubscribers() throws SQLException {
        // code for updating total count of subscribers and ratings for podcasts
        // PodcastSubscribersMetadataProcessing.processPodcastSubscribers();
        Connection conn = Connections.open();
        
        System.out.println("PodcastID: ");
        String PodCastID= input.nextLine();

        PodCast p = PodCast.readPodcast(PodCastID, conn);
        if (p == null) {
            System.out.println("Podcast with ID " + PodCastID + " does not exist");
            return;
        }
        
        System.out.println("Enter/update the total count of subscribers? Enter yes/no");
        String response = input.nextLine();
        if (response.equals("yes")) {
            System.out.println("TotalSubscribers: ");
            int totalSubscribers= Integer.parseInt(input.nextLine());
            p.setTotalSubscribers(totalSubscribers);
            int isUpdated= PodCast.updatePodcast(p, conn);
            System.out.println("Updated total count successfully");
        }

        System.out.println("Enter/update the rating of podcast? Enter yes/no");
        response = input.nextLine();
        if (response.equals("yes")) {
            System.out.println("Rating: ");
            int rating= Integer.parseInt(input.nextLine());
            p.setRating(rating);
            int isUpdated= PodCast.updatePodcast(p, conn);
            System.out.println("Updated rating successfully");
        }

        Connections.close(conn);
    }

    private static void processEpisodeListeningCount() throws SQLException {
        // code for updating listening count for podcast episodes
        // EpisodeListeningCountMetadataProcessing.processEpisodeListeningCount();
        Connection conn = Connections.open();
        
        System.out.println("PodcastEpisodeID: ");
        String PodCastEpisodeID= input.nextLine();

        PodcastEpisode pE = PodcastEpisode.readPodcastEpisode(PodCastEpisodeID, conn);
        if (pE == null) {
            System.out.println("PodcastEpisode with ID " + PodCastEpisodeID + " does not exist");
            return;
        }
        
        System.out.println("Enter/update the listening count of podcast episode? Enter yes/no");
        String response = input.nextLine();
        if (response.equals("yes")) {
            System.out.println("Listening count: ");
            int listeningCount= Integer.parseInt(input.nextLine());
            pE.setListeningCount(listeningCount);
            int isUpdated= PodcastEpisode.updatePodcastEpisode(pE, conn);
            System.out.println("Updated listening count successfully");
        }
        Connections.close(conn);
    }

    private static void processSearch() throws SQLException {
        // code for finding songs and podcast episodes given artist, album, and/or podcast
        SearchMetadataProcessing.processSearch();
    }
}
