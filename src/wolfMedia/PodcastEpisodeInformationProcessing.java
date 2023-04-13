package wolfMedia;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Podcast episode information processing.
 */
public class PodcastEpisodeInformationProcessing {

    /**
     * The constant input.
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * Process podcast episode.
     *
     * @throws SQLException the sql exception
     */
    public static void processPodcastEpisode() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create Podcast Episode");
        System.out.println("2. Update Podcast Episode");
        System.out.println("3. Delete Podcast Episode");
        System.out.println("4. Read Podcast Episode information");
        System.out.println("5. Assign Podcast Episode To Podcast");
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
            case 5:
                assignPodcastEpisodeToPodcast();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }

    }

    /**
     * Assign Podcast Episode To Podcast.
     *
     * @return nothing
     * @throws SQLException the sql exception
     */
    public static void assignPodcastEpisodeToPodcast() throws SQLException {
        Connection conn = Connections.open();
        System.out.println("Enter PodcastEpisode ID\n");
        String podcastEpisodeID = input.nextLine();
        int isCreated = 0;
        if (!podcastEpisodeID.isEmpty()) {
            isCreated = createPartOf(podcastEpisodeID, conn);
        }
        if (isCreated == 0) {
            System.out.println("Podcast Episode not assigned to Podcast mentioned");
        } else {
            System.out.println("Podcast Episode with ID: " + podcastEpisodeID + "has been assigned to respective Podcast");
        }
        Connections.close(conn);
    }

    /**
     * Create RecordLabel
     */
    private static void createPodcastEpisode() throws SQLException {
        System.out.println("Enter PodcastEpisode information:");
        System.out.print("PodcastEpisode ID: ");
        String podcastEpisodeID = input.nextLine();
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

        PodcastEpisode p = new PodcastEpisode(podcastEpisodeID,episodeTitle,duration,releaseDate,listeningCount,advertisementCount);
        int isCreated = PodcastEpisode.createPodcastEpisode(p, conn);

        if (isCreated == 0) {
            System.out.println("PodcastEpisode not created");
        }
        else{

            createPartOf(podcastEpisodeID,conn);

            System.out.println("Add guests to podcast episode? Enter yes/no");
            String response = input.nextLine();
            if (response.equals("yes")) {
                createGuestFeatured(podcastEpisodeID, conn);
            }
        }

        Connections.close(conn);
    }

    /**
     * Create part of int.
     *
     * @param podcastEpisodeID the podcast episode id
     * @param conn             the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createPartOf(String podcastEpisodeID, Connection conn) throws SQLException{
        System.out.println(
                "Podcast ID: \n");
        String podcastID = input.nextLine();
        int isCreated = 0;
        if (!podcastID.isEmpty()) {
            PartOf pO = new PartOf(podcastID,podcastEpisodeID);
            isCreated = PartOf.createPartOf(pO, conn);
        }
        return isCreated;
    }

    // public static int deletePartOf(String podcastID, String podcastEpisodeID, Connection conn) throws SQLException {
    //     int isDeleted = 0;
    //     isDeleted = PartOf.deletePartOf(podcastID, podcastEpisodeID, conn);
    //     return isDeleted;
    // }

    // public static String episodePartOf(String podcastID, String podcastEpisodeID, Connection conn) throws SQLException {
    //     PartOf po = PartOf.readPartOf(podcastID, podcastEpisodeID, conn);
    //     Language l = Language.readLanguage(sI.getLanguageID(), conn);
    //     return l.getName();
    // }

    /**
     * Create guest featured int.
     *
     * @param podcastEpisodeID the podcast episode id
     * @param conn             the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createGuestFeatured(String podcastEpisodeID, Connection conn) throws SQLException{
        System.out.println("guest featured GuestID: ");
        String guestID = input.nextLine();
        int isCreated = 0;
        if (!guestID.isEmpty()) {
            GuestFeatured gf = new GuestFeatured(guestID, podcastEpisodeID);
            isCreated = GuestFeatured.createGuestFeatured(gf, conn);
        }
        return isCreated;
    }

    /**
     * Delete guest featured int.
     *
     * @param guestID          the guest id
     * @param podcastEpisodeID the podcast episode id
     * @param conn             the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteGuestFeatured(String guestID, String podcastEpisodeID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = GuestFeatured.deleteGuestFeatured(guestID, podcastEpisodeID, conn);
        return isDeleted;
    }

    /**
     * Update podcast episode.
     *
     * @throws SQLException the sql exception
     */
    public static void updatePodcastEpisode() throws SQLException {
        System.out.println("Enter podcastEpisodeID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();

        PodcastEpisode pToUpdate = PodcastEpisode.readPodcastEpisode(updateID, conn);
        if (pToUpdate == null) {
            System.out.println("Podcast Episode with ID " + updateID + " does not exist");
        } else {
            System.out.println("Enter new podcast episode information:");
            // System.out.print("Podcast Episode ID: ");
            // pToUpdate.setPodcastEpisodeID(input.nextLine());
            System.out.print("Title: ");
            pToUpdate.setEpisodeTitle(input.nextLine());
            System.out.print("Duration: ");
            pToUpdate.setDuration(input.nextLine());
            System.out.print("Release date in yyyy-MM-dd: ");
            pToUpdate.setReleaseDate(input.nextLine());
            System.out.print("Listening Count: ");
            pToUpdate.setListeningCount(Integer.parseInt(input.nextLine()));
            System.out.print("Advertisement Count: ");
            pToUpdate.setAdvertisementCount(Integer.parseInt(input.nextLine()));
            int isUpdated;
            isUpdated = PodcastEpisode.updatePodcastEpisode(pToUpdate, conn);
            if (isUpdated == 0) {
                System.out.println("Failed to update Podcast Episode");
            }
        }
        Connections.close(conn);
    }

    /**
     * Delete podcast episode.
     *
     * @throws SQLException the sql exception
     */
    public static void deletePodcastEpisode() throws SQLException {
        System.out.println("Enter podcast episode ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
        int p = PodcastEpisode.deletePodcastEpisode(deleteID, conn);
        if (p == 0) {
            System.out.println("Podcast Episode with ID " + deleteID + " does not exist");
        } else {
            System.out.println("Podcast Episode with ID: " + deleteID + " is deleted");
        }
        Connections.close(conn);
    }

    /**
     * Read podcast episode.
     *
     * @throws SQLException the sql exception
     */
    public static void readPodcastEpisode() throws SQLException {
        // add code to prompt for song ID and display song information from data store
        System.out.println("Enter podcast episode ID to read:");
        String readID = input.nextLine();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String date = currentDate.format(formatter);

        Connection conn = Connections.open();
        PodcastEpisode p = PodcastEpisode.readPodcastEpisode(readID, conn);
        if (p == null) {
            System.out.println("Podcast Episode with ID " + readID + " does not exist");
        } else {
            System.out.println("Podcast Episode ID: " + p.getPodcastEpisodeID());
            System.out.println("Title: " + p.getEpisodeTitle());
            System.out.println("Duration: " + p.getDuration());
            System.out.println("Release Date: " + p.getReleaseDate());
            System.out.println("Royalty Paid: " + p.getListeningCount());
            System.out.println("Royalty Rate: " + p.getAdvertisementCount());

            PodCast pCast = PartOf.getPodcastByPodcastEpisodeID(readID,conn);
            System.out.println("Podcast Episode part of: " + pCast.getPodcastName());

            System.out.println("Guests Featured: ");

            List<Guest> guests = GuestFeatured.getGuestsByPodcastEpisodeID(readID, conn);
            if (guests.isEmpty()) {
                System.out.println("  (none)");
            } else {
                for (Guest guest : guests) {
                    System.out.println("  " + guest.getGuestID() + " - " + guest.getName());
                }
            }
        }
        Connections.close(conn);
    }
}

