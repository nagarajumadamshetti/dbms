import java.sql.Time;
import java.util.Date;

public class Songs {
    private String songID;
    private String title;
    private Time duration;
    private Date releaseDate;
    private float royaltyPaid;
    private float royaltyRate;
    
    public Songs(String songID, String title, Time duration, Date releaseDate, float royaltyPaid, float royaltyRate) {
        this.songID = songID;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.royaltyPaid = royaltyPaid;
        this.royaltyRate = royaltyRate;
    }
    
    public String getSongID() {
        return songID;
    }
    
    public void setSongID(String songID) {
        this.songID = songID;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Time getDuration() {
        return duration;
    }
    
    public void setDuration(Time duration) {
        this.duration = duration;
    }
    
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public float getRoyaltyPaid() {
        return royaltyPaid;
    }
    
    public void setRoyaltyPaid(float royaltyPaid) {
        this.royaltyPaid = royaltyPaid;
    }
    
    public float getRoyaltyRate() {
        return royaltyRate;
    }
    
    public void setRoyaltyRate(float royaltyRate) {
        this.royaltyRate = royaltyRate;
    }
}
