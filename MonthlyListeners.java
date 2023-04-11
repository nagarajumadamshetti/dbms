import java.util.Date;

public class MonthlyListeners {
    private String artistID;
    private Date date;
    private int count;
    
    public MonthlyListeners(String artistID, Date date, int count) {
        this.artistID = artistID;
        this.date = date;
        this.count = count;
    }
    
    public String getArtistID() {
        return artistID;
    }
    
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
