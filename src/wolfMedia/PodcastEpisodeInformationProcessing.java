package wolfMedia;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PodcastEpisodeInformationProcessing {

    public static Scanner input = new Scanner(System.in);

    public static void processPodcastEpisode() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create Podcast Episode");
        System.out.println("2. Update Podcast Episode");
        System.out.println("3. Delete Podcast Episode");
        System.out.println("4. Read Podcast Episode information");
        System.out.print("Choice: ");

        int subChoice3 = input.nextInt();

        input.nextLine(); // consume newline character

        switch (subChoice3) {
            case 1:
                createPodcastEpisode();
                break;
            case 2:
                updatePodcastEpisode();
                break;
            case 3:
                deletePodcastEpisode();
                break;
            case 4:
                readPodcastEpisode();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }

    }

    /**
     * Create RecordLabel
     */
    private static void createPodcastEpisode() throws SQLException {
        System.out.println("Enter PodcastEpisode information:");
        System.out.print("PodcastEpisode ID: ");
        String PodcastEpisodeID = input.nextLine();
        System.out.print("Episode Title: ");
        String episodeTitle = input.nextLine();
        System.out.print("Duration: ");
        String duration = input.nextLine();
        System.out.print("Release date in yyyy-MM-dd: ");
        String releaseDate = input.nextLine();
        System.out.print("Listening Count: ");
        int listeningCount = Integer.parseInt(input.nextLine());
        System.out.print("Advertisement Count: ");
        int advertisementCount = Integer.parseInt(input.nextLine());
        Connection conn = Connections.open();

        PodcastEpisode p = new PodcastEpisode(PodcastEpisodeID,episodeTitle,duration,releaseDate,listeningCount,advertisementCount);
        int isCreated = PodcastEpisode.createPodcastEpisode(p, conn);

        if (isCreated == 0) {
            System.out.println("PodcastEpisode not created");
        }
        else{
            
            

        }


        Connections.close(conn);
    }

    public static void updatePodcastEpisode() throws SQLException {

    }

    public static void deletePodcastEpisode() throws SQLException {

    }

    public static void readPodcastEpisode() throws SQLException {

    }
}

