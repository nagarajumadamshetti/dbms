package wolfMedia;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecordLabelInformationProcessing {
    
    public static Scanner input = new Scanner(System.in);

    public static void processRecordLabel() throws SQLException {
        System.out.println("Enter/update/delete basic information:");
        System.out.println("1. Create Record Label");
        System.out.println("2. Update Record Label");
        System.out.println("3. Delete Record Label");
        System.out.println("4. Read Record Label information");
        System.out.print("Choice: ");

        int subChoice3 = input.nextInt();

        input.nextLine(); // consume newline character

        switch (subChoice3) {
            case 1:
                createRecordLabel();
                break;
            case 2:
                updateRecordLabel();
                break;
            case 3:
                deleteRecordLabel();
                break;
            case 4:
                readRecordLabel();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    /**
     * Create RecordLabel
     */
    private static void createRecordLabel() throws SQLException {
        System.out.println("Enter Record Label information:");
        System.out.print("RecordLabel ID: ");
        String recordLabelID = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        Connection conn = Connections.open();

        RecordLabel rB = new RecordLabel(recordLabelID, name);
        int isCreated = RecordLabel.createRecordLabel(rB, conn);

        if (isCreated == 0) {
            System.out.println("RecordLabel not created");
        } else {

            addRecordLabelArtistcontracts(recordLabelID, conn);
        }

        Connections.close(conn);
    }

    public static int addRecordLabelArtistcontracts(String recordLabelID, Connection conn) throws SQLException {
        System.out.println("Contracted Artist IDs by space: ");
        String[] contractedArtistIDs = input.nextLine().split(" ");
        int isCreated = 0;
        if(contractedArtistIDs.length==0){
            return isCreated;
        }
        for (int i = 0; i < contractedArtistIDs.length; i++) {
            ContractedWith cW = new ContractedWith(contractedArtistIDs[i], recordLabelID);
            isCreated = ContractedWith.createContractedWith(cW, conn);
        }
        return isCreated;
    }

    /**
     * Update RecordLabel
     */
    private static void updateRecordLabel() throws SQLException {
        System.out.println("Enter artist ID to update:");
        String updateID = input.nextLine();
        Connection conn = Connections.open();

        RecordLabel aToUpdate = RecordLabel.readRecordLabel(updateID, conn);
        if (aToUpdate == null) {
            System.out.println("RecordLabel with ID " + updateID + " does not exist");
        } else {
            System.out.println("Enter new artist information:");
            System.out.print("Name: ");
            aToUpdate.setName(input.nextLine());
            int isUpdated;
            isUpdated = RecordLabel.updateRecordLabel(aToUpdate, conn);
            if (isUpdated == 0) {
                System.out.println("Failed to update artist");
            }
            // TODO update artist relationship tables
        }
        Connections.close(conn);

    }

    private static void deleteRecordLabel() throws SQLException {
        // add code to prompt for song ID and delete song from data store
        System.out.println("Enter artist ID to delete:");
        String deleteID = input.nextLine();
        Connection conn = Connections.open();
        int a = RecordLabel.deleteRecordLabel(deleteID, conn);
        if (a == 0) {
            System.out.println("RecordLabel with ID " + deleteID + " does not exist");
        } else {
            System.out.println("RecordLabel with Song ID: " + deleteID + " is deleted");
        }
        Connections.close(conn);
    }

    public static List<RecordLabelPayment> getRecordLabelPayments(String recordLabelID, Connection conn) throws SQLException {
        List<RecordLabelPayment> recordLabelPayments = PaymentReceived.getRecordLabelPayments(recordLabelID, conn);
        if (recordLabelPayments.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (RecordLabelPayment recordLabelPayment : recordLabelPayments) {
                System.out.println("  " + recordLabelPayment.getPaymentID() + " - " + recordLabelPayment.getPaymentAmount());
            }
        }
        return recordLabelPayments;
    }

    public static List<Artist> getRecordLabelContractedArtists(String recordLabelID, Connection conn)
            throws SQLException {
        List<Artist> artists = ContractedWith.getArtistsByRecordLabelID(recordLabelID, conn);
        if (artists.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Artist artist : artists) {
                System.out.println("  " + artist.getArtistID() + " - " + artist.getName());
            }
        }
        return artists;
    }

    private static void readRecordLabel() throws SQLException {
        // add code to prompt for artist ID and display artist information from data
        // store
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String date = currentDate.format(formatter);

        System.out.println("Enter artist ID to read:");
        String readID = input.nextLine();
        Connection conn = Connections.open();
        RecordLabel a = RecordLabel.readRecordLabel(readID, conn);
        if (a == null) {
            System.out.println("RecordLabel with ID " + readID + " does not exist");
        } else {
            System.out.println("RecordLabel ID: " + a.recordLabelID);
            System.out.println("Name: " + a.name);
            System.out.println("Payments Received: ");

            getRecordLabelPayments(readID, conn);

            System.out.println("RecordLabel Contracted Artists: ");

            getRecordLabelContractedArtists(readID, conn);

        }
        Connections.close(conn);
    }
}
