package wolfMedia;

import java.sql.*;
import java.util.*;

/**
 * The type Podcast host information processing.
 */
public class PodcastHostInformationProcessing {
    /**
     * The constant input.
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * Process podcast host.
     *
     * @throws SQLException the sql exception
     */
    public static void processPodcastHost() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create PodcastHost");
        System.out.println("2. Update PodcastHost");
        System.out.println("3. Delete PodcastHost");
        System.out.println("4. Read PodcastHost information");
        System.out.print("Choice: ");

        int subChoice3 = input.nextInt();

        input.nextLine(); // consume newline character

        switch (subChoice3) {
            case 1:
                createPodcastHost();
                break;
            case 2:
                updatePodcastHost();
                break;
            case 3:
                deletePodcastHost();
                break;
            case 4:
                readPodcastHost();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    /**
     * Create PodcastHost
     */
    private static void createPodcastHost() throws SQLException {
        System.out.println("Enter PodcastHost information:");
        System.out.print("PodcastHost ID: ");
        String PodcastHostID = input.nextLine();
        System.out.print("First Name: ");
        String firstName = input.nextLine();
        System.out.print("Last Name: ");
        String lastName = input.nextLine();
        System.out.print("Phone ");
        String phone = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("City: ");
        String city = input.nextLine();
        Connection conn = Connections.open();

        PodcastHost a = new PodcastHost(PodcastHostID,firstName,lastName, phone, email, city);
        int isCreated = PodcastHost.createPodcastHost(a, conn);

        if (isCreated == 0) {
            System.out.println("PodcastHost not created");
        }
        else{
            System.out.println("Want to link podcastHost to any existing podcasts? Enter yes/no");
            String response = input.nextLine();
            if (response.equals("yes")) {
                createHostedPodcasts(PodcastHostID, conn);
            }

        }


        Connections.close(conn);
    }

    
    /**
     * Update PodcastHost
     */
    private static void updatePodcastHost() throws SQLException {
        System.out.println("Enter PodcastHost ID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();

        PodcastHost aToUpdate = PodcastHost.readPodcastHost(updateID, conn);
        if (aToUpdate == null) {
            System.out.println("PodcastHost with ID " + updateID + " does not exist");
        } else {
            System.out.println("Enter new PodcastHost information:");
            System.out.print("First Name: ");
            String firstName = input.nextLine();
            aToUpdate.setFirstName(firstName);
            System.out.print("Last Name: ");
            String lastName = input.nextLine();
            aToUpdate.setLastName(lastName);
            System.out.print("Phone ");
            String phone = input.nextLine();
            aToUpdate.setPhone(phone);
            System.out.print("Email: ");
            String email = input.nextLine();
            aToUpdate.setEmail(email);
            System.out.print("City: ");
            String city = input.nextLine();
            aToUpdate.setCity(city);
            int isUpdated;
            isUpdated = PodcastHost.updatePodcastHost(aToUpdate, conn);
            if (isUpdated == 0) {
                System.out.println("Failed to update PodcastHost");
            }

        }
        Connections.close(conn);

    }

    private static void deletePodcastHost() throws SQLException {
       
        System.out.println("Enter PodcastHost ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
        int a = PodcastHost.deletePodcastHost(deleteID, conn);
        if (a == 0) {
            System.out.println("PodcastHost with ID " + deleteID + " does not exist");
        }
        Connections.close(conn);
    }

    private static void readPodcastHost() throws SQLException {
        // add code to prompt for PodcastHost ID and display PodcastHost information from data
        // store
        System.out.println("Enter PodcastHost ID to read:");
        String readID = input.nextLine();
        Connection conn = Connections.open();
        PodcastHost a = PodcastHost.readPodcastHost(readID, conn);
        if (a == null) {
            System.out.println("PodcastHost with ID " + readID + " does not exist");
        } else {
            System.out.println("PodcastHost ID: " + a.getPodcastHostID());
            System.out.println("FirstName: " + a.getFirstName());
            System.out.println("LastName: " + a.getLastName());
            System.out.println("Phone: " + a.getPhone());
            System.out.println("Email: " + a.getEmail());
            System.out.println("City: " + a.getCity());
        }
        Connections.close(conn);
    }

    /**
     * Create hosted podcasts int.
     *
     * @param podcastHostID the podcast host id
     * @param conn          the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int createHostedPodcasts(String podcastHostID, Connection conn) throws SQLException {
        System.out.println("Enter Podcast IDs by space: ");
        String[] podcastIDs = input.nextLine().split(" ");
        int isCreated = 0;
        for (int i = 0; i < podcastIDs.length; i++) {
            OwnedBy o = new OwnedBy(podcastHostID, podcastIDs[i]);
            isCreated = OwnedBy.createOwnedBy(o, conn);
        }
        return isCreated;
    }

    /**
     * Delete hosted podcasts int.
     *
     * @param podcastHostID the podcast host id
     * @param podcastID     the podcast id
     * @param conn          the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int deleteHostedPodcasts(String podcastHostID, String podcastID, Connection conn) throws SQLException {
        int isDeleted = 0;
        isDeleted = SungBy.deleteSungBy(podcastHostID, podcastID, conn);
        return isDeleted;
    }


    /**
     * Add payment received int.
     *
     * @param podcastHostID the podcast host id
     * @param paymentID     the payment id
     * @param conn          the conn
     * @return int value=> operation success(1)/failure(0)
     * @throws SQLException the sql exception
     */
    public static int addPaymentReceived(String podcastHostID, String paymentID, Connection conn) throws SQLException {
        PodcastPayments p = new PodcastPayments(paymentID, podcastHostID);
        int isCreated = PodcastPayments.createPodcastPayment(p, conn);
        return isCreated;
    }
}
