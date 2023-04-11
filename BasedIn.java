public class BasedIn {
    private String artistID;
    private String countryID;

    public BasedIn(String artistID, String countryID) {
        this.artistID = artistID;
        this.countryID = countryID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}
