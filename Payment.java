import java.util.Date;

public class Payment {
    private String paymentID;
    private Date date;

    public Payment(String paymentID, Date date) {
        this.paymentID = paymentID;
        this.date = date;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
