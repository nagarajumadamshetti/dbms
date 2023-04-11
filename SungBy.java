public class SungBy {
    private String artistID;
    private String songID;
    
    public SungBy(String artistID, String songID) {
        this.artistID = artistID;
        this.songID = songID;
    }
    
    public String getArtistID() {
        return artistID;
    }
    
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
    
    public String getSongID() {
        return songID;
    }
    
    public void setSongID(String songID) {
        this.songID = songID;
    }
}
