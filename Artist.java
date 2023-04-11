public class Artist {
    private String artistID;
    private String name;
    private String status;
    private String type;

    public Artist(String artistID, String name, String status, String type) {
        this.artistID = artistID;
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
