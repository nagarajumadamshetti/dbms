public class MediaStreamingService {
    private String ID;
    private String name;
    private String email;

    public MediaStreamingService(String ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
