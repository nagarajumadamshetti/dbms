import java.sql.Date;

public class SongsViewed {
    private String songID;
    private Date date;
    private int count;
    
    public SongsViewed(String songID, Date date, int count) {
        this.songID = songID;
        this.date = date;
        this.count = count;
    }
    
    public String getSongID() {
        return songID;
    }
    
    public void setSongID(String songID) {
        this.songID = songID;
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
