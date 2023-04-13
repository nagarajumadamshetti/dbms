package wolfMedia;
import java.sql.*;
import java.util.*;

public class PodCastInformationProcessing {


public static Scanner input = new Scanner(System.in);

public static void processPodcast() throws SQLException {
    System.out.println("Enter/update/delete basic information:");
    System.out.println("1. Create podcast");
    System.out.println("2. Update podcast");
    System.out.println("3. Delete podcast");
    System.out.println("4. Read podcast information");
    System.out.print("Choice: ");

    int subChoice2 = input.nextInt();

    input.nextLine(); // consume newline character

    switch (subChoice2) {
        case 1:
            createPodcast();
            break;
        case 2:
            updatePodcast();
            break;
        case 3:
            deletePodcast();
            break;
        case 4:
            readPodcast();
            break;
        default:
            System.out.println("Invalid choice. Please enter a valid option.");
            break;
    }
}

/**
 * Create Podcast Episode
 */
private static void createPodcast() throws SQLException {
	System.out.println("Enter podcast information:");
    System.out.print("Podcast ID: ");
    String podcastID = input.nextLine();
    System.out.print("Podcast Name: ");
    String podcastName = input.nextLine();
    System.out.print("Language: ");
    String language = input.nextLine();
    System.out.print("Episode count: ");
    int episodeCount = input.nextInt();
    System.out.print("Total subscribers: ");
    int totalSubscribers = input.nextInt();
    System.out.print("Rating: ");
    int rating =input.nextInt();;
    
    Connection conn = Connections.open();

    PodCast p = new PodCast(podcastID, podcastName, language, episodeCount, totalSubscribers, rating);
    int isCreated = PodCast.createPodcast(p, conn);



    if (isCreated == 0) {
        System.out.println("Podcast not created");
    }
    else {
        createPartOf(podcastID,conn);

        createGeneredIn(podcastID,conn);

        createSponsoredBy(podcastID,conn);

        createOwnedBy(podcastID,conn);

        createSubscribePodcast(podcastID,conn);



    }
    Connections.close(conn);
   
}

    public static int createPartOf(String podcastID, Connection conn) throws SQLException {
    	System.out.println("Podcast Episode ID: ");
        String podcastEpisodeID = input.nextLine();
        int isCreated = 0;
        if (!podcastEpisodeID.isEmpty()) {
            PartOf p = new PartOf(podcastID, podcastEpisodeID);
            isCreated = PartOf.createPartOf(p, conn);
        }
        return isCreated;
    }
    
    public static int deletePartOf(String podcastID, String podcastEpisodeID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = PartOf.deletePartOf(podcastID, podcastEpisodeID, conn);
        return isDeleted;
    }
    
    public static int createGeneredIn(String podcastID, Connection conn) throws SQLException {
        System.out.println("Song Genre: \n 1: Pop\n 2: Rock\n 3: Hip hop\n 4: Electronic\n 5: Classical\n 6: Country\n 7: Jazz\n 8: Blues\n");
        String genreID = input.nextLine();
        int isCreated = 0;
        if (!genreID.isEmpty()) {
            GeneredIn gI = new GeneredIn(podcastID, genreID);
            isCreated = GeneredIn.createGeneredIn(gI, conn);
        }
        return isCreated;
    }
    
    public static int deleteGeneredIn(String podcastID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = GeneredIn.deleteGeneredIn(podcastID, conn);
        return isDeleted;
        }
    
    public static int createSponsoredBy(String podcastID, Connection conn) throws SQLException {
    	System.out.println("Sponser ID: ");
        String sponsorID = input.nextLine();
        int isCreated = 0;
        if (!sponsorID.isEmpty()) {
            SponsoredBy s = new SponsoredBy(podcastID, sponsorID);
            isCreated = SponsoredBy.createSponsoredBy(s, conn);
        }
        return isCreated;
    }
    
    public static int deleteSponsoredBy(String podcastID, String sponsorID ,Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = SponsoredBy.deleteSponsoredBy(podcastID, sponsorID, conn);
        return isDeleted;
    }


    
    public static int createOwnedBy(String podcastID, Connection conn) throws SQLException {
        System.out.println("Owned By Podcast Host ID: ");
        String podcastHostID = input.nextLine();
        int isCreated = 0;
        if (!podcastHostID.isEmpty()) {
            OwnedBy oB = new OwnedBy(podcastID, podcastHostID);
            isCreated = OwnedBy.createOwnedBy(oB, conn);
        }
        return isCreated;
    }

    public static int deleteOwnedBy(String podcastHostID,String podcastID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = OwnedBy.deleteOwnedBy(podcastHostID, podcastID, conn);
        return isDeleted;
        }

    public static int createSubscribePodcast(String podcastID, Connection conn) throws SQLException {
        System.out.println("User ID ");
        String userID = input.nextLine();
        int isCreated = 0;
        if (!userID.isEmpty()) {
            SubscribePodcast sp = new SubscribePodcast(podcastID, userID);
            isCreated = SubscribePodcast.createSubscribePodcast(sp, conn);
        }
        return isCreated;
    }

    public static int deleteSubscribePodcast(String podcastID, String userID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = SubscribePodcast.deleteSubscribePodcast(podcastID, userID, conn);
        return isDeleted;
        }

  

    private static void updatePodcast() throws SQLException {
        // add code to prompt for podcast ID and new podcast information, then update podcast in
        // data store
        // Update an existing podcast record in the database
        System.out.println("Enter podcast ID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();
        PodCast pToUpdate = PodCast.readPodcast(updateID, conn);
        if (pToUpdate == null) {
            System.out.println("Podcast with ID " + updateID + " does not exist");
        } else {
            System.out.println("Enter new song information:");
            System.out.print("Podcast ID: ");
            pToUpdate.setPodcastID(input.nextLine());
            System.out.print("Podcast Name: ");
            pToUpdate.setPodcastName(input.nextLine());
            System.out.print("Language: ");
            pToUpdate.setLanguage(input.nextLine());
            System.out.print("Episode count: ");
            pToUpdate.setEpisodeCount(input.nextInt());
            System.out.print("Total subscribers: ");
            pToUpdate.setTotalSubscribers(input.nextInt());
            System.out.print("Total subscribers: ");
            pToUpdate.setTotalSubscribers(input.nextInt());
            System.out.print("Rating: ");
            pToUpdate.setRating(input.nextInt());
            int isUpdated;
            isUpdated = PodCast.updatePodcast(pToUpdate, conn);
            if (isUpdated == 0) {
                System.out.println("Failed to update song");
            }
            // TODO update podcast relationship tables
        }
        Connections.close(conn);
    }

    private static void deletePodcast() throws SQLException {
        // add code to prompt for podcast ID and delete podcast from data store
        System.out.println("Enter podcast ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
        int p = PodCast.deletePodcast(deleteID, conn);
        if (p == 0) {
            System.out.println("Podcast with ID " + deleteID + " does not exist");
        } else {
            System.out.println("Podcast with Podcast ID: " + deleteID + " is deleted");
        }
        Connections.close(conn);
    }

    private static void readPodcast() throws SQLException {
        // add code to prompt for podcast ID and display podcast information from data store
        System.out.println("Enter podcast ID to read:");
        String readID = input.nextLine();
        Connection conn = Connections.open();
        PodCast x = PodCast.readPodcast(readID, conn);
        if (x == null) {
            System.out.println("Podcast with ID " + readID + " does not exist");
        } else {
            System.out.println("Podcast ID: " + x.getPodcastID());
            System.out.println("Podcast Name: " + x.getPodcastName());
            System.out.println("Language: " + x.getLanguage());
            System.out.println("Episode Count: " + x.getEpisodeCount());
            System.out.println("Total Subscribers: " + x.getTotalSubscribers());
            System.out.println("Rating: " + x.getRating());
        }
        Connections.close(conn);
    }
}
