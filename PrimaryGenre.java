public class PrimaryGenre {
    private String artistID;
    private String genreID;

    public PrimaryGenre(String artistID, String genreID) {
        this.artistID = artistID;
        this.genreID = genreID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }
}
