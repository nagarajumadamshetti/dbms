public class Received {
    private String paymentID;
    private String artistID;

    public Received(String paymentID, String artistID) {
        this.paymentID = paymentID;
        this.artistID = artistID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
}
