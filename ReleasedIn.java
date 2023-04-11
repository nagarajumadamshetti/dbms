public class ReleasedIn {
    private String songID;
    private String countryID;
    
    public ReleasedIn(String songID, String countryID) {
        this.songID = songID;
        this.countryID = countryID;
    }
    
    public String getSongID() {
        return songID;
    }
    
    public void setSongID(String songID) {
        this.songID = songID;
    }
    
    public String getCountryID() {
        return countryID;
    }
    
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}
