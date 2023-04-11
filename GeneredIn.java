public class GeneredIn {
    private String songID;
    private String genreID;

    public GeneredIn(String songID, String genreID) {
        this.songID = songID;
        this.genreID = genreID;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }
}
