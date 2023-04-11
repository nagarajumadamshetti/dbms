public class Guest {
    private String guestID;
    private String name;

    public Guest(String guestID, String name) {
        this.guestID = guestID;
        this.name = name;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
