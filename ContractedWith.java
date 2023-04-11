public class ContractedWith {
    private String artistID;
    private String recordLabelID;

    public ContractedWith(String artistID, String recordLabelID) {
        this.artistID = artistID;
        this.recordLabelID = recordLabelID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getRecordLabelID() {
        return recordLabelID;
    }

    public void setRecordLabelID(String recordLabelID) {
        this.recordLabelID = recordLabelID;
    }
}
