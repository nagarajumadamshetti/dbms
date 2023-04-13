/* 
1. Information Processing: Enter/update/delete basic information about songs, artists, podcast
   hosts, and podcast episodes. Assign songs and artists to albums. Assign artists to record 
   labels. Assign podcast episodes and podcast hosts to podcasts.

2. Maintaining metadata and records:  Enter/update play count for songs.  Enter/update the 
   count of monthly listeners for artists. Enter/update the total count of subscribers and 
   ratings for podcasts. Enter/Update the listening count for podcast episodes. Find songs and 
   podcast episodes given artist, album, and/or podcast.

3. Maintaining payments: Make royalty payments for a given song. Monthly royalties are generated
   based on a royalty rate for each song times the total play count per month. 30% of the 
   collected royalties are paid to the record label and the remainder is distributed evenly 
   among all participating artists. Make payment to podcast hosts. Podcast hosts are paid a 
   single flat fee per released episode and an additional bonus based on total advertisements 
   per podcast episode. Receive payment from subscribers.

4. Reports: Generate all the following reports: Monthly play count per song/album/artist. 
   Calculate total payments made out to host/artist/record labels per a given time period. 
   Total revenue of the streaming service per month, per year. Report all songs/podcast 
   episodes given an artist, album, and/or podcast.

5. Exit
*/
package wolfMedia;
import java.sql.*;

import java.util.Scanner;

public class WolfMediaTesting {
    public static void processInformation(int subChoice) throws SQLException {
        switch (subChoice) {
            case 1:
            SongInformationProcessing.processSong();
                break;
            // case 2:
            //     processArtist();
            //     break;
            // case 3:
            //     processPodcastHost();
            //     break;
            // case 4:
            //     processPodcastEpisode();
            //     break;
            // case 5:
            //     processAlbum();
            //     break;
            // case 6:
            //     processRecordLabel();
            //     break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("Please choose an option:");
            System.out.println("1. Enter/update/delete basic Information Processing");
            System.out.println("2. Maintain metadata and records");
            System.out.println("3. Maintain payments");
            System.out.println("4. Generate reports");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter/update/delete basic information:");
                    System.out.println("1. Song");
                    System.out.println("2. Artist");
                    System.out.println("3. Podcast host");
                    System.out.println("4. Podcast episode");
                    System.out.println("5. Album");
                    System.out.println("6. Record label");
                    System.out.println("7. Podcast");
                    System.out.print("Choice: ");
                    int subChoice1 = Integer.parseInt(input.nextLine());
                    // handle sub choice here
                    InformationProcessing.processInformation(subChoice1);
                    break;

                case 2:
                    System.out.println("Maintain metadata and records:");
                    System.out.println("1. Play count for songs");
                    System.out.println("2. Count of monthly listeners for artists");
                    System.out.println("3. Total count of subscribers and ratings for podcasts");
                    System.out.println("4. Listening count for podcast episodes");
                    System.out.println("5. Find songs and podcast episodes given artist, album, and/or podcast");
                    System.out.print("Choice: ");
                    int subChoice2 = Integer.parseInt(input.nextLine());
                    // handle sub choice here
                    MetadataProcessing.processMetadata(subChoice2);
                    break;

                case 3:
                    System.out.println("Maintain payments:");
                    System.out.println("1. Make royalty payments for a given song");
                    System.out.println("2. Make payment to podcast hosts");
                    System.out.println("3. Receive payment from subscribers");
                    System.out.print("Choice: ");
                    int subChoice3 = Integer.parseInt(input.nextLine());
                    // handle sub choice here
                    break;

                case 4:
                    System.out.println("Generate reports:");
                    System.out.println("1. Monthly play count per song/album/artist");
                    System.out
                            .println("2. Total payments made out to host/artist/record labels per a given time period");
                    System.out.println("3. Total revenue of the streaming service per month, per year");
                    System.out.println("4. Report all songs/podcast episodes given an artist, album, and/or podcast");
                    System.out.print("Choice: ");
                    int subChoice4 = input.nextInt();
                    // handle sub choice here
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
