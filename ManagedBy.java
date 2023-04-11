public class ManagedBy {
    private String paymentID;
    private String ID;

    public ManagedBy(String paymentID, String ID) {
        this.paymentID = paymentID;
        this.ID = ID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
