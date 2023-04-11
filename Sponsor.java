public class Sponsor {
    private String sponsorID;
    private String sponsorName;

    public Sponsor(String sponsorID, String sponsorName) {
        this.sponsorID = sponsorID;
        this.sponsorName = sponsorName;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
