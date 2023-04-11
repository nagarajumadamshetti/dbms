public class SungIn {
    private String songID;
    private String languageID;
    
    public SungIn(String songID, String languageID) {
        this.songID = songID;
        this.languageID = languageID;
    }
    
    public String getSongID() {
        return songID;
    }
    
    public void setSongID(String songID) {
        this.songID = songID;
    }
    
    public String getLanguageID() {
        return languageID;
    }
    
    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }
}
